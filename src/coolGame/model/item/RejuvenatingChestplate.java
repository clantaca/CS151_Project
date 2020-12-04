package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Creates the Rejuvenating Chestplate under the Item Interface
 */
public class RejuvenatingChestplate extends Stats implements Item {

    //Constructors to be used in the class
    Item item;
    private Stats itemStats = new Stats();

    //String variables
    private String name = "Rejuvenating Chestplate";
    private String description = "Every 4th hit you take from the enemy, heal for 20.";

    /**
     * Random number generator method
     *
     * @param max - the upper limit of the random function
     * @param min - the lower limit of the random function
     * @return - retrieves a random number between the max and min
     */
    public int randomInt(int max, int min) {
        return (int) (Math.random() * (max - min)) + min;
    }

    /**
     * Adds stats to the item
     *
     * @param item - accepts an item to be used for the decorator pattern
     */
    public RejuvenatingChestplate(Item item) {
        itemStats.setHp(randomInt(50, 25));
        itemStats.setArm(randomInt(20, 10));
        itemStats.setcRes(randomInt(20, 10));
        itemStats.setfRes(randomInt(20, 10));
        itemStats.setpRes(randomInt(20, 10));
        itemStats.setlRes(randomInt(20, 10));
        this.item = item;
    }

    /**
     * Used to get the name of the item
     *
     * @return returns the item decorator's method and the name of the item
     */
    @Override
    public String getName() {
        return item.getName() + name;
    }

    /**
     * Used to get the description of the item's special effect
     *
     * @return returns the description of the item and the item decorator's method
     */
    @Override
    public String getDescription() {
        return description + item.getDescription();
    }

    /**
     * Method used to update the player's stats with the item's stats
     *
     * @param player - the argument is used to retrieve the player class' stats
     */
    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
        player.getMyStats().setMaxHP(player.getMyStats().getMaxHP() + itemStats.getHp());
        player.getMyStats().setcRes(player.getMyStats().getcRes() + itemStats.getcRes());
        player.getMyStats().setfRes(player.getMyStats().getfRes() + itemStats.getfRes());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
    }

    /**
     * Method used to print out the stats of the item
     */
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getHp() + " Health");
        System.out.println("+" + itemStats.getArm() + " Armor");
        System.out.println("+" + itemStats.getcRes() + " Cold Resistance");
        System.out.println("+" + itemStats.getfRes() + " Fire Resistance");
        System.out.println("+" + itemStats.getlRes() + " Lightning Resistance");
        System.out.println("+" + itemStats.getpRes() + " Poison Resistance");
    }

    /**
     * Every 4th hit will heal the player but won't make it go over his max hp
     *
     * @param player - the argument is used to retrieve the player class' stats
     */
    @Override
    public void specialEffect(Player player) {
        if (player.getHitsTakenCounter() + 1 % 4 == 0) {
            player.getMyStats().setHp((player.getMyStats().getHp() + 20));
            System.out.println("Player heals for 20 health from Rejuvenating Chestplate");
            if (player.getMyStats().getHp() > player.getMyStats().getMaxHP()) {
                player.getMyStats().setHp(player.getMyStats().getMaxHP());
            }
        }
    }
}
