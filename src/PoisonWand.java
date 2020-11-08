public class PoisonWand extends Item {

	//Start
/*	public PoisonWand(Player player) {

		updatePlayerStats(player);

	}

    public PoisonWand() {

    }*/

    @Override
    public void updatePlayerStats(Player player) {
		
		//Poison want increases player health by one
		player.getMyStats().setPoison(player.getMyStats().getPoison() + randomInt(8, 4));
        player.getMyStats().setCold(player.getMyStats().getCold() + randomInt(8, 4));
        player.getMyStats().setFire(player.getMyStats().getFire() + randomInt(8, 4));
        player.getMyStats().setLightning(player.getMyStats().getLightning() + randomInt(8, 4));
        player.getMyStats().setPoison(player.getMyStats().getPoison() + randomInt(8, 4));
		
	}
	//End
	
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
    public void specialEffect(Player player) {
        if (turnCounter == 1) {
            //TODO: MAKE IT APPLY A DEBUFF TO THE ENEMY WHERE THEY TAKE DAMAGE EVERY TURN
        }
    }
}
