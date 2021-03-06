import coolGame.model.Stats;
import coolGame.model.character.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit testing of the Character class
 */
public class CharacterTest {

    private String name;
    Character c;
    Stats s;

    /**
     * Initializes a character named "TestPlayer" before testing
     */
    @BeforeEach
    public void setUp() {
        c = new Character();
        s = new Stats();
        name = "TestPlayer";
    }

    /**
     * Tests the player's name; should return "TestPlayer" as name
     */
    @Test
    public void testSetName() {
        c.setName("TestPlayer");
        Assertions.assertEquals(c.getName(),"TestPlayer", "Name should be TestPlayer");
    }

    /**
     * Tests the stats of the player; should be all 0 since the stats are not initialized
     */
    @Test
    public void testSetStats() {
        c.setMyStats(s);
        Assertions.assertEquals(c.getMyStats().getHp(), 0 ,"Stats should not be initialized");
        Assertions.assertEquals(c.getMyStats().getDmg(), 0 ,"Stats should not be initialized");
        Assertions.assertEquals(c.getMyStats().getMana(), 0, "Stats should not be initialized");
        Assertions.assertEquals(c.getMyStats().getCold(), 0, "Stats should not be initialized");
        Assertions.assertEquals(c.getMyStats().getcRes(), 0, "Stats should not be initialized");
    }
}

