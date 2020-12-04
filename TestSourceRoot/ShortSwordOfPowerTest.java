import coolGame.model.character.Player;
import coolGame.model.item.Item;
import coolGame.model.item.ShortSwordOfPower;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit testing of the ShortSwordofPower class
 */
public class ShortSwordOfPowerTest {

    ShortSwordOfPower sp;
    Item item;
    Player p;

    /**
     * Initializes ShortSwordofPower with item and sets the hp, dmg, crit, and armor of the player before testing
     */
    @BeforeEach
    public void setUp() {
        sp = new ShortSwordOfPower(item);
        p = new Player("TestPlayer");
        p.getMyStats().setHp(10);
        p.getMyStats().setDmg(10);
        p.getMyStats().setCrit(10);
        p.getMyStats().setArm(10);
    }

    /**
     * Player stats (hp, dmg, crit, armor) should be added with the minimum random int generated from the item stats
     */
    @Test
    public void testUpdatePlayerStats() {
        sp.updatePlayerStats(p);
        Assertions.assertTrue(p.getMyStats().getHp() >= 20, "Item stats should be added to hp");
        Assertions.assertTrue(p.getMyStats().getDmg() >= 14, "Item stats should be added to physical dmg");
        Assertions.assertTrue(p.getMyStats().getCrit() == 25, "Item stats should be added to crit");
        Assertions.assertTrue(p.getMyStats().getArm() >= 15, "Item stats should be added to arm");

    }

    /**
     * Tests special effect of item; player does 15 bonus damage if requirement met
     */
    @Test
    public void testSpecialEffect() {
        sp.specialEffect(p);
        Assertions.assertEquals(p.bonusDmg, 0, "Player should not do 15 bonus damage; does not meet requirement");
    }
}

