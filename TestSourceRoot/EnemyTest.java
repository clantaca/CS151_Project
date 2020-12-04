import coolGame.model.character.Enemy;
import coolGame.model.character.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit testing for the Enemy class
 */
public class EnemyTest {

    Enemy e;
    Player p;
    final int numEnemies = 5;
    final int pSummonBoss = 10;
    final int pIncreaseCommon = 4;
    final int pIncreaseBoss = 5;

    /**
     * Initializes enemy with power of 2 and sets stats of player before testing
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        e = new Enemy(2);
        p = new Player("Tester");
        p.getMyStats().setHp(100);
        p.getMyStats().setMana(10);
    }

    /**
     * Tests the dodge and crit of the generic enemy created which should both equal 10
     */
    @Test
    public void testCreateGenericEnemy() {
        assertEquals(e.getMyStats().getDodge(), 10, "Enemy dodge is set at 10");
        assertEquals(e.getMyStats().getCrit(), 10, "Enemy crit is set at 10");
    }


    /**
     * Tests when the enemy attacks user which the player should loser health if not dodged or blocked;
     * Tests when the player blocks enemy, player should gain +5 mana
     */
    @Test
    public void testAttackUser() {

        assertTrue(p.getMyStats().getHp() == 100, "Player health should be at 100");

        e.attackUser(p);
        assertTrue(p.getMyStats().getHp() <= 100, "Player health should be lowered");

        p.blockEnemy(e);
        e.attackUser(p);
        assertTrue(p.getMyStats().getMana() == 15, "Player blocked; should gain +5 mana");
    }
}


