package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Creates the Mage Berserker Gloves under the Item Interface
 */
public class MageBerserkerGloves implements Item {

    //Constructors to be used in the class
    Item item;
    private Stats itemStats = new Stats();

    //String variables
    private String description = "Every spell cast increases the damage of consecutive spells cast by 5.";
    private String name = "Mage's Berserker Gloves";

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
    public MageBerserkerGloves(Item item) {
        itemStats.setHp(randomInt(20, 15));
        itemStats.setMana(5);
        itemStats.setArm(randomInt(5, 3));
        itemStats.setcRes(randomInt(5, 3));
        itemStats.setfRes(randomInt(5, 3));
        itemStats.setpRes(randomInt(5, 3));
        itemStats.setlRes(randomInt(5, 3));
        itemStats.setCold(randomInt(5, 3));
        itemStats.setFire(randomInt(5, 3));
        itemStats.setPoison(randomInt(5, 3));
        itemStats.setLightning(randomInt(5, 3));
        this.item = item;
    }

    /**
     * Used to get the name of the item
     *
     * @return returns the item decorator's method and the name of the item
     */
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
        player.getMyStats().setMaxHP(player.getMyStats().getMaxHP() + itemStats.getHp());
        player.getMyStats().setMaxHP(player.getMyStats().getMaxHP() + itemStats.getHp());
        player.getMyStats().setMana(player.getMyStats().getMana() + itemStats.getMana());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
        player.getMyStats().setcRes(player.getMyStats().getcRes() + itemStats.getcRes());
        player.getMyStats().setfRes(player.getMyStats().getfRes() + itemStats.getfRes());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
        player.getMyStats().setCold(player.getMyStats().getCold() + itemStats.getCold());
        player.getMyStats().setFire(player.getMyStats().getFire() + itemStats.getFire());
        player.getMyStats().setPoison(player.getMyStats().getPoison() + itemStats.getPoison());
        player.getMyStats().setLightning(player.getMyStats().getLightning() + itemStats.getLightning());
    }

    /**
     * Method used to print out the stats of the item
     */
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getHp() + " Health");
        System.out.println("+" + itemStats.getMana() + " Mana");
        System.out.println("+" + itemStats.getArm() + " Armour");
        System.out.println("+" + itemStats.getcRes() + " Cold Resistance");
        System.out.println("+" + itemStats.getfRes() + " Fire Resist");
        System.out.println("+" + itemStats.getlRes() + " Lightning Resistance");
        System.out.println("+" + itemStats.getpRes() + " Poison Resistance");
        System.out.println("+" + itemStats.getCold() + " Cold Damage");
        System.out.println("+" + itemStats.getFire() + " Fire Damage");
        System.out.println("+" + itemStats.getLightning() + " Lightning Damage");
        System.out.println("+" + itemStats.getPoison() + " Poison Damage");
    }

    /**
     * Whenever the player casts a spell, all consecutive casts have their damage increased by 5
     *
     * @param player - the argument is used to retrieve the player class' stats
     */
    @Override
    public void specialEffect(Player player) {
        player.bonuslDmg = player.getSpellCounter() * 5;
        player.bonusfDmg = player.getSpellCounter() * 5;
        player.bonuspDmg = player.getSpellCounter() * 5;
        player.bonuscDmg = player.getSpellCounter() * 5;
    }
}
