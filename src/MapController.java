import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MapController {

	private final int MOVE_NORTH = -5;
	private final int MOVE_SOUTH = 5;
	private final int MOVE_EAST = -1;
	private final int MOVE_WEST = 1;
	
	private MapView mapView;
	private StatView statViewPlayer;
	private Player player;
	private int enemyCounter = 0;
	
	public MapController(MapView mapView, Player player) {
		
		this.mapView = mapView;
		this.player = player;
		
		this.mapView.addViewStatsButtonListener(new ViewStatsButtonListener());
		this.mapView.addNorthButtonListener(new NorthButtonListner());
		this.mapView.addSouthButtonListener(new SouthButtonListner());
		this.mapView.addEastButtonListener(new EastButtonListner());
		this.mapView.addWestButtonListener(new WestButtonListner());
		
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
				new CombatController(new CombatView(player, (Enemy) mapView.getSpecificChracter(newLoc)), (Enemy) mapView.getSpecificChracter(newLoc), player);
				mapView.setSpecificCharacter(newLoc, null);
				mapView.redrawMapAfterMvmt();
				enemyCounter++;

			}
		}
		
		
	}
	
	class ViewStatsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			statViewPlayer = new StatView(player, new Enemy(1), true);
			playSound("resources/ButtonClick.wav");
			
		}
		
	}
	
	class NorthButtonListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			playerMoves(MOVE_NORTH);
			playSound("resources/ButtonClick.wav");
			
		}
		
	}
	
	class SouthButtonListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			playerMoves(MOVE_SOUTH);
			playSound("resources/ButtonClick.wav");
			
		}
		
	}
	
	class EastButtonListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			playerMoves(MOVE_EAST);
			playSound("resources/ButtonClick.wav");
			
		}
		
	}
	
	class WestButtonListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			playerMoves(MOVE_WEST);
			playSound("resources/ButtonClick.wav");
			
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
