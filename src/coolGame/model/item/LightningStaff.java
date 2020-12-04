package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Creates the Lightning Staff under the Item Interface
 */
public class LightningStaff implements Item {

    //Constructors to be used in the class
    Item item;
    private Stats itemStats = new Stats();

    //String variables
    private String name = "Lightning Staff";
    private String description = "Increases the lightning debuff that the player has to 40% increased damage taken.";

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
    public LightningStaff(Item item) {
        itemStats.setLightning(randomInt(12, 6));
        itemStats.setHp(randomInt(30, 10));
        itemStats.setMana(10);
        itemStats.setCold(randomInt(8, 5));
        itemStats.setPoison(randomInt(4, 5));
        itemStats.setFire(randomInt(8, 5));
        itemStats.setlRes(randomInt(20, 10));
        this.item = item;
    }

    /**
     * Method used to print out the stats of the item
     */
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getHp() + " Health");
        System.out.println("+" + itemStats.getMana() + " Mana");
        System.out.println("+" + itemStats.getLightning() + " Lightning Damage");
        System.out.println("+" + itemStats.getCold() + " Cold Damage");
        System.out.println("+" + itemStats.getPoison() + " Poison Damage");
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
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setMaxHP(player.getMyStats().getMaxHP() + itemStats.getHp());
        player.getMyStats().setMana(player.getMyStats().getMana() + itemStats.getMana());
        player.getMyStats().setMaxMana(player.getMyStats().getMaxMana() + itemStats.getMana());
        player.getMyStats().setLightning(player.getMyStats().getLightning() + itemStats.getLightning());
        player.getMyStats().setCold(player.getMyStats().getCold() + itemStats.getCold());
        player.getMyStats().setPoison(player.getMyStats().getPoison() + itemStats.getPoison());
        player.getMyStats().setFire(player.getMyStats().getFire() + itemStats.getFire());
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
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
     * Increases the player's lightning debuff power to 50%
     *
     * @param player - the argument is used to retrieve the player class' stats
     */
    @Override
    public void specialEffect(Player player) {
        player.lightningDebuff = 50.0f;
    }
}
