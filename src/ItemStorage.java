import java.util.ArrayList;

public class ItemStorage {

	//Start
	private Player player;
	private Item item;
	public ItemStorage(Player player) {
		this.player = player;
	}
    //End
	
    //creates an array to put the items in
    public ArrayList<Item> itemStorages = new ArrayList<>();

    public ItemStorage() {
        PoisonWand poisonWand = new PoisonWand();
        MageBerserkerGloves mageBerserkerGloves = new MageBerserkerGloves();
        RejuvenatingChestplate rejuvenatingChestplate = new RejuvenatingChestplate();
        StonewallShield stonewallShield = new StonewallShield();
        ShortSwordOfPower shortSwordOfPower = new ShortSwordOfPower();
        BloodlustGauntlets bloodlustGauntlets = new BloodlustGauntlets();
        itemStorages.add(poisonWand);
        itemStorages.add(mageBerserkerGloves);
        itemStorages.add(rejuvenatingChestplate);
        itemStorages.add(stonewallShield);
        itemStorages.add(shortSwordOfPower);
        itemStorages.add(bloodlustGauntlets);
    }

    //picks a random item from the array and then removes it
    public Item getNewItem() {

        int rand = (int)(Math.random() * itemStorages.size());

        Item returnItem = itemStorages.get(rand);
        itemStorages.remove(rand);
        return returnItem;
    }
}
