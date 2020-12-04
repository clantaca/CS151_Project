package coolGame.model.item;

import coolGame.model.character.Player;

/**
 * The item interface is the interface in which every single item implements. This is used to give all the items the same
 * method names so it is easier to go through all the special effects and item stats of the inventory arraylist.
 */
public interface Item {

    /**
     * Method that all the items use to print out their item stats. This method is used everytime a new item is picked
     * out of the item storages and whenever you print out your inventory
     */
    void itemStats();

    /**
     * This method runs at the start of every single turn to check if the conditions for the unique special effects
     * are met. If they are, they update the player class through the argument.
     *
     * @param player - player argument that the special effects of items use to enhance.
     */
    void specialEffect(Player player);

    /**
     * Method that all the items use to print out their name. This method is used everytime a new item is picked
     * out of the item storages and whenever you print out your inventory
     */
    String getName();

    /**
     * This method runs every single time you pick up a new item. This updates the the player's stats with the item's stats
     *
     * @param player - player argument that is used to add the item's stats to the player's stats
     */
    void updatePlayerStats(Player player);

    /**
     * Method that all the items use to print out their description. This method is used everytime a new item is picked
     * out of the item storages and whenever you print out your inventory
     */
    String getDescription();
}
