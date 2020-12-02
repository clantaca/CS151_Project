package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

public class VampireGreatsword implements Item {

    Item item;
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }
    private Stats itemStats = new Stats();

    public VampireGreatsword(Item item) {
        itemStats.setDmg(randomInt(15, 10));
        itemStats.setHp(randomInt(20,10));
        itemStats.setCrit(5);
        this.item = item;
    }
    @Override
    public String getName() {
        return item.getName() + "Vampiric Greatsword";
    }

    @Override
    public String getDescription() {
        return "Your physical special attack now procs every other atack." + item.getDescription();
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
    }

    //every 3rd attack deals bonus damage
    @Override
    public void specialEffect(Player player) {
        player.physSpecialAttackCounter = 1;
    }
}

