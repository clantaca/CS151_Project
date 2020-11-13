
public class BloodlustGauntlets implements Item {

    private Stats itemStats = new Stats();
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    public BloodlustGauntlets() {
        itemStats.setDmg(randomInt(25,10));
        itemStats.setHp(randomInt(0,-30));
        itemStats.setArm(randomInt(0,-5));
        itemStats.setcRes(randomInt(0,-5));
        itemStats.setlRes(randomInt(0,-5));
        itemStats.setpRes(randomInt(0,-5));
        itemStats.setfRes(randomInt(0,-5));
    }

    @Override
    public void itemStats() {
        System.out.println(itemStats.getDmg());
        System.out.println(itemStats.getHp());
        System.out.println(itemStats.getArm());
        System.out.println(itemStats.getcRes());
        System.out.println(itemStats.getlRes());
        System.out.println(itemStats.getpRes());
        System.out.println(itemStats.getfRes());
    }

    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setDmg(player.getMyStats().getDmg() + itemStats.getDmg());
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
        player.getMyStats().setcRes(player.getMyStats().getcRes() + itemStats.getcRes());
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
        player.getMyStats().setfRes(player.getMyStats().getfRes() + itemStats.getfRes());

    }

    @Override
    public String getName() {
        return "Bloodlust Gauntlets";
    }

    @Override
    public String getDescription() {
        return "While below 75%/50%/25% health, increase damage by 10/20/30 respectively.";
    }

    //When the player is at 75%/50%/25% of his max health, increase damage
    @Override
    public void specialEffect(Player player) {
        if (player.getMyStats().getHp() < player.getMyStats().getHp()*.25)
            player.getMyStats().setDmg(player.getMyStats().getDmg()+30);
        else if (player.getMyStats().getHp() < player.getMyStats().getHp()*.5)
            player.getMyStats().setDmg(player.getMyStats().getDmg()+20);
        else if (player.getMyStats().getHp() < player.getMyStats().getHp()*.75)
            player.getMyStats().setDmg(player.getMyStats().getDmg()+20);
    }
}
