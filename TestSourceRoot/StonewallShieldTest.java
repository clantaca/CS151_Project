import coolGame.model.character.Player;
import coolGame.model.item.Item;
import coolGame.model.item.StonewallShield;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit testing of the StonewallShield class
 */
public class StonewallShieldTest {
    StonewallShield ss;
    Item item;
    Player p;

    /**
     * Initializes StonewallShield with item and sets the hp, armor, damage, and resistances of the player before testing
     */
    @BeforeEach
    public void setUp() {
        ss = new StonewallShield(item);
        p = new Player("TestPlayer");
        p.getMyStats().setHp(10);
        p.getMyStats().setDmg(10);
        p.getMyStats().setArm(10);
        p.getMyStats().setcRes(10);
        p.getMyStats().setlRes(10);
        p.getMyStats().setpRes(10);
        p.getMyStats().setfRes(10);
    }

    /**
     * Player stats (hp, armor, dmg, resistances) should be added with the minimum random int generated from the item stats
     */
    @Test
    public void testUpdatePlayerStats() {
        ss.updatePlayerStats(p);
        Assertions.assertTrue(p.getMyStats().getHp() >= 30, "Item stats should be added to hp");
        Assertions.assertTrue(p.getMyStats().getDmg() >= 15, "Item stats should be added to physical dmg");
        Assertions.assertTrue(p.getMyStats().getArm() >= 15, "Item stats should be added to arm");
        Assertions.assertTrue(p.getMyStats().getcRes() >= 15, "Item stats should be added to cRes");
        Assertions.assertTrue(p.getMyStats().getlRes() >= 15, "Item stats should be added to lRes");
        Assertions.assertTrue(p.getMyStats().getpRes() >= 15, "Item stats should be added to pRes");
        Assertions.assertTrue(p.getMyStats().getfRes() >= 15, "Item stats should be added to fRes");

    }

    /**
     * Test the special effect of item; player's shield should be on if requirement met
     */
    @Test
    public void testSpecialEffect() {
        ss.specialEffect(p);
        Assertions.assertEquals(p.isShieldEffect(), false, "Shield effect should be off, player does not meet requirements");
    }
}
