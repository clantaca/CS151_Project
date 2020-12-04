package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Creates the Short Sword of Power under the Item Interface
 */
public class ShortSwordOfPower implements Item {

    //Constructors to be used in the class
    Item item;
    private Stats itemStats = new Stats();

    //String variables
    private String description = "Every special physical attack has it's damage increased by 10.";
    private String name = "Short Sword Of Power";

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
    public ShortSwordOfPower(Item item) {
        itemStats.setDmg(randomInt(10, 4));
        itemStats.setCrit(15);
        itemStats.setHp(randomInt(20, 10));
        itemStats.setArm(randomInt(10, 5));
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
        player.getMyStats().setCrit(player.getMyStats().getCrit() + itemStats.getCrit());
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
    }

    /**
     * Method used to print out the stats of the item
     */
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getDmg() + " Physical Damage");
        System.out.println("+%" + itemStats.getCrit() + " Critical Strike Chance");
        System.out.println("+" + itemStats.getHp() + " Health");
        System.out.println("+" + itemStats.getArm() + " Armor");
    }

    /**
     * Every special physical attack has it's damage increased by 10
     *
     * @param player - the argument is used to retrieve the player class' stats
     */
    @Override
    public void specialEffect(Player player) {
        if (player.getSpecialAttack() == player.physSpecialAttackCounter)
            player.bonusDmg = +15;
        else
            player.bonusDmg = 0;
    }
}
