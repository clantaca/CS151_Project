import java.util.ArrayList;
import java.util.Random;

public class Item extends Stats {

    //creates an array to put the items in
    private ArrayList<String> Items = new ArrayList<String>();

    public Item() {
        Items.add("Helmet");
        Items.add("Amulet");
        Items.add("Ring");
    }

    //picks a random item from the array and then removes it
    public String getNewItem() {

        int rand = (int)(Math.random() * Items.size() - 1);

        String returnItem = Items.get(rand);
        Items.remove(rand);
        return returnItem;
    }

    //adds the stats to the item
    public Item getItemStats (String item) {
        Item returnItem = new Item();
        Random rand = new Random();
        //case break statements for items and random stats
        switch (item) {
            case "Helmet":
                returnItem.setHp(rand.nextInt(5));
                returnItem.setArm(rand.nextInt(5));
                returnItem.setcRes(rand.nextInt(5));
                returnItem.setfRes(rand.nextInt(5));
                returnItem.setlRes(rand.nextInt(5));
                returnItem.setpRes(rand.nextInt(5));
                break;
            case "Amulet":
                returnItem.setPoison(rand.nextInt(3));
                returnItem.setLightning(rand.nextInt(3));
                returnItem.setCold(rand.nextInt(3));
                returnItem.setFire(rand.nextInt(3));
                returnItem.setDmg(rand.nextInt(3));
                break;
            case "Ring":
                returnItem.setcRes(rand.nextInt(3));
                returnItem.setfRes(rand.nextInt(3));
                returnItem.setlRes(rand.nextInt(3));
                returnItem.setpRes(rand.nextInt(3));
                returnItem.setArm(rand.nextInt(3));
                returnItem.setPoison(rand.nextInt(1));
                returnItem.setLightning(rand.nextInt(1));
                returnItem.setCold(rand.nextInt(1));
                returnItem.setFire(rand.nextInt(1));
                returnItem.setDmg(rand.nextInt(1));
                break;
        }
        return returnItem;
        }
    //test
    public static void main(String[] args) {
        Item test = new Item();
        String test1 = test.getNewItem();
        System.out.println(test.getNewItem());
        int n = test.getPoison();
        System.out.println(test.getfRes());
        System.out.println(n);
    }
}
