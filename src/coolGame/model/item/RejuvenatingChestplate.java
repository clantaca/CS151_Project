package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

public class RejuvenatingChestplate extends Stats implements Item {

    Item item;
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }
    private Stats itemStats = new Stats();

    public RejuvenatingChestplate(Item item) {
        itemStats.setHp(randomInt(50,25));
        itemStats.setArm(randomInt(20,10));
        itemStats.setcRes(randomInt(20,10));
        itemStats.setfRes(randomInt(20,10));
        itemStats.setpRes(randomInt(20,10));
        itemStats.setlRes(randomInt(20,10));
        this.item = item;
    }

    @Override
    public String getName() {
        return item.getName() + "Rejuvenating Chestplate";
    }

    @Override
    public String getDescription() {
        return "Every 5th you take from the enemy, heal for 20." + item.getDescription();
    }

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
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getHp() + " Health");
        System.out.println("+" + itemStats.getArm() + " Armor");
        System.out.println("+" + itemStats.getcRes() + " Cold Resistance");
        System.out.println("+" + itemStats.getfRes() + " Fire Resistance");
        System.out.println("+" + itemStats.getlRes() + " Lightning Resistance");
        System.out.println("+" + itemStats.getpRes() + " Poison Resistance");
    }

    //heals self after every 4th hit taken
    //TODO: MAKE IT SO THAT IT HEALS, NOT INCREASES TOTAL HEALTH BY 20
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