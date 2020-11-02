public class BloodlustGauntlets extends Item {
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    @Override
    public String getName() {
        return "Bloodlust Gauntlets";
    }

    @Override
    public void onHealthThreshold() {
        setDmg(randomInt(25,10));
        setHp(randomInt(0,-30));
        setArm(randomInt(0,-5));
        setcRes(randomInt(0,-5));
        setlRes(randomInt(0,-5));
        setpRes(randomInt(0,-5));
        setfRes(randomInt(0,-5));
    }

    @Override
    public void specialEffect() {
        //TODO RETRIEVE PLAYER HEALTH, WHEN LOWER THAN CERTAIN THRESHOLD, INCREASE DAMAGE
}
}
