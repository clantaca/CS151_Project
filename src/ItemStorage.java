import java.util.ArrayList;

public class ItemStorage {

    //creates an array to put the items in
    public ArrayList<Item> itemStorages = new ArrayList<>();

	//creates the item storage array list with all the items
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

    //picks a random item from the array, updates the player stats with the items' stats and removes it from the array
    public Item getNewItem() {

        int rand = (int)(Math.random() * itemStorages.size());

        Item returnItem = itemStorages.get(rand);
        itemStorages.remove(rand);
        return returnItem;
    }
}
