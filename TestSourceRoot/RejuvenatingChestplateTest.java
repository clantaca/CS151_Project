import coolGame.model.character.Player;
import coolGame.model.item.Item;
import coolGame.model.item.RejuvenatingChestplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit testing of the RejuvenatingChestplate class
 */
public class RejuvenatingChestplateTest {


    RejuvenatingChestplate rc;
    Item item;
    Player p;

    /**
     * Initializes RejuvenatingChestplate with item and sets the hp, armor, and resistances of the player before testing
     */
    @BeforeEach
    public void setUp() {
        rc = new RejuvenatingChestplate(item);
        p = new Player("TestPlayer");
        p.getMyStats().setHp(10);
        p.getMyStats().setArm(10);
        p.getMyStats().setcRes(10);
        p.getMyStats().setlRes(10);
        p.getMyStats().setpRes(10);
        p.getMyStats().setfRes(10);
    }

    /**
     * Player stats (hp, armor, resistances) should be added with the minimum random int generated from the item stats
     */
    @Test
    public void testUpdatePlayerStats() {
        rc.updatePlayerStats(p);
        Assertions.assertTrue(p.getMyStats().getHp() >= 35, "Item stats should be added to hp");
        Assertions.assertTrue(p.getMyStats().getArm() >= 20, "Item stats should be added to arm");
        Assertions.assertTrue(p.getMyStats().getcRes() >= 20, "Item stats should be added to cRes");
        Assertions.assertTrue(p.getMyStats().getlRes() >= 20, "Item stats should be added to lRes");
        Assertions.assertTrue(p.getMyStats().getpRes() >= 20, "Item stats should be added to pRes");
        Assertions.assertTrue(p.getMyStats().getfRes() >= 20, "Item stats should be added to fRes");
    }

    /**
     * Tests the special effect of item; damage should be 50% of max
     */
    @Test
    public void testSpecialEffect() {
        rc.specialEffect(p);
        Assertions.assertEquals(p.getMyStats().getDmg(), 20, "50% of max");
    }
}

