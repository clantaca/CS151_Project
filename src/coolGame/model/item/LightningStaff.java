package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Enemy;
import coolGame.model.character.Player;

/**
 * Model that constructs an item, Lightning Staff, that the player could obtain/utilize during combat
 */
public class LightningStaff implements Item {
    Item item;
    Enemy enemy;
    private Stats itemStats = new Stats();

    /**
     * Returns a random integer given a minimum and maximum number
     * @param max
     * @param min
     * @return randomInt
     */
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    /**
     * Constructor that initializes the stats (damages, resistance) of the item with a randomized int
     * @param item
     */
    public LightningStaff(Item item) {
        itemStats.setLightning(randomInt(12, 6));
        itemStats.setCold(randomInt(4,2));
        itemStats.setPoison(randomInt(4,2));
        itemStats.setFire(randomInt(4,2));
        itemStats.setlRes(randomInt(10,5));
        this.item = item;
    }

    /**
     * Displays the stats of the items that include the damages and resistance
     */
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getLightning() + " Lightning Damage");
        System.out.println("+" + itemStats.getCold() + " Cold Damage");
        System.out.println("+" + itemStats.getPoison() + " Poison Damage");
        System.out.println("+" + itemStats.getFire() + " Fire Damage");
        System.out.println("+" + itemStats.getpRes() + " Poison Resistance");
    }

    /**
     * Updates/Adds the stats of the player according the the item's stats
     * @param player
     */
    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setLightning(player.getMyStats().getLightning() + itemStats.getLightning());
        player.getMyStats().setCold(player.getMyStats().getCold() + itemStats.getCold());
        player.getMyStats().setPoison(player.getMyStats().getPoison() + itemStats.getPoison());
        player.getMyStats().setFire(player.getMyStats().getFire() + itemStats.getFire());
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
        player.lightningDebuff = 40.0f;
    }

    /**
     * Returns name of item
     * @return name
     */
    @Override
    public String getName() {
        return item.getName() + "Lightning Staff";
    }

    /**
     * Returns description of item
     * @return description
     */
    @Override
    public String getDescription() {
        return "Increases the lightning debuff that the player has to 40% increased damage taken."
                + item.getDescription();
    }

    /**
     * Constructs the special effect of the item;
     * Enemy starts the battle with one stack of poison debuff
     * @param player
     */
    //Enemy starts the battle with 1 stack of poison debuff.
    @Override
    public void specialEffect(Player player) {

    }
}
