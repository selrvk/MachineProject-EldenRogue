package Prog3MP;

import java.util.Random;

/**
 * BattlePhase class is the BattlePhase model for the BattlePhase segment of the code.
 * 
 * @author Charles, Myrine.
 */
public class BattlePhase {
    
    private Player player;
    private Enemy enemy;
    private int turn, incomingDamage;
    private int currentHp, maxHp, eCurrentHP, eMaxHP, currentArea;
    private boolean dodged;

    /**
     * Constructs a new BattlePhase model for the BattlePhase, for bosses where Area
     * doesnt affect stats.
     * 
     * @param player Instance of player for stats.
     * @param enemy Instance of enemy for stats.
     */
    public BattlePhase(Player player, Enemy enemy){

        this.player = player;
        this.enemy = enemy;
        currentHp = player.getHp() + player.getInventory().getWeaponHP();
        maxHp = currentHp;
        turn = 1;

        eMaxHP = enemy.getHp();
        eCurrentHP = eMaxHP;

    }
    
    /**
     * Overload constructor, for enemies where Area affects stats.
     * 
     * @param player Instance of player for stats.
     * @param enemy Instance of enemy for stats.
     * @param currentArea Area for stats multiplier.
     */
    public BattlePhase(Player player, Enemy enemy, int currentArea){

        this.player = player;
        this.enemy = enemy;
        this.currentArea = currentArea;
        currentHp = player.getHp() + player.getInventory().getWeaponHP();
        maxHp = currentHp;
        turn = 1;

        eMaxHP = randomHealth();
        eMaxHP *= currentArea;
        eCurrentHP = eMaxHP;
    }
    /**
     * Calculates a random health for the enemy depending on the Area.
     * 
     * @return returns a random health from their High or Low.
     */
    public int randomHealth(){

        int randomHealth;
        int low = enemy.getHp();
        int high = low + 10;

        Random rand = new Random();
        randomHealth = rand.nextInt((high - low) + 1) + low;

        return randomHealth;
    }
    /**
     * Calculates the dodge chance depending on the player's dex stat.
     */
    public void calculateDodge(){

        float dodgeRate;
        Random rand = new Random();
        float randomNum = rand.nextInt(100) + 1;
        randomNum /= 100;
    
        dodgeRate = (20 + ((player.getEnd() + player.getInventory().getWeaponEnd()) / 2)) / 100;
        
        if(randomNum <= dodgeRate){

            dodged = true;

        } else {

            dodged = false;
        }
    }
    /**
     * Multiplies the enemy's HP depending on the current Area.
     */
    public void multiplyCurrentArea(){

        eMaxHP *= currentArea;
        
    }
    /** 
     * @return returns if the player dodged or not.
     */
    public boolean getDodged(){

        return dodged;
    }
    /**
     * Sets the incoming damage.
     * 
     * @param damage The damage about to be taken by the player.
     */
    public void setIncomingDamage(int damage){

        incomingDamage = damage;
    }
    /**
     * Takes the incoming damage.
     * 
     * @return returns the damage taken.
     */
    public int takeIncomingDamage(){

        return incomingDamage;

    }
    /**
     * Calculates the enemy's damage, depending on their low-to-high range.
     * 
     * @return returns the final enemy damage.
     */
    public int calculateEnemyDamage(){

        int low = enemy.getDmg();
        int high = low + 10;

        Random rand = new Random();
        int finalDmg = rand.nextInt((high - low ) + 1) + low;   

        return finalDmg;
    }
    /**
     * Calculates the player's damage depending on the attack type.
     * 
     * @param atkType the attack type chosen by player. Str, Sorc, Incan.
     * @return returns the damage depending on the calculated attack type.
     */
    public int playerAttacks(String atkType){

        int damage;

        if(atkType.equals("Strength")){

        damage = player.getStr() + player.getInventory().getWeaponStr();
        damage *= (1 - enemy.getPhyDef());
        System.out.println("Dealt " + damage + " Physical Damage");

        } else if(atkType.equals("Sorcerer")){

            damage = player.getInte() + player.getInventory().getWeaponInt();
            damage *= (1 - enemy.getPhyDef());
            System.out.println("Dealt " + damage + " Sorcery Damage");

        } else{

            damage = player.getFth() + player.getInventory().getWeaponFth();
            damage *= (1 - enemy.getPhyDef());
            System.out.println("Dealt " + damage + " Incantation Damage");

        }
        
        return damage;
    }
    /**
     * @return returns the enemy's max HP. 
     */
    public int getEnemyMaxHP(){

        return eMaxHP;
    }
    /**
     * @return returns the enemy's current HP.
     */
    public int getEnemyCurrentHP(){

        return eCurrentHP;
    }
    /**
     * @return returns the player's max HP.
     */
    public int getMaxHP(){

        return maxHp;
    }
    /**
     * Takes the damage from enemy.
     * @param damage the enemy's incoming damage.   
    */
    public void takeDamage(int damage){

        currentHp -= damage;

    }
    /**
     * Does damage to the enemy/
     * 
     * @param damage the player's damage.
     */
    public void doDamage(int damage){

        eCurrentHP -= damage;
    }
    /**
     * @return returns the player's current HP.
     */
    public int getCurrentHP(){

        return currentHp;
    }
    /**
     * @return returns the player's default HP.
     */
    public int getPlayerHP(){


        return player.getHp();
    }
    /**
     * Sets the turn.
     * 
     * @param turn the turn passed to the method.
     */
    public void setTurn(int turn){

        this.turn = turn;

    }
    /**
     * Gets the current turn.
     * 
     * @return returns the turn.
     */
    public int getTurn(){

        return turn;
    }



}
