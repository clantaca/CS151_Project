import java.util.ArrayList;

public class Item extends Stats {

    //Variables that get overwritten in the other classes
    public void itemStats() { }
    public void specialEffect() { }
    public String getName() {
        return "item";
    }
    //creates an array to put the items in
    private ArrayList<Class<? extends Item>> Items = new ArrayList<>();
    public int weaponCounter;
    public int attackCounter;
    public int hitCounter;
    public int turnCounter;
    public int spellCounter;
    public Item () {
        Items.add(PoisonWand.class);
        Items.add(ShortSwordOfPower.class);
        Items.add(StonewallShield.class);
        Items.add(RejuvenatingChestplate.class);
    }

    //picks a random item from the array and then removes it
    public Class<? extends Item> getNewItem() {

        int rand = (int)(Math.random() * Items.size());

        Class<? extends Item> returnItem = Items.get(rand);
        Items.remove(rand);
        return returnItem;
    }
    public static void main(String[] args) {
        Item test = new Item();
        System.out.println(test.getNewItem());
    }
}
