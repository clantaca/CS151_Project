public class RejuvenatingChestplate extends Item {

    private int originalHp;

    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    @Override
    public String getName() {
        return "Rejuvenating Chestplate";
    }

    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setHp(player.getMyStats().getHp() + randomInt(50, 25));
        player.getMyStats().setArm(player.getMyStats().getArm() + randomInt(20, 10));
        player.getMyStats().setcRes(player.getMyStats().getcRes() + randomInt(20, 10));
        player.getMyStats().setfRes(player.getMyStats().getfRes() + randomInt(20, 10));
        player.getMyStats().setpRes(player.getMyStats().getpRes() + randomInt(20, 10));
        player.getMyStats().setlRes(player.getMyStats().getlRes() + randomInt(20, 10));
        originalHp = getHp();
    }
    @Override
    public void itemStats() {
        setHp(randomInt(50,25));
        setArm(randomInt(20,10));
        setcRes(randomInt(20,10));
        setlRes(randomInt(20,10));
        setpRes(randomInt(20,10));
        setfRes(randomInt(20,10));

    }

    //heals self after every 4th hit taken
    //TODO: MAKE IT SO THAT IT HEALS, NOT INCREASES TOTAL HEALTH BY 20
    @Override
    public void specialEffect(Player player) {
        if (hitCounter == 4) {
            setHp(getHp() + 20);
            hitCounter = 0;
        }
            else {
                setHp(originalHp);
                hitCounter++;
            }
    }
}
