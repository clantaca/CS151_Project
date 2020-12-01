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

public class Controller {
	private final int MOVE_NORTH = -5;
	private final int MOVE_SOUTH = 5;
	private final int MOVE_EAST = -1;
	private final int MOVE_WEST = 1;
	private final int POWER_NEEDED_TO_SUMMON_BOSS = 3;
	
	private MapView mapView;
	private CombatView combatView;
	private NotificationView notificationView;
	private StatView statViewPlayer;
	private StatView statViewEnemy;
	
	private Player player;
	private Enemy enemy;
	private int enemyCounter = 0;
	
	private BlockingQueue<Message> queue;
	private List<Valve> valves = new LinkedList<Valve>();
	
	public Controller(MapView mapView, Player player, BlockingQueue<Message> queue) {
		
		this.mapView = mapView;
		this.player = player;
		this.queue = queue;
		notificationView = new NotificationView();

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
	
	public void mainLoop() {
		ValveResponse response = ValveResponse.EXECUTED;
		Message message = null;
		while (response != ValveResponse.FINISH) {
			try {
				message = queue.take(); // <--- take next message from the queue
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Look for a Valve that can process a message
			for (Valve valve : valves) {
				response = valve.execute(message);
				// if successfully processed or game over, leave the loop
				if (response != ValveResponse.MISS) {
					break;
				}
			}
		}
	}
	
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

			// otherwise it means that it is a NewGameMessage message
			// actions in Model
			// actions in View
			return ValveResponse.EXECUTED;
		}
	}


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

			// otherwise it means that it is a NewGameMessage message
			// actions in Model
			// actions in View
			return ValveResponse.EXECUTED;
		}
	}

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

			// otherwise it means that it is a NewGameMessage message
			// actions in Model
			// actions in View
			return ValveResponse.EXECUTED;
		}
	}

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

			// otherwise it means that it is a NewGameMessage message
			// actions in Model
			// actions in View
			return ValveResponse.EXECUTED;
		}
	}

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

			// otherwise it means that it is a NewGameMessage message
			// actions in Model
			// actions in View
			return ValveResponse.EXECUTED;
		}
	}

	private class BlockMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != BlockMessage.class) {
				return ValveResponse.MISS;
			}
			player.startOfTurn(player);
			player.blockEnemy();
			playSound("resources/Block.wav");
			combatEnsues();

			// otherwise it means that it is a NewGameMessage message
			// actions in Model
			// actions in View
			return ValveResponse.EXECUTED;
		}
	}

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
	
	//------------------------------------ Map View Listners and Methods -------------------------------------------------
	
	private void playerMoves(int locDelta) {
		
		//TODO: make it so you can't go out of bounds of map
		int oldLoc = mapView.getPlayerCurrLocation();
		int newLoc = mapView.getPlayerCurrLocation()+locDelta;
		
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
				enemyCounter++;

			}
		}
		
		
	}
	
	private void combatEnsues() {
		
		//Create new enemy if it's dead
		if (enemy.getMyStats().getHp() <= 0) {
			player.updateInventory(player);
			enemy.setPowerCharge(0);
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
		
		if (statViewPlayer != null)
			statViewPlayer.resetStats();
		if (statViewEnemy != null) {
			statViewEnemy.setEnemy(enemy);
			statViewEnemy.resetStats();
		}
			
		combatView.resetVariables();
		
		if (player.getMyStats().getHp() <= 0) {
			playSound("resources/Death.wav");
			try {
				this.queue.put(new PlayerDiesMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	private void disposeEverything() {
		System.exit(0);
	}
	
	public void displayMessage (String msg) {
		JOptionPane.showMessageDialog(mapView, msg);
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
