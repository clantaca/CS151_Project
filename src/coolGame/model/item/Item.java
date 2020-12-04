package coolGame.model.item;

import coolGame.model.character.Player;

/**
 * Model for the interface class, Item, that would display name, description, stats, and update the player stats and
 * apply special effects
 */
public interface Item {

    /**
     * Displays the stats of the item
     */
    //Method to print out all the stats of the item
    void itemStats();

    /**
     * Special effect that runs at the start of every turn to see if conditions for the item's special
     * effects are met
     * @param player
     */
    //Continuously runs at the start of every turn to see if conditions for the item's special effects are met
    void specialEffect(Player player);

    /**
     * Returns the name of item
     * @return name
     */
    //The name of the Item
    String getName();

    /**
     * Updates the player's stats with the stats of the item
     * @param player
     */
    //Updates the player's stats with the stats of the item
    void updatePlayerStats(Player player);

    /**
     * Returns the description of the item
     * @return description
     */
    //The description of the specialeffect
    String getDescription();
}
