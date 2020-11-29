package coolGame.model;

public class SwiftShoes implements Item {
    Item item;
    private Stats itemStats = new Stats();
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    public SwiftShoes(Item item) {
        itemStats.setHp(randomInt(30, 15));
        itemStats.setArm(randomInt(10,0));
        itemStats.setcRes(randomInt(10,0));
        itemStats.setfRes(randomInt(10,0));
        itemStats.setpRes(randomInt(10,0));
        itemStats.setlRes(randomInt(10,0));
        itemStats.setDodge(25);
        this.item = item;
    }
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getHp() + " Health");
        System.out.println("+" + itemStats.getArm() + " Armor");
        System.out.println("+" + itemStats.getcRes() + " Cold Resistance");
        System.out.println("+" + itemStats.getfRes() + " Fire Resistance");
        System.out.println("+" + itemStats.getlRes() + " Lightning Resistance");
        System.out.println("+" + itemStats.getpRes() + " Poison Resistance");

    }

    @Override
    public void specialEffect(Player player) {

    }

    @Override
    public String getName() {
        return item.getName() + "Shoes of Swiftness";
    }

    @Override
    public void updatePlayerStats(Player player) {
        player.getMyStats().setHp(player.getMyStats().getHp() + itemStats.getHp());
        player.getMyStats().setMaxHP(player.getMyStats().getMaxHP() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + itemStats.getArm());
        player.getMyStats().setcRes(player.getMyStats().getcRes() + itemStats.getcRes());
        player.getMyStats().setfRes(player.getMyStats().getfRes() + itemStats.getfRes());
        player.getMyStats().setpRes(player.getMyStats().getpRes() + itemStats.getpRes());
        player.getMyStats().setlRes(player.getMyStats().getlRes() + itemStats.getlRes());
        player.getMyStats().setDodge(player.getMyStats().getDodge() + itemStats.getDodge());
    }

    @Override
    public String getDescription() {
        return "Increases Dodge chance by 25%" + item.getDescription();
    }
}
