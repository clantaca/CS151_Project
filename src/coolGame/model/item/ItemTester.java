package coolGame.model.item;

import java.util.ArrayList;

/**
 * Tester class for Items
 */
public class ItemTester extends ItemStorage {
    /**
     * Main method that adds all obtained items into an arraylist
     * @param args
     */
    public static void main(String[] args) {
        ItemStorage test = new ItemStorage();
        ArrayList<Item> testArray = new ArrayList<>();
        testArray.add(test.getNewItem());
        testArray.add(test.getNewItem());
        testArray.add(test.getNewItem());
        testArray.add(test.getNewItem());
        testArray.add(test.getNewItem());
        testArray.add(test.getNewItem());
        for (Item i : testArray) {
            System.out.println(i.getName());
            i.itemStats();
            System.out.println(i.getDescription());
            System.out.println();
        }
    }
}
