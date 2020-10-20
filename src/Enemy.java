public class Enemy extends Character{
	
	private enum dmgTypeEnum {
		PHYSICAL,
		COLD,
		POISON,
		FIRE,
		LIGHTNING
	}
	
	final int NUMBER_OF_ENEMIES = 2;
	final int POWER_NEEDED_TO_SUMMON_BOSS = 10;
	
	final int POWER_INCREASE_OF_COMMON_MOBS = 5;
	final int POWER_INCREASE_OF_COMMON_BOSS = 8;
	
	/*
	 * power increases by 1 after each enemy is defeated
	 * when power is at 10, boss is summoned
	 */
	static int power = 0; 
	dmgTypeEnum dmgType;
	
	
	//constructor
	public Enemy() {
		
		int enemyTypeID = generateRandomInt(NUMBER_OF_ENEMIES);
		
		if (++power == POWER_NEEDED_TO_SUMMON_BOSS) {
			enemyTypeID = 100; //the boss's id
		}
		
		//just give it some random basic stats
		createGenericEnemy();

		switch (enemyTypeID) {

		case 1: 
			createZombie();
			break;

		case 2: 
			createSkeleton();
			break;

		case 3: 
			createWitch();
			break;
			
		case 100:
			createGiantBoss();
			break;

		}
		
		//possible mobs; idk what to put yet
		//giant, wolf, undead, ghost, zombie, skeleton, spider, bat, slime, giant rat, ghoul, vampire, orcs, cyclops, witch, wizard, warlock, mummy, ogre, minotaur, snake, scorpion, lich
		
	}
	
	public void createZombie() {
		
		dmgType = dmgTypeEnum.PHYSICAL;
		this.setName("Slapping zombie");
		this.setDmg(power + POWER_INCREASE_OF_COMMON_MOBS);
		
	}
	
	public void createSkeleton() {
		
		dmgType = dmgTypeEnum.COLD;
		this.setName("Freezing skeleton");
		this.setCold(power + POWER_INCREASE_OF_COMMON_MOBS);
		
	}
	
	public void createWitch() {
		
		dmgType = dmgTypeEnum.POISON;
		this.setName("Poisonous witch");
		this.setPoison(power + POWER_INCREASE_OF_COMMON_MOBS);
		
	}
	
	public void createGiantBoss() {
		
		dmgType = dmgTypeEnum.POISON;
		this.setName("The Boss- Giant");
		this.setDmg(power + POWER_INCREASE_OF_COMMON_BOSS);
		
	}
	
	//I'm lazy, so this class will give generic stats that we override later
	public void createGenericEnemy() {
		
		final int ALLOWED_POWER_VARIANCE = 3;
		int randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setHp(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setDmg(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setArm(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setCold(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setPoison(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setFire(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setLightning(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setcRes(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setpRes(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setfRes(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.setlRes(power + randomInt);
		
	}
	
	//will generate a random number given upper limit
	//ex: an upper limit of "2" will return wither 1 or 2
	private int generateRandomInt(int upperLimit) {
		return (int) (Math.random() * upperLimit + 1);
	}
	
	public void attackUser(Player player) {
		
		int currPlayerHealth = player.getHp();
		int dmgTaken;
		
		switch(dmgType) {
		
			case PHYSICAL:
				dmgTaken = calculateDmgTaken(getDmg(), player.getArm());
				player.setHp(currPlayerHealth - dmgTaken);
				break;
			
			case COLD:
				dmgTaken = calculateDmgTaken(getCold(), player.getcRes());
				player.setHp(currPlayerHealth - dmgTaken);
				break;
				
			case POISON:
				dmgTaken = calculateDmgTaken(getPoison(), player.getpRes());
				player.setHp(currPlayerHealth - dmgTaken);
				break;
			
			case FIRE:
				dmgTaken = calculateDmgTaken(getFire(), player.getfRes());
				player.setHp(currPlayerHealth - dmgTaken);
				break;
			
			case LIGHTNING:
				dmgTaken = calculateDmgTaken(getLightning(), player.getlRes());
				player.setHp(currPlayerHealth - dmgTaken);
				break;
			
		}
		
	}
	
	//helper method of attackUser that helps calculate damgae taken
	//arguments: how much dmg the enemy is capable is doing, and the player's def against that type
	private int calculateDmgTaken(int enemyDmg, int playerDef) {
		
		int dmgTaken = enemyDmg - playerDef;
		
		if (dmgTaken < 0)
			return 0;
		else
			return dmgTaken;
		
	}
	
	//Call this once enemy has been defeated to give player the new item the enemy dropped
	public void enemyHasBeenDefeated(Player player) {
		
		//create a new array that has size incremented 
		Item[]	playerItems 	= player.getItem();
		int		prevItemsLength	= playerItems.length;
		
		Item[] 	newPlayerItems 	= new Item[prevItemsLength];
		Item 	newItemToAdd	= new Item();
		
		//copy old array into new one
		for (int i = 0; i < prevItemsLength; i++) {
			newPlayerItems[i] = playerItems[i];
		}
		
		//add new item
		newPlayerItems[prevItemsLength+1] = newItemToAdd;
		
	}
	
	public String toString() {
		
		String result = "";
		
		result += "Name 			: " + getName() + "\n";	
		result += "Damage Type 		: " + this.dmgType + "\n";	
		result += "Power			: " + Enemy.power + "\n";		
		result += "Health			: " + getHp() + "\n";
		result += "Physical Damage		: " + getDmg() + "\n";
		result += "Armor			: " + getArm() + "\n";
		result += "Cold Damage		: " + getCold() + "\n";
		result += "Poison Damage		: " + getPoison() + "\n";
		result += "Fire Damage		: " + getFire() + "\n";
		result += "Lightning Damage	: " + getLightning() + "\n";
		result += "Cold Resistance		: " + getcRes() + "\n";
		result += "Poison Resistance	: " + getpRes() + "\n";
		result += "Fire Resistance		: " + getfRes() + "\n";
		result += "Lighning Resistance	: " + getlRes() + "\n";
		
		return result;
					
	}
	
	public static void main(String[] args) {
		
		Enemy firstEnemy = new Enemy();
		System.out.println(firstEnemy);
	}
	
}
