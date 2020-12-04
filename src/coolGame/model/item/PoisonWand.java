package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Model that constructs an item, Poison Wand, that the player could obtain/utilize during combat
 */
public class PoisonWand implements Item {
    Item item;
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
     * Constructor that initializes the stats (damages, resistanc) of the item with a randomized int
     * @param item
     */
    public PoisonWand(Item item) {
        itemStats.setPoison(randomInt(8, 4));
        itemStats.setCold(randomInt(4,2));
        itemStats.setLightning(randomInt(4,2));
        itemStats.setFire(randomInt(4,2));
        itemStats.setpRes(randomInt(10,0));
        this.item = item;
    }

    /**
     * Displays the stats of the items that include the damages and resistance
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
     * Updates/Adds the stats of the player according the the item's stats
     * @param player
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
     * Returns the name of item
     * @return name
     */
    @Override
    public String getName() {
        return item.getName() + "Poisonous Wand";
    }

    /**
     * Returns the description of item
     * @return description
     */
    @Override
    public String getDescription() {
        return "At the start of every combat, apply a debuff, causing the enemy to take 10 damage every turn for 3 turns"
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
        if (player.getTurnCounter() == 0) {
            player.setDebuffOn(true);
            player.setDebuffCounter(player.getDebuffTurnCounter()+1);
        }
    }
}
