public class RejuvenatingChestplate extends Stats implements Item {

    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }
    private Stats itemStats = new Stats();

    public RejuvenatingChestplate() {
        itemStats.setHp(randomInt(50,25));
        itemStats.setArm(randomInt(20,10));
        itemStats.setcRes(randomInt(20,10));
        itemStats.setfRes(randomInt(20,10));
        itemStats.setpRes(randomInt(20,10));
        itemStats.setlRes(randomInt(20,10));
    }

    @Override
    public String getName() {
        return "Rejuvenating Chestplate";
    }

    @Override
    public String getDescription() {
        return "Every 5th you take from the enemy, heal for 20.";
    }

    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
        player.getMyStats().setcRes(player.getMyStats().getcRes() + itemStats.getcRes());
        player.getMyStats().setfRes(player.getMyStats().getfRes() + itemStats.getfRes());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
    }
    @Override
    public void itemStats() {
        System.out.println(itemStats.getHp());
        System.out.println(itemStats.getArm());
        System.out.println(itemStats.getcRes());
        System.out.println(itemStats.getfRes());
        System.out.println(itemStats.getpRes());
        System.out.println(itemStats.getlRes());
    }

    //heals self after every 4th hit taken
    //TODO: MAKE IT SO THAT IT HEALS, NOT INCREASES TOTAL HEALTH BY 20
    @Override
    public void specialEffect(Player player) {
        if (player.hitCounter == 4) {
            player.getMyStats().setHp((player.getMyStats().getHp() + 20));
            player.hitCounter = 0;
        }
            else {
                player.hitCounter++;
            }
    }
}
