package coolGame.model.character;

/**
 * Model- Represents a single enemy that player may encounter
 * 
 */

public class Enemy extends Character{

	//Variables ------------------------------------------------------------------------------------------------

	final int 		NUMBER_OF_ENEMIES = 5;					//the number of basic types of enemies available
	final int 		POWER_NEEDED_TO_SUMMON_BOSS = 3;		

	final int 		POWER_INCREASE_OF_COMMON_MOBS = 4;		//change boost in power of mobs to their dmgType and naturalRes
	final int 		POWER_INCREASE_OF_THE_BOSS = 5;

	//every enemy has a specialized attack
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

	private String	enemyImage;

	private int 	power = 0; 							//power is how strong a monster
	private int		enemyTypeID;						//The type of enemy's ID

	private int		powerCharge;						//countdown for when enemy will use a powerful attack

	//Constructor ----------------------------------------------------------------------------------------

	/**
	 * Create an Enemy with a certain power level
	 * @param power- The power level of the enemy
	 * 
	 */
	public Enemy(int power) {

		this.power = power;
		this.setName(""); 									//need to initialize to empty string
		enemyTypeID = generateRandomInt(NUMBER_OF_ENEMIES);	//chooses the enemyTypeID randomly of all available

		if (power % POWER_NEEDED_TO_SUMMON_BOSS == 0) {
			enemyTypeID = 100; //when boss appears, it is given a unique ID case
		}

		//All enemies start off with generic stats; this will increase later depending on specialization
		createGenericEnemy();

		//Will create the monster type depending on random number generator
		switch (enemyTypeID) {

		case 1: 
			createZombie();		//Specializes in physical attacks
			break;

		case 2: 
			createSkeleton();	//Specializes in physical attacks
			break;

		case 3: 
			createWitch();		//Specializes in physical attacks
			break;

		case 4:
			createWarlock();	//Specializes in physical attacks
			break;

		case 5:
			createYeti();		//Specializes in physical attacks
			break;

		case 100:
			createGiantBoss();
			break;

		}

	}

	//Creating the initial enemy----------------------------------------------------------------------------------------

	/**
	 * Creates a zombie enemy- is deals physical attack
	 */
	private void createZombie() {

		dmgType = DmgTypeEnum.PHYSICAL;
		setDmgType(dmgType);
		setNaturalRes();
		concatateToName("zombie");
		setEnemyImage("resources/zombie.gif");
	}

	/**
	 * Creates skeleton enemy- it deals cold attack
	 */
	private void createSkeleton() {

		dmgType = DmgTypeEnum.COLD;
		setDmgType(dmgType);
		setNaturalRes();
		concatateToName("skeleton");
		setEnemyImage("resources/skeleton.gif");
	}

	/**
	 * Creates witch enemy- it deals poison attack
	 */
	private void createWitch() {

		dmgType = DmgTypeEnum.POISON;
		setDmgType(dmgType);
		setNaturalRes();
		concatateToName("witch");
		setEnemyImage("resources/witch.gif");
	}

	/**
	 * Creates warlock enemy- it deals fire attack
	 */
	private void createWarlock() {

		dmgType = DmgTypeEnum.FIRE;
		setDmgType(dmgType);
		setNaturalRes();
		concatateToName("warlock");
		setEnemyImage("resources/warlock.gif");
	}

	/**
	 * Creates yeti enemy- it deals lightning attack
	 */
	private void createYeti() {

		dmgType = DmgTypeEnum.LIGHTNING;
		setDmgType(dmgType);
		setNaturalRes();
		concatateToName("yeti");
		setEnemyImage("resources/yeti.gif");
	}

	/**
	 * Special case- final is a the boss
	 */
	private void createGiantBoss() {
		dmgType = DmgTypeEnum.FIRE;
		this.getMyStats().setHp(350);
		this.getMyStats().setMaxHP(350);
		this.getMyStats().setDmg(100);
		this.getMyStats().setCold(100);
		this.getMyStats().setPoison(100);
		this.getMyStats().setFire(100);
		this.getMyStats().setLightning(100);
		this.getMyStats().setArm(30);
		this.getMyStats().setcRes(50);
		this.getMyStats().setpRes(50);
		this.getMyStats().setfRes(50);
		this.getMyStats().setlRes(50);
		this.getMyStats().setDodge(10);
		this.getMyStats().setCrit(10);
		concatateToName("Diablo");
		setEnemyImage("resources/boss.gif");
	}


