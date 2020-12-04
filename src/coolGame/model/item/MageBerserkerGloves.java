package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Model that constructs an item, Mage Berserker Gloves, that the player could obtain/utilize during combat
 */
public class MageBerserkerGloves implements Item {

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
     * Constructor that initializes the stats (hp, armor, resistances, damages) of the item with a randomized int
     * @param item
     */
    public MageBerserkerGloves(Item item) {
        itemStats.setHp(randomInt(10, 5));
        itemStats.setArm(randomInt(5, 0));
        itemStats.setcRes(randomInt(5, 0));
        itemStats.setfRes(randomInt(5, 0));
        itemStats.setpRes(randomInt(5, 0));
        itemStats.setlRes(randomInt(5, 0));
        itemStats.setCold(randomInt(4, 2));
        itemStats.setFire(randomInt(4, 2));
        itemStats.setPoison(randomInt(4, 2));
        itemStats.setLightning(randomInt(4, 2));
        this.item = item;
    }

    /**
     * Returns name of item
     * @return name
     */
    @Override
    public String getName() {
        return item.getName() + "Mage's Berserker Gloves";
    }

    /**
     * Returns description of item
     * @return description
     */
    @Override
    public String getDescription() {
        return "Every spell cast increases the damage of consecutive spells cast by 3." + item.getDescription();
    }

    /**
     * Updates/Adds the stats of the player according the the item's stats
     * @param player
     */
    @Override
    public void updatePlayerStats (Player player) {
        player.getMyStats().setHp(player.getMyStats().getHp() + randomInt(20, 15));
        player.getMyStats().setMaxHP(player.getMyStats().getMaxHP() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + randomInt(5, 3));
        player.getMyStats().setcRes(player.getMyStats().getcRes() + randomInt(5, 3));
        player.getMyStats().setfRes(player.getMyStats().getfRes() + randomInt(5, 3));
        player.getMyStats().setpRes(player.getMyStats().getpRes() + randomInt(5, 3));
        player.getMyStats().setlRes(player.getMyStats().getlRes() + randomInt(5, 3));
        player.getMyStats().setCold(player.getMyStats().getCold() + randomInt(5, 3));
        player.getMyStats().setFire(player.getMyStats().getFire() + randomInt(5, 3));
        player.getMyStats().setPoison(player.getMyStats().getPoison() + randomInt(5, 3));
        player.getMyStats().setLightning(player.getMyStats().getLightning() + randomInt(5, 3));
    }

    /**
     * Displays the stats of the items that include the health, armor, damages and resistances
     */
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getHp() + " Health");
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
     * Constructs the special effect of the item;
     * Each spell casted increases all magic damage
     * @param player
     */
    //each spell casts increases all magic damage
    @Override
    public void specialEffect(Player player) {
        player.bonuslDmg = player.getSpellCounter()*3;
        player.bonusfDmg = player.getSpellCounter()*3;
        player.bonuspDmg = player.getSpellCounter()*3;
        player.bonuscDmg = player.getSpellCounter()*3;
}
}
