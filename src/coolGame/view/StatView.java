package coolGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import coolGame.model.character.Enemy;
import coolGame.model.character.Player;

public class StatView extends JFrame {

    private Player player;
    private Enemy enemy;

    private int fWidth = 450;
    private int fHeight = 220;
    private int border = 10;
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

    public StatView(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;

        createView();
        createNorth();
        createWest();
        createCenter();
        createEast();

        this.setVisible(true);
    }
    
    public StatView(Player player, Enemy enemy, boolean initialized) {
    	
        this.player = player;
        this.enemy = enemy;
        this.initialized = initialized;

        createView();
        createNorth();
        createWest();
        createCenter();
        createEast();

        this.setVisible(true);
    }

    private void createView() {

        this.setSize(fWidth, fHeight);
        this.setTitle("Stats");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // displays which stats you are viewing
    private void createNorth() {
        northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));
        northPanel.add(Box.createRigidArea(new Dimension(border, border)));

        if (initialized) {
            statsLabel = new JLabel("Character Stats");
            statsLabel.setHorizontalAlignment(JLabel.CENTER);
        }
        else
        {
            statsLabel = new JLabel("Enemy Stats", SwingConstants.CENTER);
            statsLabel.setHorizontalAlignment(JLabel.CENTER);
        }
        northPanel.setBackground(Color.LIGHT_GRAY);
        northPanel.add(statsLabel);
        this.add(northPanel, BorderLayout.NORTH);
    }

    // displays basic stats and character image
    private void createWest() {
        westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
        westPanel.add(Box.createRigidArea(new Dimension(border, border)));
        westPanel.setBackground(Color.LIGHT_GRAY);

        if (initialized) {
            pNameLabel = new JLabel(player.getName() + " Stats");
            //playerPic = new JLabel(new ImageIcon("Player.PNG"));
            pHpLabel = new JLabel("Health: " + player.getMyStats().getHp() + "/" + player.getMyStats().getMaxHP());
            pManaLabel = new JLabel("Mana: " + player.getMyStats().getMana() + "/" + player.getMyStats().getMaxMana());
            pArmorLabel = new JLabel("Armor: " + player.getMyStats().getArm());

            westPanel.add(pNameLabel);
            //westPanel.add(playerPic);
            westPanel.add(pHpLabel);
            westPanel.add(pManaLabel);
            westPanel.add(pArmorLabel);

        }
        else {
            eNameLabel = new JLabel(enemy.getName() + " Stats");
            //enemyPic = new JLabel(new ImageIcon(("Enemy.png")));
            eHpLabel = new JLabel("Health: " + enemy.getMyStats().getHp());
            eArmorLabel = new JLabel("Armor: " + enemy.getMyStats().getArm());
            ePowerLabel = new JLabel("Power: " + enemy.getPower());

            westPanel.add(eNameLabel);
            //westPanel.add(enemyPic);
            westPanel.add(eHpLabel);
            westPanel.add(eArmorLabel);
            westPanel.add(ePowerLabel);
        }

        this.add(westPanel, BorderLayout.WEST);
    }

    // displays Attack Damage
    private void createCenter() {
        centPanel = new JPanel();
        centPanel.setLayout(new BoxLayout(centPanel, BoxLayout.PAGE_AXIS));
        centPanel.add(Box.createRigidArea(new Dimension(border, border)));
        centPanel.setBackground(Color.RED);
        dmgLabel = new JLabel("Attack Damage");
        centPanel.add(dmgLabel);

        if (initialized) {
            pDmgLabel = new JLabel("Physical: " + player.getMyStats().getDmg());
            pCDmgLabel = new JLabel("Cold: " + player.getMyStats().getCold());
            pPDmgLabel = new JLabel("Poison: " + player.getMyStats().getPoison());
            pFDmgLabel = new JLabel("Fire: " + player.getMyStats().getFire());
            pLDmgLabel = new JLabel("Lightning: " + player.getMyStats().getLightning());
            centPanel.add(pDmgLabel);
            centPanel.add(pCDmgLabel);
            centPanel.add(pPDmgLabel);
            centPanel.add(pFDmgLabel);
            centPanel.add(pLDmgLabel);
        }
        else {
            eDmgLabel = new JLabel("Physical: " + enemy.getMyStats().getDmg());
            eCDmgLabel = new JLabel("Cold: " + enemy.getMyStats().getCold());
            ePDmgLabel = new JLabel("Poison: " + enemy.getMyStats().getPoison());
            eFDmgLabel = new JLabel("Fire: " + enemy.getMyStats().getFire());
            eLDmgLabel = new JLabel("Lightning: " + enemy.getMyStats().getLightning());
            centPanel.add(eDmgLabel);
            centPanel.add(eCDmgLabel);
            centPanel.add(ePDmgLabel);
            centPanel.add(eFDmgLabel);
            centPanel.add(eLDmgLabel);
        }
        this.add(centPanel, BorderLayout.CENTER);
    }

    // displays Resistances
    private void createEast() {

        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
        eastPanel.add(Box.createRigidArea(new Dimension(border, border)));
        eastPanel.setBackground(Color.BLUE);
        resLabel = new JLabel("Resistances");
        eastPanel.add(resLabel);

        if (initialized) {
            emptyLabel = new JLabel("");
            pCResLabel = new JLabel("Cold: " + player.getMyStats().getcRes());
            pPResLabel = new JLabel("Poison: " + player.getMyStats().getpRes());
            pFResLabel = new JLabel("Fire: " + player.getMyStats().getfRes());
            pLResLabel = new JLabel("Lightning: " + player.getMyStats().getlRes());
            eastPanel.add(emptyLabel);
            eastPanel.add(pCResLabel);
            eastPanel.add(pPResLabel);
            eastPanel.add(pFResLabel);
            eastPanel.add(pLResLabel);
        }
        else {
            emptyLabel = new JLabel("");
            eCResLabel = new JLabel("Cold: " + enemy.getMyStats().getcRes());
            ePResLabel = new JLabel("Poison: " + enemy.getMyStats().getpRes());
            eFResLabel = new JLabel("Fire: " + enemy.getMyStats().getfRes());
            eLResLabel = new JLabel("Lightning: " + enemy.getMyStats().getlRes());
            eastPanel.add(emptyLabel);
            eastPanel.add(eCResLabel);
            eastPanel.add(ePResLabel);
            eastPanel.add(eFResLabel);
            eastPanel.add(eLResLabel);
        }
        this.add(eastPanel, BorderLayout.EAST);
    }

    public void resetStats() {
    	
    	if (initialized) {
    		
            pNameLabel.setText(player.getName() + " Stats");
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


    public Boolean getInitialized() {
        return initialized;
    }

    public void setInitialized(Boolean initialized) {
        this.initialized = initialized;
    }
    
    public void setEnemy(Enemy enemy) {
    	this.enemy = enemy;
    }

    public static void main(String[] args) {

        new StatView(new Player("Zero"), new Enemy(1));
    }

}

