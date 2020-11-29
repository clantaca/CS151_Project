package coolGame.model;

public class ItemDec implements Item {
    @Override
    public void itemStats() {

    }

    @Override
    public void specialEffect(Player player) {

    }

    @Override
    public String getName() {
        return "Item: ";
    }

    @Override
    public void updatePlayerStats(Player player) {

    }

    @Override
    public String getDescription() {
        return System.lineSeparator() + "-----------------------------------------------------------------------";
    }
}
