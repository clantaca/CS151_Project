package coolGame.view;

import coolGame.model.character.Enemy;
import coolGame.model.character.Player;

import javax.swing.*;
import java.awt.*;

/**
 * View that displays all the stats of the player or enemy that would include their hp, damages, resistances, etc.
 */

public class StatView extends JFrame {

    private Player player;
    private Enemy enemy;

    private int fWidth = 450;
    private int fHeight = 220;
    private GridBagConstraints 	constraints;
    private final String background_Image = "resources/background1.jpg"; // change background here
    private JLabel pic;
    private Boolean initialized = true; // changes from player to enemy stats

    private JPanel northPanel;
    private JLabel statsLabel;
    private JLabel emptyLabel;

    private JPanel westPanel;
    private JLabel playerPic;
    private JLabel pNameLabel;
    private JLabel pHpLabel;
    private JLabel pManaLabel;
    private JLabel pArmorLabel;
    private JLabel enemyPic;
    private JLabel eNameLabel;
    private JLabel eHpLabel;
    private JLabel eArmorLabel;
    private JLabel ePowerLabel;

    private JPanel centPanel;
    private JLabel dmgLabel;
    private JLabel pDmgLabel;
    private JLabel pCDmgLabel;
    private JLabel pPDmgLabel;
    private JLabel pFDmgLabel;
    private JLabel pLDmgLabel;
    private JLabel eDmgLabel;
    private JLabel eCDmgLabel;
    private JLabel ePDmgLabel;
    private JLabel eFDmgLabel;
    private JLabel eLDmgLabel;

    private JPanel eastPanel;
    private JLabel resLabel;
    private JLabel pCResLabel;
    private JLabel pPResLabel;
    private JLabel pFResLabel;
    private JLabel pLResLabel;
    private JLabel eCResLabel;
    private JLabel ePResLabel;
    private JLabel eFResLabel;
    private JLabel eLResLabel;
    private JLabel fullPanel;

    /**
     * Constructor that would initialize the player and enemy, creating a background image, and all the necessary
     * labels that would display their stats
     * @param player
     * @param enemy
     */
    public StatView(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;

        ImageIcon backgroundImage = new ImageIcon(background_Image);
        fullPanel = new JLabel(backgroundImage);
        fullPanel.setLayout(new GridBagLayout());

        createView();
        createNorth();
        createWest();
        createCenter();
        createEast();

        this.add(fullPanel);
        pack();

        this.setVisible(true);
    }

    /**
     * Constructor that includes initialized in the parameter that would indicate what stats the player wants to view.
     * If it is initialized, then the stats of the player would be displayed.
     *
     * @param player
     * @param enemy
     * @param initialized - indicates whether you are viewing player or enemy stats
     */
    public StatView(Player player, Enemy enemy, boolean initialized) {

        this.player = player;
        this.enemy = enemy;
        this.initialized = initialized;

        ImageIcon backgroundImage = new ImageIcon(background_Image);
        fullPanel = new JLabel(backgroundImage);
        fullPanel.setLayout(new GridBagLayout());

        createView();
        createNorth();
        createWest();
        createCenter();
        createEast();

        this.add(fullPanel);
        pack();
        this.setVisible(true);
    }

