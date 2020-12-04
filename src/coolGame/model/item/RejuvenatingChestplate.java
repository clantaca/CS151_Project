package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Model that constructs an item, Rejuvenating Chest plate, that the player could obtain/utilize during combat
 */
public class RejuvenatingChestplate extends Stats implements Item {

    Item item;

    /**
     * Returns a random integer given a minimum and maximum number
     * @param max
     * @param min
     * @return randomInt
     */
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }
    private Stats itemStats = new Stats();

    /**
     * Constructor that initializes the stats (hp, armor, resistances) of the item with a randomized int
     * @param item
     */
    public RejuvenatingChestplate(Item item) {
        itemStats.setHp(randomInt(50,25));
        itemStats.setArm(randomInt(20,10));
        itemStats.setcRes(randomInt(20,10));
        itemStats.setfRes(randomInt(20,10));
        itemStats.setpRes(randomInt(20,10));
        itemStats.setlRes(randomInt(20,10));
        this.item = item;
    }

    /**
     * Returns name of item
     * @return name
     */
    @Override
    public String getName() {
        return item.getName() + "Rejuvenating Chestplate";
    }

    /**
     * Returns description of item
     * @return description
     */
    @Override
    public String getDescription() {
        return "Every 5th hit you take from the enemy, heal for 20." + item.getDescription();
    }

    /**
     * Updates/Adds the stats of the player according the the item's stats
     * @param player
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
     * Displays the stats of the items that include the health, armor, and resistances
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
     * Constructs the special effect of the item;
     * Heals player after every fourth hit taken from enemy
     * @param player
     */
    //heals self after every 4th hit taken
    @Override
    public void specialEffect(Player player) {
        if (player.getHitsTakenCounter()+1 % 5 == 0) {
            player.getMyStats().setHp((player.getMyStats().getHp() + 20));
            System.out.println("Player heals for 20 health from Rejuvenating Chestplate");
            if (player.getMyStats().getHp() > player.getMyStats().getMaxHP()) {
                player.getMyStats().setHp(player.getMyStats().getMaxHP());
            }
        }
    }
}
