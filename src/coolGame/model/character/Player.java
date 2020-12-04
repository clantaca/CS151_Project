package coolGame.model.character;

import coolGame.model.item.Item;
import coolGame.model.item.ItemStorage;

import java.util.ArrayList;

/**
 * Model that constructs a single player in the game
 */
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
    public boolean blocked = false;
    public boolean isFrozen = false;
    private boolean hasLightningDebuff = false;            //An enemy can have a lightning debuff when attacked by lightning attack
    private int originalDmg;
    public int bonusDmg = 0;
    public int bonusDmg2 = 0;
    public int bonuscDmg = 0;
    public int bonusfDmg = 0;
    public int bonuspDmg = 0;
    public int bonuslDmg = 0;

    private int spellCounter = 0; // whenever the player casts a spell
    private int attackCounter = 0; // whenever the player attacks with physical attack
    private int hitsTakenCounter = 0; // whenever the player takes a hit from the enemy
    private int turnCounter = 0; // whenever the player attacks/enemy attacks, add 1 to this counter
    private int blockCounter = 0; // whenever the player uses block
    public int physSpecialAttackCounter = 2;
    public float lightningDebuff = 25.0f;


    /**
     * Returns the amount of turns with debuff
     *
     * @return debuffTurnCounter
     */
    public int getDebuffTurnCounter() {
        return debuffTurnCounter;
    }

    /**
     * Sets the debuff counter
     *
     * @param debuffCounter
     */
    public void setDebuffCounter(int debuffCounter) {
        this.debuffCounter = debuffCounter;
    }

    /**
     * Sets whether debuff is on/off
     *
     * @param debuffOn
     */
    public void setDebuffOn(boolean debuffOn) {
        this.debuffOn = debuffOn;
    }

    /**
     * Returns number of spell used
     *
     * @return spellCounter
     */
    public int getSpellCounter() {
        return spellCounter;
    }

    /**
     * Returns number of hits taken
     *
     * @return hitsTakenCounter
     */
    public int getHitsTakenCounter() {
        return hitsTakenCounter;
    }

    /**
     * Sets the number of hits taken
     *
     * @param hitsTakenCounter
     */
    public void setHitsTakenCounter(int hitsTakenCounter) {
        this.hitsTakenCounter = hitsTakenCounter;
    }

    /**
     * Returns the number of turns
     *
     * @return turnCounter
     */
    public int getTurnCounter() {
        return turnCounter;
    }

    /**
     * Returns whether the player's shield is in effect or not
     *
     * @return
     */
    public boolean isShieldEffect() {
        return shieldEffect;
    }

    /**
     * Sets the shield effect of the player
     *
     * @param shieldEffect
     */
    public void setShieldEffect(boolean shieldEffect) {
        this.shieldEffect = shieldEffect;
    }

    /**
     * Returns the original damage
     *
     * @return originalDmg
     */
    public int getOriginalDmg() {
        return originalDmg;
    }

    private boolean shieldEffect;

    /**
     * Returns whether the player has lightning debuff on/off
     *
     * @return
     */
    public boolean getHasLightningDebuff() {
        return hasLightningDebuff;
    }

    /**
     * Sets the lightning debuff
     *
     * @param hasLightningDebuff
     */
    public void setHasLightningDebuff(boolean hasLightningDebuff) {
        this.hasLightningDebuff = hasLightningDebuff;
    }


    /**
     * Returns an arraylist of the player's inventory
     *
     * @return inventory
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Returns an item from ItemStorage
     *
     * @return ItemStorage
     */
    public ItemStorage getItem() {
        return itemStorage;
    }

    /**
     * Sets the item of ItemStorage
     *
     * @param itemStorage
     */
    public void setItem(ItemStorage itemStorage) {
        this.itemStorage = itemStorage;
    }

    /**
     * Constructor that initializes the players name, hp, mana, damages, resistances, etc.
     *
     * @param name
     */
    public Player(String name) {
        this.setName(name);
        this.N = 10; // fixed inventory size
        this.itemStorage = new ItemStorage();

        // default values for each new player
        this.getMyStats().setHp(100);
        this.getMyStats().setMaxHP(100);
        this.getMyStats().setDmg(20);
        this.getMyStats().setCold(20);
        this.getMyStats().setPoison(15);
        this.getMyStats().setFire(20);
        this.getMyStats().setLightning(25);
        this.getMyStats().setArm(5);
        this.getMyStats().setMana(20);
        this.getMyStats().setMaxMana(20);
        this.getMyStats().setcRes(15);
        this.getMyStats().setpRes(15);
        this.getMyStats().setfRes(15);
        this.getMyStats().setlRes(15);
        this.getMyStats().setDodge(5);
        this.getMyStats().setCrit(5);
    }

    /**
     * Returns a random number from a minimum and maximum value
     *
     * @param min
     * @param max
     * @return randomInt
     */
    // generates random integer
    public int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    /**
     * Randomizes the player's damage based off of the attack they choose
     *
     * @param dmgType
     * @return random damage
     */
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
                    min = (int) (max * (80.0f / 100.0f));
                }
                pdmg = randomInt(min, max);
                //System.out.println(pdmg);
                break;

            case "Cold":
                max = getMyStats().getCold();
                if (max > 5) {
                    min = (int) (max * (80.0f / 100.0f));
                }
                pdmg = randomInt(min, max);
                //System.out.println(pdmg);
                break;

            case "Poison":
                max = getMyStats().getPoison();
                if (max > 5) {
                    min = (int) (max * (80.0f / 100.0f));
                }
                pdmg = randomInt(min, max);
                //System.out.println(pdmg);
                break;

            case "Fire":
                max = getMyStats().getFire();
                if (max > 5) {
                    min = (int) (max * (80.0f / 100.0f));
                }
                pdmg = randomInt(min, max);
                //System.out.println(pdmg);
                break;

            case "Lightning":
                max = getMyStats().getLightning();
                if (max > 5) {
                    min = (int) (max * (80.0f / 100.0f));
                }
                pdmg = randomInt(min, max);
                //System.out.println(pdmg);
                break;
        }
        return pdmg;
    }

    /**
     * Physical attack method that calculates the damage dealt by the enemy;
     * Every fourth physical attack does a special physical attack
     *
     * @param enemy
     */
    public void physicalAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor
        int pdmg = randomizeDmg("Physical") + bonusDmg + bonusDmg2;

        //Calculates additional damage when enemy has lightning debuff
        if (getHasLightningDebuff())
            pdmg = pdmg + (int) (pdmg * (lightningDebuff / 100.0f));

        int enemyRes = enemy.getMyStats().getArm();
        int critChance;
        critChance = randomInt(0, 100);
        int tdmg = (pdmg < enemyRes) ? 1 : (pdmg - enemyRes);  //Modified here so that enemy doesn't gain health if it's armour exceeds dmg taken
        isFrozen = false;
        // 4th physical attack does special attack
        if (randomInt(0, 100) <= enemy.getMyStats().getDodge()) {
            System.out.println("Enemy dodges your attack!");
            return;
        } else if (specialAttack == physSpecialAttackCounter && critChance <= getMyStats().getCrit()) {

            // if the randomly generated player dmg is less than enemy's resistance, the new total dmg is the enemy's resistance + player damage
            // for a guaranteed special attack

            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg * 4);
            getMyStats().setHp(getMyStats().getHp() + tdmg * 4);
            if (getMyStats().getHp() > getMyStats().getMaxHP())
                getMyStats().setHp(getMyStats().getMaxHP());
            System.out.println("Player uses special physical attack and crits, stealing" + tdmg * 4 + "HP from the enemy!");
            specialAttack = 0;                                //Set specialAttack to zero after performing special attack, else you'll just be doing it all the time.. then it's not very special


        } else if (specialAttack == physSpecialAttackCounter) {

            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg * 2);
            getMyStats().setHp(getMyStats().getHp() + tdmg * 2);
            if (getMyStats().getHp() > getMyStats().getMaxHP())
                getMyStats().setHp(getMyStats().getMaxHP());
            System.out.println("Player uses special physical attack, stealing " + tdmg * 2 + " HP from the enemy!");
            specialAttack = 0;
        } else if (critChance <= getMyStats().getCrit()) {


            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (tdmg * 2));
            System.out.println("Player lands a critical strike, dealing " + pdmg * 2 + " damage. Enemy loses " + tdmg * 2 + " HP.");
            specialAttack++;
        } else {

            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (tdmg));
            System.out.println("Player attacks with his weapon, dealing " + pdmg + " damage. Enemy loses " + tdmg + " HP.");
            specialAttack++;
        }

        // checks poison debuff and enemy loses 10 hp if poisoned
        if (debuffOn) {
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (getMyStats().getPoison() * debuffCounter));
            System.out.println("Enemy is poisoned and loses an additional " + (getMyStats().getPoison() * debuffCounter) + " HP.");
            debuffTurnCounter++;
        }

