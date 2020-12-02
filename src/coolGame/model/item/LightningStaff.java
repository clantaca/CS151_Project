package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Enemy;
import coolGame.model.character.Player;

public class LightningStaff implements Item {
    Item item;
    Enemy enemy;
    private Stats itemStats = new Stats();
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    public LightningStaff(Item item) {
        itemStats.setLightning(randomInt(12, 6));
        itemStats.setCold(randomInt(4,2));
        itemStats.setPoison(randomInt(4,2));
        itemStats.setFire(randomInt(4,2));
        itemStats.setlRes(randomInt(10,5));
        this.item = item;
    }

    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getLightning() + " Lightning Damage");
        System.out.println("+" + itemStats.getCold() + " Cold Damage");
        System.out.println("+" + itemStats.getPoison() + " Poison Damage");
        System.out.println("+" + itemStats.getFire() + " Fire Damage");
        System.out.println("+" + itemStats.getpRes() + " Poison Resistance");
    }

    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setLightning(player.getMyStats().getLightning() + itemStats.getLightning());
        player.getMyStats().setCold(player.getMyStats().getCold() + itemStats.getCold());
        player.getMyStats().setPoison(player.getMyStats().getPoison() + itemStats.getPoison());
        player.getMyStats().setFire(player.getMyStats().getFire() + itemStats.getFire());
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
    }

    @Override
    public String getName() {
        return item.getName() + "Lightning Staff";
    }

    @Override
    public String getDescription() {
        return "At the start of every combat, apply a debuff, causing the enemy to take 20% increased damage."
                + item.getDescription();
    }

    //Enemy starts the battle with 1 stack of poison debuff.
    @Override
    public void specialEffect(Player player) {
        if (player.getTurnCounter() == 0) {
            player.setHasLightningDebuff(true);
        }
    }
}
