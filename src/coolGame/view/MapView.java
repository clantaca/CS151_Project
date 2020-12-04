package coolGame.view;

import coolGame.controller.*;
import coolGame.model.Map;
import coolGame.model.character.Character;
import coolGame.model.character.Enemy;
import coolGame.model.character.Player;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * MapView is the main view used;
 * It will display movement made by the user
 */

public class MapView extends JFrame {

    //Final variables
    private final int FRAME_WIDTH = 1400;
    private final int FRAME_HEIGHT = 1200;
    private final int COMPONENT_PADDING_HEIGHT = 35;
    private final int COMPONENT_PADDING_LENGTH = 70;
    private final int ROWS_PER_SIDE = 5;
    private final int TOTAL_ENEMIES_ALLOWED = ROWS_PER_SIDE * ROWS_PER_SIDE;
    private final int PLAYER_STARTING_LOCATION = 22;
    private final int EXIT_STARTING_LOCATION = 2;
    private final int BOSS_ID = 100;
    private final String FILE_PATH = "resources/BGM.wav";

    //All of the swing components
    private GridBagConstraints constraints;

    private JPanel helpPanel;
    private JLabel showEnemyPowerLabel;
    private JButton viewStatsButton;

    private JLabel mapPanel;

    private JPanel mvmtPanel;
    private JButton northButton;
    private JButton southButton;
    private JButton eastButton;
    private JButton westButton;

    //Variables
    private int playerCurrLocation = PLAYER_STARTING_LOCATION;
    private static int currEnemyPower = 1;                                    //The current power level of all enemies - this increments after each combat
    private BlockingQueue<Message> queue;

    //Models
    private Player player;
    private Map map;
    private String currBGFile = "resources/mapbackground.jpg";

    /**
     * Initializes mapView with the appropriate models and queue
     *
     * @param player
     * @param map
     * @param queue
     * @return
     */
    public static MapView init(Player player, Map map, BlockingQueue<Message> queue) {
        return new MapView(player, map, queue);
    }

    /**
     * Constructor- will create all of the necessary components and add them
     *
     * @param player
     * @param map
     * @param queue
     */
    public MapView(Player player, Map map, BlockingQueue<Message> queue) {

        this.player = player;
        this.map = map;
        this.queue = queue;

        createFrame();
        createMapPanel();
        createHelpPanel();
        createMovementPanel();
        playMusic(FILE_PATH);

    }

    //Initialize and add components to mapView--------------------------------------------------------------------------------------------