//        increaseMana(3);
        resetBlock();
        attackCounter++;
        turnCounter++;

        // sets enemy hp to 0 if hp is negative
        int enemyHp = enemy.getMyStats().getHp();
        if (enemyHp < 0) {
            enemy.getMyStats().setHp(0);
        }
    }

    /**
     * Cold spell method that calculates the damage dealt by the enemy;
     * Every third cold spell freezes enemy
     *
     * @param enemy
     */
    public void coldAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor and cold resistance
        int pdmg = randomizeDmg("Cold") + bonuscDmg;

        //Calculates additional damage when enemy has lightning debuff
        if (getHasLightningDebuff())
            pdmg = pdmg + (int) (pdmg * (lightningDebuff / 100.0f));

        isFrozen = false;
        int enemyRes = enemy.getMyStats().getcRes();
        int tdmg = (int) (pdmg - (pdmg * (enemyRes / 100.0f)));

        // needs 5 mana to cold spell
        if (getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use cold spell!");
        } else {
            if (randomInt(0, 100) <= enemy.getMyStats().getDodge()) {
                useSpell();
                System.out.println("Enemy dodges your attack!");
                return;
            } else {
                // 3rd cold spell freezes enemy
                if (cSpellCounter == 2) {
                    useSpell();
                    // if the randomly generated player dmg is less than enemy's resistance, the new total dmg is the enemy's resistance + player damage
                    // for a guaranteed special cold spell

                    isFrozen = true;
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg * 2);
                    System.out.println("Player uses cold spell and does " + pdmg * 2 + " damage. Enemy loses " + tdmg * 2 + "!");
                    cSpellCounter = 0;
                } else {
                    useSpell();
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                    System.out.println("Player uses cold spell and does " + pdmg + " damage. Enemy loses " + tdmg + " HP.");
                    cSpellCounter++;
                }
            }
            // checks poison debuff
            if (debuffOn) {
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (getMyStats().getPoison() * debuffCounter));
                System.out.println("Enemy is poisoned and loses an additional " + (getMyStats().getPoison() * debuffCounter) + " HP.");
                debuffTurnCounter++;
            }