    /**
     * Creates the initial frame of StatView by setting the size and title
     */
    private void createView() {

        this.setSize(fWidth, fHeight);
        this.setTitle("Stats");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Creates the north portion of the frame that would display the gif and name of the enemy/player
     */
    // displays which stats you are viewing
    private void createNorth() {
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,20,20,5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.insets.bottom = 0;
        constraints.weightx = 0;
        constraints.weighty = 0;

        if (initialized) {
            statsLabel = new JLabel(player.getName() + " Stats");
            statsLabel.setHorizontalAlignment(JLabel.CENTER);
            fullPanel.add(statsLabel, constraints);
            constraints.gridy=5;
            pic = new JLabel(new ImageIcon("resources/player.gif"));
            fullPanel.add(pic, constraints);

        }
        else
        {
            statsLabel = new JLabel(enemy.getName() + " Stats");
            statsLabel.setHorizontalAlignment(JLabel.CENTER);
            fullPanel.add(statsLabel, constraints);
            constraints.gridy=5;
            pic = new JLabel(new ImageIcon(enemy.getEnemyImage()));
            fullPanel.add(pic, constraints);
        }
    }

    /**
     * Creates the west portion of the frame that displays the health, mana, and armor for the player and the health,
     * armor, and power for the enemy
     */
    // displays basic stats and character image
    private void createWest() {
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,20,20,5);
        constraints.gridx = 1;
        constraints.gridy = 2;

        if (initialized) {
            pHpLabel = new JLabel("Health: " + player.getMyStats().getHp() + "/" + player.getMyStats().getMaxHP());
            pManaLabel = new JLabel("Mana: " + player.getMyStats().getMana() + "/" + player.getMyStats().getMaxMana());
            pArmorLabel = new JLabel("Armor: " + player.getMyStats().getArm());

            constraints.gridy = 4;
            constraints.gridy++;
            fullPanel.add(pHpLabel, constraints);
            constraints.gridy++;
            fullPanel.add(pManaLabel, constraints);
            constraints.gridy++;
            fullPanel.add(pArmorLabel, constraints);

        }
        else {
            eHpLabel = new JLabel("Health: " + enemy.getMyStats().getHp());
            eArmorLabel = new JLabel("Armor: " + enemy.getMyStats().getArm());
            ePowerLabel = new JLabel("Power: " + enemy.getPower());

            constraints.gridy = 4;
            constraints.gridy++;
            fullPanel.add(eHpLabel, constraints);
            constraints.gridy++;
            fullPanel.add(eArmorLabel, constraints);
            constraints.gridy++;
            fullPanel.add(ePowerLabel, constraints);
        }
    }

    /**
     * Creates the center portion of the frame that displays all the damages for the enemy/player
     */
    // displays Attack Damage
    private void createCenter() {
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,20,20,5);
        constraints.gridx = 2;
        constraints.gridy = 4;
        dmgLabel = new JLabel("Attack Damage");
        fullPanel.add(dmgLabel, constraints);