    /**
     * Creates initial frame with constraints
     */
    private void createFrame() {

        //Initalizes constraints
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(COMPONENT_PADDING_HEIGHT, COMPONENT_PADDING_LENGTH, COMPONENT_PADDING_HEIGHT, COMPONENT_PADDING_LENGTH);
        constraints.ipadx = 20;
        constraints.ipady = 20;

        //Creates frame values
        this.setTitle("You are looking at the map! Yay!");
        this.setVisible(true);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Create helpPanel (top)
     */
    private void createHelpPanel() {

        helpPanel = new JPanel(new GridBagLayout());
        helpPanel.setBackground(Color.GRAY);

        //Power label
        constraints.gridx = 0;
        constraints.gridy = 0;
        showEnemyPowerLabel = new JLabel("Current enemy power: " + currEnemyPower);
        helpPanel.add(showEnemyPowerLabel, constraints);

        //View stats button
        constraints.gridy = 1;
        viewStatsButton = new JButton("Click here to view your stats");
        viewStatsButton.addActionListener(event -> {
            try {
                this.queue.put(new viewStatsMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        helpPanel.add(viewStatsButton, constraints);

        this.add(helpPanel, BorderLayout.NORTH);
        helpPanel.repaint();
        helpPanel.revalidate();

    }

    /**
     * Create mapPanel (center)
     */
    private void createMapPanel() {

        //We set mapPanel to a label so that we can add a background image
        ImageIcon backgroundImage = new ImageIcon(currBGFile);
        mapPanel = new JLabel(backgroundImage);
        mapPanel.setLayout(new GridBagLayout());
        mapPanel.setBackground(Color.DARK_GRAY);

        constraints.gridx = 0;
        constraints.gridy = 0;

        //Filled with JLabels that set text depending on Map.java
        for (int i = 0; i < TOTAL_ENEMIES_ALLOWED; i++) {

            JLabel tempLabel;

            try {
                tempLabel = new JLabel("   " + map.getSpecificCharacter(i).getName());
                tempLabel.setOpaque(true);

                if (i == EXIT_STARTING_LOCATION)
                    if (i == EXIT_STARTING_LOCATION && (new Enemy(currEnemyPower).getEnemyTypeID() != BOSS_ID))
                        tempLabel.setText("   Stairway Up");
            } catch (NullPointerException e) {
                tempLabel = new JLabel();
                tempLabel.setOpaque(true);
            }


            mapPanel.add(tempLabel, constraints);

            constraints.gridx++;

            //new row
            if (constraints.gridx % 5 == 0) {
                constraints.gridx = 0;
                constraints.gridy++;
            }

        }

        this.add(mapPanel, BorderLayout.CENTER);
        mapPanel.repaint();
        mapPanel.revalidate();
    }

    /**
     * Create movement panel (bottom)
     */
    private void createMovementPanel() {

        mvmtPanel = new JPanel(new GridBagLayout());
        mvmtPanel.setBackground(Color.GRAY);

        //North button
        constraints.gridx = 1;
        constraints.gridy = 0;
        northButton = new JButton("^");

        northButton.addActionListener(event -> {
            try {
                this.queue.put(new NorthMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        mvmtPanel.add(northButton, constraints);

        //South button
        constraints.gridy = 1;
        southButton = new JButton("\\/");
        southButton.addActionListener(event -> {
            try {
                this.queue.put(new SouthMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        mvmtPanel.add(southButton, constraints);

        //East button
        constraints.gridx = 0;
        eastButton = new JButton("<");

        eastButton.addActionListener(event -> {
            try {
                this.queue.put(new EastMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        mvmtPanel.add(eastButton, constraints);

        //West button
        constraints.gridx = 2;
        westButton = new JButton(">");

        westButton.addActionListener(event -> {
            try {
                this.queue.put(new WestMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        mvmtPanel.add(westButton, constraints);

        this.add(mvmtPanel, BorderLayout.SOUTH);

    }

    /**
     * Initialize mapView with background music
     *
     * @param filePath
     */
    public void playMusic(String filePath) {
        try {
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    //Methods that update how mapView looks--------------------------------------------------------------------------------------------

    /**
     * When combat is over, we will update mapView to look accordingly
     */
    public void resetAfterCombat() {

        //Changes background based of currEnemyPower
        currEnemyPower++;
        if (currEnemyPower == 2)
            currBGFile = "resources/mapbackground1.jpg";
        if (currEnemyPower == 3)
            currBGFile = "resources/mapbackground3.jpg";

        map = new Map(player, currEnemyPower);

        playerCurrLocation = PLAYER_STARTING_LOCATION;
        player.getMyStats().setHp(player.getMyStats().getMaxHP());
        remove(mapPanel);    //Delete old mapPanel and create new one in it's place
        createMapPanel();
        createHelpPanel();
        createMovementPanel();
    }

    /**
     * Redraws map based on single movement
     */
    public void redrawMapAfterMvmt() {
        remove(mapPanel);
        createMapPanel();
    }

    //Getters and setters--------------------------------------------------------------------------------------------

    public Player getPlayer() {
        return player;
    }

    public void setPlayerCurrLocation(int newLoc) {
        this.playerCurrLocation = newLoc;
    }

    public int getPlayerCurrLocation() {
        return playerCurrLocation;
    }

    public void setSpecificCharacter(int i, Character newCharacter) {
        map.setSpecificCharacter(i, newCharacter);
    }

    public Character getSpecificCharacter(int value) {
        return map.getSpecificCharacter(value);
    }

}
