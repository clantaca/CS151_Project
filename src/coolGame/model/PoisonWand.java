package coolGame.model;

public class PoisonWand implements Item {
    Item item;
    private Stats itemStats = new Stats();
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    public PoisonWand(Item item) {
        itemStats.setPoison(randomInt(8, 4));
        itemStats.setCold(randomInt(4,2));
        itemStats.setLightning(randomInt(4,2));
        itemStats.setFire(randomInt(4,2));
        itemStats.setpRes(randomInt(10,0));
        this.item = item;
    }

    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getPoison() + " Poison Damage");
        System.out.println("+" + itemStats.getCold() + " Cold Damage");
        System.out.println("+" + itemStats.getLightning() + " Lightning Damage");
        System.out.println("+" + itemStats.getFire() + " Fire Damage");
        System.out.println("+" + itemStats.getpRes() + " Poison Resistance");
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
        return item.getName() + "Poisonous Wand";
    }

    @Override
    public String getDescription() {
        return "At the start of every combat, apply a debuff, causing the enemy to take 10 damage every turn for 3 turns"
                + item.getDescription();
    }

    @Override
    public void specialEffect(Player player) {
        if (player.getTurnCounter() == 0) {
            player.setDebuffOn(true);
            player.setDebuffCounter(player.getDebuffTurnCounter()+1);
        }
    }
}
