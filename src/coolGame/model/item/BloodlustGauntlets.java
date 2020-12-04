package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Model that constructs an item, Bloodlust Guantlets, that the player could obtain/utilize during combat
 */
public class BloodlustGauntlets implements Item {

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
     * Constructor that initializes the stats (hp, armor, damage, resistances) of the item with a randomized int
     * @param item
     */
    public BloodlustGauntlets(Item item) {
        itemStats.setDmg(randomInt(25,10));
        itemStats.setHp(randomInt(0,-30));
        itemStats.setArm(randomInt(0,-5));
        itemStats.setcRes(randomInt(0,-5));
        itemStats.setlRes(randomInt(0,-5));
        itemStats.setpRes(randomInt(0,-5));
        itemStats.setfRes(randomInt(0,-5));
        this.item = item;
    }

    /**
     * Displays the stats of the items that include the hp, damage, armor, and resistances
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
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
        player.getMyStats().setfRes(player.getMyStats().getfRes() + itemStats.getfRes());

    }

    /**
     * Returns the name of item
     * @return name
     */
    @Override
    public String getName() {
        return item.getName() + "Bloodlust Gauntlets";
    }

    /**
     * Returns description of item
     * @return description
     */
    @Override
    public String getDescription() {
        return "While below 75%/50%/25% health, increase damage by 10/20/30 respectively." + item.getDescription();
    }

    /**
     * Constructs the special effect of the item;
     * When the player is at 75% / 50% / 25% of their max health, the damage increases
     * @param player
     */
    //When the player is at 75%/50%/25% of his max health, increase damage
    @Override
    public void specialEffect(Player player) {
        if (player.getMyStats().getHp() < player.getMyStats().getHp()*.25)
            player.getMyStats().setDmg(player.getMyStats().getDmg()+30);
        else if (player.getMyStats().getHp() < player.getMyStats().getHp()*.5)
            player.getMyStats().setDmg(player.getMyStats().getDmg()+20);
        else if (player.getMyStats().getHp() < player.getMyStats().getHp()*.75)
            player.getMyStats().setDmg(player.getMyStats().getDmg()+10);
    }
}
