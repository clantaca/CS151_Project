public interface Item {
    public void itemStats();
    public void specialEffect(Player player);
    public String getName();
    public void updatePlayerStats(Player player);
    public String getDescription();
}
