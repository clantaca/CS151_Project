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
    public void itemStats() {
        weaponCounter++;
        setDmg(randomInt(10,4));
        originalDmg = getDmg();
    }

    //every 2nd attack deals bonus damage
    @Override
    public void specialEffect() {
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