//            increaseMana(1);
            resetBlock();
            turnCounter++;

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }
    }

    /**
     * Poison spell method that calculates the damage dealt by the enemy;
     * Once used, a debuff on the enemy is applied in which the enemy loses an additional 10 HP every round
     *
     * @param enemy
     */
    public void poisonAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor and poison resistance
        int pdmg = randomizeDmg("Poison") + bonuspDmg;

        //Calculates additional damage when enemy has lightning debuff
        if (getHasLightningDebuff())
            pdmg = pdmg + (int) (pdmg * (lightningDebuff / 100.0f));

        int enemyRes = enemy.getMyStats().getpRes();
        int tdmg = (pdmg < enemyRes) ? 0 : (pdmg - enemyRes);
        isFrozen = false;
        // needs 5 mana to use poison spell
        if (getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use poison spell!");
        } else {
            if (randomInt(0, 100) <= enemy.getMyStats().getDodge()) {
                useSpell();
                System.out.println("Enemy dodges your attack!");
                return;
            } else {
                // sets debuff on enemy
                useSpell();

                debuffOn = true;
                debuffCounter++;
                debuffTurnCounter = 0;
            }
            if (debuffOn) {
                System.out.println("Enemy is poisoned.");
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (getMyStats().getPoison() * debuffCounter));
                System.out.println("Enemy is poisoned and loses an additional " + (getMyStats().getPoison() * debuffCounter) + " HP.");
                debuffTurnCounter++;
            }

            resetBlock();
