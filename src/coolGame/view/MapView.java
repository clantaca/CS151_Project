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

public class MapView extends JFrame{

	private final int 	FRAME_WIDTH = 1400;
	private final int 	FRAME_HEIGHT = 1000;
	private final int	COMPONENT_PADDING_HEIGHT = 35;
	private final int	COMPONENT_PADDING_LENGTH = 70;
	private final int	ROWS_PER_SIDE = 5; 		//Map will be a 5x5 square
	private final int	TOTAL_ENEMIES_ALLOWED = ROWS_PER_SIDE * ROWS_PER_SIDE;
	private final int 	PLAYER_STARTING_LOCATION = 22;
	private final int 	EXIT_STARTING_LOCATION = 2;
	private final int	BOSS_ID = 100;
	private final String FILE_PATH = "resources/BGM.wav";
	
	private JLabel	mapPanel;
	private GridBagConstraints 	constraints;
	
	private JPanel helpPanel;
	private JLabel showEnemyPowerLabel;
	private JButton viewStatsButton;
	
	private JPanel mvmtPanel;
	private JButton northButton;
	private JButton southButton;
	private JButton eastButton;
	private JButton westButton;
	
	private int playerCurrLocation = PLAYER_STARTING_LOCATION;
	private static int currEnemyPower = 1;	//mapView holds the current power level of all enemies - this increments after each combat
	
	private Player player;
	private Map map;
	private String currBGFile = "resources/mapbackground.jpg";
	
	private BlockingQueue<Message> queue;
	
	public static MapView init(Player player, Map map, BlockingQueue<Message> queue) {
		// Create object of type view
		return new MapView(player, map, queue);
	}
	
	public MapView(Player player, Map map, BlockingQueue<Message> queue) {
		
		this.player = player;
		this.map = map;
		this.queue = queue;

		constraints = new GridBagConstraints();
		constraints.insets = new Insets(COMPONENT_PADDING_HEIGHT, COMPONENT_PADDING_LENGTH, COMPONENT_PADDING_HEIGHT, COMPONENT_PADDING_LENGTH);
		constraints.ipadx = 20;
		constraints.ipady = 20;

		createFrame();
		createMapPanel();
		createHelpPanel();
		createMovementPanel();
		playMusic(FILE_PATH);

	}
	
	public void resetAfterCombat() {
		currEnemyPower++;
		if (currEnemyPower == 2)
			currBGFile = "resources/mapbackground1.jpg";
		if (currEnemyPower == 3)
			currBGFile = "resources/mapbackground2.jpg";
		
		
		map = new Map(player, currEnemyPower);
		
		playerCurrLocation = PLAYER_STARTING_LOCATION;
		player.getMyStats().setHp(player.getMyStats().getMaxHP());
		remove(mapPanel);
		createMapPanel();
		createHelpPanel();
		createMovementPanel();
	}
	
	public void redrawMapAfterMvmt() {
		remove(mapPanel);
		createMapPanel();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayerCurrLocation(int newLoc) {
		this.playerCurrLocation = newLoc;
	}
	
	public int getPlayerCurrLocation() {
		return playerCurrLocation;
	}
	
	public void setSpecificCharacter(int i, Character newCharacter){
		map.setSpecificCharacter(i, newCharacter);
	}
	
	public Character getSpecificCharacter(int value) {
		return map.getSpecificCharacter(value);
	}
	
	//--------------------------------------------------------------------------------------------
	
	private void createFrame() {

		this.setTitle("You are looking at the map! Yay!");
		this.setVisible(true);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private void createMapPanel() {
			ImageIcon backgroundImage = new ImageIcon(currBGFile);
			mapPanel = new JLabel(backgroundImage);
			mapPanel.setLayout(new GridBagLayout());
			mapPanel.setBackground(Color.DARK_GRAY);

			constraints.gridx = 0;
			constraints.gridy = 0;

			for (int i = 0; i < TOTAL_ENEMIES_ALLOWED; i++) {

				JLabel tempLabel;

				try {
					tempLabel = new JLabel("*" + map.getSpecificCharacter(i).getName() + "*");
					tempLabel.setOpaque(true);

					if (i == EXIT_STARTING_LOCATION)
						if (i == EXIT_STARTING_LOCATION && (new Enemy(currEnemyPower).getEnemyTypeID() != BOSS_ID) )
							tempLabel.setText("Stairway Up");
				}

				catch (NullPointerException e) {
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
	
	private void createHelpPanel() {
		
		helpPanel = new JPanel(new GridBagLayout());
		helpPanel.setBackground(Color.GRAY);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		showEnemyPowerLabel = new JLabel("Current enemy power: " + currEnemyPower);
		helpPanel.add(showEnemyPowerLabel, constraints);
		
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
	
	private void createMovementPanel() {
		
		mvmtPanel = new JPanel(new GridBagLayout());
		mvmtPanel.setBackground(Color.GRAY);
		
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
	
	//--------------------------------------------------------------------------------------------
	
	//Creates background music
	public void playMusic(String filePath) {
		try {
			File musicPath = new File (filePath);
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
	
	public static void main(String[] args) {
		
		//new MapView(new Player("Jon"));
		
	}
	
}
