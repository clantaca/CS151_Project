public class Enemy extends Character{
	public String				enemyImage;
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
		DESERT, //fire resistance
		MOUNTIN	//lightning resistance
	}
	
	DmgTypeEnum 	dmgType;
	ResTypeEnum 	resType;
	
	final int 		NUMBER_OF_ENEMIES = 5;					//change this whenever you want to add more enemies 
	final int 		POWER_NEEDED_TO_SUMMON_BOSS = 10;			
	
	final int 		POWER_INCREASE_OF_COMMON_MOBS = 4;		//change boost in power of mobs to their dmgType and naturalRes
	final int 		POWER_INCREASE_OF_THE_BOSS = 5;
	
	private int 	power = 0; 							//power is how strong a mob is; boss is summoned on power 10
	private int		enemyTypeID;
	
	//----------------------------------------------------------------------------------------
	
	//Constructor- chooses a random enemy type and gives stats representative of that type
	public Enemy(int power) {
		
		this.power = power;
		this.setName(""); //need to initialize
		enemyTypeID = generateRandomInt(NUMBER_OF_ENEMIES);
		
		if (power % POWER_NEEDED_TO_SUMMON_BOSS == 0) {
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
			createYeti();		//lightning
			break;
			
		case 100:
			createGiantBoss();
			break;

		}
		
		//possible mobs; idk what to put yet
		//giant, yeti, undead, ghost, zombie, skeleton, spider, bat, slime, giant rat, ghoul, vampire, orcs, cyclops, witch, wizard, warlock, mummy, ogre, minotaur, snake, scorpion, lich
		
	}
	
	public int getPower() {
		
		return power;
		
	}
	
	public int getEnemyTypeID() {
		return enemyTypeID;
	}
	
	
	//----------------------------------------------------------------------------------------
	
	//Below are the methods that create a specific mob 
	private void createZombie() {
		
		dmgType = DmgTypeEnum.PHYSICAL;
		setDmgType(dmgType, POWER_INCREASE_OF_COMMON_MOBS);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("zombie");
		enemyImage = "zombie.gif";
	}
	
	private void createSkeleton() {
		
		dmgType = DmgTypeEnum.COLD;
		setDmgType(dmgType, POWER_INCREASE_OF_COMMON_MOBS);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("skeleton");
		enemyImage = "skeleton.gif";
	}
	
	private void createWitch() {
		
		dmgType = DmgTypeEnum.POISON;
		setDmgType(dmgType, POWER_INCREASE_OF_COMMON_MOBS);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("witch");
		enemyImage = "witch.gif";
	}
	
	private void createWarlock() {
		
		dmgType = DmgTypeEnum.FIRE;
		setDmgType(dmgType, POWER_INCREASE_OF_COMMON_MOBS);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("warlock");
		enemyImage = "warlock.gif";
	}
	
	private void createYeti() {
		
		dmgType = DmgTypeEnum.LIGHTNING;
		setDmgType(dmgType, POWER_INCREASE_OF_COMMON_MOBS);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("yeti");
		enemyImage = "yeti.gif";
	}
	
	//no, it's not a giant (big) boss, it's a giant (species)
	private void createGiantBoss() {
		
		dmgType = DmgTypeEnum.PHYSICAL;
		setDmgType(dmgType, POWER_INCREASE_OF_THE_BOSS);
		setNaturalRes(POWER_INCREASE_OF_THE_BOSS);
		concatateToName("GIANT BOSS");
		enemyImage = "boss.gif";
	}
	

	//I'm lazy, so this class will give generic stats that we override later
	private void createGenericEnemy() {
		
		final int ALLOWED_POWER_VARIANCE = 3;
		this.getMyStats().setHp(power * randomInt(80, 100));

		this.getMyStats().setDmg(power * randomInt(5,10));
		
		this.getMyStats().setArm(power * randomInt(3,6));
		
		this.getMyStats().setCold(power * randomInt(5,10));
		
		this.getMyStats().setPoison(power * randomInt(5,10));

		this.getMyStats().setFire(power * randomInt(5,10));
		
		this.getMyStats().setLightning(power * randomInt(5,10));
		
		this.getMyStats().setcRes(power * randomInt(3,6));
		
		this.getMyStats().setpRes(power * randomInt(3,6));

		this.getMyStats().setfRes(power * randomInt(3,6));

		this.getMyStats().setlRes(power * randomInt(3,6));

		this.getMyStats().setDodge(10);			//lowered dodge and crit chances cause it can get way out of hand

		this.getMyStats().setCrit(10);
		
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
			concatateToName("mountain");
			this.getMyStats().setlRes(power*2 + powerIncrease);
			break;
		
		}
		
		
	}
	
	//will generate a random number given upper limit
	//ex: an upper limit of "2" will return either 1 or 2
	private int generateRandomInt(int upperLimit) {
		return (int) (Math.random() * upperLimit + 1);
	}
	public int randomInt(int min, int max) {
		return (int) (Math.random() * (max - min)) + min;
	}
	private void concatateToName(String str) {
		this.setName(this.getName() + str + " ");
	}
	
	//----------------------------------------------------------------------------------------
	
	//will decrease player's health depending on how much damage the enemy can do and the player's resistance to that dmgType
	public void attackUser(Player player) {

		boolean ifPlayerDodgesEnemyAttack = generateRandomInt(100) <= player.getMyStats().getDodge();
		if (ifPlayerDodgesEnemyAttack) //if a random number generated between 1-100 is less than the player's chance of dodgeing, player dodges and no more damage is taken by enemy
		{
			System.out.println("Player dodges the enemy's attack!");
			return;
		}
		if (player.isShieldEffect()) {
			System.out.println("Player's Stonewall shield blocks all the damage!");
			return;
		}
		switch(dmgType) {
		
			case PHYSICAL:
				calculateDmgTaken(this.getMyStats().getDmg(), player.getMyStats().getArm(), player);
				player.setHitsTakenCounter(player.getHitsTakenCounter()+1);
				break;
			
			case COLD:
				calculateDmgTaken(this.getMyStats().getCold(), player.getMyStats().getcRes(), player);
				player.setHitsTakenCounter(player.getHitsTakenCounter()+1);
				break;
				
			case POISON:
				calculateDmgTaken(this.getMyStats().getPoison(), player.getMyStats().getpRes(), player);
				player.setHitsTakenCounter(player.getHitsTakenCounter()+1);
				break;
			
			case FIRE:
				calculateDmgTaken(this.getMyStats().getFire(), player.getMyStats().getfRes(), player);
				player.setHitsTakenCounter(player.getHitsTakenCounter()+1);
				break;
			
			case LIGHTNING:
				calculateDmgTaken(this.getMyStats().getLightning(), player.getMyStats().getlRes(), player);
				player.setHitsTakenCounter(player.getHitsTakenCounter()+1);
				break;
			
		}
		
	}
	
	//helper method of attackUser that helps calculate damage taken
	//arguments: how much dmg the enemy is capable is doing, and the player's def against that type, and the player object
	private void calculateDmgTaken(int enemyDmg, int playerDef, Player player) {
		
		boolean ifEnemyLandsCrit = generateRandomInt(100) <= this.getMyStats().getCrit();
		final double EFFECTIVNESS_OF_DEF = 0.1;
		
		if(ifEnemyLandsCrit) {
			
			int dmgTakenDoubled = ((enemyDmg*2 < (int)(playerDef*EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg*2 - (int)(playerDef*EFFECTIVNESS_OF_DEF));
			player.getMyStats().setHp(player.getMyStats().getHp() - dmgTakenDoubled); //crits ignore player defense
			System.out.println("Enemy lands critical attack and does " + dmgTakenDoubled + " damage. Player loses " + dmgTakenDoubled + " HP.");
			System.out.println();
		}

		else {
			if (player.blocked) {

				int dmgTaken = ((enemyDmg/2 < (int) (playerDef * EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg/2 - (int) (playerDef * EFFECTIVNESS_OF_DEF));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
				System.out.println("Player blocks enemy and takes " + dmgTaken + " damage and restores 5 mana.");
				System.out.println();
				player.resetBlock();
			}
			else {
				int dmgTaken = ((enemyDmg < (int) (playerDef * EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg - (int) (playerDef * EFFECTIVNESS_OF_DEF));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
				System.out.println("Enemy attacks player and does " + dmgTaken + " damage. Player loses " + dmgTaken + " HP.");
				System.out.println();
			}
		}
	}
	
	//----------------------------------------------------------------------------------------
	
	public String toString() {
		
		String result = "";
		
		result += "Power			: " 		+ this.power + "\n";	
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
		result += "Dodge Chance		: " 	+ this.getMyStats().getDodge() + "\n";
		result += "Critical Strike Chance 	: " 	+ this.getMyStats().getCrit() + "\n";
		
		return result;
					
	}
	
	public static void main(String[] args) {
		
		Enemy newEnemy;
		
		for (int i = 1; i <= 20; i++) {
			
			newEnemy = new Enemy(i);
			System.out.println(newEnemy);
			
		}
	}
	
}
