package coolGame.model.item;

import coolGame.model.Stats;
import coolGame.model.character.Player;

public class MageBerserkerGloves implements Item {

    Item item;
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }
    private Stats itemStats = new Stats();

    public MageBerserkerGloves(Item item) {
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
        this.item = item;
    }

    @Override
    public String getName() {
        return item.getName() + "Mage's Berserker Gloves";
    }

    @Override
    public String getDescription() {
        return "Every spell cast increases the damage of consecutive spells cast by 2." + item.getDescription();
    }

    @Override
    public void updatePlayerStats (Player player) {
        player.getMyStats().setHp(player.getMyStats().getHp() + randomInt(20, 15));
        player.getMyStats().setMaxHP(player.getMyStats().getMaxHP() + itemStats.getHp());
        player.getMyStats().setArm(player.getMyStats().getArm() + randomInt(5, 3));
        player.getMyStats().setcRes(player.getMyStats().getcRes() + randomInt(5, 3));
        player.getMyStats().setfRes(player.getMyStats().getfRes() + randomInt(5, 3));
        player.getMyStats().setpRes(player.getMyStats().getpRes() + randomInt(5, 3));
        player.getMyStats().setlRes(player.getMyStats().getlRes() + randomInt(5, 3));
        player.getMyStats().setCold(player.getMyStats().getCold() + randomInt(5, 3));
        player.getMyStats().setFire(player.getMyStats().getFire() + randomInt(5, 3));
        player.getMyStats().setPoison(player.getMyStats().getPoison() + randomInt(5, 3));
        player.getMyStats().setLightning(player.getMyStats().getLightning() + randomInt(5, 3));
    }
    @Override
    public void itemStats() {
        System.out.println("+" + itemStats.getHp() + " Health");
        System.out.println("+" + itemStats.getArm() + " Armour");
        System.out.println("+" + itemStats.getcRes() + " Cold Resistance");
        System.out.println("+" + itemStats.getfRes() + " Fire Resist");
        System.out.println("+" + itemStats.getlRes() + " Lightning Resistance");
        System.out.println("+" + itemStats.getpRes() + " Poison Resistance");
        System.out.println("+" + itemStats.getCold() + " Cold Damage");
        System.out.println("+" + itemStats.getFire() + " Fire Damage");
        System.out.println("+" + itemStats.getLightning() + " Lightning Damage");
        System.out.println("+" + itemStats.getPoison() + " Poison Damage");
    }

    //each spell casts increase all magic damage
    @Override
    public void specialEffect(Player player) {
        player.bonuslDmg = player.getSpellCounter()*2;
        player.bonusfDmg = player.getSpellCounter()*2;
        player.bonuspDmg = player.getSpellCounter()*2;
        player.bonuscDmg = player.getSpellCounter()*2;
}
}
