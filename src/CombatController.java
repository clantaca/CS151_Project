import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CombatController {
	

	private CombatView combatView;
	private StatView statViewPlayer;
	private StatView statViewEnemy;
	private Player player;
	private Enemy enemy;

	
	public CombatController(CombatView combatView, Enemy enemy, Player player) {

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
	
	//------------------------------------ Combat View Listners and Methods -------------------------------------------------
	private void combatEnsues() {
		
		//Create new enemy if it's dead
		if (enemy.getMyStats().getHp() <= 0) {
			player.updateInventory(player);
			
			if (statViewPlayer != null) {
				statViewPlayer.setVisible(false);
				statViewPlayer.dispose();
			}
			
			if (statViewEnemy != null) {
				statViewEnemy.setVisible(false);
				statViewEnemy.dispose();
			}
			
			combatView.setVisible(false);
			combatView.dispose();
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
			player.startOfTurn(player);
			statViewPlayer = new StatView(player, enemy, true);
			playSound("ButtonClick.wav");
			
		}
		
	}
	
	class EnemyStatsButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			statViewEnemy = new StatView(player, enemy, false);
			playSound("ButtonClick.wav");
			
		}
		
	}
	
	//UNFINISHED
	class PlayerInvButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			//Need implementation, waiting to see how item class turns out
			playSound("ButtonClick.wav");
			
		}
		
	}
	
	class PhyAtkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.physicalAttack(enemy);
			playSound("ButtonClick.wav");
			combatEnsues();
			
		}
		
	}
	
	class ColdSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.coldAttack(enemy);
			playSound("ButtonClick.wav");
			combatEnsues();
			
		}
		
	}
	
	class FireSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.fireAttack(enemy);
			playSound("ButtonClick.wav");
			combatEnsues();
			
		}
		
	}
	
	class LightningSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.lightningAttack(enemy);
			playSound("ButtonClick.wav");
			combatEnsues();
			
		}
		
	}
	
	class PoisonSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.poisonAttack(enemy);
			playSound("ButtonClick.wav");
			combatEnsues();
			
		}
		
	}
	
	class BlockButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.blockEnemy();
			playSound("ButtonClick.wav");
			
		}
		
	}
	//method to play sounds from a file
	public void playSound (String filePath)
	{
		try
		{
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile( ));
			Clip clip = AudioSystem.getClip( );
			clip.open(audioInput);
			clip.start( );
		}
		catch(Exception ex)
		{
			ex.printStackTrace( );
		}
	}
	
}
	