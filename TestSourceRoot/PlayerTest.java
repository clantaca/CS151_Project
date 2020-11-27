/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    Player p;
    Enemy e;

    @BeforeEach
    public void setUp() throws Exception {
        p = new Player("Test Player");
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

    */
/*
    //Not sure if we should test methods whose behavior is random each time its called

    @Test
    public int testRandomizeDmg() {
        p.randomizeDmg("Physical");
    }

    @Test
    public void testPhysicalAttack() {
        p.physicalAttack(e);
    }

    @Test
    public void testColdAttack() {
        p.coldAttack(e);
    }

    @Test
    public void testPoisonAttack() {
        p.poisonAttack(e);
    }

    @Test
    public void testFireAttack() {
        p.fireAttack(e);
    }

    @Test
    public void testLightningAttack() {
        p.lightningAttack(e);
    }
    *//*


    @Test
    public void testUseSpell() {
        p.useSpell();
        assertEquals(p.getMyStats().getMana(), 10, "Mana should decrease by 5");
    }

    @Test
    public void testIncreaseMana() {
        p.increaseMana(2);
        assertEquals(p.getMyStats().getMana(), 17, "Mana should increase by 2");
    }
    @Test
    public void testEnemyBlocked() {
        Player p = new Player("TestPlayer");
        p.blockEnemy();
        assertTrue(p.blockEnemy(), "Player should block enemy");
        assertEquals(p.getMyStats().getMana(), 20, "Mana should increase");
    }
}*/
