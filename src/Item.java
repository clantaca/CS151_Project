public class Item extends Stats {
    public int value;
    public String itemName;
    
    public Item() {
    	
    	double randomItem = Math.random();
    	
    	//for example it chooses 1, which is equal to wand
    	randomItem = 1;
    	
    	if (randomItem == 1)
    		Wand(10);
    	else if (randomItem == 2)
    		Sword(2);
    	else
    		Shield(3);
    	
    	
    }
    
    public void Wand (int value) {
        setFire(fire+2);
        setCold(cold+2);
        setLightning(lightning+2);
        setPoison(poison+2);
        itemName.equals("Powerful Wand");
    }
    
    public void Sword (int value) {
        setFire(fire+2);
        setCold(cold+2);
        setLightning(lightning+2);
        setPoison(poison+2);
        itemName.equals("Powerful Wand");
    }
    
    public void Shield (int value) {
        setFire(fire+2);
        setCold(cold+2);
        setLightning(lightning+2);
        setPoison(poison+2);
        itemName.equals("Powerful Wand");
    }

}
