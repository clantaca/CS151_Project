package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Creates the Bloodlust Gauntlets item under the Item Interface
 */
public class BloodlustGauntlets implements Item {

    //Constructors to be used in the class
    Item item;
    private Stats itemStats = new Stats();

    //String variables
    private String name = "Bloodlust Gauntlets";
    private String description = "While below 75%/50%/25% health, increase damage by 15/25/35 respectively.";

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
    public BloodlustGauntlets(Item item) {
        itemStats.setDmg(randomInt(25, 10));
        itemStats.setHp(randomInt(0, -20));
        itemStats.setArm(randomInt(0, -5));
        itemStats.setcRes(randomInt(0, -5));
        itemStats.setlRes(randomInt(0, -5));
        itemStats.setpRes(randomInt(0, -5));
        itemStats.setfRes(randomInt(0, -5));
        this.item = item;
    }

    /**
     * Method used to print out the stats of the item
     */
    @Override
    public void itemStats() {
        System.out.println("" + itemStats.getHp() + " HP");
        System.out.println("+" + itemStats.getDmg() + " Physical DMG");
        System.out.println("" + itemStats.getArm() + " Armor");
        System.out.println("" + itemStats.getcRes() + " Cold Resistance");
        System.out.println("" + itemStats.getfRes() + " Fire Resistance");
        System.out.println("" + itemStats.getlRes() + " Lightning Resistance");
        System.out.println("" + itemStats.getpRes() + " Poison Resistance");
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
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
        player.getMyStats().setfRes(player.getMyStats().getfRes() + itemStats.getfRes());

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
     * Whenever the player is below certain thresholds of health, it increases player's physical damage
     *
     * @param player - the argument is used to retrieve the player class' stats
     */
    @Override
    public void specialEffect(Player player) {
        if (player.getMyStats().getHp() < player.getMyStats().getHp() * .25)
            player.bonusDmg2 = 35;
        else if (player.getMyStats().getHp() < player.getMyStats().getHp() * .5)
            player.bonusDmg2 = 25;
        else if (player.getMyStats().getHp() < player.getMyStats().getHp() * .75)
            player.bonusDmg2 = 15;
        else
            player.bonusDmg2 = 0;
    }
}
