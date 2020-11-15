import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CombatView extends JFrame{

	private final int 	FRAME_WIDTH = 600;
	private final int 	FRAME_HEIGHT = 400;
	private final int	PANEL_BORDER = 10;
	private final int	COMPONENT_PADDING = 5;
	private final int	UNITS_FOR_IMAGE_HEIGHT = 150;

	//All components used in this view
	private JPanel				fullPanel;

	private JLabel				playerImageArea;
	private JLabel 				playerNameLabel;
	private JLabel 				playerHpLabel;
	private JLabel 				playerManaLabel;
	private JButton 			playerStatsBut;
	private JButton				playerInvBut;

	private JButton 			phyAtkBut;
	private JButton 			coldSpBut;
	private JButton 			fireSpBut;
	private JButton 			lightningSpBut;
	private JButton 			poisonSpBut;
	private JButton 			blockBut;


	private JLabel				enemyImageArea;
	private JLabel 				enemyNameLabel;
	private JLabel 				enemyHpLabel;
	private JLabel 				enemyPowerLabel;
	private JButton 			enemyStatsBut;

	private GridBagConstraints 	constraints;

	private Player 				player;
	private Enemy				enemy;

	public CombatView(Player player, Enemy enemy) {

		this.player = player;
		this.enemy = enemy;

		fullPanel = new JPanel(new GridBagLayout());
		fullPanel.setBackground(Color.CYAN);
		
		createFrame();
		createWestPanel();
		createCentPanel();
		createEastPanel();
		
		this.add(fullPanel);

		pack();
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


		constraints = new GridBagConstraints();
		constraints.insets = new Insets(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING);

		playerNameLabel = new JLabel(player.getName());
		playerImageArea = new JLabel("Player image goes here");		//Delete this text later
		playerHpLabel = new JLabel("Health: " + player.getMyStats().getHp());
		playerManaLabel = new JLabel("Mana: " + player.getMyStats().getMana());
		playerStatsBut = new JButton("Check Stats");
		playerInvBut = new JButton("Open Inventory");

		constraints.gridx = 0;
		constraints.gridy = 0;
		fullPanel.add(playerNameLabel, constraints);
		
		constraints.ipady = UNITS_FOR_IMAGE_HEIGHT; //makes component taller
		constraints.gridy++;
		fullPanel.add(playerImageArea, constraints);

		constraints.ipady = 0; //reset back
		constraints.gridy++;
		fullPanel.add(playerHpLabel, constraints);

		constraints.gridy++;
		fullPanel.add(playerManaLabel, constraints);

		constraints.gridy++;
		fullPanel.add(playerStatsBut, constraints);

		constraints.gridy++;
		fullPanel.add(playerInvBut, constraints);
	}


	//Creates center panel and adds it
	private void createCentPanel() {

		constraints = new GridBagConstraints();
		constraints.insets = new Insets(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING);

		phyAtkBut = new JButton("Physical Attack");
		coldSpBut = new JButton("Cold Spell Attack");
		fireSpBut = new JButton("Fire Spell Attack");
		lightningSpBut = new JButton("Lightning Spell Attack");
		poisonSpBut = new JButton("Poison Spell Attack");
		blockBut = new JButton("Block");

		constraints.gridx = 1;
		constraints.gridy = 0;
		fullPanel.add(phyAtkBut, constraints);

		constraints.gridy++;
		fullPanel.add(coldSpBut, constraints);

		constraints.gridy++;
		fullPanel.add(fireSpBut, constraints);

		constraints.gridy++;
		fullPanel.add(lightningSpBut, constraints);

		constraints.gridy++;
		fullPanel.add(poisonSpBut, constraints);

		constraints.gridy++;
		fullPanel.add(blockBut, constraints);
	}


	//Creates right panel and adds it
	private void createEastPanel() {

		constraints = new GridBagConstraints();
		constraints.insets = new Insets(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING);

		enemyNameLabel = new JLabel(enemy.getName());
		enemyImageArea = new JLabel("Enemy image goes here");		//Delete this text later
		enemyHpLabel = new JLabel("Health: " + enemy.getMyStats().getHp());
		enemyPowerLabel = new JLabel("Power: " + enemy.getPower());
		enemyStatsBut = new JButton("Check Stats");

		constraints.gridx = 2;
		constraints.gridy = 0;
		fullPanel.add(enemyNameLabel, constraints);
		
		constraints.ipady = UNITS_FOR_IMAGE_HEIGHT;
		constraints.gridy++;
		fullPanel.add(enemyImageArea, constraints);
		
		constraints.ipady = 0;
		constraints.gridy++;
		fullPanel.add(enemyHpLabel, constraints);
		
		constraints.gridy++;
		fullPanel.add(enemyPowerLabel, constraints);
		
		constraints.gridy++;
		fullPanel.add(enemyStatsBut, constraints);

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
		fullPanel.setBackground(Color.RED);
		displayMessage("You are dead");
	}

	public void displayMessage (String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

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

	//----------------------------------------------------------------------------------------

	public static void main(String[] args) {

		new CombatView(new Player("Bob"), new Enemy());

	}

}
