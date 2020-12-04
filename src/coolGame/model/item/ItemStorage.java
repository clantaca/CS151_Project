package coolGame.model.item;

import java.util.ArrayList;

/**
 * This is the storage class of all the objects from all the item classes underneath the item interface
 */
public class ItemStorage {

    //Creates an array to put the items in
    public ArrayList<Item> itemStorages = new ArrayList<>();

    /**
     * uses the item storage arraylist from above to add all the items into it
     */
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
        itemStorages.add(swiftShoes);
        itemStorages.add(lightningStaff);
        itemStorages.add(vampireGreatsword);
    }

    /**
     * Method is used to pick a random item from the itemStorages array,
     * return that item, and then delete the item from the array.
     *
     * @return - returns an Item object from the itemStorages arraylist
     */
    public Item getNewItem() {

        int rand = (int) (Math.random() * itemStorages.size());

        Item returnItem = itemStorages.get(rand);
        itemStorages.remove(rand);
        return returnItem;
    }
}
