import java.util.ArrayList;

public class Player extends Character {

    private int N; // size of inventory space
    private ItemStorage itemStorage; //Item class to use methods
    private ArrayList<Item> inventory = new ArrayList<>(); // inventory of items

    // special attack and effects
    private int specialAttack = 0; // increases every physical attack
    private int cSpellCounter = 0;
    private int fSpellCounter = 0;
    private int debuffCounter = 0; // resets debuff after every 3 turns
    private boolean debuffOn = false; // poison spell causes enemy to lose 10 hp each turn

    //TODO: ADD THESE COUNTERS TO THEIR RESPECTIVE METHODS
    public int spellCounter = 0; // whenever the player casts a spell
    public int attackCounter = 0; // whenever the player attacks with physical attack
    public int hitsTakenCounter = 0; // whenever the player takes a hit from the enemy
    public int turnCounter = 0; // whenever the player attacks/enemy attacks, add 1 to this counter
    public int blockCounter = 0; // whenever the player uses block

    //TODO: ADD TO START OF TURN AND END OF BATTLE
    public boolean startOfTurn;
    public boolean endOfBattle;

    public int originalDmg;

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
        int tdmg = (pdmg < enemyRes) ? 0 : pdmg-enemyRes;  //Modified here so that enemy doesn't gain health if it's armour exceeds dmg taken
        
        if(specialAttack == 4) {
            System.out.println("Physical Special Attack");
            tdmg = ((getMyStats().getDmg()*2) < enemyRes) ? 0 : ((getMyStats().getDmg()*2) - enemyRes); //Also modified here; same case as above
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);		
            specialAttack = 0;								//Set specialAttack to zero after performing special attack, else you'll just be doing it all the time.. then it's not very special
        }
        else {
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            specialAttack++;
        }

        if(debuffOn){
            if(debuffCounter == 3) {
                resetDebuff();
            }
            else {
                System.out.println("Enemy is poisoned");
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - 10);
                debuffCounter++;
            }
        }

        increaseMana();

        int enemyHp = enemy.getMyStats().getHp();
        if (enemyHp < 0) {
            enemy.getMyStats().setHp(0);
        }
    }

    public void coldAttack(Enemy enemy) {
        int pdmg = randomizeDmg("Cold");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getcRes();
        int tdmg = (pdmg < enemyRes) ? 0 : pdmg-enemyRes;

        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana!");
        }
        else {
            // 3rd cold spell freezes enemy
            if(cSpellCounter == 3) {
                useSpell();
                tdmg = ((getMyStats().getCold()*3) < enemyRes) ? 0 : ((getMyStats().getCold()*3) - enemyRes);
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                System.out.println("Enemy is frozen");
                cSpellCounter = 0;
            }
            else {
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                cSpellCounter++;
            }

            if(debuffOn){
                if(debuffCounter == 3) {
                    resetDebuff();
                }
                else {
                    System.out.println("Enemy is poisoned");
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - 10);
                    debuffCounter++;
                }
            }

            increaseMana();

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }
    }

    public void poisonAttack(Enemy enemy) {
        int pdmg = randomizeDmg("Poison");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getpRes();
        int tdmg = (pdmg < enemyRes) ? 0 : pdmg-enemyRes;

        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana!");
        }
        else {
            // sets debuff on enemy
            useSpell();
            tdmg = ((getMyStats().getPoison()*3) < enemyRes) ? 0 : ((getMyStats().getPoison()*3) - enemyRes);
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            System.out.println("Enemy poisoned");

            debuffOn = true;
            increaseMana();

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }
    }

    public void fireAttack(Enemy enemy) {
        int pdmg = randomizeDmg("Fire");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getfRes();
        int tdmg = (pdmg < enemyRes) ? 0 : pdmg-enemyRes;

        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana!");
        }
        else {
            // 2nd fire spell does bonus damage
            if(fSpellCounter == 2) {
                useSpell();
                System.out.println("Bonus fire damage");
                tdmg = ((getMyStats().getFire()*3) < enemyRes) ? 0 : ((getMyStats().getFire()*3) - enemyRes);
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                fSpellCounter = 0;
            }
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            fSpellCounter++;

            if(debuffOn){
                if(debuffCounter == 3) {
                    resetDebuff();
                }
                else {
                    System.out.println("Enemy is poisoned");
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - 10);
                    debuffCounter++;
                }
            }

            increaseMana();

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }
    }

    public void lightningAttack(Enemy enemy) {
        int pdmg = randomizeDmg("Lightning");
        int enemyRes = enemy.getMyStats().getArm() + enemy.getMyStats().getlRes();
        int tdmg = (pdmg < enemyRes) ? 0 : pdmg-enemyRes;

        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana!");
        }
        else {
            // enemy damage increases
            useSpell();
            tdmg = ((getMyStats().getLightning()*4) < enemyRes) ? 0 : ((getMyStats().getLightning()*4) - enemyRes);
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
            System.out.println("Lightning attack");

            if(debuffOn){
                if(debuffCounter == 3) {
                    resetDebuff();
                }
                else {
                    System.out.println("Enemy is poisoned");
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - 10);
                    debuffCounter++;
                }
            }

            increaseMana();

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }
    }

    public void resetDebuff() {
        debuffOn = false;
        debuffCounter = 0;
    }

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
        return true;
    }

    // updates inventory with obtained item
    public void updateInventory(Player player) {
        inventory.add(itemStorage.getNewItem());
        inventory.get(getInventory().size()-1).updatePlayerStats(player);
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

