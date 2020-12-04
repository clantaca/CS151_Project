package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

/**
 * Model that constructs an item, Short Sword of Power, that the player could obtain/utilize during combat
 */
public class ShortSwordOfPower implements Item {

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
     * Constructor that initializes the stats (dmg, crit, hp, armor) of the item with a randomized int
     * @param item
     */
    public ShortSwordOfPower(Item item) {
        itemStats.setDmg(randomInt(10, 4));
        itemStats.setCrit(15);
        itemStats.setHp(randomInt(20,10));
        itemStats.setArm(randomInt(10,5));
        this.item = item;
    }

    /**
     * Returns name of item
     * @return name
     */
    @Override
    public String getName() {
        return item.getName() + "Short Sword Of Power";
    }

    /**
     * Returns description of item
     * @return description
     */
    @Override
    public String getDescription() {
        return "Every special physical attack has it's damage increased by 10." + item.getDescription();
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
     * Displays the stats of the items that include the physical damage, health, armor and crit
     */
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getDmg() + " Physical Damage");
        System.out.println("+%" + itemStats.getCrit() + " Critical Strike Chance");
        System.out.println("+" + itemStats.getHp() + " Health");
        System.out.println("+" + itemStats.getArm() + " Armor");
    }

    /**
     * Constructs the special effect of the item;
     * Every third attack deals a bonus damage
     * @param player
     */
    //every 3rd attack deals bonus damage
    @Override
    public void specialEffect(Player player) {
        if (player.getSpecialAttack() == player.physSpecialAttackCounter)
            player.bonusDmg = 15;
        else
            player.bonusDmg = 0;
    }
}