//            increaseMana(1);
            turnCounter++;

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }
        }
    }

    /**
     * Fire spell method that calculates the damage dealt by the enemy;
     * Every second fire spell does bonus fire damage
     *
     * @param enemy
     */
    public void fireAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor and fire resistance
        int pdmg = randomizeDmg("Fire") + bonusfDmg;

        //Calculates additional damage when enemy has lightning debuff
        if (getHasLightningDebuff())
            pdmg = pdmg + (int) (pdmg * (lightningDebuff / 100.0f));

        int enemyRes = enemy.getMyStats().getfRes();
        int tdmg = (int) (pdmg - (pdmg * (enemyRes / 100.0f)));
        isFrozen = false;

        // needs 5 mana to use fire spell
        if (getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use fire spell!");
        } else {
            if (randomInt(0, 100) <= enemy.getMyStats().getDodge()) {
                useSpell();
                System.out.println("Enemy dodges your attack!");
                return;
            } else {
                if (fSpellCounter == 1) {
                    useSpell();
                    // if the randomly generated player dmg is less than enemy's resistance, the new total dmg is the enemy's resistance + player damage
                    // for a guaranteed special fire spell

                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg * 2);
                    System.out.println("Player does bonus fire damage. Enemy loses " + tdmg * 2 + " HP.");

                    fSpellCounter = 0;
                } else {
                    useSpell();
                    enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                    System.out.println("Player uses fire spell and does " + pdmg + " damage. Enemy loses " + tdmg + " HP.");
                    System.out.println("Enemy is ignited!");
                    fSpellCounter++;

                    // 2nd fire spell does bonus damage
                }
            }

            // checks poison debuff
            if (debuffOn) {
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (getMyStats().getPoison() * debuffCounter));
                System.out.println("Enemy is poisoned and loses an additional " + (getMyStats().getPoison() * debuffCounter) + " HP.");
                debuffTurnCounter++;
            }
        }
//            increaseMana(1);
        resetBlock();
        turnCounter++;

        int enemyHp = enemy.getMyStats().getHp();
        if (enemyHp < 0) {
            enemy.getMyStats().setHp(0);
        }
    }


    /**
     * Lightning spell method that calculates the damage dealt by the enemy;
     * Damages enemy and applies lightning debuff that does additional damage to enemy every turn
     *
     * @param enemy
     */
    public void lightningAttack(Enemy enemy) {
        // randomizes damage and calculates damage based enemy armor and lightning resistance
        int pdmg = randomizeDmg("Lightning") + bonuslDmg;

        //Calculates additional damage when enemy has lightning debuff
        if (getHasLightningDebuff())
            pdmg = pdmg + (int) (pdmg * (lightningDebuff / 100.0f));

        int enemyRes = enemy.getMyStats().getlRes();
        int tdmg = (int) (pdmg - (pdmg * (enemyRes / 100.0f)));
        isFrozen = false;
        // needs 5 mana to use lightning spell
        if (getMyStats().getMana() < 5) {
            System.out.println("Not enough mana to use lightning spell!");
        } else {
            if (randomInt(0, 100) <= enemy.getMyStats().getDodge()) {
                useSpell();
                System.out.println("Enemy dodges your attack!");
                System.out.println();
                return;
            } else {
                // enemy damage increases
                useSpell();
                //tdmg = ((getMyStats().getLightning() * 3) < enemyRes) ? 0 : ((getMyStats().getLightning() * 3) - enemyRes);
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - tdmg);
                System.out.println("Player uses lightning spell and does " + pdmg + " damage. Enemy loses " + tdmg + " HP.");
            }
            // checks poison debuff
            if (debuffOn) {
                enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (getMyStats().getPoison() * debuffCounter));
                System.out.println("Enemy is poisoned and loses an additional " + (getMyStats().getPoison() * debuffCounter) + " HP.");
                debuffTurnCounter++;
            }

