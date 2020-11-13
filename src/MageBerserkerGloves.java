public class MageBerserkerGloves implements Item {

    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }
    private Stats itemStats = new Stats();

    public MageBerserkerGloves() {
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
    }

    @Override
    public String getName() {
        return "Mage's Berserker Gloves";
    }

    @Override
    public String getDescription() {
        return "Every spell cast increases the damage of consecutive spells cast by 2.";
    }

    @Override
    public void updatePlayerStats (Player player) {
        player.getMyStats().setHp(player.getMyStats().getHp() + randomInt(10, 5));
        player.getMyStats().setArm(player.getMyStats().getArm() + randomInt(5, 0));
        player.getMyStats().setcRes(player.getMyStats().getcRes() + randomInt(5, 0));
        player.getMyStats().setfRes(player.getMyStats().getfRes() + randomInt(5, 0));
        player.getMyStats().setpRes(player.getMyStats().getpRes() + randomInt(5, 0));
        player.getMyStats().setlRes(player.getMyStats().getlRes() + randomInt(5, 0));
        player.getMyStats().setCold(player.getMyStats().getCold() + randomInt(4, 2));
        player.getMyStats().setFire(player.getMyStats().getfRes() + randomInt(4, 2));
        player.getMyStats().setPoison(player.getMyStats().getPoison() + randomInt(4, 2));
        player.getMyStats().setLightning(player.getMyStats().getLightning() + randomInt(4, 2));
    }
    @Override
    public void itemStats() {
        System.out.println(itemStats.getHp());
        System.out.println(itemStats.getArm());
        System.out.println(itemStats.getcRes());
        System.out.println(itemStats.getfRes());
        System.out.println(itemStats.getpRes());
        System.out.println(itemStats.getlRes());
        System.out.println(itemStats.getCold());
        System.out.println(itemStats.getFire());
        System.out.println(itemStats.getPoison());
        System.out.println(itemStats.getLightning());
    }

    //each spell casts increase all magic damage
    @Override
    public void specialEffect(Player player) {
        player.getMyStats().setCold(player.getMyStats().getCold() + (2 * player.spellCounter));
        player.getMyStats().setFire(player.getMyStats().getfRes() + (2 * player.spellCounter));
        player.getMyStats().setPoison(player.getMyStats().getPoison() + (2 * player.spellCounter));
        player.getMyStats().setLightning(player.getMyStats().getLightning() + (2 * player.spellCounter));
}
}
