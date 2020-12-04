package coolGame.model.character;

import coolGame.model.Stats;

/**
 * Model that constructs a character with a name and stats
 */
public class Character {

    private String name;//the entity's name
    private Stats myStats = new Stats();

    /**
     * Returns name of character
     *
     * @return name
     */
    //things would get confusing if they didn't have names
    public String getName() {
        return name;
    }

    /**
     * Sets the name of character
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the stats of the character
     *
     * @return myStats
     */
    public Stats getMyStats() {
        return myStats;
    }

    /**
     * Sets the stats of the character
     *
     * @param myStats
     */
    public void setMyStats(Stats myStats) {
        this.myStats = myStats;
    }


}
