public class MageChargerGloves extends Item {
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    @Override
    public String getName() {
        return "Mage's Charging up Gloves";
    }

    @Override
    public void itemStats() {
        setPoison(randomInt(4, 2));
        setCold(randomInt(4,2));
        setLightning(randomInt(4,2));
        setFire(randomInt(4,4));
        setHp(randomInt(10,5));
        setlRes(randomInt(5,0));
        setcRes(randomInt(5,0));
        setpRes(randomInt(5,0));
        setfRes(randomInt(5,0));
        setArm(randomInt(5,0));
    }

    //each spell casts increase all magic damage
    @Override
    public void specialEffect() {
        setPoison(getPoison()+(spellCounter*2));
        setCold(getCold());
        getFire();
        getLightning();
    }
}
