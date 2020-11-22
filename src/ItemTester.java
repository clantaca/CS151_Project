import java.util.ArrayList;

public class ItemTester extends ItemStorage {
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
