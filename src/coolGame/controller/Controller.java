package coolGame.controller;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import coolGame.model.character.Enemy;
import coolGame.model.character.Player;
import coolGame.view.CombatView;
import coolGame.view.MapView;
import coolGame.view.NotificationView;
import coolGame.view.StatView;

/**
 * Controller will control what to do with any Message added to the ongoing blockingQueue
 */

public class Controller {

	//Values needed to move on the map
	private final int 					MOVE_NORTH = -5;
	private final int 					MOVE_SOUTH = 5;
	private final int 					MOVE_EAST = -1;
	private final int 					MOVE_WEST = 1;
	private final int 					POWER_NEEDED_TO_SUMMON_BOSS = 3;	//What level the boss is summoned on
	private final int 					TOTAL_ENEMIES_ALLOWED = 25;

	//Views used in this program
	private MapView 					mapView;
	private CombatView 					combatView;
	private StatView 					statViewPlayer;
	private StatView 				statViewEnemy;

	private Player 						player;
	private Enemy						enemy;

	private BlockingQueue<Message> 		queue;
	private List<Valve> 				valves = new LinkedList<Valve>();

	/**
	 * Constructor will initialize variables and add all necessary valves to list
	 * 
	 * @param mapView
	 * @param player
	 * @param queue
	 */
	public Controller(MapView mapView, Player player, BlockingQueue<Message> queue) {

		this.mapView = mapView;
		this.player = player;
		this.queue = queue;
		new NotificationView(); //New instance of notificationView that never closes untill end of program

		valves.add(new ViewStatsMessageValve());
		valves.add(new NorthMessageValve());
		valves.add(new SouthMessageValve());
		valves.add(new EastMessageValve());
		valves.add(new WestMessageValve());
		valves.add(new PlayerDiesMessageValve());
		valves.add(new BossDiesMessageValve());

		valves.add(new PhyAtkMessageValve());
		valves.add(new ColdAtkMessageValve());
		valves.add(new FireAtkMessageValve());
		valves.add(new LightningAtkMessageValve());
		valves.add(new PoisonAtkMessageValve());
		valves.add(new BlockMessageValve());
		valves.add(new PlayerStatsMessageValve());
		valves.add(new EnemyStatsMessageValve());
		valves.add(new PlayerInvMessageValve());

	}

	/**
	 * Valves are responsible for executing a Message
	 */
	private interface Valve {

		public ValveResponse execute(Message message);
	}

	/**
	 * Main loop of the program that will take any message into the queue and execute them
	 * It will change the response accordingly 
	 */
	public void mainLoop() {
		ValveResponse response = ValveResponse.EXECUTED;
		Message message = null;
		while (response != ValveResponse.FINISH) {
			try {
				message = queue.take(); //Take next message when avilable
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Go through all of the valves until the correct one is found
			for (Valve valve : valves) {
				response = valve.execute(message);
				//Break when done
				if (response != ValveResponse.MISS) {
					break;
				}
			}
		}
	}

	/**
	 * When called, the view stats for player StatView opens
	 */
	private class ViewStatsMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != viewStatsMessage.class) {
				return ValveResponse.MISS;
			}

