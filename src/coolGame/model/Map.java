package coolGame.model;

import coolGame.model.character.Character;
import coolGame.model.character.Enemy;
import coolGame.model.character.Player;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Model that's responsible for returning a random ArrayList of all Characters on a map
 */
public class Map {
	
	//Final variables
	private final int				ROWS_PER_SIDE = 5; 						
	private final int				TOTAL_ENEMIES_ALLOWED = ROWS_PER_SIDE * ROWS_PER_SIDE;
	private final int 				PLAYER_STARTING_LOCATION = 22;
	private final int 				EXIT_STARTING_LOCATION = 2;
	private final int 				NUM_ENEMIES_ON_MAP = 3;
	private final int				POWER_NEEDED_TO_SUMMON_BOSS = 3; 							//Must be in sync with variable in enemy class
	
	private ArrayList<Character> 	allChractersOnMap;
	private int 					currEnemyPower;												//All of the enemies created by map will be this level
	
	Player 							player;

	/**
	 * Constructor- will create a map appropriate with the current enemy power on that layer
	 * @param player
	 * @param currEnemyPower
	 */
	public Map(Player player, int currEnemyPower) {

		this.player = player;
		this.currEnemyPower = currEnemyPower; 
		
		//Creates boss layer when appropriate, else create a regular layer
		if (currEnemyPower % POWER_NEEDED_TO_SUMMON_BOSS == 0)
			this.allChractersOnMap = loadChractersBossLevel();
		else
			this.allChractersOnMap = loadChracters();
		
	}

	//Main methods of this class--------------------------------------------------------------------------
	
	/**
	 * Fills an arrayList of length TOTAL_ENEMIES_ALLOWED with null enemies except for NUM_ENEMIES_ON_MAP unique spots where it is filled with enemies at power level currEnemyPower.
	 * @return ArrayList<Character> that new map should be
	 */
	private ArrayList<Character> loadChracters() {

		ArrayList<Character> finalArrList = new ArrayList<>();

		//Generates unique numbers to be used for character locations 
		HashSet<Integer> occupiedLocations = new HashSet<>();
		occupiedLocations.add(PLAYER_STARTING_LOCATION);
		occupiedLocations.add(EXIT_STARTING_LOCATION);

		//Add 3 enemies to the map (it is num of enemies + 1 since player and exit take up a spot each)
		while(occupiedLocations.size() < NUM_ENEMIES_ON_MAP+2) {

			int randomInt = generateRandomInt(TOTAL_ENEMIES_ALLOWED-1);
			occupiedLocations.add(randomInt);

		}

		//Add appropriate Character to finalArrList depending on results of occupiedLocations
		for (int i = 0; i < TOTAL_ENEMIES_ALLOWED; i++) {

			if (i == PLAYER_STARTING_LOCATION || i == EXIT_STARTING_LOCATION)
				finalArrList.add(player);
			else if (occupiedLocations.contains(i))
				finalArrList.add(new Enemy(currEnemyPower));
			else
				finalArrList.add(null);

		}

		return finalArrList;

	}

	/**
	 * A variant of the above method where there is only one final boss where the stairway usually is 
	 * @return ArrayList<Character> that new map should be
	 */
	private ArrayList<Character> loadChractersBossLevel() {

		ArrayList<Character> finalArrList = new ArrayList<>();

		for (int i = 0; i < TOTAL_ENEMIES_ALLOWED; i++) {

			if (i == PLAYER_STARTING_LOCATION)
				finalArrList.add(player);
			else if (i == EXIT_STARTING_LOCATION)
				finalArrList.add(new Enemy(currEnemyPower));
			else
				finalArrList.add(null);

		}

		return finalArrList;

	}
	
	//Helper methods----------------------------------------------------
	
	/**
	 * Will given a random number with an upper limit
	 * Ex: upperLimit=2 will create either 1 or 2 
	 * 
	 * @param upperLimit
	 * @return random number
	 */
	private int generateRandomInt(int upperLimit) {
		return (int) (Math.random() * upperLimit + 1);
	}

	//Getters and setters------------------------------------------------------
	
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
