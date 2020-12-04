package coolGame.model.item;

import coolGame.model.character.Player;

/**
 * A simple item decorator class that adds base strings for the description and the names
 */
public class ItemDec implements Item {

    /**
     * nothing
     */
    @Override
    public void itemStats() {

    }

    /**
     * nothing
     *
     * @param player - player argument that the special effects of items use to enhance.
     */
    @Override
    public void specialEffect(Player player) {

    }

    /**
     * This method is a string that all the names items use
     *
     * @return - a string
     */
    @Override
    public String getName() {
        return "Item: ";
    }

    /**
     * nothing
     *
     * @param player - player argument that is used to add the item's stats to the player's stats
     */
    @Override
    public void updatePlayerStats(Player player) {

    }

    /**
     * This method is a string that all descriptions of items use
     *
     * @return - a string formatting
     */
    @Override
    public String getDescription() {
        return System.lineSeparator() + "-----------------------------------------------------------------------------";
    }
}
