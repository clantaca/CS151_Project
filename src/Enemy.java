public class Enemy extends Character{
	
	//every mob has a damage type they can only deal that boosts an offensive stat
	private enum DmgTypeEnum {
		PHYSICAL,
		COLD,
		POISON,
		FIRE,
		LIGHTNING
	}
	
	//every mob has a natural resistance that boosts a defensive stat; idk if we want it to be seen in it's name tho
	private enum ResTypeEnum {
		FOREST,	//armor
		TUNDRA,	//cold resistance
		SWAMP,	//poison resistance
		DESERT,//fire resistance
		MOUNTIN	//lightning resistance
	}
	
	DmgTypeEnum 	dmgType;
	ResTypeEnum 	resType;
	
	final int 		NUMBER_OF_ENEMIES = 5;					//change this whenever you want to add more enemies 
	final int 		POWER_NEEDED_TO_SUMMON_BOSS = 10;			
	
	final int 		POWER_INCREASE_OF_COMMON_MOBS = 4;		//change boost in power of mobs to their dmgType and naturalRes
	final int 		POWER_INCREASE_OF_THE_BOSS = 5;
	
	static int 		power = 0; 							//power is how strong a mob is; boss is summoned on power 10
	
	//----------------------------------------------------------------------------------------
	
	//Constructor- chooses a random enemy type and gives stats representative of that type
	public Enemy() {
		
		this.setName(""); //need to initialize
		int enemyTypeID = generateRandomInt(NUMBER_OF_ENEMIES);
		
		if (++power % POWER_NEEDED_TO_SUMMON_BOSS == 0) {
			enemyTypeID = 100; //the boss's id
		}
		
		//Load random stats to enemy that aren't very good
		createGenericEnemy();

		switch (enemyTypeID) {

		case 1: 
			createZombie();		//physical
			break;

		case 2: 
			createSkeleton();	//cold
			break;

		case 3: 
			createWitch();		//poison
			break;
			
		case 4:
			createWarlock();	//fire
			break;
			
		case 5:
			createWolf();		//lightning
			break;
			
		case 100:
			createGiantBoss();
			break;

		}
		
		//possible mobs; idk what to put yet
		//giant, wolf, undead, ghost, zombie, skeleton, spider, bat, slime, giant rat, ghoul, vampire, orcs, cyclops, witch, wizard, warlock, mummy, ogre, minotaur, snake, scorpion, lich
		
	}
	
	public int getPower() {
		
		return Enemy.power;
		
	}
	
	//----------------------------------------------------------------------------------------
	
	//Below are the methods that create a specific mob 
	private void createZombie() {
		
		dmgType = DmgTypeEnum.PHYSICAL;
		setDmgType(dmgType, POWER_INCREASE_OF_COMMON_MOBS);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("zombie");
		
	}
	
	private void createSkeleton() {
		
		dmgType = DmgTypeEnum.COLD;
		setDmgType(dmgType, POWER_INCREASE_OF_COMMON_MOBS);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("skeleton");
		
	}
	
	private void createWitch() {
		
		dmgType = DmgTypeEnum.POISON;
		setDmgType(dmgType, POWER_INCREASE_OF_COMMON_MOBS);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("witch");
		
	}
	
	private void createWarlock() {
		
		dmgType = DmgTypeEnum.FIRE;
		setDmgType(dmgType, POWER_INCREASE_OF_COMMON_MOBS);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("warlock");
		
	}
	
	private void createWolf() {
		
		dmgType = DmgTypeEnum.LIGHTNING;
		setDmgType(dmgType, POWER_INCREASE_OF_COMMON_MOBS);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("wolf");
		
	}
	
	//no, it's not a giant (big) boss, it's a giant (species)
	private void createGiantBoss() {
		
		dmgType = DmgTypeEnum.PHYSICAL;
		setDmgType(dmgType, POWER_INCREASE_OF_THE_BOSS);
		setNaturalRes(POWER_INCREASE_OF_THE_BOSS);
		concatateToName("GIANT BOSS");
		
	}
	
	//I'm lazy, so this class will give generic stats that we override later
	private void createGenericEnemy() {
		
		final int ALLOWED_POWER_VARIANCE = 3;
		int randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setHp(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setDmg(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setArm(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setCold(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setPoison(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setFire(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setLightning(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setcRes(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setpRes(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setfRes(power + randomInt);
		randomInt = generateRandomInt(ALLOWED_POWER_VARIANCE);
		
		this.getMyStats().setlRes(power + randomInt);
		
	}

	//assign a damage type to a mob
	private void setDmgType(DmgTypeEnum dmgType, int powerIncrease) {
		
		switch(dmgType) {
		
		case PHYSICAL:
			
			concatateToName("Punching");
			this.getMyStats().setDmg(power*2 + powerIncrease);
			break;
			
		case COLD:
			
			concatateToName("Freezing");
			this.getMyStats().setCold(power*2 + powerIncrease);
			break;
			
		case POISON:
			
			concatateToName("Poisonous");
			this.getMyStats().setPoison(power*2 + powerIncrease);
			break;
			
		case FIRE:
			
			concatateToName("Firey");
			this.getMyStats().setFire(power*2 + powerIncrease);
			break;
			
		case LIGHTNING:
			
			concatateToName("Electrified");
			this.getMyStats().setLightning(power*2 + powerIncrease);
			break;
			
		}
		
	}
	
	//each mob will get a natural resistance against a certain attack type
	private void setNaturalRes(int powerIncrease) {
		
		int resTypeID = generateRandomInt(ResTypeEnum.values().length);
		
		switch(resTypeID) {
		
		case 1:
			
			resType = ResTypeEnum.FOREST;
			concatateToName("forest");
			this.getMyStats().setArm(power*2 + powerIncrease);
			break;
		
		case 2:
			
			resType = ResTypeEnum.TUNDRA;
			concatateToName("tundra");
			this.getMyStats().setcRes(power*2 + powerIncrease);
			break;
			
		case 3:
			
			resType = ResTypeEnum.SWAMP;
			concatateToName("swamp");
			this.getMyStats().setpRes(power*2 + powerIncrease);
			break;
			
		case 4:
			
			resType = ResTypeEnum.DESERT;
			concatateToName("desert");
			this.getMyStats().setfRes(power*2 + powerIncrease);
			break;
			
		case 5:
			
			resType = ResTypeEnum.MOUNTIN;
			concatateToName("mountin");
			this.getMyStats().setlRes(power*2 + powerIncrease);
			break;
		
		}
		
		
	}
	
	//will generate a random number given upper limit
	//ex: an upper limit of "2" will return wither 1 or 2
	private int generateRandomInt(int upperLimit) {
		return (int) (Math.random() * upperLimit + 1);
	}
	
	private void concatateToName(String str) {
		this.setName(this.getName() + str + " ");
	}
	
	//----------------------------------------------------------------------------------------
	
	//will decrease player's health depending on how much damage the enemy can do and the player's resistance to that dmgType
	public void attackUser(Player player) {
		
		switch(dmgType) {
		
			case PHYSICAL:
				calculateDmgTaken(this.getMyStats().getDmg(), player.getMyStats().getArm(), player);
				break;
			
			case COLD:
				calculateDmgTaken(this.getMyStats().getCold(), player.getMyStats().getcRes(), player);
				break;
				
			case POISON:
				calculateDmgTaken(this.getMyStats().getPoison(), player.getMyStats().getpRes(), player);
				break;
			
			case FIRE:
				calculateDmgTaken(this.getMyStats().getFire(), player.getMyStats().getfRes(), player);
				break;
			
			case LIGHTNING:
				calculateDmgTaken(this.getMyStats().getLightning(), player.getMyStats().getlRes(), player);
				break;
			
		}
		
	}
	
	//helper method of attackUser that helps calculate damage taken
	//arguments: how much dmg the enemy is capable is doing, and the player's def against that type, and the player object
	private void calculateDmgTaken(int enemyDmg, int playerDef, Player player) {
		
		int dmgTaken = (enemyDmg < playerDef) ? 0 : enemyDmg-playerDef;
		
		player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
		
	}
	
	//----------------------------------------------------------------------------------------
	
	public String toString() {
		
		String result = "";
		
		result += "Power			: " 		+ Enemy.power + "\n";	
		result += "Name 			: " 		+ this.getName() + "\n";	
		result += "Damage Type 		: " 		+ this.dmgType + "\n";	
		result += "Natural Resistance 	: " 	+ this.resType + "\n";		
		result += "Health			: " 		+ this.getMyStats().getHp() + "\n";
		result += "Physical Damage		: " 	+ this.getMyStats().getDmg() + "\n";
		result += "Armor			: " 		+ this.getMyStats().getArm() + "\n";
		result += "Cold Damage		: " 		+ this.getMyStats().getCold() + "\n";
		result += "Poison Damage		: " 	+ this.getMyStats().getPoison() + "\n";
		result += "Fire Damage		: " 		+ this.getMyStats().getFire() + "\n";
		result += "Lightning Damage	: " 		+ this.getMyStats().getLightning() + "\n";
		result += "Cold Resistance		: " 	+ this.getMyStats().getcRes() + "\n";
		result += "Poison Resistance	: " 	+ this.getMyStats().getpRes() + "\n";
		result += "Fire Resistance		: " 	+ this.getMyStats().getfRes() + "\n";
		result += "Lighning Resistance	: " 	+ this.getMyStats().getlRes() + "\n";
		
		return result;
					
	}
	
	public static void main(String[] args) {
		
		Enemy newEnemy;
		
		for (int i = 0; i < 20; i++) {
			
			newEnemy = new Enemy();
			System.out.println(newEnemy);
			
		}
	}
	
}
