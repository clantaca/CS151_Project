package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

public class SwiftShoes implements Item {

    //Constructors to be used in the class
    Item item;
    private Stats itemStats = new Stats();

    //String variables
    private String name = "Shoes of Swiftness";
    private String description = "Increases Dodge chance by 25%";

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
    public SwiftShoes(Item item) {
        itemStats.setHp(randomInt(30, 20));
        itemStats.setArm(randomInt(10, 0));
        itemStats.setcRes(randomInt(10, 0));
        itemStats.setfRes(randomInt(10, 0));
        itemStats.setpRes(randomInt(10, 0));
        itemStats.setlRes(randomInt(10, 0));
        itemStats.setDodge(25);
        this.item = item;
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
     * Nothing, special effect is an increase of a rare stat of dodge.
     *
     * @param player - the argument is used to retrieve the player class' stats
     */
    @Override
    public void specialEffect(Player player) {

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

    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setMaxHP(player.getMyStats().getMaxHP() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
        player.getMyStats().setcRes(player.getMyStats().getcRes() + itemStats.getcRes());
        player.getMyStats().setfRes(player.getMyStats().getfRes() + itemStats.getfRes());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
        player.getMyStats().setDodge(player.getMyStats().getDodge() + itemStats.getDodge());
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
}
