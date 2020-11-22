import java.util.ArrayList;

public class Player extends Character {

    private int N; // size of inventory space
    private ItemStorage itemStorage; //Item class to use methods
    private ArrayList<Item> inventory = new ArrayList<>(); // inventory of items

    // special attack and effects
    private int specialAttack = 0; // increases every physical attack
    private int cSpellCounter = 0;
    private int fSpellCounter = 0;
    private int debuffTurnCounter = 0; // resets debuff after every 3 turns
    private int debuffCounter;
    private boolean debuffOn = false; // poison spell causes enemy to lose 10 hp each turn

    private int spellCounter = 0; // whenever the player casts a spell
    private int attackCounter = 0; // whenever the player attacks with physical attack
    private int hitsTakenCounter = 0; // whenever the player takes a hit from the enemy
    private int turnCounter = 0; // whenever the player attacks/enemy attacks, add 1 to this counter
    private int blockCounter = 0; // whenever the player uses block

    public int getcSpellCounter() {
        return cSpellCounter;
    }

    public void setcSpellCounter(int cSpellCounter) {
        this.cSpellCounter = cSpellCounter;
    }

    public int getfSpellCounter() {
        return fSpellCounter;
    }

    public void setfSpellCounter(int fSpellCounter) {
        this.fSpellCounter = fSpellCounter;
    }

    public int getDebuffTurnCounter() {
        return debuffTurnCounter;
    }

    public void setDebuffTurnCounter(int debuffTurnCounter) {
        this.debuffTurnCounter = debuffTurnCounter;
    }

    public int getDebuffCounter() {
        return debuffCounter;
    }

    public void setDebuffCounter(int debuffCounter) {
        this.debuffCounter = debuffCounter;
    }

    public boolean isDebuffOn() {
        return debuffOn;
    }

    public void setDebuffOn(boolean debuffOn) {
        this.debuffOn = debuffOn;
    }

    public int getSpellCounter() {
        return spellCounter;
    }

    public void setSpellCounter(int spellCounter) {
        this.spellCounter = spellCounter;
    }

    public int getAttackCounter() {
        return attackCounter;
    }

    public void setAttackCounter(int attackCounter) {
        this.attackCounter = attackCounter;
    }

    public int getHitsTakenCounter() {
        return hitsTakenCounter;
    }

    public void setHitsTakenCounter(int hitsTakenCounter) {
        this.hitsTakenCounter = hitsTakenCounter;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public int getBlockCounter() {
        return blockCounter;
    }

    public void setBlockCounter(int blockCounter) {
        this.blockCounter = blockCounter;
    }

    public boolean isShieldEffect() {
        return shieldEffect;
    }

    public void setShieldEffect(boolean shieldEffect) {
        this.shieldEffect = shieldEffect;
    }

    public int getOriginalDmg() {
        return originalDmg;
    }

    public void setOriginalDmg(int originalDmg) {
        this.originalDmg = originalDmg;
    }

    private boolean shieldEffect;


    private int originalDmg;


    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public ItemStorage getItem() {
        return itemStorage;
    }

    public void setItem(ItemStorage itemStorage) {
        this.itemStorage = itemStorage;
    }

    public Player(String name) {
        this.setName(name);
        this.N = 10; // fixed inventory size
        this.itemStorage = new ItemStorage();

        // default values for each new player
        this.getMyStats().setHp(100);
        this.getMyStats().setDmg(5);
        this.getMyStats().setArm(5);
        this.getMyStats().setMana(20);
        this.getMyStats().setcRes(randomInt(0,5));
        this.getMyStats().setpRes(randomInt(0,5));
        this.getMyStats().setfRes(randomInt(0,5));
        this.getMyStats().setlRes(randomInt(0,5));
        this.getMyStats().setDodge(5);
        this.getMyStats().setCrit(5);
    }

    // generates random integer
    public int randomInt(int min, int max) {
        return (int) (Math.random()*(max-min))+min;
    }

    // randomizes player's damages for each attack type
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
        // randomizes damage and calculates damage based enemy armor
        int pdmg = randomizeDmg("Physical");
        int enemyRes = enemy.getMyStats().getArm();
        int tdmg = (pdmg < enemyRes) ? 0 : pdmg-enemyRes;  //Modified here so that enemy doesn't gain health if it's armour exceeds dmg taken

        // 4th physical attack does special attack
        if(specialAttack == 4) {
            tdmg = ((getMyStats().getDmg()*2) < enemyRes) ? 0 : ((getMyStats().getDmg()*2) - enemyRes); //Also modified here; same case as above
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            System.out.println("Player uses special physical attack and does " + tdmg + " damage. Enemy loses " + tdmg + " HP.");
            specialAttack = 0;								//Set specialAttack to zero after performing special attack, else you'll just be doing it all the time.. then it's not very special
        }
        else {
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            System.out.println("Player uses physical attack and does " + tdmg + " damage. Enemy loses " + tdmg + " HP.");
            specialAttack++;
        }

        // checks poison debuff and enemy loses 10 hp if poisoned
        if(debuffOn){
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (10*debuffCounter));
            System.out.println("Enemy is poisoned and loses an additional " + (10*debuffCounter) + " HP.");
            debuffTurnCounter++;
        }

