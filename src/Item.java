public interface Item {

    //Method to print out all the stats of the item
    public void itemStats();

    //Continuously runs at the start of every turn to see if conditions for the item's special effects are met
    public void specialEffect(Player player);

    //The name of the Item
    public String getName();

    //Updates the player's stats with the stats of the item
    public void updatePlayerStats(Player player);

    //The description of the specialeffect
    public String getDescription();
}
