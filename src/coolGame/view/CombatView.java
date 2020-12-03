package coolGame.view;

import coolGame.controller.*;
import coolGame.model.character.Enemy;
import coolGame.model.character.Player;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

/**
 * A view that allows player and enemy to interact with each other
 */
public class CombatView extends JFrame{

	private final int 	FRAME_WIDTH = 600;
	private final int 	FRAME_HEIGHT = 200;
	private final int	COMPONENT_PADDING = 5;
	private final String BACKGROUND_IMAGE = "resources/background.jpg";

	//All components used in this view
	private JLabel				fullPanel;

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

	private BlockingQueue<Message> queue;

	/**
	 * Initializes combatView with the appropriate models and queue
	 * @param player
	 * @param enemy
	 * @param queue
	 * @return
	 */
	public static CombatView init(Player player, Enemy enemy, BlockingQueue<Message> queue) {
		return new CombatView(player, enemy, queue);
	}

	/**
	 * Will create all of the necessary components and add them
	 * @param player
	 * @param enemy
	 * @param queue
	 */
	public CombatView(Player player, Enemy enemy, BlockingQueue<Message> queue) {

		this.player = player;
		this.enemy = enemy;
		this.queue = queue;

		createFrame();
		createWestPanel();
		createCentPanel();
		createEastPanel();
		this.add(fullPanel);
		pack();

	}

	//Initialize and add components to combatView--------------------------------------------------------------------------------------------

	/**
	 * Creates the initial frame
	 */
	private void createFrame() {

		ImageIcon backgroundImage = new ImageIcon(BACKGROUND_IMAGE);
		fullPanel = new JLabel(backgroundImage);
		fullPanel.setLayout(new GridBagLayout());
		fullPanel.setBackground(Color.CYAN);

		this.setTitle("You are in combat!");
		this.setVisible(true);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Creates left panel (for player)
	 */
	private void createWestPanel() {


		constraints = new GridBagConstraints();
		constraints.insets = new Insets(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING);

		playerNameLabel = new JLabel(player.getName());
		playerNameLabel.setForeground(Color.WHITE);
		playerImageArea = new JLabel(new ImageIcon("resources/player.gif"));		//Delete this text later
		playerHpLabel = new JLabel("Health: " + player.getMyStats().getHp() + "/" + player.getMyStats().getMaxHP());
		playerHpLabel.setForeground(Color.WHITE);
		playerManaLabel = new JLabel("Mana: " + player.getMyStats().getMana() + "/" + player.getMyStats().getMaxMana());
		playerManaLabel.setForeground(Color.WHITE);
		playerStatsBut = new JButton("Check Stats");
		playerInvBut = new JButton("Open Inventory");

		constraints.gridx = 0;
		constraints.gridy = 0;
		fullPanel.add(playerNameLabel, constraints);

		constraints.weighty = 1; //makes component taller
		constraints.gridy++;
		fullPanel.add(playerImageArea, constraints);

		constraints.weighty = 0;
		constraints.gridy++;
		fullPanel.add(playerHpLabel, constraints);

		constraints.gridy++;
		fullPanel.add(playerManaLabel, constraints);

		constraints.gridy++;
		fullPanel.add(playerStatsBut, constraints);

		constraints.gridy++;
		fullPanel.add(playerInvBut, constraints);

		playerStatsBut.addActionListener(event -> {
			try {
				this.queue.put(new PlayerStatsMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		playerInvBut.addActionListener(event -> {
			try {
				this.queue.put(new PlayerInvMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		constraints.gridx = 0;
		constraints.gridy = 4;
		fullPanel.add(playerStatsBut, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		fullPanel.add(playerInvBut, constraints);
	}


	/**
	 * Creates the center panel (for attack buttons)
	 */
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

		phyAtkBut.addActionListener(event -> {
			try {
				this.queue.put(new PhyAtkMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		coldSpBut.addActionListener(event -> {
			try {
				this.queue.put(new ColdAtkMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		fireSpBut.addActionListener(event -> {
			try {
				this.queue.put(new FireAtkMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		lightningSpBut.addActionListener(event -> {
			try {
				this.queue.put(new LightningAtkMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		poisonSpBut.addActionListener(event -> {
			try {
				this.queue.put(new PoisonAtkMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		blockBut.addActionListener(event -> {
			try {
				this.queue.put(new BlockMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

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


	/**
	 * Creates the right panel (for enemy)
	 */
	private void createEastPanel() {

		constraints = new GridBagConstraints();
		constraints.insets = new Insets(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING);

		enemyNameLabel = new JLabel(enemy.getName());
		enemyNameLabel.setForeground(Color.WHITE);
		enemyImageArea = new JLabel(new ImageIcon(enemy.getEnemyImage()));
		enemyHpLabel = new JLabel("Health: " + enemy.getMyStats().getHp() + "/" + enemy.getMyStats().getMaxHP());
		enemyHpLabel.setForeground(Color.WHITE);
		enemyPowerLabel = new JLabel("Power: " + enemy.getPowerCharge() + "/4");
		enemyPowerLabel.setForeground(Color.WHITE);
		enemyStatsBut = new JButton("Check Stats");

		constraints.gridx = 2;
		constraints.gridy = 0;
		fullPanel.add(enemyNameLabel, constraints);

		constraints.weighty = 1;
		constraints.gridy++;
		fullPanel.add(enemyImageArea, constraints);

		constraints.weighty = 0;
		constraints.gridy++;
		fullPanel.add(enemyHpLabel, constraints);

		constraints.gridy++;
		fullPanel.add(enemyPowerLabel, constraints);

		constraints.gridy++;
		fullPanel.add(enemyStatsBut, constraints);

		enemyStatsBut.addActionListener(event -> {
			try {
				this.queue.put(new EnemyStatsMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		constraints.gridx = 2;
		constraints.gridy = 4;
		fullPanel.add(enemyStatsBut, constraints);

	}


	/**
	 * Reset display on combatView
	 */
	public void resetVariables() {

		playerNameLabel.setText(player.getName());
		playerHpLabel.setText("Health: " + player.getMyStats().getHp() + "/" + player.getMyStats().getMaxHP());
		playerHpLabel.setForeground(Color.WHITE);
		playerManaLabel.setText("Mana: " + player.getMyStats().getMana() + "/" + player.getMyStats().getMaxMana());
		playerManaLabel.setForeground(Color.WHITE);
		enemyNameLabel.setText(enemy.getName());
		enemyNameLabel.setForeground(Color.WHITE);
		enemyHpLabel.setText("Health: " + enemy.getMyStats().getHp() + "/" + enemy.getMyStats().getMaxHP());
		enemyHpLabel.setForeground(Color.WHITE);
		enemyPowerLabel.setText("Power: " + enemy.getPowerCharge() + "/4");
		enemyPowerLabel.setForeground(Color.WHITE);

	}

	//Getters and setters------------------------------------------------------

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
}
