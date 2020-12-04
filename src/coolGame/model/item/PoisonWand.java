package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Creates the Poison Wand under the Item Interface
 */
public class PoisonWand implements Item {

    //Constructors to be used in the class
    Item item;
    private Stats itemStats = new Stats();

    //String variables
    private String name = "Poisonous Wand";
    private String description = "At the start of every combat, attacks the enemy once automatically, with your poison attack.";

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
    public PoisonWand(Item item) {
        itemStats.setPoison(randomInt(8, 4));
        itemStats.setCold(randomInt(4, 2));
        itemStats.setLightning(randomInt(4, 2));
        itemStats.setFire(randomInt(4, 2));
        itemStats.setpRes(randomInt(10, 0));
        this.item = item;
    }

    /**
     * Method used to print out the stats of the item
     */
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getPoison() + " Poison Damage");
        System.out.println("+" + itemStats.getCold() + " Cold Damage");
        System.out.println("+" + itemStats.getLightning() + " Lightning Damage");
        System.out.println("+" + itemStats.getFire() + " Fire Damage");
        System.out.println("+" + itemStats.getpRes() + " Poison Resistance");
    }

    /**
     * Method used to update the player's stats with the item's stats
     *
     * @param player - the argument is used to retrieve the player class' stats
     */
    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setPoison(player.getMyStats().getPoison() + itemStats.getPoison());
        player.getMyStats().setCold(player.getMyStats().getCold() + itemStats.getCold());
        player.getMyStats().setLightning(player.getMyStats().getLightning() + itemStats.getLightning());
        player.getMyStats().setFire(player.getMyStats().getFire() + itemStats.getFire());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
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
     * Automatically attacks the enemy with a poison attack when the combat starts
     *
     * @param player - the argument is used to retrieve the player class' stats
     */
    @Override
    public void specialEffect(Player player) {
        if (player.getTurnCounter() == 0) {
            player.setDebuffOn(true);
            player.setDebuffCounter(player.getDebuffTurnCounter() + 1);
        }
    }
}
