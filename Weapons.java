package Prog3MP;

import java.util.ArrayList;

    /**
     * This class handles and stores all the weapons in the game.
     * 
     * @author Charles, Myrine.
     */
public class Weapons {

    private String name;
    private int dexStat, healthStat, enduranceStat, strengthStat, intelligenceStat, faithStat, price;
    public ArrayList<Weapons> weaponList;
    /**
     * Constructs the list containing all the game weapons.
     */
    public Weapons(){

        weaponList = new ArrayList<Weapons>();
        
        weaponList.add(new Weapons("Demo Shroom",              999, 0, 999, 999, 999, 999, 0)); 
        
        weaponList.add(new Weapons("Short Sword",              0,  13, 15, 15, 15, 15, 1000)); 
        weaponList.add(new Weapons("Rogier's Rapier",          10, 18, 35, 25, 35, 35, 2000));
        weaponList.add(new Weapons("Coded Sword",              20, 21, 40, 35, 40, 40, 4000));
        weaponList.add(new Weapons("Sword of Night and Flame", 30, 25, 55, 45, 55, 55, 8000)); 

        weaponList.add(new Weapons("Uchigatana",      20, 15, 0, 35, 30, 0, 1875));
        weaponList.add(new Weapons("Moonveil",        30, 20, 0, 40, 45, 0, 3750));
        weaponList.add(new Weapons("Rivers of Blood", 40, 25, 0, 45, 60, 0, 7500)); 
        weaponList.add(new Weapons("Hand of Malenia", 50, 30, 0, 50, 75, 0, 15000));

        weaponList.add(new Weapons("Whip",                    15, 20, 0, 60, 20, 0, 1500)); 
        weaponList.add(new Weapons("Urumi",                   20, 25, 10, 70, 40, 0, 3000));
        weaponList.add(new Weapons("Thorned Whip",            30, 30, 0, 80, 50, 40, 5000));
        weaponList.add(new Weapons("Hoslow's Petal Whip",     35, 35, 20, 90, 55, 20, 10000)); 

        weaponList.add(new Weapons("Claymore",               15, 9, 0,  10, 20, 0, 3000)); 
        weaponList.add(new Weapons("Starscourge Greatsword", 20, 14, 0,  15, 40, 20, 6000));
        weaponList.add(new Weapons("Inseparable Sword",      25, 19, 60, 20, 70, 60, 12000));
        weaponList.add(new Weapons("Maliketh's Black Blade", 30, 24, 40, 25, 80, 60, 24000)); 

        weaponList.add(new Weapons("Astrologer's Staff",   5,  12, 25, 20, 5,  15, 2000));
        weaponList.add(new Weapons("Albinauric Staff",     10, 14, 45, 30, 10, 35, 4000));
        weaponList.add(new Weapons("Staff of the Guilty",  15, 16, 65, 40, 15, 60, 8000)); 
        weaponList.add(new Weapons("Carian Regal Scepter", 25, 18, 85, 50, 20, 75, 16000));

        weaponList.add(new Weapons("Finger Seal",           10, 10, 15, 45, 0, 20, 2500));
        weaponList.add(new Weapons("Godslayer's Seal",      15, 12, 35, 50, 75, 40, 5000));
        weaponList.add(new Weapons("Golden Order Seal",     20, 14, 65, 55, 0, 65, 10000)); 
        weaponList.add(new Weapons("Dragon Communion Seal", 25, 18, 75, 60, 0, 80, 15000));

    }
    /**
     * Sets the values and stats of each weapon.
     * @param name Weapon's name.
     * @param hp Weapon's HP.
     * @param dex Weapon's Dexterity.
     * @param inte Weapon's Intelligence.
     * @param end Weapon's Endurance.
     * @param str Weapon's Strength.
     * @param fth Weapon's Faith.
     * @param price Weapon's Price.
     */
    public Weapons(String name, int hp, int dex, int inte, int end, int str, int fth, int price){

        this.name = name;
        this.dexStat = dex;
        this.healthStat = hp;
        this.intelligenceStat = inte;
        this.enduranceStat = end;
        this.strengthStat = str;
        this.faithStat = fth;
        this.price = price;

    }

    /**
     * Gets the weapon's stats depending on the given name.
     * @param boughtWeapon the name of the weapon bought from the shop.
     * @return returns the full weapon's stats depending on the name.
     */
    public int getWeapon(String boughtWeapon){

        int i = 0;
        int j = 0;

        for(Weapons weapons : weaponList){

            
            if(weapons.name.equals(boughtWeapon)){

                j = i;
            }

            i++;
        }
        
        return j;
    }
    /**
     * Gets the name of the weapon.
     * @return returns the weapon's name.
     */
    public String getName(){

        return name;

    }
    /**
     * Gets the HP of the weapon.
     * @return returns the weapon's HP.
     */
    public int getHp(){

        return healthStat;
        
    }
    /**
     * Gets the Dex of the weapon.
     * @return returns the weapon's Dex.
     */
    public int getDex(){

        return dexStat;
        
    }
    /**
     * Gets the Int of the weapon.
     * @return returns the weapon's Int.
     */
    public int getInt(){

        return intelligenceStat;
        
    }
    /**
     * Gets the End of the weapon.
     * @return returns the weapon's End.
     */
    public int getEnd(){

        return enduranceStat;
        
    }
    /**
     * Gets the Str of the weapon.
     * @return returns the weapon's Sre.
     */
    public int getStr(){

        return strengthStat;
        
    }
    /**
     * Gets the Fth of the weapon.
     * @return returns the weapon's Fth.
     */
    public int getFth(){

        return faithStat;
        
    }
    /**
     * Gets the price of the weapon.
     * @return returns the weapon's price.
     */
    public int getPrice(){

        return price;
    }
    
}
