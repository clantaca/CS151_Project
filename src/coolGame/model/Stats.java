package coolGame.model;

/**
 * Model that includes all the stats for the characters, player and enemies
 */
public class Stats {

	int hp;		//health points
	int mana;
	int dmg;	//physical damage
	int arm;	//armor
	int cold;	//cold damage
	int poison;	//poison damage
	int fire;	//fire damage
	int lightning;	//lightning damage
	int cRes;	//cold resist
	int pRes;	//poison resist
	int fRes;	//fire resist
	int lRes;	//lightning resist
	int dodge;
	int crit;
	int maxHP;
	int maxMana;

	/**
	 * Returns the max mana
	 * @return mana
	 */
	public int getMaxMana() {
		return maxMana;
	}

	/**
	 * Sets the max mana
	 * @param maxMana
	 */
	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	/**
	 * Returns the max hp
	 * @return hp
	 */
	public int getMaxHP() {
		return maxHP;
	}

	/**
	 * Sets the max hp
	 * @param maxHP
	 */
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	/**
	 * Returns the dodge
	 * @return dodge
	 */
	public int getDodge() {
		return dodge;
	}

	/**
	 * Sets the dodge
	 * @param dodge
	 */
	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	/**
	 * Returns the crit
	 * @return crit
	 */
	public int getCrit() {
		return crit;
	}

	/**
	 * Sets the crit
	 * @param crit
	 */
	public void setCrit(int crit) {
		this.crit = crit;
	}

	/**
	 * Returns the cold damage
	 * @return cold
	 */
	public int getCold() {
		return cold;
	}

	/**
	 * Sets amount of cold damage
	 * @param cold
	 */
	public void setCold(int cold) {
		this.cold = cold;
	}

	/**
	 * Returns the poison damage
	 * @return poison
	 */
	public int getPoison() {
		return poison;
	}

	/**
	 * Sets the amount of poison damage
	 * @param poison
	 */
	public void setPoison(int poison) {
		this.poison = poison;
	}

	/**
	 * Returns the fire damage
	 * @return fire
	 */
	public int getFire() {
		return fire;
	}

	/**
	 * Sets the amount of fire damage
	 * @param fire
	 */
	public void setFire(int fire) {
		this.fire = fire;
	}

	/**
	 * Returns the lightning damage
	 * @return lightning
	 */
	public int getLightning() {
		return lightning;
	}

	/**
	 * Sets the amount of lightning damage
	 * @param lightning
	 */
	public void setLightning(int lightning) {
		this.lightning = lightning;
	}

	/**
	 * Returns the health
	 * @return hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Sets the amount of health
	 * @param hp
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * Returns the physical damage
	 * @return dmg
	 */
	public int getDmg() {
		return dmg;
	}

	/**
	 * Sets the amount of physical damage
	 * @param dmg
	 */
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	/**
	 * Returns the armor/physical resistance
	 * @return arm
	 */
	public int getArm() {
		return arm;
	}

	/**
	 * Sets the amount of armor/physical resistance
	 * @param arm
	 */
	public void setArm(int arm) {
		this.arm = arm;
	}

	/**
	 * Returns the cold resistance
	 * @return cRes
	 */
	public int getcRes() {
		return cRes;
	}

	/**
	 * Sets the amount of cold resistance
	 * @param cRes
	 */
	public void setcRes(int cRes) {
		this.cRes = cRes;
	}

	/**
	 * Returns the poison resistance
	 * @return pRes
	 */
	public int getpRes() {
		return pRes;
	}

	/**
	 * Sets the amount of poison resistance
	 * @param pRes
	 */
	public void setpRes(int pRes) {
		this.pRes = pRes;
	}

	/**
	 * Returns the fire resistance
	 * @return fRes
	 */
	public int getfRes() {
		return fRes;
	}

	/**
	 * Sets the amount of fire resistance
	 * @param fRes
	 */
	public void setfRes(int fRes) {
		this.fRes = fRes;
	}

	/**
	 * Returns the lightning resistance
	 * @return lRes
	 */
	public int getlRes() {
		return lRes;
	}

	/**
	 * Sets the amount of lightning resistance
	 * @param lRes
	 */
	public void setlRes(int lRes) {
		this.lRes = lRes;
	}

	/**
	 * Returns the mana
	 * @return mana
	 */
	public int getMana() { return mana; }

	/**
	 * Sets the amount of mana
	 * @param mana
	 */
	public void setMana(int mana) { this.mana = mana; }
	
}
