package Prog3MP;

import java.util.*;
import java.awt.Point;

    /**
     * This class handles all the tiles used for the rooms.
     * 
     * @author Charles, Myrine.
     */
public class Tiles {

    private ArrayList<Point> room1SpawnTiles;
    private ArrayList<Point> room2SpawnTiles;
    private ArrayList<Point> room3SpawnTiles;
    private ArrayList<Point> room4SpawnTiles;
    private ArrayList<Point> room5SpawnTiles;   
    private ArrayList<Point> room2EmptyTiles, bossRoomEmptyTiles;
    private Point room1, room2, room3, room4, bossRoomSize, bossPoint;

    /**
     * Constructs the tiles and arraylists depending on the passed Area.
     * @param areaString the Area string.
     */
    public Tiles(String areaString){

        if(areaString.equals("Area1")){
            
            room1SpawnTiles = new ArrayList<Point>();
            room2SpawnTiles = new ArrayList<Point>();

            room1 = new Point(3, 7);
            room2 = new Point(7, 7);
            bossRoomSize = new Point(5, 7);
        
            room1SpawnTiles.add(new Point(0,1));
            room1SpawnTiles.add(new Point(2,1));

            room2SpawnTiles.add(new Point(3,1));
            room2SpawnTiles.add(new Point(0,3));
            room2SpawnTiles.add(new Point(2,3));
            room2SpawnTiles.add(new Point(3,3));
            room2SpawnTiles.add(new Point(4,3));
            room2SpawnTiles.add(new Point(6,3));
            room2SpawnTiles.add(new Point(2,5));
            room2SpawnTiles.add(new Point(4,5));

            bossPoint = new Point(2,3);

        } else if(areaString.equals("Area2")){
            
            room1SpawnTiles = new ArrayList<Point>();
            room2SpawnTiles = new ArrayList<Point>();
            room3SpawnTiles = new ArrayList<Point>();
            room4SpawnTiles = new ArrayList<Point>();
            room5SpawnTiles = new ArrayList<Point>();

            room2EmptyTiles = new ArrayList<Point>();
            bossRoomEmptyTiles = new ArrayList<Point>();

            room1 = new Point(5,5);
            room2 = new Point(3,7);
            room3 = new Point(5,7);
            room4 = new Point(6,3);
            bossRoomSize = new Point(7,8);

            bossPoint = new Point(3,4);

            room1SpawnTiles.add(new Point(1,3));
            room1SpawnTiles.add(new Point(3,3));

            room2SpawnTiles.add(new Point(0,1));
            room2SpawnTiles.add(new Point(0,3));
            room2SpawnTiles.add(new Point(0,5));

            room3SpawnTiles.add(new Point(2,1));
            room3SpawnTiles.add(new Point(2,5));
            
            room4SpawnTiles.add(new Point(2,0));
            room4SpawnTiles.add(new Point(4,0));
            room4SpawnTiles.add(new Point(2,2));
            room4SpawnTiles.add(new Point(4,2));

            room5SpawnTiles.add(new Point(1,2));
            room5SpawnTiles.add(new Point(1,4));
            room5SpawnTiles.add(new Point(1,6));
            room5SpawnTiles.add(new Point(3,2));
            room5SpawnTiles.add(new Point(5,2));
            room5SpawnTiles.add(new Point(5,4));
            room5SpawnTiles.add(new Point(5,6));

            room2EmptyTiles.add(new Point(0,0));
            room2EmptyTiles.add(new Point(0,1));
            room2EmptyTiles.add(new Point(0,5));
            room2EmptyTiles.add(new Point(0,6));
            room2EmptyTiles.add(new Point(4,0));
            room2EmptyTiles.add(new Point(4,1));
            room2EmptyTiles.add(new Point(4,5));
            room2EmptyTiles.add(new Point(4,6));

            bossRoomEmptyTiles.add(new Point(0,0));
            bossRoomEmptyTiles.add(new Point(1,0));
            bossRoomEmptyTiles.add(new Point(5,0));
            bossRoomEmptyTiles.add(new Point(6,0));
  
        } else if(areaString.equals("Area3")){

            System.out.println(areaString);
            room1SpawnTiles = new ArrayList<Point>();
            room3SpawnTiles = new ArrayList<Point>();
            bossRoomEmptyTiles = new ArrayList<Point>();

            room1 = new Point(3,9);
            bossRoomSize = new Point(7,7);
            room3 = new Point(3,9);

            bossPoint = new Point(3,3);

            room1SpawnTiles.add(new Point(1,4));

            room3SpawnTiles.add(new Point(0,1));
            room3SpawnTiles.add(new Point(0,3));
            room3SpawnTiles.add(new Point(0,5));
            room3SpawnTiles.add(new Point(0,7));
            room3SpawnTiles.add(new Point(2,1));
            room3SpawnTiles.add(new Point(2,3));
            room3SpawnTiles.add(new Point(2,5));
            room3SpawnTiles.add(new Point(2,7));

            bossRoomEmptyTiles.add(new Point(0,0));
            bossRoomEmptyTiles.add(new Point(0,6));
            bossRoomEmptyTiles.add(new Point(6,0));
            bossRoomEmptyTiles.add(new Point(6,6));
        }

    }
    /**
     * called when a tile is interacted with (a spawn tile).
     * @param playerX player's x point.
     * @param playerY player's y point.
     * @return returns a value between 1-100, determining if it will be a battle or treasure tile.
     */
    public int tileInteract(int playerX, int playerY, int currentArea, Point currentRoom){

        boolean tileRemoved = false;
        Random random = new Random();

        do{

            if(currentArea == 1){
            
            if(bossPoint.x == playerX && bossPoint.y == playerY && currentRoom == bossRoomSize){

                bossPoint.x = -99;
                bossPoint.y = -99;
                tileRemoved = true;
    
            } else {
                
            for(Point pRoom1 : room1SpawnTiles){

                if(pRoom1.x == playerX && pRoom1.y == playerY){

                    pRoom1.x = -99;
                    pRoom1.y = -99;
                    tileRemoved = true;
                }
            }

            for(Point pRoom2 : room2SpawnTiles){

                if(pRoom2.x == playerX && pRoom2.y == playerY){

                    pRoom2.x = -99;
                    pRoom2.y = -99;
                    tileRemoved = true;
                }
            }

        }


            } else if(currentArea == 2){

                for(Point pRoom1 : room1SpawnTiles){

                    if(pRoom1.x == playerX && pRoom1.y == playerY){
    
                        pRoom1.x = -99;
                        pRoom1.y = -99;
                        tileRemoved = true;
                    }
                }
    
                for(Point pRoom2 : room2SpawnTiles){
    
                    if(pRoom2.x == playerX && pRoom2.y == playerY){
    
                        pRoom2.x = -99;
                        pRoom2.y = -99;
                        tileRemoved = true;
                    }
                }

                for(Point pRoom3 : room3SpawnTiles){
    
                    if(pRoom3.x == playerX && pRoom3.y == playerY){
    
                        pRoom3.x = -99;
                        pRoom3.y = -99;
                        tileRemoved = true;
                    }
                }

                for(Point pRoom4 : room4SpawnTiles){
    
                    if(pRoom4.x == playerX && pRoom4.y == playerY){
    
                        pRoom4.x = -99;
                        pRoom4.y = -99;
                        tileRemoved = true;
                    }
                }

                for(Point pRoom5 : room5SpawnTiles){
    
                    if(pRoom5.x == playerX && pRoom5.y == playerY){
    
                        pRoom5.x = -99;
                        pRoom5.y = -99;
                        tileRemoved = true;
                    }
                }

                if(bossPoint.x == playerX && bossPoint.y == playerY && currentRoom == bossRoomSize){

                    bossPoint.x = -99;
                    bossPoint.y = -99;
                    tileRemoved = true;
    
                }

            } else if(currentArea == 3){

                for(Point pRoom1 : room1SpawnTiles){

                    if(pRoom1.x == playerX && pRoom1.y == playerY){
    
                        pRoom1.x = -99;
                        pRoom1.y = -99;
                        tileRemoved = true;
                    }
                }

                for(Point pRoom3 : room3SpawnTiles){

                    if(pRoom3.x == playerX && pRoom3.y == playerY){
    
                        pRoom3.x = -99;
                        pRoom3.y = -99;
                        tileRemoved = true;
                    }
                }

                if(bossPoint.x == playerX && bossPoint.y == playerY && currentRoom == bossRoomSize){

                    bossPoint.x = -99;
                    bossPoint.y = -99;
                    tileRemoved = true;
    
                }

            }

        }   while(tileRemoved == false);
        
        int i = random.nextInt(101);

        return i;
    }
    /**
     * Returns how much runes are gained depending on the current Area.
     * @param currentArea currentArea of the player.
     * @return returns the amount of runes gaine depending on the currentArea.
     */
    public int treasureYield(int currentArea){

        Random random = new Random();

        int runesGained = currentArea * random.nextInt(151) + 50;

        return runesGained;
    }
    /**
     * Gets room1's size.
     * @return returns the Point values of room1.
     */
    public Point getRoom1Size(){

        return room1;

    }
    /**
     * Gets room2's size.
     * @return returns the Point values of room2.
     */
    public Point getRoom2Size(){

        return room2;

    }
    /**
     * Gets room3's size.
     * @return returns the Point values room3.
     */
    public Point getRoom3Size(){

        return room3;
    }
    /**
     * Gets room4's size.
     * @return returns the Point values room4.
     */
    public Point getRoom4Size(){

        return room4;
    }
    /**
     * Gets bossPoint.
     * @return returns the boss point value.
     */
    public Point getBossPoint(){

        return bossPoint;
    }
    /**
     * Gets the Boss Room's size.
     * @return returns the Point value of the Boss Room.
     */
    public Point getBossRoomSize(){

        return bossRoomSize;
    }
    /**
     * Gets the Points of all the room1 spawn tiles.
     * @return returns an arrayList containing all the spawn tiles for room1.
     */
    public ArrayList<Point> getRoom1SpawnTiles(){

        return room1SpawnTiles;

    }
    /**
     * Gets the Points of all the room2 spawn tiles.
     * @return returns an arrayList containing all the spawn tiles for room2.
     */
    public ArrayList<Point> getRoom2SpawnTiles() {

        return room2SpawnTiles;

    }
    /**
     * Gets the Points of all the room3 spawn tiles.
     * @return returns an arrayList containing all the spawn tiles for room3.
     */
    public ArrayList<Point> getRoom3SpawnTiles() {

        return room3SpawnTiles;

    }
    /**
     * Gets the Points of all the room4 spawn tiles.
     * @return returns an arrayList containing all the spawn tiles for room4.
     */
    public ArrayList<Point> getRoom4SpawnTiles() {

        return room4SpawnTiles;

    }
    /**
     * Gets the Points of all the room5 spawn tiles.
     * @return returns an arrayList containing all the spawn tiles for room5.
     */
    public ArrayList<Point> getRoom5SpawnTiles() {

        return room5SpawnTiles;

    }
    /**
     * Gets the Points of all the room6 spawn tiles.
     * @return returns an arrayList containing all the spawn tiles for room6.
     */
    public ArrayList<Point> getRoomEmptyTiles() {

        return room2EmptyTiles;

    }
    /**
     * Gets the Points of all the room7 spawn tiles.
     * @return returns an arrayList containing all the spawn tiles for room7.
     */
    public ArrayList<Point> getBossEmptyTiles() {

        return bossRoomEmptyTiles;

    }
}
