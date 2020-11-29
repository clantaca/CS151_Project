package coolGame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import coolGame.model.character.Enemy;
import coolGame.model.character.Player;
import coolGame.view.CombatView;
import coolGame.view.StatView;

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
			
			player.resetCounters();
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
			playSound("resources/Death.wav");
			combatView.setPlayerDie();
		}

	}
	
	class PlayerStatsButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			statViewPlayer = new StatView(player, enemy, true);
			playSound("resources/ButtonClick.wav");
			
		}
		
	}
	
	class EnemyStatsButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			statViewEnemy = new StatView(player, enemy, false);
			playSound("resources/ButtonClick.wav");
			
		}
		
	}
	
	//UNFINISHED
	class PlayerInvButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			//Need implementation, waiting to see how item class turns out
			player.displayInventory();
			playSound("resources/ButtonClick.wav");
			
		}
		
	}
	
	class PhyAtkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.physicalAttack(enemy);
			playSound("resources/PhysAttack.wav");
			combatEnsues();
			
		}
		
	}
	
	class ColdSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.coldAttack(enemy);
			playSound("resources/ColdAttack.wav");
			combatEnsues();
			
		}
		
	}
	
	class FireSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.fireAttack(enemy);
			playSound("resources/FireAttack.wav");
			combatEnsues();
			
		}
		
	}
	
	class LightningSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.lightningAttack(enemy);
			playSound("resources/LightningAttack.wav");
			combatEnsues();
			
		}
		
	}
	
	class PoisonSpButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.poisonAttack(enemy);
			playSound("resources/PoisonAttack.wav");
			combatEnsues();
			
		}
		
	}
	
	class BlockButListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			player.startOfTurn(player);
			player.blockEnemy();
			playSound("resources/Block.wav");
			combatEnsues();
			
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
	