        increaseMana();
        attackCounter++;
        turnCounter++;

        // sets enemy hp to 0 if hp is negative
        int enemyHp = enemy.getMyStats().getHp();
        if (enemyHp < 0) {
            enemy.getMyStats().setHp(0);
        }
    }

    public void coldAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor and cold resistance
        int pdmg = randomizeDmg("Cold");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getcRes();
        int tdmg = (pdmg < enemyRes) ? 0 : pdmg-enemyRes;

        // needs 5 mana to cold spell
        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use cold spell!");
        }
        else {
            // 3rd cold spell freezes enemy
            if(cSpellCounter == 3) {
                useSpell();
                tdmg = ((getMyStats().getCold()*3) < enemyRes) ? 0 : ((getMyStats().getCold()*3) - enemyRes);
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                System.out.println("Player uses cold spell and does " + tdmg + " damage. Enemy loses " + tdmg + " HP and is frozen.");
                cSpellCounter = 0;
            }
            else {
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                System.out.println("Player uses cold spell and does " + tdmg + " damage. Enemy loses " + tdmg + " HP.");
                cSpellCounter++;
            }

            // checks poison debuff
            if(debuffOn){
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (10*debuffCounter));
                System.out.println("Enemy is poisoned and loses an additional " + (10*debuffCounter) + " HP.");
                debuffTurnCounter++;
            }

            increaseMana();
            turnCounter++;

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }
    }

    public void poisonAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor and poison resistance
        int pdmg = randomizeDmg("Poison");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getpRes();
        int tdmg = (pdmg < enemyRes) ? 0 : pdmg-enemyRes;

        // needs 5 mana to use poison spell
        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use poison spell!");
        }
        else {
            // sets debuff on enemy
            useSpell();

            debuffOn = true;
            debuffCounter++;
            debuffTurnCounter = 0;
            if(debuffOn){
                System.out.println("Enemy is poisoned.");
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (10*debuffCounter));
                debuffTurnCounter++;
            }

            increaseMana();
            turnCounter++;

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }
    }

    public void fireAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor and fire resistance
        int pdmg = randomizeDmg("Fire");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getfRes();
        int tdmg = (pdmg < enemyRes) ? 0 : pdmg-enemyRes;

        // needs 5 mana to use fire spell
        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use fire spell!");
        }
        else {
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            System.out.println("Player uses fire spell and does " + tdmg + " damage. Enemy loses " + tdmg + " HP.");
            fSpellCounter++;

            // 2nd fire spell does bonus damage
            if(fSpellCounter == 2) {
                useSpell();
                tdmg = ((getMyStats().getFire()*3) < enemyRes) ? 0 : ((getMyStats().getFire()*3) - enemyRes);
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                System.out.println("Player does bonus fire damage. Enemy loses " + tdmg + " HP.");
                fSpellCounter = 0;
            }


            // checks poison debuff
            if(debuffOn){
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (10*debuffCounter));
                System.out.println("Enemy is poisoned and loses an additional " + (10*debuffCounter) + " HP.");
                debuffTurnCounter++;
            }

            increaseMana();
            turnCounter++;

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }
    }

    public void lightningAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor and lightning resistance
        int pdmg = randomizeDmg("Lightning");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getlRes();
        int tdmg = (pdmg < enemyRes) ? 0 : pdmg-enemyRes;

        // needs 5 mana to use lightning spell
        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use lightning spell!");
        }
        else {
            // enemy damage increases
            useSpell();
            tdmg = ((getMyStats().getLightning()*4) < enemyRes) ? 0 : ((getMyStats().getLightning()*4) - enemyRes);
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            System.out.println("Player uses lightning spell and does " + tdmg + " damage. Enemy loses " + tdmg + " HP.");

            // checks poison debuff
            if(debuffOn){
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (10*debuffCounter));
                System.out.println("Enemy is poisoned and loses an additional " + (10*debuffCounter) + " HP.");
                debuffTurnCounter++;
            }

            increaseMana();
            turnCounter++;

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }
    }

    // resets all the counters
    public void resetCounters() {
        specialAttack = 0;
        cSpellCounter = 0;
        fSpellCounter = 0;
        debuffCounter = 0;
        debuffTurnCounter = 0;
        debuffOn = false;
        spellCounter = 0;
        attackCounter = 0;
        hitsTakenCounter = 0;
        turnCounter = 0;
        blockCounter = 0;
    }

    // removes poison buff from enemy
    public void resetDebuff() {
        debuffOn = false;
        debuffTurnCounter = 0;
        debuffCounter = 0;
    }

    // adds +2 mana each time a player attacks
    public void increaseMana() {
        int cMana = getMyStats().getMana()+2;
        if (cMana >= 20) {
            getMyStats().setMana(20);
        }
        else {
            getMyStats().setMana(cMana);
        }
    }

    // returns true is attack if blocked, enemy cannot damage player
    public boolean blockEnemy() {
        int cMana = getMyStats().getMana()+5;
        if (cMana >= 20) {
            getMyStats().setMana(20);
        }
        else {
            getMyStats().setMana(cMana);
        }
        blockCounter++;
        System.out.println("Player blocks enemy and obtains 5 mana!");
        return true;
    }

    // each spell uses 5 mana
    public boolean useSpell() {
        int cMana = getMyStats().getMana()-5;
        if(cMana <= 0) {
            getMyStats().setMana(0);
        }
        else {
            getMyStats().setMana(cMana);
        }
        spellCounter++;
        return true;
    }

    // updates inventory with obtained item
    public void updateInventory(Player player) {
        inventory.add(itemStorage.getNewItem());
        inventory.get(getInventory().size()-1).updatePlayerStats(player);
        Item item = inventory.get(inventory.size()-1);

        // displays item name + description
        System.out.println();
        System.out.println("Player obtains new item: " + item.getName() + "!");
        System.out.println(item.getName() + ": " + item.getDescription());
        System.out.println();

        originalDmg = player.getMyStats().getDmg();
    }

    //checks the start of the turn if any conditions for special effect from items are met
    public void startOfTurn(Player player) {
        for (Item i : inventory) {
            i.specialEffect(player);
        }
    }

    //prints out the inventory of all the items with their name, stats, and description
    public void displayInventory() {
        System.out.println("Displaying inventory...");
        for (Item i : inventory) {
            System.out.println(i.getName());
            i.itemStats();
            System.out.println(i.getDescription());
        }
    }


	public int getSpecialAttack() {
        return specialAttack;
    }


    public static void main(String[] args) {
        // Test
        Player player = new Player("Zero");
        System.out.println(" *** CHARACTER STATS ***");
        System.out.println("Name: " + player.getName());
        System.out.println("Hp: " + player.getMyStats().getHp());
        System.out.println("Mana: " + player.getMyStats().getMana());
        System.out.println("Armor: " + player.getMyStats().getArm());

        System.out.println(" *** DAMAGE ***");
        System.out.println("Physical Damage:" + player.getMyStats().getDmg());
        System.out.println("Cold Damage:" + player.getMyStats().getCold());
        System.out.println("Poison Damage:" + player.getMyStats().getPoison());
        System.out.println("Fire Damage:" + player.getMyStats().getFire());
        System.out.println("Lightning Damage:" + player.getMyStats().getLightning());

        System.out.println(" *** RESISTANCES ***");
        System.out.println("Cold Resistance:" + player.getMyStats().getcRes());
        System.out.println("Poison Resistance:" + player.getMyStats().getpRes());
        System.out.println("Fire Resistance:" + player.getMyStats().getfRes());
        System.out.println("Lightning Resistance:" + player.getMyStats().getlRes());
    }
}

