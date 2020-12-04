package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Creates the Stonewall Shield under the Item Interface
 */
public class StonewallShield extends Stats implements Item {

    //Constructors to be used in the class
    Item item;
    private Stats itemStats = new Stats();

    //String variables
    private String name = "Stonewall's Shield";
    private String description = "On every 3rd turn, ignore all damage taken.";

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
    public StonewallShield(Item item) {
        itemStats.setDmg(randomInt(10, 5));
        itemStats.setHp(randomInt(30, 20));
        itemStats.setArm(randomInt(10, 5));
        itemStats.setcRes(randomInt(10, 5));
        itemStats.setfRes(randomInt(10, 5));
        itemStats.setpRes(randomInt(10, 5));
        itemStats.setlRes(randomInt(10, 5));
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
        player.getMyStats().setDmg(player.getMyStats().getDmg() + itemStats.getDmg());
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setMaxHP(player.getMyStats().getMaxHP() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
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
        System.out.println("+" + itemStats.getDmg() + " Physical Damage");
        System.out.println("+" + itemStats.getHp() + " Health");
        System.out.println("+" + itemStats.getArm() + " Armor");
        System.out.println("+" + itemStats.getcRes() + " Cold Resistance");
        System.out.println("+" + itemStats.getfRes() + " Fire Resistance");
        System.out.println("+" + itemStats.getlRes() + " Lightning Resistance");
        System.out.println("+" + itemStats.getpRes() + " Poison Resistance");
    }

    /**
     * Ignores all damage taken from the enemy on every 3rd turn
     *
     * @param player - the argument is used to retrieve the player class' stats
     */
    @Override
    public void specialEffect(Player player) {
        if (player.getTurnCounter() + 1 % 3 == 0)
            player.setShieldEffect(true);
        else
            player.setShieldEffect(false);
    }
}
