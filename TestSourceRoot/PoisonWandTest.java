import coolGame.model.character.Player;
import coolGame.model.item.Item;
import coolGame.model.item.PoisonWand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit testing of the PoisonWand class
 */
public class PoisonWandTest {

    PoisonWand pw;
    Item item;
    Player p;

    /**
     * Initializes PoisonWand with item and sets the damages and resistance of the player before testing
     */
    @BeforeEach
    public void setUp() {
        pw = new PoisonWand(item);
        p = new Player("TestPlayer");
        p.getMyStats().setCold(10);
        p.getMyStats().setFire(10);
        p.getMyStats().setPoison(10);
        p.getMyStats().setLightning(10);
        p.getMyStats().setpRes(10);
    }


    /**
     * Player stats (damages, resistance) should be added with the minimum random int generated from the item stats
     */
    @Test
    public void testUpdatePlayerStats() {
        pw.updatePlayerStats(p);
        Assertions.assertTrue(p.getMyStats().getCold() >= 12, "Item stats should be added to cold dmg");
        Assertions.assertTrue(p.getMyStats().getFire() >= 12, "Item stats should be added to fire dmg");
        Assertions.assertTrue(p.getMyStats().getLightning() >= 12, "Item stats should be added to lightning dmg");
        Assertions.assertTrue(p.getMyStats().getPoison() >= 14, "Item stats should be added to poison dmg");
        Assertions.assertTrue(p.getMyStats().getpRes() >= 10, "Item stats should be added to pRes");
    }


    /**
     * Tests the special effect of item; damage should be 50% of max
     */
    @Test
    public void testSpecialEffect() {
        pw.specialEffect(p);
        Assertions.assertEquals(p.getMyStats().getDmg(), 20, "50% of max");
    }
}

