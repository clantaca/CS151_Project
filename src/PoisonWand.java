public class PoisonWand extends Item {


    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    @Override
    public String getName() {
        return "Poisonous Wand";
    }

    @Override
    public void itemStats() {
        setPoison(randomInt(8, 4));
        setCold(randomInt(4,1));
        setLightning(randomInt(4,2));
        setFire(randomInt(8,4));
        setpRes(randomInt(10,0));
    }

    @Override
    public void specialEffect() {
        if (turnCounter == 1) {
            //TODO: MAKE IT APPLY A DEBUFF TO THE ENEMY WHERE THEY TAKE DAMAGE EVERY TURN
        }
    }
}
