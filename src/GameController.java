import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
	
	private CombatView combatView;
	private StatView statViewPlayer;
	private StatView statViewEnemy;
	private Enemy enemy;
	private Player player;
	
	public GameController(CombatView combatView, Enemy enemy, Player player) {
		
		this.combatView = combatView;
		this.enemy = enemy;
		this.player = player;
		
		this.combatView.addPlayerStatsButListener(new PlayerStatsButListener());
		this.combatView.addPlayerInvButListener(new PlayerInvButListener());
		this.combatView.addPhyAtkButListener(new PhyAtkListener());
		this.combatView.addColdSpButListener(new ColdSpButListener());
		this.combatView.addFireSpButListener(new FireSpButListener());
		this.combatView.addLightningSpButListener(new LightningSpButListener());
		this.combatView.addPoisonSpButListener(new PoisonSpButListener());
		this.combatView.addBlockButListener(new BlockButListener());
		this.combatView.addEnemyStatsButListener(new EnemyStatsButListener());
		
	}
	
	private void combatEnsues() {
		
		//Create new enemy if it's dead
		if (enemy.getMyStats().getHp() <= 0) {
			enemy = new Enemy();
		}
		
		//Else player gets hit
		else {
			enemy.attackUser(player);
		}
		
		//Update combatView
		combatView.setEnemy(enemy);
		
		if (statViewPlayer != null)
			statViewPlayer.resetStats();
		if (statViewEnemy != null) {
			statViewEnemy.setEnemy(enemy);
			statViewEnemy.resetStats();
		}
			
		combatView.resetVariables();
		
		if (player.getMyStats().getHp() <= 0) {
			combatView.setPlayerDie();
		}

	}
	
	class PlayerStatsButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			statViewPlayer = new StatView(player, enemy, true);
			
		}
		
	}
	
	class EnemyStatsButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			statViewEnemy = new StatView(player, enemy, false);
			
		}
		
	}
	
	//UNFINISHED
	class PlayerInvButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			//Need implementation, waiting to see how item class turns out
			
		}
		
	}
	
	class PhyAtkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			player.physicalAttack(enemy);
			combatEnsues();
			
		}
		
	}
	
	class ColdSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			player.coldAttack(enemy);
			combatEnsues();
			
		}
		
	}
	
	class FireSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			player.fireAttack(enemy);
			combatEnsues();
			
		}
		
	}
	
	class LightningSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			player.lightningAttack(enemy);
			combatEnsues();
			
		}
		
	}
	
	class PoisonSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			player.poisonAttack(enemy);
			combatEnsues();
			
		}
		
	}
	
	class BlockButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			player.blockEnemy();
			
		}
		
	}
	
}
	