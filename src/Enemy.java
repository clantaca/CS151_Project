
public class Enemy extends Character{
	
	/*
	 * power increases by 1 after each enemy is defeated
	 * when power is at 10, boss is summoned
	 */
	int power; 
	
	public void createRandomEnemy(int currentPower) {
		double ran = Math.random();
	}
	
	public void attackUser() {
		
	}
	
	public void enemyHasBeenDefeated() {
		
	}
	
	public String toString() {
		
		String result = "";
		
		result += "Power			: " + this.power + "\n";		
		result += "Health			: " + this.hp + "\n";
		result += "Physical Damage		: " + this.dmg + "\n";
		result += "Armor			: " + this.arm + "\n";
		result += "Cold Damage		: " + this.cold + "\n";
		result += "Poison Damage		: " + this.poison + "\n";
		result += "Fire Damage		: " + this.fire + "\n";
		result += "Lightning Damage	: " + this.lightning + "\n";
		result += "Cold Resistance		: " + this.cRes + "\n";
		result += "Poison Resistance	: " + this.pRes + "\n";
		result += "Fire Resistance		: " + this.fRes + "\n";
		result += "Lighning Resistance	: " + this.lRes + "\n";
		
		return result;
					
	}
	
	public static void main(String[] args) {
		
		Enemy firstEnemy = new Enemy();
		System.out.println(firstEnemy);
	}
	
}
