public class BloodlustGauntlets extends Item {
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    private Player player;

    @Override
    public String getName() {
        return "Bloodlust Gauntlets";
    }

    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setDmg(player.getMyStats().getDmg() + randomInt(25, 10));
        player.getMyStats().setHp(player.getMyStats().getPoison() + randomInt(0, -30));
        player.getMyStats().setcRes(player.getMyStats().getcRes() + randomInt(0, -5));
        player.getMyStats().setfRes(player.getMyStats().getfRes() + randomInt(0, -5));
        player.getMyStats().setlRes(player.getMyStats().getlRes() + randomInt(0, -5));
        player.getMyStats().setpRes(player.getMyStats().getpRes() + randomInt(0, -5));
        player.getMyStats().setArm(player.getMyStats().getArm() + randomInt(0, -5));
    }

    @Override
    public void itemStats() {
        setDmg(randomInt(25,10));
        setHp(randomInt(0,-30));
        setArm(randomInt(0,-5));
        setcRes(randomInt(0,-5));
        setlRes(randomInt(0,-5));
        setpRes(randomInt(0,-5));
        setfRes(randomInt(0,-5));
    }

    @Override
    public void specialEffect(Player player) {
        //TODO RETRIEVE PLAYER HEALTH, WHEN LOWER THAN CERTAIN THRESHOLD, INCREASE DAMAGE
}
}
