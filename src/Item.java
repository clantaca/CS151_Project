import java.util.ArrayList;

public class Item extends Stats {

	//Start
	private Player player;
	public Item(Player player) {
		this.player = player;
	}
	//End
	
    //Variables that get overwritten in the other classes
    public void itemStats() { }
    public void specialEffect(Player player) { }
    public String getName() {
        return "item";
    }
    public void updatePlayerStats(Player player) { }
    //creates an array to put the items in
    private ArrayList<Item> Items = new ArrayList<>();
    public int weaponCounter;
    public int attackCounter;
    public int hitCounter;
    public int turnCounter;
    public int spellCounter = 1;
    public Item () {
        Item poisonWand = new PoisonWand();
        Item bloodlustGauntlets = new BloodlustGauntlets();

        Items.add(poisonWand);
        Items.add(bloodlustGauntlets);
    }

    //picks a random item from the array and then removes it
    public Item getNewItem() {

        int rand = (int)(Math.random() * Items.size());

        Item returnItem = Items.get(rand);
        Items.remove(rand);
        return returnItem;
    }
    public static void main(String[] args) {
        Item test = new Item();
        System.out.println(test.getNewItem());
    }
}
