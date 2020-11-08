public class ShortSwordOfPower extends Item {
    private int originalDmg;

    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    @Override
    public String getName() {
        return "Short Sword Of Power";
    }

    @Override
    public void updatePlayerStats (Player player) {
        player.getMyStats().setDmg(player.getMyStats().getDmg() + randomInt(10, 4));
        player.getMyStats().setHp(player.getMyStats().getHp() + randomInt(10, 5));
        player.getMyStats().setArm(player.getMyStats().getArm() + randomInt(10, 5));
    }
    @Override
    public void itemStats() {
        weaponCounter++;
        setDmg(randomInt(10,4));
        
        originalDmg = getDmg();
    }

    //every 2nd attack deals bonus damage
    //TODO: FIX IT SO THAT ITS A TEMP BUFF ON 2ND ATTACK
    @Override
    public void specialEffect(Player player) {
        if (attackCounter == 1) {
            setDmg(getDmg()+15);
            attackCounter = 0;
        }
        else {
            setDmg(originalDmg);
            attackCounter++;
        }
    }
}
