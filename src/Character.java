
abstract class Character {

	String name;//the entity's name
	int hp;		//health points
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

	//things would get confusing if they didn't have names
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCold() {
		return cold;
	}

	public void setCold(int cold) {
		this.cold = cold;
	}

	public int getPoison() {
		return poison;
	}

	public void setPoison(int poison) {
		this.poison = poison;
	}

	public int getFire() {
		return fire;
	}

	public void setFire(int fire) {
		this.fire = fire;
	}

	public int getLightning() {
		return lightning;
	}

	public void setLightning(int lightning) {
		this.lightning = lightning;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public int getArm() {
		return arm;
	}

	public void setArm(int arm) {
		this.arm = arm;
	}

	public int getcRes() {
		return cRes;
	}

	public void setcRes(int cRes) {
		this.cRes = cRes;
	}

	public int getpRes() {
		return pRes;
	}

	public void setpRes(int pRes) {
		this.pRes = pRes;
	}

	public int getfRes() {
		return fRes;
	}

	public void setfRes(int fRes) {
		this.fRes = fRes;
	}

	public int getlRes() {
		return lRes;
	}

	public void setlRes(int lRes) {
		this.lRes = lRes;
	}

}
