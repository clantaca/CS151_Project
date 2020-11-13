public class StonewallShield extends Stats implements Item {

    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }
    private Stats itemStats = new Stats();

    public StonewallShield() {
        itemStats.setDmg(randomInt(7,1));
        itemStats.setHp(randomInt(20,10));
        itemStats.setArm(randomInt(10,5));
        itemStats.setcRes(randomInt(10,5));
        itemStats.setfRes(randomInt(10,5));
        itemStats.setpRes(randomInt(10,5));
        itemStats.setlRes(randomInt(10,5));
    }

    @Override
    public String getName() {
        return "Stonewall's Shield";
    }

    @Override
    public String getDescription() {
        return "On every 3rd turn, ignore all damage taken.";
    }

    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setDmg(player.getMyStats().getDmg() + itemStats.getDmg());
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
        player.getMyStats().setcRes(player.getMyStats().getcRes() + itemStats.getcRes());
        player.getMyStats().setfRes(player.getMyStats().getfRes() + itemStats.getfRes());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
    }
    @Override
    public void itemStats() {
        System.out.println(itemStats.getDmg());
        System.out.println(itemStats.getHp());
        System.out.println(itemStats.getArm());
        System.out.println(itemStats.getcRes());
        System.out.println(itemStats.getfRes());
        System.out.println(itemStats.getpRes());
        System.out.println(itemStats.getlRes());
    }

    //ignore all damage every 3rd turn
    @Override
    public void specialEffect(Player player) {
        if (player.turnCounter == 3) {
            //TODO TAKE NO DAMAGE FROM ENEMY ON THIS TURN
        }
        else {
            player.turnCounter++;
        }
    }
}
