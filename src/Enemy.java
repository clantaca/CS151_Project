
public class Enemy extends Character{
	
	/*
	 * power increases by 1 after each enemy is defeated
	 * when power is at 10, boss is summoned
	 */
	int power; 
	
	public void createRandomEnemy(int currentPower) {
		
	}
	
	public void attackUser() {
		
	}
	
	public void enemyHasBeenDefeated() {
		
	}
	
	public String toString() {
		
		String result = "";
		
		result += "\nHealth				: " + this.hp;
		result += "\nPhysical Damage	: " + this.dmg;
		result += "\nArmor				: " + this.arm;
		result += "\nCold Damage		: " + this.cold;
		result += "\nPoison Damage		: " + this.poison;
		result += "\nFire Damage		: " + this.fire;
		result += "\nLightning Damage	: " + this.lightning;
		result += "\nCold Resistance	: " + this.cRes;
		result += "\nPoison Resistance	: " + this.pRes;
		result += "\nFire Resistance	: " + this.fRes;
		result += "\nLighning Resistance: " + this.lRes;
		
		return result;
				
				
	}
	
	public static void main(String[] args) {
		
		Enemy firstEnemy = new Enemy();
		System.out.println(firstEnemy);
	}
	
}
