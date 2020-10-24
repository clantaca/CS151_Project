public class Player extends Character {

    private int N; // size of inventory space
    private Item[] item; // inventory of items
    private int specialAttack = 0; // increases after every attack

    public Player(String name) {
        this.setName(name);
        this.N = 10; // fixed inventory size
        this.item = new Item[N];
        // initializing all items to null
        for (int i = 0; i < N; i++) {
            this.item = null;
        }

        // default values for each new player
        this.getMyStats().setHp(100);
        this.getMyStats().setDmg(5);
        this.getMyStats().setArm(5);
        this.getMyStats().setcRes(randomInt(0,5));
        this.getMyStats().setpRes(randomInt(0,5));
        this.getMyStats().setfRes(randomInt(0,5));
        this.getMyStats().setlRes(randomInt(0,5));
    }

    // generates random integer
    public int randomInt(int max, int min) {
        return (int) (Math.random()*(max-min))+min;
    }

    // randomizes player's damages
    public int randomizeDmg(String dmgType) {
        int pdmg = 0;
        int min = 0;
        int max = 0;
        switch (dmgType) {

            case "Physical":
                max = getMyStats().getDmg();
                pdmg = randomInt(min, max);
                break;

            case "Cold":
                max = getMyStats().getCold();
                pdmg = randomInt(min, max);
                break;

            case "Poison":
                max = getMyStats().getPoison();
                pdmg = randomInt(min, max);
                break;

            case "Fire":
                max = getMyStats().getFire();
                pdmg = randomInt(min, max);
                break;

            case "Lightning":
                max = getMyStats().getLightning();
                pdmg = randomInt(min, max);
                break;
        }
        return pdmg;
    }

    public void physicalAttack(Enemy enemy) {

        int pdmg = randomizeDmg("Physical");
        int enemyRes = enemy.getMyStats().getArm();
        int tdmg = (pdmg - enemyRes);

        if(specialAttack == 10) {
            System.out.println("Physical Special Attack");
            tdmg = ((getMyStats().getDmg()*2) - enemyRes);
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
        }
        else {
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            specialAttack++;
        }

        int enemyHp = enemy.getMyStats().getHp();
        if (enemyHp < 0) {
            enemy.getMyStats().setHp(0);
        }
    }

    public void coldAttack(Enemy enemy) {
        int pdmg = randomizeDmg("Cold");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getcRes();
        int tdmg = (pdmg - enemyRes);

        if(specialAttack == 10) {
            System.out.println("Cold Special Attack");
            tdmg = ((getMyStats().getDmg()*2) - enemyRes);
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
        }
        else {
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            specialAttack++;
        }

        int enemyHp = enemy.getMyStats().getHp();
        if (enemyHp < 0) {
            enemy.getMyStats().setHp(0);
        }
    }

    public void poisonAttack(Enemy enemy) {
        int pdmg = randomizeDmg("Cold");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getpRes();
        int tdmg = (pdmg - enemyRes);

        if(specialAttack == 10) {
            System.out.println("Poison Special Attack");
            tdmg = ((getMyStats().getDmg()*2) - enemyRes);
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            specialAttack++;
        }
        else {
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
        }

        int enemyHp = enemy.getMyStats().getHp();
        if (enemyHp < 0) {
            enemy.getMyStats().setHp(0);
        }
    }

    public void fireAttack(Enemy enemy) {
        int pdmg = randomizeDmg("Fire");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getfRes();
        int tdmg = (pdmg - enemyRes);

        if(specialAttack == 10) {
            System.out.println("Fire Special Attack");
            tdmg = ((getMyStats().getDmg()*2) - enemyRes);
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            specialAttack++;
        }
        else {
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
        }

        int enemyHp = enemy.getMyStats().getHp();
        if (enemyHp < 0) {
            enemy.getMyStats().setHp(0);
        }
    }

    public void lightningAttack(Enemy enemy) {
        int pdmg = randomizeDmg("Lightning");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getlRes();
        int tdmg = (pdmg - enemyRes);

        if(specialAttack == 10) {
            System.out.println("Lightning Special Attack");
            tdmg = ((getMyStats().getDmg()*2) - enemyRes);
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            specialAttack++;
        }
        else {
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
        }

        int enemyHp = enemy.getMyStats().getHp();
        if (enemyHp < 0) {
            enemy.getMyStats().setHp(0);
        }
    }

    // returns true is attack if blocked, enemy cannot damage player
    public boolean blockEnemy(Enemy enemy) {
        return true;
    }

    // updates inventory with obtained item
    public void updateInventory(Item item) {
        for (int i = 0; i < N; i++) {
            if (this.item[i] == null) {
                this.item[i] = item;
                break;
            }
        }
    }

    // checks if there is space in inventory to add item
    public boolean checkInventory(Item item) {
        if (item == null)
            throw new NullPointerException("Item is null");
        for (int i = 0; i < N; i++) {
            if (this.item[i] == null) {
                return true; // inventory has space
            }
        }
        return false;
    }

    public void displayInventory() {
        System.out.println("Displaying inventory...");
        int count = 1;
        for (int i = 0; i < N; i++) {
            System.out.println(count + ". " + item[1]);
            count++;
        }
    }

	public Item[] getItem() {
        return item;
	}

	public void setItem(Item item) {
        if (checkInventory(item)) {
            updateInventory(item);
        }
        else
            System.out.println("Inventory Full. Cannot obtain item.");
	}

    public static void main(String[] args) {
        // Test
        Player player = new Player("");
        System.out.println(player.getMyStats());
    }
}

