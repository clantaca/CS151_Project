package coolGame.model;

import java.util.ArrayList;
import java.util.HashSet;

import coolGame.model.character.Character;
import coolGame.model.character.Enemy;
import coolGame.model.character.Player;

public class Map {

	private final int	ROWS_PER_SIDE = 5; 		//Map will be a 5x5 square
	private final int	TOTAL_ENEMIES_ALLOWED = ROWS_PER_SIDE * ROWS_PER_SIDE;
	private final int 	PLAYER_STARTING_LOCATION = 22;
	private final int 	EXIT_STARTING_LOCATION = 2;
	private final int 	NUM_ENEMIES_ON_MAP = 3;
	private final int	POWER_NEEDED_TO_SUMMON_BOSS = 3; //find in enemy class
	
	private ArrayList<Character> allChractersOnMap;
	private static int currEnemyPower = 1;	//mapView holds the current power level of all enemies - this increments after each combat
	
	Player player;

	public Map(Player player, int currEnemyPower) {

		this.player = player;
		this.currEnemyPower = currEnemyPower; 
		
		if (currEnemyPower % POWER_NEEDED_TO_SUMMON_BOSS == 0)
			this.allChractersOnMap = loadChractersBossLevel();
		else
			this.allChractersOnMap = loadChracters();
		
	}
	
	

	//Fills an arrayList of length 25 with null enemies except for three unique spots where it is filled with enemies of a given power level.
	private ArrayList<Character> loadChracters() {

		ArrayList<Character> finalArrList = new ArrayList<>();

		//Generates unique numbers to be used for character locations 
		HashSet<Integer> occupiedLocations = new HashSet<>();
		occupiedLocations.add(PLAYER_STARTING_LOCATION);
		occupiedLocations.add(EXIT_STARTING_LOCATION);

		//Add 3 enemies to the map (it is num of enemies + 1 since player and exit take up a spot each)
		while(occupiedLocations.size() < NUM_ENEMIES_ON_MAP+2) {

			int randomInt = generateRandomInt(TOTAL_ENEMIES_ALLOWED);
			occupiedLocations.add(randomInt);

		}


		for (int i = 0; i < 25; i++) {

			if (i == PLAYER_STARTING_LOCATION || i == EXIT_STARTING_LOCATION)
				finalArrList.add(player);
			else if (occupiedLocations.contains(i))
				finalArrList.add(new Enemy(currEnemyPower));
			else
				finalArrList.add(null);

		}

		return finalArrList;

	}

	//Use this array list when we on final level
	private ArrayList<Character> loadChractersBossLevel() {

		ArrayList<Character> finalArrList = new ArrayList<>();

		for (int i = 0; i < 25; i++) {

			if (i == PLAYER_STARTING_LOCATION)
				finalArrList.add(player);
			else if (i == EXIT_STARTING_LOCATION)
				finalArrList.add(new Enemy(currEnemyPower));
			else
				finalArrList.add(null);

		}

		return finalArrList;

	}
	
	//will generate a random number given upper limit
	//ex: an upper limit of "2" will return 0, 1, or 2
	private int generateRandomInt(int upperLimit) {
		return (int) (Math.random() * upperLimit);
	}

	public ArrayList<Character> getAllChractersOnMap() {
		return allChractersOnMap;
	}

	public void setAllChractersOnMap(ArrayList<Character> allChractersOnMap) {
		this.allChractersOnMap = allChractersOnMap;
	}
	
	public void setSpecificCharacter(int i, Character newCharacter){
		allChractersOnMap.set(i, newCharacter);
	}
	
	public Character getSpecificCharacter(int value) {
		return allChractersOnMap.get(value);
	}

}
