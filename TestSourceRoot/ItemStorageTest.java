import coolGame.model.item.ItemStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit testing for the ItemStorage class
 */
public class ItemStorageTest {

    ItemStorage itemStorage;

    /**
     * Initializes itemStorage before testing
     */
    @BeforeEach
    public void setUp() {
        itemStorage = new ItemStorage();
    }

    /**
     * Once initialized, 9 items should be added into the arraylist of itemStorages
     */
    @Test
    public void testItemStorage() {
        Assertions.assertEquals(itemStorage.itemStorages.size(), 9, "Seven items should be placed into storage");
    }

    /**
     * Tests when a player gets a new item, the size of the arraylist of the itemStorage should decrease as the item is removed
     */
    @Test
    public void testGetNewItem() {
        itemStorage.getNewItem();
        Assertions.assertEquals(itemStorage.itemStorages.size(), 8, "An item should be removed");
        itemStorage.getNewItem();
        Assertions.assertEquals(itemStorage.itemStorages.size(), 7, "An item should be removed");
        itemStorage.getNewItem();
        Assertions.assertEquals(itemStorage.itemStorages.size(), 6, "An item should be removed");
        itemStorage.getNewItem();
        Assertions.assertEquals(itemStorage.itemStorages.size(), 5, "An item should be removed");
        itemStorage.getNewItem();
        Assertions.assertEquals(itemStorage.itemStorages.size(), 4, "An item should be removed");
        itemStorage.getNewItem();
        Assertions.assertEquals(itemStorage.itemStorages.size(), 3, "An item should be removed");
        itemStorage.getNewItem();
        Assertions.assertEquals(itemStorage.itemStorages.size(), 2, "An item should be removed");
        itemStorage.getNewItem();
        Assertions.assertEquals(itemStorage.itemStorages.size(), 1, "An item should be removed");
    }


}
