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
	private boolean	hasLightningDebuff = false;			//An enemy can have a lightning debuff when attacked by lightning attack
	private int		powerCharge;						//countdown for when enemy will use a powerful attack

	//Constructor ----------------------------------------------------------------------------------------
	
	/**
	 * Create an Enemy with a certain power level
	 * @param power The power level of the enemy
	 * 
	 */
	public Enemy(int power) {
		
		this.power = power;
		this.setName(""); 									//need to initialize to empty string
		enemyTypeID = generateRandomInt(NUMBER_OF_ENEMIES);	//chooses the enemyTypeID randomly of all available
		
		if (power % POWER_NEEDED_TO_SUMMON_BOSS == 0) {
			enemyTypeID = 100; //the boss's id
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
	
	//----------------------------------------------------------------------------------------
	
	//Below are the methods that create a specific mob 
	private void createZombie() {
		
		dmgType = DmgTypeEnum.PHYSICAL;
		setDmgType(dmgType);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("zombie");
		setEnemyImage("resources/zombie.gif");
	}
	
	private void createSkeleton() {
		
		dmgType = DmgTypeEnum.COLD;
		setDmgType(dmgType);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("skeleton");
		setEnemyImage("resources/skeleton.gif");
	}
	
	private void createWitch() {
		
		dmgType = DmgTypeEnum.POISON;
		setDmgType(dmgType);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("witch");
		setEnemyImage("resources/witch.gif");
	}
	
	private void createWarlock() {
		
		dmgType = DmgTypeEnum.FIRE;
		setDmgType(dmgType);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("warlock");
		setEnemyImage("resources/warlock.gif");
	}
	
	private void createYeti() {
		
		dmgType = DmgTypeEnum.LIGHTNING;
		setDmgType(dmgType);
		setNaturalRes(POWER_INCREASE_OF_COMMON_MOBS);
		concatateToName("yeti");
		setEnemyImage("resources/yeti.gif");
	}
	
	//no, it's not a giant (big) boss, it's a giant (species)
	private void createGiantBoss() {
		
		dmgType = DmgTypeEnum.PHYSICAL;
		setDmgType(dmgType);
		setNaturalRes(POWER_INCREASE_OF_THE_BOSS);
		concatateToName("GIANT BOSS");
		setEnemyImage("resources/boss.gif");
	}
	

	//I'm lazy, so this class will give generic stats that we override later
	private void createGenericEnemy() {

		final int ALLOWED_POWER_VARIANCE = 3;
		int enemyRes;
		int enemyAttack = randomInt(15,20);
		int enemyHealth = randomInt(80, 100);
		this.getMyStats().setHp(power * enemyHealth);
		this.getMyStats().setMaxHP(this.getMyStats().getHp());

		this.getMyStats().setDmg(power * enemyAttack);

		this.getMyStats().setArm(power * randomInt(5,10));

		enemyAttack = randomInt(10,15);
		this.getMyStats().setCold(power * enemyAttack);

		enemyAttack = randomInt(10,15);
		this.getMyStats().setPoison(power * enemyAttack);

		enemyAttack = randomInt(10,15);
		this.getMyStats().setFire(power * enemyAttack);

		enemyAttack = randomInt(10,15);
		this.getMyStats().setLightning(power * enemyAttack);

		enemyRes = randomInt(10,15);
		this.getMyStats().setcRes(power * enemyRes);

		enemyRes = randomInt(10,15);
		this.getMyStats().setpRes(power * enemyRes);

		enemyRes = randomInt(10,15);
		this.getMyStats().setfRes(power * enemyRes);

		enemyRes = randomInt(10,15);
		this.getMyStats().setlRes(power * enemyRes);

		this.getMyStats().setDodge(10);			//lowered dodge and crit chances cause it can get way out of hand

		this.getMyStats().setCrit(10);

	}

	//assign a damage type to a mob
	private void setDmgType(Enemy.DmgTypeEnum dmgType) {

		switch(dmgType) {

			case PHYSICAL:

				concatateToName("Punching");
				this.getMyStats().setDmg(power * this.getMyStats().getDmg());
				break;

			case COLD:

				concatateToName("Freezing");
				this.getMyStats().setCold(power * this.getMyStats().getCold());
				break;

			case POISON:

				concatateToName("Poisonous");
				this.getMyStats().setPoison(power * this.getMyStats().getPoison());
				break;

			case FIRE:

				concatateToName("Firey");
				this.getMyStats().setFire(power* this.getMyStats().getFire());
				break;

			case LIGHTNING:

				concatateToName("Electrified");
				this.getMyStats().setLightning(power * this.getMyStats().getLightning());
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
		if (player.isFrozen) {
			System.out.println("Enemy is frozen and unable to act!");
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
		final double EFFECTIVNESS_OF_DEF = 1.0;

		if (ifEnemyLandsCrit) {

			int dmgTakenDoubled = ((enemyDmg*2 < (int)(playerDef*EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg*2 - (int)(playerDef*EFFECTIVNESS_OF_DEF));
			player.getMyStats().setHp(player.getMyStats().getHp() - dmgTakenDoubled); //crits ignore player defense
			System.out.println("Enemy lands critical attack and does " + dmgTakenDoubled + " damage. Player loses " + dmgTakenDoubled + " HP.");
			System.out.println();
			powerCharge++;
		}
		else
		if (dmgType == DmgTypeEnum.PHYSICAL)
		{
			if (player.blocked) {

				int dmgTaken = ((enemyDmg/2 < (int) (playerDef * EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg/2 - (int) (playerDef * EFFECTIVNESS_OF_DEF));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
				System.out.println("Player blocks enemy and takes " + dmgTaken + " damage and restores 5 mana.");
				System.out.println();
				powerCharge++;
				player.resetBlock();
			}
			else
			if (powerCharge == 4) {
				int dmgTakenDoubled = ((enemyDmg*2 < (int)(playerDef*EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg*2 - (int)(playerDef*EFFECTIVNESS_OF_DEF));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTakenDoubled); //crits ignore player defense
				System.out.println("Enemy lands a power attack and does " + dmgTakenDoubled + " damage. Player loses " + dmgTakenDoubled + " HP.");
				System.out.println();
				powerCharge = 0;
			}
			else {
				int dmgTaken = ((enemyDmg < (int) (playerDef * EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg - (int) (playerDef * EFFECTIVNESS_OF_DEF));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
				System.out.println("Enemy attacks player with " + enemyDmg + " physical damage. Player takes " + dmgTaken + " damage.");
				System.out.println();
				powerCharge++;
			}
		}
		else {
			if (player.blocked) {
				int dmgTaken = enemyDmg/2 - (int) (enemyDmg/2 * (playerDef/100.0f));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
				System.out.println("Player blocks enemy and takes " + dmgTaken + " damage and restores 5 mana.");
				System.out.println();
				powerCharge++;
				player.resetBlock();
			}
			else if (powerCharge == 4) {
				int dmgTakenDoubled = ((enemyDmg*2 < (int)(playerDef*EFFECTIVNESS_OF_DEF)) ? 1 : enemyDmg*2 - (int)(playerDef*EFFECTIVNESS_OF_DEF));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTakenDoubled); //crits ignore player defense
				System.out.println("Enemy lands a power attack and does " + dmgTakenDoubled + " damage. Player loses " + dmgTakenDoubled + " HP.");
				System.out.println();
				powerCharge = 0;
			}
			else {
				int dmgTaken = enemyDmg - (int) (enemyDmg * (playerDef/100.0f));
				player.getMyStats().setHp(player.getMyStats().getHp() - dmgTaken);
				System.out.println("Enemy attacks player and does " + dmgTaken + " damage. Player loses " + dmgTaken + " HP.");
				System.out.println();
				powerCharge++;
			}
		}
	}
	
	public boolean getHasLightningDebuff() {
		return hasLightningDebuff;
	}
	
	public void setHasLightningDebuff(boolean hasLightningDebuff) {
		this.hasLightningDebuff = hasLightningDebuff;
	}
	
	public void setPowerCharge(int powerCharge) {
		this.powerCharge = powerCharge;
	}
	
	public int getPowerCharge() {
		return powerCharge;
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
	
}
