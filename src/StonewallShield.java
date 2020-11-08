public class StonewallShield extends Item {
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    @Override
    public String getName() {
        return "Stonewall's Shield";
    }

    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setHp(player.getMyStats().getHp() + randomInt(20, 10));
        player.getMyStats().setcRes(player.getMyStats().getcRes() + randomInt(10, 5));
        player.getMyStats().setfRes(player.getMyStats().getfRes() + randomInt(10, 5));
        player.getMyStats().setpRes(player.getMyStats().getpRes() + randomInt(10, 5));
        player.getMyStats().setlRes(player.getMyStats().getlRes() + randomInt(10, 5));
        player.getMyStats().setDmg(player.getMyStats().getDmg() + randomInt(7, 1));
    }
    @Override
    public void itemStats() {
        setHp(randomInt(20,10));
        setlRes(randomInt(10,5));
        setcRes(randomInt(10,5));
        setpRes(randomInt(10,5));
        setfRes(randomInt(10,5));
        setArm(randomInt(10,5));
        setDmg(randomInt(7,1));
    }

    //ignore all damage every 3rd turn
    @Override
    public void specialEffect(Player player) {
        if (turnCounter == 3) {
            //TODO TAKE NO DAMAGE FROM ENEMY ON THIS TURN
        }
        else {
            turnCounter++;
        }
    }
}
