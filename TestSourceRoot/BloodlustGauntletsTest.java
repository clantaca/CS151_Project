import coolGame.model.Stats;
import coolGame.model.character.Player;
import coolGame.model.item.BloodlustGauntlets;
import coolGame.model.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit testing for BloodlustGauntlets class
 */
public class BloodlustGauntletsTest {

    BloodlustGauntlets bg;
    Item item;
    Player p;
    Stats itemStats;

    /**
     * Initializes BloodlustGauntlets with item and sets the dmg, hp, armor, and resistances of player before testing
     */
    @BeforeEach
    public void setUp() {
        itemStats = new Stats();
        bg = new BloodlustGauntlets(item);
        p = new Player("TestPlayer");
        p.getMyStats().setDmg(10);
        p.getMyStats().setHp(10);
        p.getMyStats().setMaxHP(10);
        p.getMyStats().setArm(10);
        p.getMyStats().setcRes(10);
        p.getMyStats().setlRes(10);
        p.getMyStats().setpRes(10);
        p.getMyStats().setfRes(10);
    }

    /**
     * Player stats (dmg, hp, arm, resistances) should be added with the minimum random int generated from the item stats
     */
    @Test
    public void testUpdatePlayerStats() {
        bg.updatePlayerStats(p);
        Assertions.assertTrue(p.getMyStats().getDmg() > 10, "Item stats should be added to dmg");
        Assertions.assertTrue(p.getMyStats().getHp() <= 10, "Item stats should be added to hp");
        Assertions.assertTrue(p.getMyStats().getMaxHP() <= 10, "Item stats should be added to maxhp");
        Assertions.assertTrue(p.getMyStats().getArm() <= 10, "Item stats should be added to arm");
        Assertions.assertTrue(p.getMyStats().getcRes() <= 10, "Item stats should be added to cRes");
        Assertions.assertTrue(p.getMyStats().getlRes() <= 10, "Item stats should be added to lRes");
        Assertions.assertTrue(p.getMyStats().getpRes() <= 10, "Item stats should be added to pRes");
        Assertions.assertTrue(p.getMyStats().getfRes() <= 10, "Item stats should be added to fRes");
    }

    /**
     * Tests special effect of item; player should have 50% of max
     */
    @Test
    public void testSpecialEffect() {
        bg.specialEffect(p);
        Assertions.assertEquals(p.getMyStats().getDmg(), 10, "50% of max");
    }
}

