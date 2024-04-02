package Prog3MP;

import java.awt.*;

/**
 * Area3 Class is a child class/subclass of Area1. It handles the and takes in different initialization
 * of data which makes it different from it's superclass.
 * 
 * @author Charles, Myrine.
 * @extends (Area1).
 */
public class Area3 extends Area1{
    
    /**
     * Constructs Area 3 with it's exclusive data and variables.
     * 
     * @param player Instance of the player for stats, runes, weapons.
     */
    public Area3(Player player){

        this.player = player;
        area = "Area3";
        
        
        fastTravelPoint = new Point(1,8);
        playerPoint = new Point(1,8);

        currentArea = 3;

        door1 = new Point(1,0);
        door2 = new Point(3,6);

        door3 = new Point(3,0);
        door4 = new Point(1,8);

        exitPoint = new Point(1,0);

        
        printArea();
        printDetails();
        printButtons();
        printRoom1();
        
    }


}
