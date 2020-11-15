import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    @Test
    public void testEnemyBlocked() {
        Player p = new Player("TestPlayer");
        assertTrue(p.blockEnemy(), "Player should block enemy");
    }

    @Test
    public void testUseSpell() {
        Player p = new Player("TestPlayer");
        assertTrue(p.useSpell(), "Scroll should be used by player");
    }
    
    @Test
    public void testHp() {
        Player p = new Player("TestPlayer");
        assertEquals(p.getMyStats().getHp(), 100, "Hp default value should be equal to 100");
    }

    @Test
    public void testMana() {
        Player p = new Player("TestPlayer");
        assertEquals(p.getMyStats().getMana(), 20, "Mana default value should be equal to 20");
    }

    @Test
    public void testArmor() {
        Player p = new Player("TestPlayer");
        assertEquals(p.getMyStats().getArm(), 5, "Armor default value should be equal to 5");
    }

    @Test
    public void testDmg() {
        Player p = new Player("TestPlayer");
        assertEquals(p.getMyStats().getDmg(), 5, "Damage default value should be equal to 5");
    }

    @Test
    public void testSpecialAttack() {
        Player p = new Player("TestPlayer");
        assertEquals(p.getSpecialAttack(), 0, "Special Attack default value should be equal to 0");
    }
}

