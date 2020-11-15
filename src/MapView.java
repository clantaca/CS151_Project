import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;

public class MapView extends JFrame{

	
	
	
	private final int 	FRAME_WIDTH = 800;
	private final int 	FRAME_HEIGHT = 800;
	private final int	COMPONENT_PADDING = 5;
	
	private JPanel	fullPanel;
	private GridBagConstraints 	constraints;
	
	private int numEnemiesAtBase;
	private ArrayList<Enemy> allEnemies;
	
	private Player player;
	
	
	
	
	public MapView(Player player, int numEnemiesAtBase) {
		
		this.player = player;
		this.numEnemiesAtBase = numEnemiesAtBase;
		this.allEnemies = loadRandomEnemies(numEnemiesAtBase);
		
		fullPanel = new JPanel(new GridBagLayout());
		fullPanel.setBackground(Color.YELLOW);

		constraints = new GridBagConstraints();
		constraints.insets = new Insets(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING);

		//printArrayListOfEnemies(allEnemies);
		
		createFrame();
		createNewLayer(numEnemiesAtBase, 0);
		
		this.add(fullPanel);

	}
	
	
	
	
	private ArrayList<Enemy> loadRandomEnemies(int numEnemiesAtBase) {
		
		ArrayList<Enemy> finalArrList = new ArrayList<>();
		
		for (int i = 0; i < numEnemiesAtBase; i++) {
			for (int j = i; j < numEnemiesAtBase; j++) {
				
				finalArrList.add(new Enemy());
				
			}
		}
		
		return finalArrList;
		
	}
	
	private void printArrayListOfEnemies(ArrayList<Enemy> input) {
		
		for (Enemy x: input) {
			System.out.println(x.getName());
		}
		
	}
	
	
	
	
	private void createFrame() {

		this.setTitle("You are in combat!");
		this.setVisible(true);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private int createNewLayer(int numEnemiesOnThisLayer, int currEnemy) {
		
		
		if (numEnemiesOnThisLayer == 0)
			return -1;
		
		//fill this layer with buttons
		for (int i = 1; i <= numEnemiesOnThisLayer; i++) {
			
			constraints.gridx = i;
			constraints.gridy = numEnemiesOnThisLayer; //note: number of enemies also corresponds to the layer number
			JButton newButton = new JButton(allEnemies.get(currEnemy++).getName());
			
			int finalI = i-1;
			
			newButton.addActionListener(e -> {
				
				new CombatView(player, allEnemies.get(finalI));	//figure this out later
			
			});
			
			fullPanel.add(newButton, constraints);
			
		}
		
		return createNewLayer(numEnemiesOnThisLayer-1, currEnemy);
		
	}
	
	public static void main(String[] args) {
		
		new MapView(new Player("Jon"), 10);
		
	}
	
}
