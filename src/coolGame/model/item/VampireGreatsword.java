package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Model that constructs an item, Vampire Great Sword, that the player could obtain/utilize during combat
 */
public class VampireGreatsword implements Item {

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
     * Constructor that initializes the stats (physical dmg, hp, crit) of the item with a randomized int
     * @param item
     */
    public VampireGreatsword(Item item) {
        itemStats.setDmg(randomInt(15, 10));
        itemStats.setHp(randomInt(20,10));
        itemStats.setCrit(5);
        this.item = item;
    }

    /**
     * Returns the name of the item
     * @return name
     */
    @Override
    public String getName() {
        return item.getName() + "Vampiric Greatsword";
    }

    /**
     * Returns the description of the item
     * @return description
     */
    @Override
    public String getDescription() {
        return "Your physical special attack now procs every other atack." + item.getDescription();
    }

    /**
     * Updates/Adds the stats of the player according the the item's stats
     * @param player
     */
    @Override
    public void updatePlayerStats (Player player) {
        player.getMyStats().setDmg(player.getMyStats().getDmg() + itemStats.getDmg());
        player.getMyStats().setCrit(player.getMyStats().getCrit() + itemStats.getCrit());
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
    }

    /**
     * Displays the stats of the items that include the damage, crits, and hp
     */
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getDmg() + " Physical Damage");
        System.out.println("+%" + itemStats.getCrit() + " Critical Strike Chance");
        System.out.println("+" + itemStats.getHp() + " Health");
    }

    /**
     * Constructs the special effect of the item;
     * Every 3rd attack deals with bonus damage
     * @param player
     */
    //every 3rd attack deals bonus damage
    @Override
    public void specialEffect(Player player) {
        player.physSpecialAttackCounter = 1;
    }
}

