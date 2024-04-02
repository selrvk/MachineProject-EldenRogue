package Prog3MP;

import java.util.*;

/**
 * Enemy class sets and stores data regarding enemies and bosses.
 * 
 * @author Charles, Myrine.
 */

public class Enemy {
    
    private String name;
    private int dmg;
    private int hp;
    private double phyDef, sorDef, incDef;
    private ArrayList<Enemy> area1EnemyList;
    private ArrayList<Enemy> area2EnemyList;
    private ArrayList<Enemy> bossesList;

    /**
     * Constructs the list of enemies, their stats and names. 
     */
    public Enemy(){

        area1EnemyList = new ArrayList<Enemy>();
        area2EnemyList = new ArrayList<Enemy>();
        bossesList = new ArrayList<Enemy>();

        area1EnemyList.add(new Enemy("Godrick Soldier"    , 70,  20, 0.2,  0.15,   0.10));
        area1EnemyList.add(new Enemy("Godrick Archer"     , 110, 25, 0.5,  0.15,   0.20));
        area1EnemyList.add(new Enemy("Godrick Knight"     , 120, 70, 0.25, 0.25  , 0.20));
    
        area2EnemyList.add(new Enemy("Living Jar"         , 70,  20, 0.2,  0.15,   0.10));
        area2EnemyList.add(new Enemy("Glintstone Sorcerer", 110, 25, 0.5,  0.15,   0.20));
        area2EnemyList.add(new Enemy("Battlemage"         , 120, 70, 0.25, 0.25  , 0.20));
        
        bossesList.add(new Enemy("Godrick the Grafted",             150, 200, 0.35, 0.2, 0.15));
        bossesList.add(new Enemy("Rennala, Queen of the Full Moon", 150, 400, 0.35, 0.2, 0.15));
        bossesList.add(new Enemy("The Elden Beast",                 250, 800, 0.25, 0.5, 0.4));
    }   

    /**
     * Overload constructor for setting enemy stats.
     * 
     * @param name Enemy name.
     * @param dmg Enemy damage.
     * @param hp Enemy HP.
     * @param phyDef Enemy physical defense.
     * @param sorDef Enemy sorcery defense.
     * @param incDef Enemy incantation defense.
     */
    public Enemy(String name, int dmg, int hp, double phyDef, double sorDef, double incDef){

        this.name = name;
        this.dmg = dmg;
        this.hp = hp;
        this.phyDef = phyDef;
        this.sorDef = sorDef;
        this.incDef = incDef;

    }

    /**
     *  Method to get enemies from Area 1.
     * @param typeRandomized Randomized integer (1-3).
     * @return returns The enemy depending on the random integer.
     */
    public Enemy getArea1Enemy(int typeRandomized){

        return area1EnemyList.get(typeRandomized - 1);

    }

    /**
     *  Method to get enemies from Area 2.
     * @param typeRandomized Randomized integer (1-3).
     * @return returns The enemy depending on the random integer.
     */
    public Enemy getArea2Enemy(int typeRandomized){

        return area2EnemyList.get(typeRandomized - 1);
    }

    /**
     *  Method to get bosses depending on the Area.
     * @param typeRandomized  Area integer (1-3).
     * @return returns the boss depending on the Area integer.
     */
    public Enemy getBoss(int areaNumber){

        return bossesList.get(areaNumber - 1);

    }
    
    /**
     * Gets the enemy's name.
     * @return retuns the enemy's name.
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the enemy's HP.
     * @return retuns the enemy's HP.
     */

    public int getHp(){
        return hp;
    }

    /**
     * Gets the enemy's damage.
     * @return retuns the enemy's damage.
     */
    public int getDmg(){

        return dmg;
    }

    /**
     * Gets the enemy's physical defense.
     * @return retuns the enemy's physical defense.
     */
    public double getPhyDef(){

        return phyDef;
    }

    /**
     * Gets the enemy's sorcery defense.
     * @return retuns the enemy's sorcery defense.
     */
    public double getSorDef(){

        return sorDef;
    }

    /**
     * Gets the enemy's incantation defense.
     * @return retuns the enemy's incantation defense.
     */
    public double getIncDef(){

        return incDef;
    }
}
