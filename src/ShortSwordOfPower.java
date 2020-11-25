public class ShortSwordOfPower extends Stats implements Item {

    Item item;
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }
    private Stats itemStats = new Stats();

    public ShortSwordOfPower(Item item) {
        itemStats.setDmg(randomInt(10, 4));
        itemStats.setCrit(15);
        itemStats.setHp(randomInt(10,5));
        itemStats.setArm(randomInt(10,5));
        this.item = item;
    }
    @Override
    public String getName() {
        return item.getName() + "Short Sword Of Power";
    }

    @Override
    public String getDescription() {
        return "Every 3rd physical attack has it's damage increased by 15." + item.getDescription();
    }

    @Override
    public void updatePlayerStats (Player player) {
        player.getMyStats().setDmg(player.getMyStats().getDmg() + itemStats.getDmg());
        player.getMyStats().setCrit(player.getMyStats().getCrit() + itemStats.getCrit());
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
    }
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getDmg() + " Physical Damage");
        System.out.println("+%" + itemStats.getCrit() + " Critical Strike Chance");
        System.out.println("+" + itemStats.getHp() + " Health");
        System.out.println("+" + itemStats.getArm() + " Armor");
    }

    //every 2nd attack deals bonus damage
    @Override
    public void specialEffect(Player player) {
        if (player.getAttackCounter() % 3 == 0)
            player.getMyStats().setDmg(player.getMyStats().getDmg() + 15);
        else
            player.getMyStats().setDmg(player.getOriginalDmg());
    }
}
