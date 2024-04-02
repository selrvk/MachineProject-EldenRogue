package Prog3MP;

import java.awt.*;

/**
 * Area2 Class is a child class/subclass of Area1. It handles the and takes in different initialization
 * of data which makes it different from it's superclass.
 * 
 * @author Charles, Myrine.
 * @extends (Area1)
 */
public class Area2 extends Area1{

    public Area2 (){
    }  

    /**
     * Constructs a new Area for the player with exclusive Area 2 variables.
     * 
     * @param player Instance of the player for stats, runes, weapon.
     */
    public Area2(Player player, boolean areaCleared){

        this.areaCleared = areaCleared;
        this.player = player;
        area = "Area2";
        
        fastTravelPoint = new Point(2,0);
        playerPoint = new Point(2,0);

        currentArea = 2;

        door1 = new Point(2,4);
        door2 = new Point(1,0);

        door3 = new Point(2,3);
        door4 = new Point(0,3);

        door5 = new Point(4,3);
        door6 = new Point(0,1);

        door7 = new Point(2,0);
        door8 = new Point(3,7);

        exitPoint = new Point(3,0);

        if(areaCleared == true){

            exitUnlocked = true;
        }
        
        printArea();
        printDetails();
        printButtons();
        printRoom1();
    }

    
}
