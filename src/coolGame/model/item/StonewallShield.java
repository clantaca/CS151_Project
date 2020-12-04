package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Model that constructs an item, Stone Wall Shield, that the player could obtain/utilize during combat
 */
public class StonewallShield extends Stats implements Item {

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
     * Constructor that initializes the stats (HP, armor, resistances) of the item with a randomized int
     * @param item
     */
    public StonewallShield(Item item) {
        itemStats.setDmg(randomInt(7,1));
        itemStats.setHp(randomInt(30,20));
        itemStats.setArm(randomInt(10,5));
        itemStats.setcRes(randomInt(10,5));
        itemStats.setfRes(randomInt(10,5));
        itemStats.setpRes(randomInt(10,5));
        itemStats.setlRes(randomInt(10,5));
        this.item = item;
    }

    /**
     * Returns the name of item
     * @return name
     */
    @Override
    public String getName() {
        return item.getName() + "Stonewall's Shield";
    }

    /**
     * Returns the description of item
     * @return description
     */
    @Override
    public String getDescription() {
        return "On every 3rd turn, ignore all damage taken." + item.getDescription();
    }

    /**
     * Updates/Adds the stats of the player according the the item's stats
     * @param player
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
     * Displays the stats of the items that include the physical damage, health, armor and resistances
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
     * Constructs the special effect of the item;
     * Ignores all damage every third turn
     * @param player
     */
    //ignore all damage every 3rd turn
    @Override
    public void specialEffect(Player player) {
        if (player.getTurnCounter()+1 % 3 == 0)
            player.setShieldEffect(true);
        else
            player.setShieldEffect(false);
    }
}
