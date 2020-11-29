package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

public class StonewallShield extends Stats implements Item {

    Item item;
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }
    private Stats itemStats = new Stats();

    public StonewallShield(Item item) {
        itemStats.setDmg(randomInt(7,1));
        itemStats.setHp(randomInt(20,10));
        itemStats.setArm(randomInt(10,5));
        itemStats.setcRes(randomInt(10,5));
        itemStats.setfRes(randomInt(10,5));
        itemStats.setpRes(randomInt(10,5));
        itemStats.setlRes(randomInt(10,5));
        this.item = item;
    }

    @Override
    public String getName() {
        return item.getName() + "Stonewall's Shield";
    }

    @Override
    public String getDescription() {
        return "On every 3rd turn, ignore all damage taken." + item.getDescription();
    }

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

    //ignore all damage every 3rd turn
    @Override
    public void specialEffect(Player player) {
        if (player.getTurnCounter()+1 % 3 == 0)
            player.setShieldEffect(true);
        else
            player.setShieldEffect(false);
    }
}
