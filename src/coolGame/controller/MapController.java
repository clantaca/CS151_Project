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
import coolGame.view.StatView;

public class MapController {

	private final int MOVE_NORTH = -5;
	private final int MOVE_SOUTH = 5;
	private final int MOVE_EAST = -1;
	private final int MOVE_WEST = 1;
	
	private MapView mapView;
	private StatView statViewPlayer;
	private Player player;
	private int enemyCounter = 0;
	
	private BlockingQueue<Message> queue;
	private List<Valve> valves = new LinkedList<Valve>();
	
	public MapController(MapView mapView, Player player, BlockingQueue<Message> queue) {
		
		this.mapView = mapView;
		this.player = player;
		this.queue = queue;

		//TODO: change name to fit convention
		valves.add(new ViewStatsMessageValve());
		valves.add(new NorthMessageValve());
		valves.add(new SouthMessageValve());
		valves.add(new EastMessageValve());
		valves.add(new WestMessageValve());
		
	}
	
	private interface Valve {
		public ValveResponse execute(Message message);
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
			if (message.getClass() != northMessage.class) {
				return ValveResponse.MISS;
			}

			playerMoves(MOVE_NORTH);
			playSound("resources/ButtonClick.wav");
			System.out.println("north");
			return ValveResponse.EXECUTED;
		}
	}
	
	private class SouthMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != southMessage.class) {
				return ValveResponse.MISS;
			}

			playerMoves(MOVE_SOUTH);
			playSound("resources/ButtonClick.wav");
			System.out.println("south");
			return ValveResponse.EXECUTED;
		}
	}
	
	private class EastMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != eastMessage.class) {
				return ValveResponse.MISS;
			}

			playerMoves(MOVE_EAST);
			playSound("resources/ButtonClick.wav");
			System.out.println("east");
			return ValveResponse.EXECUTED;
		}
	}
	
	private class WestMessageValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != westMessage.class) {
				return ValveResponse.MISS;
			}

			playerMoves(MOVE_WEST);
			playSound("resources/ButtonClick.wav");
			System.out.println("west");
			return ValveResponse.EXECUTED;
		}
	}
	
	//------------------------------------ Map View Listners and Methods -------------------------------------------------
	
	private void playerMoves(int locDelta) {
		
		//TODO: make it so you can't go out of bounds of map
		int oldLoc = mapView.getPlayerCurrLocation();
		int newLoc = mapView.getPlayerCurrLocation()+locDelta;
		
		//if there's nothing we can bump into, we swap places with that empty space
		if (mapView.getSpecificChracter(newLoc) == null) {
			
			mapView.setSpecificCharacter(newLoc, mapView.getPlayer());
			mapView.setPlayerCurrLocation(newLoc);
			mapView.setSpecificCharacter(oldLoc, null);
			mapView.redrawMapAfterMvmt();
		}
		
		//if we hit the exit (exit is part of player class)
		else if (mapView.getSpecificChracter(newLoc) instanceof Player ) {
			
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

			int option = JOptionPane.showConfirmDialog(null, "Do you want to fight this " + mapView.getSpecificChracter(newLoc).getName() +"?", "Enemy encountered!", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				new CombatController(new CombatView(queue), (Enemy)mapView.getSpecificChracter(newLoc), player, queue);
				mapView.setSpecificCharacter(newLoc, null);
				mapView.redrawMapAfterMvmt();
				enemyCounter++;

			}
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
