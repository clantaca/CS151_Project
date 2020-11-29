package coolGame.model.character;

import coolGame.model.item.Item;
import coolGame.model.item.ItemStorage;

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
    public boolean blocked = true;

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
        this.getMyStats().setMaxHP(100);
        this.getMyStats().setDmg(20);
        this.getMyStats().setCold(20);
        this.getMyStats().setPoison(20);
        this.getMyStats().setFire(20);
        this.getMyStats().setLightning(20);
        this.getMyStats().setArm(5);
        this.getMyStats().setMana(20);
        this.getMyStats().setMaxMana(20);
        this.getMyStats().setcRes(5);
        this.getMyStats().setpRes(5);
        this.getMyStats().setfRes(5);
        this.getMyStats().setlRes(5);
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
        int min = 1;
        int max = 0;
        switch (dmgType) {

            case "Physical":
                max = getMyStats().getDmg();
                // if player has over 5 dmg, the min is 10% over their max dmg
                if (max > 5) {
                    min = (int)(max*(80.0f/100.0f));
                }
                pdmg = randomInt(min, max);
                //System.out.println(pdmg);
                break;

            case "Cold":
                max = getMyStats().getCold();
                if (max > 5) {
                    min = (int)(max*(80.0f/100.0f));
                }
                pdmg = randomInt(min, max);
                //System.out.println(pdmg);
                break;

            case "Poison":
                max = getMyStats().getPoison();
                if (max > 5) {
                    min = (int)(max*(80.0f/100.0f));
                }
                pdmg = randomInt(min, max);
                //System.out.println(pdmg);
                break;

            case "Fire":
                max = getMyStats().getFire();
                if (max > 5) {
                    min = (int)(max*(80.0f/100.0f));
                }
                pdmg = randomInt(min, max);
                //System.out.println(pdmg);
                break;

            case "Lightning":
                max = getMyStats().getLightning();
                if (max > 5) {
                    min = (int)(max*(80.0f/100.0f));
                }
                pdmg = randomInt(min, max);
                //System.out.println(pdmg);
                break;
        }
        return pdmg;
    }

    public void physicalAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor
        int pdmg = randomizeDmg("Physical");
        int enemyRes = enemy.getMyStats().getArm();
        int tdmg = (pdmg < enemyRes) ? 0 : (pdmg - enemyRes);  //Modified here so that enemy doesn't gain health if it's armour exceeds dmg taken

        // 4th physical attack does special attack
        if (randomInt(0, 100) <= enemy.getMyStats().getDodge()) {
            System.out.println("Enemy dodges your attack!");
            return;
        }
        else {
            if (specialAttack == 4) {
                // if the randomly generated player dmg is less than enemy's resistance, the new total dmg is the enemy's resistance + player damage
                // for a guaranteed special attack
                if(pdmg < enemyRes) {
                    tdmg = enemyRes+pdmg;
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg*2);
                    System.out.println("Player uses special physical attack and does " + pdmg*2 + " damage. Enemy loses " + tdmg*2 + " HP.");
                    specialAttack = 0;                                //Set specialAttack to zero after performing special attack, else you'll just be doing it all the time.. then it's not very special

                }
                else {
                    //tdmg = ((getMyStats().getDmg() * 2) < enemyRes) ? 0 : ((getMyStats().getDmg() * 2) - enemyRes); //Also modified here; same case as above
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg*2);
                    System.out.println("Player uses special physical attack and does " + pdmg*2 + " damage. Enemy loses " + tdmg*2 + " HP.");
                    specialAttack = 0;
                }
            } else {
                if (randomInt(0, 100) <= getMyStats().getCrit()) {
                    //tdmg = ((getMyStats().getCold() * 2) < enemyRes) ? 0 : ((getMyStats().getCold() * 2) - enemyRes);
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (tdmg*2));
                    System.out.println("Player lands a critical strike, dealing " + pdmg*2 + " damage. Enemy loses " + tdmg*2 + " HP.");
                    specialAttack++;
                }
                else {
                    //tdmg = ((getMyStats().getCold() * 1) < enemyRes) ? 0 : ((getMyStats().getCold() * 1) - enemyRes);
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (tdmg));
                    System.out.println("Player attacks with his weapon, dealing " + pdmg + " damage. Enemy loses " + tdmg + " HP.");
                    specialAttack++;
                }
            }
        }

        // checks poison debuff and enemy loses 10 hp if poisoned
        if(debuffOn){
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (10*debuffCounter));
            System.out.println("Enemy is poisoned and loses an additional " + (10*debuffCounter) + " HP.");
            debuffTurnCounter++;
        }

        increaseMana(3);
        resetBlock();
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
        int enemyRes = enemy.getMyStats().getcRes();
        int tdmg = (int)(pdmg-(pdmg*(enemyRes/100.0f)));

        // needs 5 mana to cold spell
        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use cold spell!");
        }
        else {
            if (randomInt(0, 100) <= enemy.getMyStats().getDodge()) {
                System.out.println("Enemy dodges your attack!");
                return;
            }
            else {
                // 3rd cold spell freezes enemy
                if (cSpellCounter == 3) {
                    useSpell();
                    // if the randomly generated player dmg is less than enemy's resistance, the new total dmg is the enemy's resistance + player damage
                    // for a guaranteed special cold spell

                        //tdmg = ((getMyStats().getCold() * 3) < enemyRes) ? 0 : ((getMyStats().getCold() * 3) - enemyRes);
                        enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg*3);
                        System.out.println("Player uses cold spell and does " + pdmg*3 + " damage. Enemy loses " + tdmg*3 + " HP and is frozen.");
                        cSpellCounter = 0;
                    }
                 else {
                    //tdmg = ((getMyStats().getCold() * 1) < enemyRes) ? 0 : ((getMyStats().getCold() * 1) - enemyRes);
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                    System.out.println("Player uses cold spell and does " + pdmg + " damage. Enemy loses " + tdmg + " HP.");
                    cSpellCounter++;
                }
            }
            // checks poison debuff
            if(debuffOn){
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (10*debuffCounter));
                System.out.println("Enemy is poisoned and loses an additional " + (10*debuffCounter) + " HP.");
                debuffTurnCounter++;
            }

            increaseMana(1);
            resetBlock();
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
        int enemyRes = enemy.getMyStats().getpRes();
        int tdmg = (pdmg < enemyRes) ? 0 : (pdmg - enemyRes);

        // needs 5 mana to use poison spell
        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use poison spell!");
        }
        else {
            if (randomInt(0, 100) <= enemy.getMyStats().getDodge()) {
                System.out.println("Enemy dodges your attack!");
                return;
            }
            else {
                // sets debuff on enemy
                useSpell();

                debuffOn = true;
                debuffCounter++;
                debuffTurnCounter = 0;
            }
                if (debuffOn) {
                    System.out.println("Enemy is poisoned.");
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (10 * debuffCounter));
                    System.out.println("Enemy is poisoned and loses an additional " + (10*debuffCounter) + " HP.");
                    debuffTurnCounter++;
                }

                resetBlock();
            increaseMana(1);
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
        int enemyRes = enemy.getMyStats().getfRes();
        int tdmg = (int)(pdmg-(pdmg*(enemyRes/100.0f)));
        // needs 5 mana to use fire spell
        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use fire spell!");
        }
        else {
            if (randomInt(0, 100) <= enemy.getMyStats().getDodge()) {
                System.out.println("Enemy dodges your attack!");
                return;
            }
            else {
                useSpell();
                //tdmg = ((getMyStats().getFire() * 1) < enemyRes) ? 0 : ((getMyStats().getFire() * 1) - enemyRes);
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                System.out.println("Player uses fire spell and does " + pdmg + " damage. Enemy loses " + tdmg + " HP.");
                System.out.println("Enemy is ignited!");
                fSpellCounter++;

                // 2nd fire spell does bonus damage
                if (fSpellCounter == 2) {
                    useSpell();
                    // if the randomly generated player dmg is less than enemy's resistance, the new total dmg is the enemy's resistance + player damage
                    // for a guaranteed special fire spell

                        //tdmg = ((getMyStats().getFire() * 3) < enemyRes) ? 0 : ((getMyStats().getFire() * 3) - enemyRes);
                        enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg*2);
                        System.out.println("Player does bonus fire damage. Enemy loses " + tdmg*2 + " HP.");

                    fSpellCounter = 0;
                }
            }

            // checks poison debuff
            if(debuffOn){
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (10*debuffCounter));
                System.out.println("Enemy is poisoned and loses an additional " + (10*debuffCounter) + " HP.");
                debuffTurnCounter++;
            }
}
            increaseMana(1);
            resetBlock();
            turnCounter++;

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }


    public void lightningAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor and lightning resistance
        int pdmg = randomizeDmg("Lightning");
        int enemyRes = enemy.getMyStats().getlRes();
        int tdmg = (int)(pdmg-(pdmg*(enemyRes/100.0f)));

        // needs 5 mana to use lightning spell
        if(getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use lightning spell!");
        }
        else {
            if (randomInt(0, 100) <= enemy.getMyStats().getDodge()) {
                System.out.println("Enemy dodges your attack!");
                System.out.println();
                return;
            }
            else {
                // enemy damage increases
                useSpell();
                //tdmg = ((getMyStats().getLightning() * 3) < enemyRes) ? 0 : ((getMyStats().getLightning() * 3) - enemyRes);
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                System.out.println("Player uses lightning spell and does " + pdmg + " damage. Enemy loses " + tdmg + " HP.");
            }
            // checks poison debuff
            if(debuffOn){
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (10*debuffCounter));
                System.out.println("Enemy is poisoned and loses an additional " + (10*debuffCounter) + " HP.");
                debuffTurnCounter++;
            }

            increaseMana(1);
            resetBlock();
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
    public void increaseMana(int num) {
        int cMana = getMyStats().getMana()+num;
        if (cMana >= 20) {
            getMyStats().setMana(20);
        }
        else {
            getMyStats().setMana(cMana);
        }
    }

    public void resetBlock() {
        blocked = false;
    }

    // returns true is attack if blocked, enemy cannot damage player
    public boolean blockEnemy() {
        int bMana = getMyStats().getMana()+5;
        if (bMana >= 20) {
            getMyStats().setMana(20);
        }
        else {
            getMyStats().setMana(bMana);
        }
        blockCounter++;
        //System.out.println(getMyStats().getMana());
        return blocked = true;
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
        item.itemStats();
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
