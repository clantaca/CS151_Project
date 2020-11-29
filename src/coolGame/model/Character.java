package coolGame.model;

public class Character {

	private String name;//the entity's name
	private Stats myStats = new Stats();

	//things would get confusing if they didn't have names
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Stats getMyStats() {
		return myStats;
	}

	public void setMyStats(Stats myStats) {
		this.myStats = myStats;
	}
	
	

}
