public class RejuvenatingChestplate extends Item {

    private int originalHp;

    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    @Override
    public String getName() {
        return "Rejuvenating Chestplate";
    }

    @Override
    public void itemStats() {
        setHp(randomInt(50,25));
        setArm(randomInt(20,10));
        setcRes(randomInt(20,10));
        setlRes(randomInt(20,10));
        setpRes(randomInt(20,10));
        setfRes(randomInt(20,10));
        originalHp = getHp();
    }

    //heals self after every 4th hit taken
    @Override
    public void specialEffect() {
        if (hitCounter == 4) {
            setHp(getHp() + 20);
            hitCounter = 0;
        }
            else {
                setHp(originalHp);
                hitCounter++;
            }
    }
}
