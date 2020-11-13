public class PoisonWand implements Item {

    private Stats itemStats = new Stats();
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    public PoisonWand() {
        itemStats.setPoison(randomInt(8, 4));
        itemStats.setCold(randomInt(4,2));
        itemStats.setLightning(randomInt(4,2));
        itemStats.setFire(randomInt(4,2));
        itemStats.setpRes(randomInt(10,0));
    }

    @Override
    public void itemStats() {
        System.out.println(itemStats.getPoison());
        System.out.println(itemStats.getCold());
        System.out.println(itemStats.getLightning());
        System.out.println(itemStats.getFire());
        System.out.println(itemStats.getpRes());
    }

    @Override
    public void updatePlayerStats(Player player) {
		player.getMyStats().setPoison(player.getMyStats().getPoison() + itemStats.getPoison());
        player.getMyStats().setCold(player.getMyStats().getCold() + itemStats.getCold());
        player.getMyStats().setLightning(player.getMyStats().getLightning() + itemStats.getLightning());
        player.getMyStats().setFire(player.getMyStats().getFire() + itemStats.getFire());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
	}

    @Override
    public String getName() {
        return "Poisonous Wand";
    }

    @Override
    public String getDescription() {
        return "At the start of every combat, apply a debuff, causing the enemy to take 1 damage every turn";
    }

    @Override
    public void specialEffect(Player player) {
        //if (turnCounter == 1) {
            //TODO: MAKE IT APPLY A DEBUFF TO THE ENEMY WHERE THEY TAKE DAMAGE EVERY TURN
        //}
    }
}