	/**
	 * All enemies start off with generic stats;
	 * two of the stats will be overridden later for it's specialty 
	 */
	private void createGenericEnemy() {

		int enemyRes;
		int enemyAttack = randomInt(7,10);
		int enemyHealth = randomInt(80, 100);

		this.getMyStats().setHp(power * enemyHealth);
		this.getMyStats().setMaxHP(this.getMyStats().getHp());

		this.getMyStats().setDmg(power * enemyAttack);

		this.getMyStats().setArm(power * randomInt(5,10));

		enemyAttack = randomInt(7,10);
		this.getMyStats().setCold(power * enemyAttack);

		enemyAttack = randomInt(7,10);
		this.getMyStats().setPoison(power * enemyAttack);

		enemyAttack = randomInt(7,10);
		this.getMyStats().setFire(power * enemyAttack);

		enemyAttack = randomInt(7,10);
		this.getMyStats().setLightning(power * enemyAttack);

		enemyRes = randomInt(10,15);
		this.getMyStats().setcRes(power * enemyRes);

		enemyRes = randomInt(10,15);
		this.getMyStats().setpRes(power * enemyRes);

		enemyRes = randomInt(10,15);
		this.getMyStats().setfRes(power * enemyRes);

		enemyRes = randomInt(10,15);
		this.getMyStats().setlRes(power * enemyRes);

		this.getMyStats().setDodge(10);			

		this.getMyStats().setCrit(10);

	}

	/**
	 * 
	 * Depending on what kind of damage the monster deals, we will change it's name and buff that stat 
	 * 
	 * @param dmgType
	 */
	private void setDmgType(Enemy.DmgTypeEnum dmgType) {

		switch(dmgType) {

		case PHYSICAL:

			concatateToName("Punching");
			this.getMyStats().setDmg(power * this.getMyStats().getDmg()+4);
			break;

		case COLD:

			concatateToName("Freezing");
			this.getMyStats().setCold(power * this.getMyStats().getCold()+4);
			break;

		case POISON:

			concatateToName("Poisonous");
			this.getMyStats().setPoison(power * this.getMyStats().getPoison()+4);
			break;

		case FIRE:

			concatateToName("Firey");
			this.getMyStats().setFire(power* this.getMyStats().getFire()+4);
			break;

		case LIGHTNING:

			concatateToName("Electrified");
			this.getMyStats().setLightning(power * this.getMyStats().getLightning()+4);
			break;

		}
	}

	/**
	 * 
	 * Each monster is given a random natural resistance, here we buff that stat
	 * 
	 */
	private void setNaturalRes() {

		int resTypeID = generateRandomInt(ResTypeEnum.values().length);

		switch(resTypeID) {

		case 1:

			resType = ResTypeEnum.FOREST;
			concatateToName("forest");
			this.getMyStats().setArm(power* (this.getMyStats().getArm()+1));
			break;

		case 2:

			resType = ResTypeEnum.TUNDRA;
			concatateToName("tundra");
			this.getMyStats().setcRes(power * (this.getMyStats().getcRes()+5));
			break;

		case 3:

			resType = ResTypeEnum.SWAMP;
			concatateToName("swamp");
			this.getMyStats().setpRes(power * (this.getMyStats().getPoison()+5));
			break;

		case 4:

			resType = ResTypeEnum.DESERT;
			concatateToName("desert");
			this.getMyStats().setfRes(power * (this.getMyStats().getfRes()+5));
			break;

		case 5:

			resType = ResTypeEnum.MOUNTIN;
			concatateToName("mountain");
			this.getMyStats().setlRes(power * (this.getMyStats().getlRes()+5));
			break;

		}


	}

	//Simple helper methods----------------------------------------------------------------------------------------

	
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

	/**
	 * Similar to above method, but we can define lower limit
	 * 
	 * @param min
	 * @param max
	 * @return random number
	 */
	public int randomInt(int min, int max) {
		return (int) (Math.random() * (max - min)) + min;
	}

	/**
	 * All monsters start with an empty string as a name, then we keep contating to the string
	 * until we have the desired name
	 * 
	 * @param str- string we want to concatenate
	 */
	private void concatateToName(String str) {
		this.setName(this.getName() + str + " ");
	}

	//Methods that attack player----------------------------------------------------------------------------------------

