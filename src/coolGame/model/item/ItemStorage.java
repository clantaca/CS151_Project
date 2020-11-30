package coolGame.model.item;

import java.util.ArrayList;

public class ItemStorage {

    //creates an array to put the items in
    public ArrayList<Item> itemStorages = new ArrayList<>();

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
        itemStorages.add(poisonWand);
        itemStorages.add(mageBerserkerGloves);
        itemStorages.add(rejuvenatingChestplate);
        itemStorages.add(stonewallShield);
        itemStorages.add(shortSwordOfPower);
        itemStorages.add(bloodlustGauntlets);
        itemStorages.add(swiftShoes);
    }

    //picks a random item from the array, updates the player stats with the items' stats and removes it from the array
    public Item getNewItem() {

        int rand = (int)(Math.random() * itemStorages.size());
        
        Item returnItem = itemStorages.get(rand);
        itemStorages.remove(rand);
        return returnItem;
    }
}
