import coolGame.model.character.Player;
import coolGame.model.item.Item;
import coolGame.model.item.MageBerserkerGloves;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit testing for the MageBerserkerGloves class
 */
public class MageBerserkerGlovesTest {

    MageBerserkerGloves mg;
    Item item;
    Player p;

    /**
     * Initializes MageBerserkerGloves with item and sets the hp, arm, resistances, and damages of the player before testing
     */
    @BeforeEach
    public void setUp() {
        mg = new MageBerserkerGloves(item);
        p = new Player("TestPlayer");
        p.getMyStats().setHp(10);
        p.getMyStats().setMaxHP(10);
        p.getMyStats().setMana(10);
        p.getMyStats().setArm(10);
        p.getMyStats().setcRes(10);
        p.getMyStats().setlRes(10);
        p.getMyStats().setpRes(10);
        p.getMyStats().setfRes(10);
        p.getMyStats().setCold(10);
        p.getMyStats().setFire(10);
        p.getMyStats().setPoison(10);
        p.getMyStats().setLightning(10);
    }


    /**
     * Player stats (dmg, hp, arm, resistances, damages) should be added with the minimum random int generated from the item stats
     */
    @Test
    public void testUpdatePlayerStats() {
        mg.updatePlayerStats(p);
        Assertions.assertTrue(p.getMyStats().getHp() > 25, "Item stats should be added to hp");
        Assertions.assertTrue(p.getMyStats().getMaxHP() > 25, "Item stats should be added to maxhp");
        Assertions.assertTrue(p.getMyStats().getMana() == 15, "Item stats should be added to mana");
        Assertions.assertTrue(p.getMyStats().getArm() >= 13, "Item stats should be added to arm");
        Assertions.assertTrue(p.getMyStats().getcRes() >= 13, "Item stats should be added to cRes");
        Assertions.assertTrue(p.getMyStats().getlRes() >= 13, "Item stats should be added to lRes");
        Assertions.assertTrue(p.getMyStats().getpRes() >= 13, "Item stats should be added to pRes");
        Assertions.assertTrue(p.getMyStats().getfRes() >= 13, "Item stats should be added to fRes");
        Assertions.assertTrue(p.getMyStats().getCold() >= 13, "Item stats should be added to cold dmg");
        Assertions.assertTrue(p.getMyStats().getFire() >= 13, "Item stats should be added to fire dmg");
        Assertions.assertTrue(p.getMyStats().getLightning() >= 13, "Item stats should be added to lightning dmg");
        Assertions.assertTrue(p.getMyStats().getPoison() >= 13, "Item stats should be added to poison dmg");
    }


    /**
     * Tests the special effect of item; physical damage should be 50% of max
     */
    @Test
    public void testSpecialEffect() {
        mg.specialEffect(p);
        Assertions.assertEquals(p.getMyStats().getDmg(), 20, "50% of max");
    }
}