			statViewPlayer = new StatView(player, new Enemy(1), true);
			playSound("resources/ButtonClick.wav");
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player will move north on map
	 */
	private class NorthMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != NorthMessage.class) {
				return ValveResponse.MISS;
			}

			playerMoves(MOVE_NORTH);
			playSound("resources/ButtonClick.wav");
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player will move south on map
	 */
	private class SouthMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != SouthMessage.class) {
				return ValveResponse.MISS;
			}

			playerMoves(MOVE_SOUTH);
			playSound("resources/ButtonClick.wav");
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player will move east on map
	 */
	private class EastMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != EastMessage.class) {
				return ValveResponse.MISS;
			}

			playerMoves(MOVE_EAST);
			playSound("resources/ButtonClick.wav");
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player will move west on map
	 */
	private class WestMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != WestMessage.class) {
				return ValveResponse.MISS;
			}

			playerMoves(MOVE_WEST);
			playSound("resources/ButtonClick.wav");
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, game ends with player death message
	 */
	private class PlayerDiesMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != PlayerDiesMessage.class) {
				return ValveResponse.MISS;
			}

			displayMessage("You are dead");
			disposeEverything();
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, game ends with boss death message
	 */
	private class BossDiesMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != BossDiesMessage.class) {
				return ValveResponse.MISS;
			}
			displayMessage("You have slain the boss!");
			disposeEverything();
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player deals physical damage to enemy
	 */
	private class PhyAtkMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != PhyAtkMessage.class) {
				return ValveResponse.MISS;
			}
			player.startOfTurn(player);
			player.physicalAttack(enemy);
			playSound("resources/PhysAttack.wav");
			combatEnsues();
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player deals cold damage to enemy
	 */
	private class ColdAtkMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != ColdAtkMessage.class) {
				return ValveResponse.MISS;
			}
			player.startOfTurn(player);
			player.coldAttack(enemy);
			playSound("resources/ColdAttack.wav");
			combatEnsues();
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player deals fire damage to enemy
	 */
	private class FireAtkMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != FireAtkMessage.class) {
				return ValveResponse.MISS;
			}
			player.startOfTurn(player);
			player.fireAttack(enemy);
			playSound("resources/FireAttack.wav");
			combatEnsues();
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player deals lightning damage to enemy
	 */
	private class LightningAtkMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != LightningAtkMessage.class) {
				return ValveResponse.MISS;
			}
			player.startOfTurn(player);
			player.lightningAttack(enemy);
			playSound("resources/LightningAttack.wav");
			combatEnsues();
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player deals poison damage to enemy
	 */
	private class PoisonAtkMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != PoisonAtkMessage.class) {
				return ValveResponse.MISS;
			}
			player.startOfTurn(player);
			player.poisonAttack(enemy);
			playSound("resources/PoisonAttack.wav");
			combatEnsues();
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player decides to block
	 */
	private class BlockMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != BlockMessage.class) {
				return ValveResponse.MISS;
			}
			player.startOfTurn(player);
			player.blockEnemy(enemy);
			playSound("resources/Block.wav");
			combatEnsues();
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player statsView is opened 
	 */
	private class PlayerStatsMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != PlayerStatsMessage.class) {
				return ValveResponse.MISS;
			}
			player.startOfTurn(player);
			statViewPlayer = new StatView(player, enemy, true);
			playSound("resources/ButtonClick.wav");
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, enemy statsView is opened 
	 */
	private class EnemyStatsMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != EnemyStatsMessage.class) {
				return ValveResponse.MISS;
			}
			player.startOfTurn(player);
			statViewEnemy = new StatView(player, enemy, false);
			playSound("resources/ButtonClick.wav");
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 *When called, player inventory is displayed 
	 */
	private class PlayerInvMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != PlayerInvMessage.class) {
				return ValveResponse.MISS;
			}
			player.displayInventory();
			playSound("resources/ButtonClick.wav");
			return ValveResponse.EXECUTED;
		}
	}

	//Helper methods---------------------------------------------------------------

	/**
	 * Player moves to new location on map
	 * @param locDelta- a numerical value on where player should move on map
	 */
	private void playerMoves(int locDelta) {

		//Calculate loations
		int oldLoc = mapView.getPlayerCurrLocation();
		int newLoc = mapView.getPlayerCurrLocation()+locDelta;

		//Do nothing if new location is invalid
		if (newLoc < 0 || newLoc > TOTAL_ENEMIES_ALLOWED)
			return;

		//if there's nothing we can bump into, we swap places with that empty space
		if (mapView.getSpecificCharacter(newLoc) == null) {

			mapView.setSpecificCharacter(newLoc, mapView.getPlayer());
			mapView.setPlayerCurrLocation(newLoc);
			mapView.setSpecificCharacter(oldLoc, null);
			mapView.redrawMapAfterMvmt();

		}

		//if we hit the exit (exit is part of player class)
		else if (mapView.getSpecificCharacter(newLoc) instanceof Player ) {

			if(statViewPlayer != null) {
				statViewPlayer.setVisible(false);
				statViewPlayer.dispose();
			}

			int option = JOptionPane.showConfirmDialog(null, "You have reached the exit", "Would you like to go up to the next level of this tower?", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				mapView.resetAfterCombat();
			}

		}

		//else it's an enemy
		else {

			if(statViewPlayer != null) {
				statViewPlayer.setVisible(false);
				statViewPlayer.dispose();
			}

			int option = JOptionPane.showConfirmDialog(null, "Do you want to fight this " + mapView.getSpecificCharacter(newLoc).getName() +"?", "Enemy encountered!", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				combatView = new CombatView(player, (Enemy)mapView.getSpecificCharacter(newLoc), queue);
				enemy = (Enemy)mapView.getSpecificCharacter(newLoc);
				mapView.setSpecificCharacter(newLoc, null);
				mapView.redrawMapAfterMvmt();

			}
		}

	}

	/**
	 * After a combat button is pressed, we change all values to get ready for next round
	 */
	private void combatEnsues() {

		//Create new enemy if it's dead
		if (enemy.getMyStats().getHp() <= 0) {
			player.updateInventory(player);
			enemy.setPowerCharge(0);
			player.getMyStats().setDmg(player.getOriginalDmg());
			player.getMyStats().setHp(player.getMyStats().getHp()+20);

			if (player.getMyStats().getHp() > player.getMyStats().getMaxHP())
				player.getMyStats().setHp(player.getMyStats().getMaxHP());

			//Close all appropriate views if they are open
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

			player.resetCounters();

			//Check if the next layer will be the boss layer
			if (enemy.getPower() == POWER_NEEDED_TO_SUMMON_BOSS) {
				try {
					this.queue.put(new BossDiesMessage());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		//Else player gets hit
		else {
			enemy.attackUser(player);
		}

		//Update combatView
		combatView.setEnemy(enemy);

		//Reset views for next round of combat with same enemy
		if (statViewPlayer != null)
			statViewPlayer.resetStats();
		if (statViewEnemy != null) {
			statViewEnemy.setEnemy(enemy);
			statViewEnemy.resetStats();
		}

		combatView.resetVariables();

		//Check if the player dies from enemy attack- end game if so
		if (player.getMyStats().getHp() <= 0) {
			playSound("resources/Death.wav");
			try {
				this.queue.put(new PlayerDiesMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Ends the program
	 */
	private void disposeEverything() {
		System.exit(0);
	}

	/**
	 * Display input string as a pop-up message
	 * @param msg
	 */
	public void displayMessage (String msg) {
		JOptionPane.showMessageDialog(mapView, msg);
	}

	/**
	 * Plays sound from file
	 * @param filePath
	 */
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
