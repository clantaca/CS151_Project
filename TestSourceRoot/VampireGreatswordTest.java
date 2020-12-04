import coolGame.model.character.Player;
import coolGame.model.item.Item;
import coolGame.model.item.VampireGreatsword;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit testing of the VampireGreatsword class
 */
public class VampireGreatswordTest {

    VampireGreatsword vg;
    Item item;
    Player p;

    /**
     * Initializes SwiftShoes with item and sets the hp, damage, and crit of the player before testing
     */
    @BeforeEach
    public void setUp() {
        vg = new VampireGreatsword(item);
        p = new Player("TestPlayer");
        p.getMyStats().setHp(10);
        p.getMyStats().setDmg(10);
        p.getMyStats().setCrit(10);
    }

    /**
     * Player stats (hp, damage, crit) should be added with the minimum random int generated from the item stats
     */
    @Test
    public void testUpdatePlayerStats() {
        vg.updatePlayerStats(p);
        Assertions.assertTrue(p.getMyStats().getHp() >= 20, "Item stats should be added to hp");
        Assertions.assertTrue(p.getMyStats().getDmg() >= 20, "Item stats should be added to physical dmg");
        Assertions.assertTrue(p.getMyStats().getCrit() == 15, "Item stats should be added to crit");
    }

    /**
     * Tests the special effect of item; Player's physical special attack counter resets to 1
     */
    @Test
    public void testSpecialEffect() {
        vg.specialEffect(p);
        Assertions.assertTrue(p.physSpecialAttackCounter == 1, "Physical attack counter sets to 1");
    }
}
