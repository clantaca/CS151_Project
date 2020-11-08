public class MageChargerGloves extends Item {
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    @Override
    public String getName() {
        return "Mage's Charging up Gloves";
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
        setPoison(randomInt(4, 2));
        setCold(randomInt(4,2));
        setLightning(randomInt(4,2));
        setFire(randomInt(4,4));
        setHp(randomInt(10,5));
        setlRes(randomInt(5,0));
        setcRes(randomInt(5,0));
        setpRes(randomInt(5,0));
        setfRes(randomInt(5,0));
        setArm(randomInt(5,0));
    }

    //each spell casts increase all magic damage
    @Override
    public void specialEffect(Player player) {
        player.getMyStats().setCold(player.getMyStats().getCold() + (2 * spellCounter));
        player.getMyStats().setFire(player.getMyStats().getfRes() + (2 * spellCounter));
        player.getMyStats().setPoison(player.getMyStats().getPoison() + (2 * spellCounter));
        player.getMyStats().setLightning(player.getMyStats().getLightning() + (2 * spellCounter));
}
}
