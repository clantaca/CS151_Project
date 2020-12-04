import coolGame.model.character.Enemy;
import coolGame.model.character.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit testing of the Player class
 */
public class PlayerTest {
    Player p;
    Enemy e;

    /**
     * Initializes all the player's stats and enemy with a power of 1 before testing
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        p = new Player("Test Player");
        e = new Enemy(1);
        e.getMyStats().setHp(100);
        p.getMyStats().setHp(100);
        p.getMyStats().setMaxHP(100);
        p.getMyStats().setDmg(20);
        p.getMyStats().setCold(19);
        p.getMyStats().setPoison(18);
        p.getMyStats().setFire(17);
        p.getMyStats().setLightning(16);
        p.getMyStats().setArm(5);
        p.getMyStats().setMana(15);
        p.getMyStats().setMaxMana(20);
        p.getMyStats().setcRes(5);
        p.getMyStats().setpRes(4);
        p.getMyStats().setfRes(3);
        p.getMyStats().setlRes(2);
    }

    /**
     * Player stats (dmg, hp, arm, resistances, damages) should be initialized with specified amount in the setup
     */
    @Test
    public void testPlayer() {
        assertEquals(p.getMyStats().getHp(), 100, "Player starts with 100 hp");
        assertEquals(p.getMyStats().getMaxHP(), 100, "Player max health is 100");
        assertEquals(p.getMyStats().getDmg(), 20, "Player does 5 physical dmg");
        assertEquals(p.getMyStats().getCold(), 19, "Player does 19 cold dmg");
        assertEquals(p.getMyStats().getPoison(), 18, "Player does 18 poison dmg");
        assertEquals(p.getMyStats().getFire(), 17, "Player does 17 poison dmg");
        assertEquals(p.getMyStats().getLightning(), 16, "Player does 16 lightning dmg");
        assertEquals(p.getMyStats().getArm(), 5, "Player starts with 5 armor");
        assertEquals(p.getMyStats().getMana(), 15, "Player starts with 20 mana");
        assertEquals(p.getMyStats().getMaxMana(), 20, "Player max mana is 20");
        assertEquals(p.getMyStats().getcRes(), 5, "Player has 5 cold resistance");
        assertEquals(p.getMyStats().getpRes(), 4, "Player has 4 poison resistance");
        assertEquals(p.getMyStats().getfRes(), 3, "Player has 3 fire resistance");
        assertEquals(p.getMyStats().getlRes(), 2, "Player has 2 cold resistance");
    }


    /**
     * Tests physical attack where the enemy should lose health if not dodged
     */
    @Test
    public void testPhysicalAttack() {
        p.physicalAttack(e);
        assertTrue(e.getMyStats().getHp() <= 100, "Enemy's health should be decreased");
    }

    /**
     * Tests cold spell where the enemy should lose health if not dodged
     */
    @Test
    public void testColdAttack() {
        p.coldAttack(e);
        assertTrue(e.getMyStats().getHp() <= 100, "Player uses cold spell; enemy loses hp");
    }

    /**
     * Tests poison spell where the enemy should lose 10 health on the next attack as a poison debuff is applied
     */
    @Test
    public void testPoisonAttack() {
        p.poisonAttack(e);
        p.physicalAttack(e);
        assertTrue(e.getMyStats().getHp() <= 100, "Player uses poison spell; enemy loses hp");
    }

    /**
     * Tests fire spell where the enemy should lose health if not dodged
     */
    @Test
    public void testFireAttack() {
        p.fireAttack(e);
        assertTrue(e.getMyStats().getHp() <= 100, "Player uses fire spell; enemy loses hp");
    }

    /**
     * Tests lightning spell where the enemy should lose health if not dodged
     */
    @Test
    public void testLightningAttack() {
        p.lightningAttack(e);
        assertTrue(e.getMyStats().getHp() <= 100, "Player uses lightning spell; enemy loses hp");
    }


    /**
     * Tests when the player uses a spell; player should lose 5 mana as a result
     */
    @Test
    public void testUseSpell() {
        p.useSpell();
        assertEquals(p.getMyStats().getMana(), 10, "Mana should decrease by 5");
    }

    /**
     * Tests when the player's mana increases
     */
    @Test
    public void testIncreaseMana() {
        p.increaseMana(2);
        assertEquals(p.getMyStats().getMana(), 17, "Mana should increase by 2");
    }

    /**
     * Tests when the player blocks the enemy; player should get 5 additional mana as a result
     */
    @Test
    public void testEnemyBlocked() {
        Player p = new Player("TestPlayer");
        p.blockEnemy(e);
        assertTrue(p.blockEnemy(e), "Player should block enemy");
        assertEquals(p.getMyStats().getMana(), 20, "Mana should increase");
    }
}
