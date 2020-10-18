public class Item extends Character {
    public int value;
    public String itemName;
    public void Wand (int value) {
        setFire(fire+2);
        setCold(cold+2);
        setLightning(lightning+2);
        setPoison(poison+2);
        itemName.equals("Powerful Wand");
    }

}
