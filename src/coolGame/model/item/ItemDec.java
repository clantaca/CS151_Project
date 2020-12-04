package coolGame.model.item;

import coolGame.model.character.Player;

/**
 * Decorator class for Item
 */
public class ItemDec implements Item {
    /**
     * Displays the stats of the item
     */
    @Override
    public void itemStats() {

    }

    /**
     * Constructs the special effects for the item
     * @param player
     */
    @Override
    public void specialEffect(Player player) {

    }

    /**
     * Returns the name of item
     * @return name
     */
    @Override
    public String getName() {
        return "Item: ";
    }

    /**
     * Updates/Adds the player's stats according to the item's stats
     * @param player
     */
    @Override
    public void updatePlayerStats(Player player) {

    }

    /**
     * Returns the description of the item
     * @return description
     */
    @Override
    public String getDescription() {
        return System.lineSeparator() + "-----------------------------------------------------------------------";
    }
}