//            increaseMana(1);
            resetBlock();
            turnCounter++;

            int enemyHp = enemy.getMyStats().getHp();
            if (enemyHp < 0) {
                enemy.getMyStats().setHp(0);
            }

            setHasLightningDebuff(true);    //give enemy lightning debuff
            System.out.println("Enemy is affected by a lightning debuff, increasing the damage he takes by " + lightningDebuff + "%.");
        }
    }

    /**
     * Resets all counters for attacks, spells, damages, etc.
     */
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
        isFrozen = false;
        bonusDmg = 0;
        bonuscDmg = 0;
        bonusfDmg = 0;
        bonuspDmg = 0;
        bonuslDmg = 0;
    }

    /**
     * Removes debuffs from enemy
     */
    // removes poison buff from enemy
    public void resetDebuff() {
        debuffOn = false;
        debuffTurnCounter = 0;
        debuffCounter = 0;
    }

    /**
     * Player's mana increases each time they attack
     *
     * @param num
     */
    // adds +2 mana each time a player attacks
    public void increaseMana(int num) {
        int cMana = getMyStats().getMana() + num;
        if (cMana >= 20) {
            getMyStats().setMana(20);
        } else {
            getMyStats().setMana(cMana);
        }
    }

    /**
     * Resets block as false when attacking
     */
    public void resetBlock() {
        blocked = false;
    }

    /**
     * Returns whether the player has blocked the enemy's attack and gains additional mana
     *
     * @param enemy
     * @return blocked
     */
    // returns true is attack if blocked, enemy cannot damage player
    public boolean blockEnemy(Enemy enemy) {
        int bMana = getMyStats().getMana() + 5;
        if (bMana >= 20) {
            getMyStats().setMana(20);
        } else {
            getMyStats().setMana(bMana);
        }
        if (debuffOn) {
            enemy.getMyStats().setHp(enemy.getMyStats().getHp() - (getMyStats().getPoison() * debuffCounter));
            System.out.println("Enemy is poisoned and loses an additional " + (getMyStats().getPoison() * debuffCounter) + " HP.");
            debuffTurnCounter++;
        }
        blockCounter++;
        isFrozen = false;
        //System.out.println(getMyStats().getMana());
        return blocked = true;
    }

    /**
     * Decreases the player's mana by 5 when using spell
     */
    // each spell uses 5 mana
    public void useSpell() {
        int cMana = getMyStats().getMana() - 5;
        if (cMana <= 0) {
            getMyStats().setMana(0);
        } else {
            getMyStats().setMana(cMana);
        }
        spellCounter++;

    }

    /**
     * Updates inventory with an obtained item
     *
     * @param player
     */
    // updates inventory with obtained item
    public void updateInventory(Player player) {
        inventory.add(itemStorage.getNewItem());
        inventory.get(getInventory().size() - 1).updatePlayerStats(player);
        Item item = inventory.get(inventory.size() - 1);

        // displays item name + description
        System.out.println();
        System.out.println("Player obtains new item: " + item.getName() + "!");
        item.itemStats();
        System.out.println(item.getName() + ": " + item.getDescription());
        System.out.println();

        originalDmg = player.getMyStats().getDmg();
    }

    /**
     * @param player
     */
    //checks the start of the turn if any conditions for special effect from items are met
    public void startOfTurn(Player player) {
        for (Item i : inventory) {
            i.specialEffect(player);
        }
    }

    /**
     * Displays the player's inventory of items with their name, stats, and description
     */
    //prints out the inventory of all the items with their name, stats, and description
    public void displayInventory() {
        System.out.println("Displaying inventory...");
        for (Item i : inventory) {
            System.out.println(i.getName());
            i.itemStats();
            System.out.println(i.getDescription());
        }
    }

    /**
     * Returns the number of special attacks
     *
     * @return specialAttack
     */
    public int getSpecialAttack() {
        return specialAttack;
    }

}