        if (initialized) {
            pDmgLabel = new JLabel("Physical: " + player.getMyStats().getDmg());
            pCDmgLabel = new JLabel("Cold: " + player.getMyStats().getCold());
            pPDmgLabel = new JLabel("Poison: " + player.getMyStats().getPoison());
            pFDmgLabel = new JLabel("Fire: " + player.getMyStats().getFire());
            pLDmgLabel = new JLabel("Lightning: " + player.getMyStats().getLightning());
            constraints.gridy++;
            fullPanel.add(pDmgLabel, constraints);
            constraints.gridy++;
            fullPanel.add(pCDmgLabel, constraints);
            constraints.gridy++;
            fullPanel.add(pPDmgLabel, constraints);
            constraints.gridy++;
            fullPanel.add(pFDmgLabel, constraints);
            constraints.gridy++;
            fullPanel.add(pLDmgLabel, constraints);
            constraints.gridy++;
        }
        else {
            eDmgLabel = new JLabel("Physical: " + enemy.getMyStats().getDmg());
            eCDmgLabel = new JLabel("Cold: " + enemy.getMyStats().getCold());
            ePDmgLabel = new JLabel("Poison: " + enemy.getMyStats().getPoison());
            eFDmgLabel = new JLabel("Fire: " + enemy.getMyStats().getFire());
            eLDmgLabel = new JLabel("Lightning: " + enemy.getMyStats().getLightning());
            constraints.gridy++;
            fullPanel.add(eDmgLabel, constraints);
            constraints.gridy++;
            fullPanel.add(eCDmgLabel, constraints);
            constraints.gridy++;
            fullPanel.add(ePDmgLabel, constraints);
            constraints.gridy++;
            fullPanel.add(eFDmgLabel, constraints);
            constraints.gridy++;
            fullPanel.add(eLDmgLabel, constraints);
        }
    }

    /**
     * Creates the east portion of the frame that displays all the resistances of the enemy/player
     */
    // displays Resistances
    private void createEast() {

        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,20,20,5);
        constraints.gridx = 3;
        constraints.gridy = 4;
        resLabel = new JLabel("Resistances");
        fullPanel.add(resLabel, constraints);

        if (initialized) {
            pCResLabel = new JLabel("Cold: " + player.getMyStats().getcRes());
            pPResLabel = new JLabel("Poison: " + player.getMyStats().getpRes());
            pFResLabel = new JLabel("Fire: " + player.getMyStats().getfRes());
            pLResLabel = new JLabel("Lightning: " + player.getMyStats().getlRes());
            constraints.gridy=6;
            fullPanel.add(pCResLabel, constraints);
            constraints.gridy++;
            fullPanel.add(pPResLabel, constraints);
            constraints.gridy++;
            fullPanel.add(pFResLabel, constraints);
            constraints.gridy++;
            fullPanel.add(pLResLabel, constraints);
        }
        else {
            //emptyLabel = new JLabel("");
            eCResLabel = new JLabel("Cold: " + enemy.getMyStats().getcRes());
            ePResLabel = new JLabel("Poison: " + enemy.getMyStats().getpRes());
            eFResLabel = new JLabel("Fire: " + enemy.getMyStats().getfRes());
            eLResLabel = new JLabel("Lightning: " + enemy.getMyStats().getlRes());
            //fullPanel.add(emptyLabel, constraints);
            constraints.gridy=6;
            fullPanel.add(eCResLabel, constraints);
            constraints.gridy++;
            fullPanel.add(ePResLabel, constraints);
            constraints.gridy++;
            fullPanel.add(eFResLabel, constraints);
            constraints.gridy++;
            fullPanel.add(eLResLabel, constraints);
        }
    }

    /**
     * Resets/updates all the stats of the player/enemy during combat
     */
    public void resetStats() {

        if (initialized) {

            pNameLabel = new JLabel(player.getName() + " Stats");
            pHpLabel.setText("Health: " + player.getMyStats().getHp());
            pManaLabel.setText("Mana: " + player.getMyStats().getMana());
            pArmorLabel.setText("Armor: " + player.getMyStats().getArm());
            pDmgLabel.setText("Physical: " + player.getMyStats().getDmg());
            pCDmgLabel.setText("Cold: " + player.getMyStats().getCold());
            pPDmgLabel.setText("Poison: " + player.getMyStats().getPoison());
            pFDmgLabel.setText("Fire: " + player.getMyStats().getFire());
            pLDmgLabel.setText("Lightning: " + player.getMyStats().getLightning());
            pCResLabel.setText("Cold: " + player.getMyStats().getcRes());
            pPResLabel.setText("Poison: " + player.getMyStats().getpRes());
            pFResLabel.setText("Fire: " + player.getMyStats().getfRes());
            pLResLabel.setText("Lightning: " + player.getMyStats().getlRes());

        }

        else {

            eNameLabel = new JLabel(enemy.getName() + " Stats");
            eHpLabel.setText("Health: " + enemy.getMyStats().getHp());
            eArmorLabel.setText("Armor: " + enemy.getMyStats().getArm());
            ePowerLabel.setText("Power: " + enemy.getPower());
            eDmgLabel.setText("Physical: " + enemy.getMyStats().getDmg());
            eCDmgLabel.setText("Cold: " + enemy.getMyStats().getCold());
            ePDmgLabel.setText("Poison: " + enemy.getMyStats().getPoison());
            eFDmgLabel.setText("Fire: " + enemy.getMyStats().getFire());
            eLDmgLabel.setText("Lightning: " + enemy.getMyStats().getLightning());
            eCResLabel.setText("Cold: " + enemy.getMyStats().getcRes());
            ePResLabel.setText("Poison: " + enemy.getMyStats().getpRes());
            eFResLabel.setText("Fire: " + enemy.getMyStats().getfRes());
            eLResLabel.setText("Lightning: " + enemy.getMyStats().getlRes());

        }

    }

    /**
     * Sets the variable enemy as enemy in parameter
     * @param enemy
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

}


