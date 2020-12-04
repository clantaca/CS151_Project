package coolGame.model.item;

import java.util.ArrayList;

/**
 * Model class that holds an arraylist of items into a storage
 */
public class ItemStorage {

    //creates an array to put the items in
    public ArrayList<Item> itemStorages = new ArrayList<>();

    /**
     * Constructor that initializes all the items and adds them to the storage
     */
	//creates the item storage array list with all the items
    public ItemStorage() {
        Item itemDec = new ItemDec();
        Item poisonWand = new PoisonWand(itemDec);
        Item mageBerserkerGloves = new MageBerserkerGloves(itemDec);
        Item rejuvenatingChestplate = new RejuvenatingChestplate(itemDec);
        Item stonewallShield = new StonewallShield(itemDec);
        Item shortSwordOfPower = new ShortSwordOfPower(itemDec);
        Item bloodlustGauntlets = new BloodlustGauntlets(itemDec);
        Item swiftShoes = new SwiftShoes(itemDec);
        Item lightningStaff = new LightningStaff(itemDec);
        Item vampireGreatsword = new VampireGreatsword(itemDec);
        itemStorages.add(poisonWand);
        itemStorages.add(mageBerserkerGloves);
        itemStorages.add(rejuvenatingChestplate);
        itemStorages.add(stonewallShield);
        itemStorages.add(shortSwordOfPower);
        itemStorages.add(bloodlustGauntlets);
//        itemStorages.add(swiftShoes);
        itemStorages.add(lightningStaff);
        itemStorages.add(vampireGreatsword);
    }

    /**
     * Returns a random item from the array of items and removes it from the array, updating the player stats with it
     * @return
     */
    //picks a random item from the array, updates the player stats with the items' stats and removes it from the array
    public Item getNewItem() {

        int rand = (int)(Math.random() * itemStorages.size());
        
        Item returnItem = itemStorages.get(rand);
        itemStorages.remove(rand);
        return returnItem;
    }
}
