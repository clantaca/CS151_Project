import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CombatView extends JFrame{

	private final int 	FRAME_WIDTH = 450;
	private final int 	FRAME_HEIGHT = 220;
	private final int	PANEL_BORDER = 10;
	
	//All components used in this view
	private JPanel 		westPanel;
	private JLabel 		playerNameLabel;
	private JLabel 		playerHpLabel;
	private JLabel 		playerManaLabel;
	private JButton 	playerStatsBut;
	private JButton		playerInvBut;
	
	private JPanel 		centPanel;
	private JButton 	phyAtkBut;
	private JButton 	coldSpBut;
	private JButton 	fireSpBut;
	private JButton 	lightningSpBut;
	private JButton 	poisonSpBut;
	private JButton 	blockBut;
	
	private JPanel 		eastPanel;
	private JLabel 		enemyNameLabel;
	private JLabel 		enemyHpLabel;
	private JLabel 		enemyPowerLabel;
	private JButton 	enemyStatsBut;
	
	private Player 		player;
	private Enemy		enemy;
	
	public CombatView(Player player, Enemy enemy) {
		
		this.player = player;
		this.enemy = enemy;
		
		createFrame();
		createWestPanel();
		createCentPanel();
		createEastPanel();
		//this.pack();
		
	}

	//----------------------------------------------------------------------------------------
	
	//Initialize all of the various components
	
	//Creates the initial frame
	private void createFrame() {
		
		this.setTitle("You are in combat!");
		this.setVisible(true);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

	//Creates left panel and adds it
	private void createWestPanel() {
		
		westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
		westPanel.add(Box.createRigidArea(new Dimension(PANEL_BORDER, PANEL_BORDER)));
		westPanel.setBackground(Color.GREEN);
		
		playerNameLabel = new JLabel(player.getName());
		playerHpLabel = new JLabel("Health: " + player.getMyStats().getHp());
		playerManaLabel = new JLabel("Mana: " + player.getMyStats().getMana());
		playerStatsBut = new JButton("Check Stats");
		playerInvBut = new JButton("Open Inventory");
		
		westPanel.add(playerNameLabel);
		westPanel.add(playerHpLabel);
		westPanel.add(playerManaLabel);
		westPanel.add(playerStatsBut);
		westPanel.add(playerInvBut);
		
		this.add(westPanel, BorderLayout.WEST);
		
	}

	
	
	//Creates center panel and adds it
	private void createCentPanel() {
		
		centPanel = new JPanel();
		centPanel.setLayout(new BoxLayout(centPanel, BoxLayout.PAGE_AXIS));
		centPanel.add(Box.createRigidArea(new Dimension(PANEL_BORDER, PANEL_BORDER)));
		centPanel.setBackground(Color.YELLOW);
		
		phyAtkBut = new JButton("Physical Attack");
		coldSpBut = new JButton("Cold Spell Attack");
		fireSpBut = new JButton("Fire Spell Attack");
		lightningSpBut = new JButton("Lightning Spell Attack");
		poisonSpBut = new JButton("Poison Spell Attack");
		blockBut = new JButton("Block");
		
		centPanel.add(phyAtkBut);
		centPanel.add(coldSpBut);
		centPanel.add(fireSpBut);
		centPanel.add(lightningSpBut);
		centPanel.add(poisonSpBut);
		centPanel.add(blockBut);
		
		this.add(centPanel, BorderLayout.CENTER);
		
	}
	
	
	//Creates right panel and adds it
	private void createEastPanel() {

		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
		eastPanel.add(Box.createRigidArea(new Dimension(PANEL_BORDER, PANEL_BORDER)));
		eastPanel.setBackground(Color.ORANGE);
		
		enemyNameLabel = new JLabel(enemy.getName());
		System.out.println(enemy.getName());
		enemyHpLabel = new JLabel("Health: " + enemy.getMyStats().getHp());
		enemyPowerLabel = new JLabel("Power: " + enemy.getPower());
		enemyStatsBut = new JButton("Check Stats");
		
		eastPanel.add(enemyNameLabel);
		eastPanel.add(enemyHpLabel);
		eastPanel.add(enemyPowerLabel);
		eastPanel.add(enemyStatsBut);
		
		this.add(eastPanel, BorderLayout.EAST);
		
	}

	//----------------------------------------------------------------------------------------
	
	//All action listeners for needed buttons so that controller can be notified 
	
	public void addPlayerStatsButListener (ActionListener listener) {
		playerStatsBut.addActionListener(listener);
	}
	
	public void addPlayerInvButListener (ActionListener listener) {
		playerInvBut.addActionListener(listener);
	}

	public void addPhyAtkButListener (ActionListener listener) {
		phyAtkBut.addActionListener(listener);
	}
	
	public void addColdSpButListener (ActionListener listener) {
		coldSpBut.addActionListener(listener);
	}
	
	public void addFireSpButListener (ActionListener listener) {
		fireSpBut.addActionListener(listener);
	}
	
	public void addLightningSpButListener (ActionListener listener) {
		lightningSpBut.addActionListener(listener);
	}
	
	public void addPoisonSpButListener (ActionListener listener) {
		poisonSpBut.addActionListener(listener);
	}
	
	public void addBlockButListener (ActionListener listener) {
		blockBut.addActionListener(listener);
	}
	
	public void addEnemyStatsButListener (ActionListener listener) {
		enemyStatsBut.addActionListener(listener);
	}
	
	//----------------------------------------------------------------------------------------
	
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	
	public void resetVariables() {
		
		playerNameLabel.setText(player.getName());
		playerHpLabel.setText("Health: " + player.getMyStats().getHp());
		playerManaLabel.setText("Mana: " + player.getMyStats().getMana());
		enemyNameLabel.setText(enemy.getName());
		enemyHpLabel.setText("Health: " + enemy.getMyStats().getHp());
		enemyPowerLabel.setText("Power: " + enemy.getPower());
		
	}
	
	public void setPlayerDie() {
		westPanel.setBackground(Color.RED);
		centPanel.setBackground(Color.RED);
		eastPanel.setBackground(Color.RED);
		displayMessage("You are dead");
	}
	
	public void displayMessage (String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	//----------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		new CombatView(new Player("Bob"), new Enemy());
		
	}

}