	/**
	 * Will lower the player's health depending on the Enemy's stats
	 * 
	 * @param player- player that we want it's health lowered
	 */
	public void attackUser(Player player) {

		boolean ifPlayerDodgesEnemyAttack = generateRandomInt(100) <= player.getMyStats().getDodge();

		//See if stonewall sheild (item) take a effect
		if (player.isShieldEffect()) {
			System.out.println("Player's Stonewall shield blocks all the damage!");
			System.out.println();
			powerCharge++;
			if (powerCharge >= 4)
				powerCharge = 0;
			return;
		}
		
		//See if enemy is frozen
		if (player.isFrozen) {
			System.out.println("Enemy is frozen and unable to act!");
			System.out.println();
			powerCharge++;
			if (powerCharge >= 4)
				powerCharge = 0;
			return;
		}
		
		//See if player dodges the attack
		if (ifPlayerDodgesEnemyAttack) //if a random number generated between 1-100 is less than the player's chance of dodgeing, player dodges and no more damage is taken by enemy
		{
			System.out.println("Player dodges the enemy's attack!");
			System.out.println();
			powerCharge++;
			if (powerCharge >= 4)
				powerCharge = 0;
			return;
		}
		
		//Will calculate damage taken depending on enemy's damage type
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

	/**
	 * Healper method of attackUser that helps calculate damage taken
	 * 
	 * @param enemyDmg- how much damage the enemy is capable of dealing
	 * @param playerDef- the player's defense against that type of damage
	 * @param player- the Player used
	 */
	private void calculateDmgTaken(int enemyDmg, int playerDef, Player player) {

		final double EFFECTIVNESS_OF_DEF = 1.0;

		boolean ifEnemyLandsCrit = generateRandomInt(100) <= this.getMyStats().getCrit(); //calculate if enemy lands a crit

		//If enemy lands a crit
		if (ifEnemyLandsCrit) {
			int dmgTakenDoubled = ((enemyDmg*2 < (int)(playerDef*EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg*2 - (int)(playerDef*EFFECTIVNESS_OF_DEF));
			player.getMyStats().setHp(player.getMyStats().getHp() - dmgTakenDoubled); //crits ignore player defense
			System.out.println("Enemy lands a critical attack and does " + dmgTakenDoubled +
					dmgType + " damage. Player loses " + dmgTakenDoubled + " Health.");
			System.out.println();
			powerCharge++;
		}

		//Else enemy does not land a crit
		else if (dmgType == DmgTypeEnum.PHYSICAL) {

			//If enemy has enough power to special attack and the enemy blocks
			if (powerCharge == 4 && player.blocked){
				int dmgTakenDoubled = ((enemyDmg < (int)(playerDef*EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg - (int)(playerDef*EFFECTIVNESS_OF_DEF));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTakenDoubled); //crits ignore player defense
				System.out.println("Enemy lands a power attack and does " + enemyDmg*2 + " damage. Player blocks, losing only " + dmgTakenDoubled + " HP.");
				System.out.println();
				powerCharge = 0;
				player.resetBlock();
			}

			//If the enemy blocks
			else if (player.blocked) {

				int dmgTaken = ((enemyDmg/2 < (int) (playerDef * EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg/2 - (int) (playerDef * EFFECTIVNESS_OF_DEF));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
				System.out.println("Player blocks enemy, taking " + dmgTaken + "physical damage and restores 5 mana.");
				System.out.println();
				powerCharge++;
				player.resetBlock();
			}

			//If the enemy special attacks
			else if (powerCharge == 4) {
				int dmgTakenDoubled = ((enemyDmg*2 < (int)(playerDef*EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg*2 - (int)(playerDef*EFFECTIVNESS_OF_DEF));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTakenDoubled); //crits ignore player defense
				System.out.println("Enemy lands a power attack and does " + dmgTakenDoubled + " damage. Player loses " + dmgTakenDoubled + " HP.");
				System.out.println();
				powerCharge = 0;
			}

			//Else it is a regular crit
			else {
				int dmgTaken = ((enemyDmg < (int) (playerDef * EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg - (int) (playerDef * EFFECTIVNESS_OF_DEF));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
				System.out.println("Enemy attacks player with " + enemyDmg + " physical damage. Player takes " + dmgTaken + " damage.");
				System.out.println();
				powerCharge++;
			}

		}

		//No crit, special attack, and blocked
		else if (powerCharge == 4 && player.blocked) {
			int dmgTaken = enemyDmg/2 - (int) (enemyDmg/2 * (playerDef/100.0f));
			player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken); //crits ignore player defense
			System.out.println("Enemy lands a power attack and does " + enemyDmg*2 + " damage. Player blocks and loses " + dmgTaken + " HP.");
			System.out.println();
			powerCharge = 0;
			player.resetBlock();
		}
		
		//Blocked
		else if (player.blocked) {
			int dmgTaken = enemyDmg/2 - (int) (enemyDmg/2 * (playerDef/100.0f));
			player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
			System.out.println("Player blocks enemy and takes " + dmgTaken + " damage and restores 5 mana.");
			System.out.println();
			powerCharge++;
			player.resetBlock();
		}
		
		//Special attack
		else if (powerCharge == 4) {
			int dmgTakenDoubled = (enemyDmg*2 - (int) (enemyDmg*2 * (playerDef/100.0f)));
			player.getMyStats().setHp(player.getMyStats().getHp() - dmgTakenDoubled); //crits ignore player defense
			System.out.println("Enemy lands a power attack and does " + enemyDmg*2 + " damage. Player loses " + dmgTakenDoubled + " HP.");
			System.out.println();
			powerCharge = 0;
		}
		
		//Else it's a completely normal attack by enemy
		else {
			int dmgTaken = enemyDmg - (int) (enemyDmg * (playerDef/100.0f));
			player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
			System.out.println("Enemy attacks player and does " + dmgTaken + " damage. Player loses " + dmgTaken + " HP.");
			System.out.println();
			powerCharge++;
		}

	}

	//Getters and setters -----------------------------------------------------------------

	public void setPowerCharge(int powerCharge) {
		this.powerCharge = powerCharge;
	}

	public int getPowerCharge() {
		return powerCharge;
	}

	public String getEnemyImage() {
		return enemyImage;
	}

	public void setEnemyImage(String enemyImage) {
		this.enemyImage = enemyImage;
	}

	public int getPower() {
		return power;
	}

	public int getEnemyTypeID() {
		return enemyTypeID;
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

}
