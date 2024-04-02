package Prog3MP;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Area1 Class is the parent class/superclass of Area2 and Area3. It prints and handles everything regarding the
 * area exploration segment of the code.
 * 
 * @author Charles, Myrine.
 */

public class Area1 {
    /**
     * Swing components
     */
    public JFrame mainFrame;
    private JLabel backgroundLabel, weaponLbl,details1 ,details2, areaName;
    private ImageIcon backgroundImage, tilesImg, playerImg, doorImg, spawnTile, weaponImg, fastTravelTileImg, bossTileImg, emptyTile, credsTile;
    private JLayeredPane layeredPane;
    private JPanel areaPanel, weaponPanel;
    private JButton upBtn, downBtn, leftBtn, rightBtn;

    /**
     * Class instances
     */
    private BattlePhase battlePhase;
    protected BattlePhaseController controller;
    private BattlePhaseView view;
    private Enemy enemy;
    protected Player player;

    /**
     * Area variables
     */
    protected Point fastTravelPoint, exitPoint, playerPoint;
    protected Point door1, door2, door3, door4, door5, door6, door7, door8, enteredDoor;
    private Point currentRoom, room1, room2, room3, room4, bossRoom;
    protected ArrayList<Point> room1SpawnTiles, room2SpawnTiles, room3SpawnTiles, room4SpawnTiles, room5SpawnTiles, roomEmptyTiles, bossRoomEmptyTiles;
    private Tiles tiles;
    protected String area, direction;
    protected int currentArea, heldRunes = 0;
    protected boolean creditsOpen = false, creditsInitiated = false, exitUnlocked = false, detailHelper = false;
    protected boolean bossDefeated = false, areaCleared;
    
    public Area1(){
    }

    /**
     * Constructs a new Area for the player.
     * 
     * @param player Instance of the player for stats, runes, weapon.
     */
    public Area1(Player player, boolean areaCleared){

        this.areaCleared = areaCleared;
        this.player = player;
        area = "Area1";

        fastTravelPoint = new Point(1,6);
        playerPoint = new Point(1,6);

        currentArea = 1;

        door1 = new Point(1,0);
        door2 = new Point(3,6);
        door3 = new Point(3,0);
        door4 = new Point(2,6);

        exitPoint = new Point(2,0);

        if(areaCleared == true){

            exitUnlocked = true;
        }

        printArea();
        printDetails();
        printButtons();
        printRoom1();
    }
    /**
     * Prints the Area's background and JFrame.
     */
    protected void printArea(){

        layeredPane = new JLayeredPane(); 

        // CREATE TILES
        tiles = new Tiles(area);
        layeredPane.setBounds(0,0,1280,720);

        room1SpawnTiles = tiles.getRoom1SpawnTiles();
        room2SpawnTiles = tiles.getRoom2SpawnTiles();
        room3SpawnTiles = tiles.getRoom3SpawnTiles();
        room4SpawnTiles = tiles.getRoom4SpawnTiles();
        room5SpawnTiles = tiles.getRoom5SpawnTiles();

        room1 = tiles.getRoom1Size();
        room2 = tiles.getRoom2Size();
        room3 = tiles.getRoom3Size();
        room4 = tiles.getRoom4Size();
        bossRoom = tiles.getBossRoomSize();

        roomEmptyTiles = tiles.getRoomEmptyTiles();
        bossRoomEmptyTiles = tiles.getBossEmptyTiles();

        // FRAME
        mainFrame = new JFrame("Area");
        mainFrame.setSize(1280,720);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setUndecorated(true);
        mainFrame.setLocation(300, 160);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundImage = new ImageIcon("Prog3MP/DefaultBackgrounds.png");

        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0,1280,720);
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setOpaque(false);
        layeredPane.add(backgroundLabel, Integer.valueOf(-1));
        

        mainFrame.add(layeredPane);
        mainFrame.setVisible(true);

    }
    /**
     * Prints the initial Room 1.
     */
    protected void printRoom1(){

        tilesImg = new ImageIcon("Prog3MP/Tiles.png");
        playerImg = new ImageIcon("Prog3MP/PlayerIcon.png");
        doorImg = new ImageIcon("Prog3MP/DoorIcon.png");
        spawnTile = new ImageIcon("Prog3MP/SpawnTile.png");
        fastTravelTileImg = new ImageIcon("Prog3MP/FastTravelTile.png");
        bossTileImg = new ImageIcon("Prog3MP/BossTile.png");
        emptyTile = new ImageIcon("Prog3MP/EmptyTile.png");
        credsTile = new ImageIcon("Prog3MP/CreditsTile.png");

        currentRoom = room1;

        areaPanel = new JPanel();
        areaPanel.setBounds(240,100,500,500);
        areaPanel.setLayout(new GridBagLayout());
        areaPanel.setBackground(Color.black);
        layeredPane.add(areaPanel, Integer.valueOf(2));
        updateAreaPanel();



    }
    /**
     * Prints the input/arrow buttons for the Area.
     */
    protected void printButtons(){

        upBtn = new JButton();
        upBtn.setBounds(900,400,50,50);

        downBtn = new JButton();
        downBtn.setBounds(900,450,50,50);

        leftBtn = new JButton();
        leftBtn.setBounds(850,450,50,50);

        rightBtn = new JButton();
        rightBtn.setBounds(950,450,50,50);

        layeredPane.add(upBtn, Integer.valueOf(3));
        layeredPane.add(downBtn, Integer.valueOf(3));
        layeredPane.add(leftBtn, Integer.valueOf(3));
        layeredPane.add(rightBtn, Integer.valueOf(3));

        upBtn.addActionListener(e -> {
            
            direction = "Up";
            playerPoint.y -= 1;
            validateMove();
            

        });

        downBtn.addActionListener(e -> {

            direction = "Down";
            playerPoint.y += 1;
            validateMove();

        });

        leftBtn.addActionListener(e -> {

            direction = "Left";
            playerPoint.x -= 1;
            validateMove();

        });

        rightBtn.addActionListener(e -> {

            direction = "Right";
            playerPoint.x += 1;
            validateMove();

        });

    }
    /**
     * Updates the Area Panel evertime a button is clicked.
     */
    private void updateAreaPanel() {
        
        areaPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < currentRoom.x; i++) {

            for (int j = 0; j < currentRoom.y; j++) {

                gbc.gridx = i;
                gbc.gridy = j;

                if(currentArea == 1){

                if(currentRoom == room1){

                for(Point point : room1SpawnTiles) {

                        if(point.x == i && point.y == j){

                            JLabel label = new JLabel();
                            label.setIcon(spawnTile);
                            areaPanel.add(label, gbc);

                        }
                    }
                    
                } else if(currentRoom == room2){

                    for(Point point : room2SpawnTiles) {

                        if(point.x == i && point.y == j){
    
                            JLabel label = new JLabel();
                            label.setIcon(spawnTile);
                            areaPanel.add(label, gbc);
    
                        }
                    }
                } 
            } else if(currentArea == 2){

                if(currentRoom == room1){

                    for(Point point : room1SpawnTiles) {
    
                            if(point.x == i && point.y == j){
    
                                JLabel label = new JLabel();
                                label.setIcon(spawnTile);
                                areaPanel.add(label, gbc);
    
                            }
                        }

                    } else if(currentRoom == room2){

                        for(Point point : room2SpawnTiles) {
        
                                if(point.x == i && point.y == j){
        
                                    JLabel label = new JLabel();
                                    label.setIcon(spawnTile);
                                    areaPanel.add(label, gbc);
        
                                }
                            }
                            
                        } else if(currentRoom == room3){


                            for(Point point : roomEmptyTiles) {
            
                                if(point.x == i && point.y == j){
        
                                    JLabel label = new JLabel();
                                    label.setIcon(emptyTile);
                                    areaPanel.add(label, gbc);
        
                                }
                            }

                            for(Point point : room3SpawnTiles) {
            
                                    if(point.x == i && point.y == j){
            
                                        JLabel label = new JLabel();
                                        label.setIcon(spawnTile);
                                        areaPanel.add(label, gbc);
            
                                    }
                                }
                            
                                
                            } else if(currentRoom == room4){

                                for(Point point : room4SpawnTiles) {
                
                                        if(point.x == i && point.y == j){
                
                                            JLabel label = new JLabel();
                                            label.setIcon(spawnTile);
                                            areaPanel.add(label, gbc);
                
                                        }
                                    }
                                    
                            } else if(currentRoom == bossRoom){
                                

                                for(Point point : bossRoomEmptyTiles){

                                    if(point.x == i && point.y == j){

                                        JLabel label = new JLabel();
                                        label.setIcon(emptyTile);
                                        areaPanel.add(label, gbc);

                                    }
                                }

                                for(Point point : room5SpawnTiles) {
                
                                        if(point.x == i && point.y == j){
                
                                            JLabel label = new JLabel();
                                            label.setIcon(spawnTile);
                                            areaPanel.add(label, gbc);
                
                                        }
                                    }
                                    
                                }

            } else if(currentArea == 3){

                if(currentRoom == room1){

                    for(Point point: room1SpawnTiles){

                        if(point.x == i && point.y == j){
    
                            JLabel label = new JLabel();
                            label.setIcon(spawnTile);
                            areaPanel.add(label, gbc);

                        }

                    }

                } else if(currentRoom == bossRoom){
                                
                    for(Point point : bossRoomEmptyTiles){

                        if(point.x == i && point.y == j){

                            JLabel label = new JLabel();
                            label.setIcon(emptyTile);
                            areaPanel.add(label, gbc);

                        }
                    }

                }else if(currentRoom == room3){

                    for(Point point: room3SpawnTiles){

                        if(point.x == i && point.y == j){
    
                            JLabel label = new JLabel();
                            label.setIcon(spawnTile);
                            areaPanel.add(label, gbc);

                        }

                    }
                }

            }

            if(currentArea != 3){

                // PRINT FAST TRAVEL TILE
                if(currentRoom == bossRoom && i == exitPoint.x && j == exitPoint.y && exitUnlocked == true){

                    JLabel label = new JLabel();
                    label.setIcon(fastTravelTileImg);
                    areaPanel.add(label, gbc);

                }
            } else {

                if(currentRoom == room3 && i == exitPoint.x && j == exitPoint.y && creditsOpen == true){

                    JLabel label = new JLabel();
                    label.setIcon(credsTile);
                    areaPanel.add(label, gbc);

                }
            }

                // PRINT PLAYER IMG ON TILE
                if(playerPoint.x == i && playerPoint.y == j){

                    JLabel label = new JLabel();
                    label.setIcon(playerImg);
                    areaPanel.add(label, gbc);

                } 

                if(i == fastTravelPoint.x && j == fastTravelPoint.y && currentRoom == room1){


                    JLabel label = new JLabel();
                    label.setIcon(fastTravelTileImg);
                    areaPanel.add(label, gbc);
    
                }

                // PRINT BOSS TILE
                if(i == tiles.getBossPoint().x && j == tiles.getBossPoint().y && currentRoom == bossRoom){

                    JLabel label = new JLabel();
                    label.setIcon(bossTileImg);
                    areaPanel.add(label, gbc);
                }
                
                // PRINT DOORS
                if(currentArea == 1){

                    if(door1.x == i && door1.y == j && currentRoom == room1){
                        
                        JLabel label = new JLabel();
                        label.setIcon(doorImg);
                        areaPanel.add(label, gbc);
    
                        } else if(door2.x == i && door2.y == j && currentRoom == room2){
                        
                            JLabel label = new JLabel();
                            label.setIcon(doorImg);
                            areaPanel.add(label, gbc);
        
                            } else if(door3.x == i && door3.y == j && currentRoom == room2){
                        
                                JLabel label = new JLabel();
                                label.setIcon(doorImg);
                                areaPanel.add(label, gbc);
            
                                } else if(door4.x == i && door4.y == j && currentRoom == bossRoom){
    
                                    JLabel label = new JLabel();
                                    label.setIcon(doorImg);
                                    areaPanel.add(label, gbc);
                
                                } else{
                            
                            // Prints tiles by default if no doors
                            JLabel label = new JLabel();
                            label.setIcon(tilesImg);    
                            areaPanel.add(label, gbc);
                        }
    
                    } else if(currentArea == 2){
                        

                        if(door1.x == i && door1.y == j && currentRoom == room1){
                        
                        JLabel label = new JLabel();
                        label.setIcon(doorImg);
                        areaPanel.add(label, gbc);
    
                        } else if(door2.x == i && door2.y == j && currentRoom == room2){
                        
                            JLabel label = new JLabel();
                            label.setIcon(doorImg);
                            areaPanel.add(label, gbc);
        
                            } else if(door3.x == i && door3.y == j && currentRoom == room2){
                        
                                JLabel label = new JLabel();
                                label.setIcon(doorImg);
                                areaPanel.add(label, gbc);
            
                                } else if(door4.x == i && door4.y == j && currentRoom == room3){
    
                                    JLabel label = new JLabel();
                                    label.setIcon(doorImg);
                                    areaPanel.add(label, gbc);
                
                                } else if(door5.x == i && door5.y == j && currentRoom == room3){
    
                                    JLabel label = new JLabel();
                                    label.setIcon(doorImg);
                                    areaPanel.add(label, gbc);
                
                                } else if(door6.x == i && door6.y == j && currentRoom == room4){
    
                                    JLabel label = new JLabel();
                                    label.setIcon(doorImg);
                                    areaPanel.add(label, gbc);
                
                                } else if(door7.x == i && door7.y == j && currentRoom == room3){
    
                                    JLabel label = new JLabel();
                                    label.setIcon(doorImg);
                                    areaPanel.add(label, gbc);
                
                                } else if(door8.x == i && door8.y == j && currentRoom == bossRoom){
    
                                    JLabel label = new JLabel();
                                    label.setIcon(doorImg);
                                    areaPanel.add(label, gbc);
                
                                } else {
                            
                                    
                            // Prints tiles by default if no doors
                            JLabel label = new JLabel();
                            label.setIcon(tilesImg);    
                            areaPanel.add(label, gbc);
                        }
                    } else if(currentArea == 3){

                        if(door1.x == i && door1.y == j && currentRoom == room1){
                        
                            JLabel label = new JLabel();
                            label.setIcon(doorImg);
                            areaPanel.add(label, gbc);
        
                            } else if(door2.x == i && door2.y == j && currentRoom == bossRoom){
                            
                                JLabel label = new JLabel();
                                label.setIcon(doorImg);
                                areaPanel.add(label, gbc);
            
                                } else if(door3.x == i && door3.y == j && currentRoom == bossRoom){
                            
                                    JLabel label = new JLabel();
                                    label.setIcon(doorImg);
                                    areaPanel.add(label, gbc);
                
                                    } else if(door4.x == i && door4.y == j && currentRoom == room3){
        
                                        JLabel label = new JLabel();
                                        label.setIcon(doorImg);
                                        areaPanel.add(label, gbc);
                    
                                    } else{
                                
                                // Prints tiles by default if no doors
                                JLabel label = new JLabel();
                                label.setIcon(tilesImg);    
                                areaPanel.add(label, gbc);
                            }
                    }   
            }
        }

        areaPanel.revalidate();
        areaPanel.repaint();

    }
    /**
     * Validates and checks if a move is valid.
     */
    private void validateMove(){

        if(currentRoom == room3 && currentArea == 2){
        for(Point point : roomEmptyTiles){

            if((currentRoom == room3) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Up")){

                playerPoint.y += 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            } else if((currentRoom == room3) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Down")){

                playerPoint.y -= 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            } else if((currentRoom == room3) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Left")){

                playerPoint.x += 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            } else if((currentRoom == room3) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Right")){

                playerPoint.x -= 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            }
        }

    } else if(currentRoom == bossRoom && currentArea == 2){

        // Empty Tile conditional exception
        for(Point point : bossRoomEmptyTiles){

            if((currentRoom == bossRoom) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Up")){

                playerPoint.y += 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            } else if((currentRoom == bossRoom) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Down")){

                playerPoint.y -= 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            } else if((currentRoom == bossRoom) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Left")){

                playerPoint.x += 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            } else if((currentRoom == bossRoom) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Right")){

                playerPoint.x -= 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            }
        }

    } else if(currentArea == 3 && currentRoom == bossRoom){

            for(Point point : bossRoomEmptyTiles){

            if((currentRoom == bossRoom) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Up")){

                playerPoint.y += 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            } else if((currentRoom == bossRoom) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Down")){

                playerPoint.y -= 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            } else if((currentRoom == bossRoom) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Left")){

                playerPoint.x += 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            } else if((currentRoom == bossRoom) && (point.x == playerPoint.x) && (point.y  == playerPoint.y) && direction.equals("Right")){

                playerPoint.x -= 1;
                JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);
            }
        }


    }
        if(playerPoint.x > currentRoom.x - 1){

            playerPoint.x -= 1;
            JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);

        } else if(playerPoint.x < 0){

            playerPoint.x += 1;
            JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);

        } else if(playerPoint.y > currentRoom.y - 1){

            playerPoint.y -= 1;
            JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);

        } else if(playerPoint.y < 0){

            playerPoint.y += 1;
            JOptionPane.showMessageDialog(null, "Out of bounds!", "Error Message", JOptionPane.WARNING_MESSAGE);

        }  else if(checkForBossTile() == true){

            tiles.tileInteract(playerPoint.x, playerPoint.y, currentArea, currentRoom);

                // Boss enemies

                enemy = new Enemy();
                if(currentArea == 1){
                enemy = enemy.getBoss(currentArea);
                } else if(currentArea == 2){
                    enemy = enemy.getBoss(currentArea);
                } else if(currentArea == 3){
                    enemy = enemy.getBoss(currentArea);
                }

                battlePhase = new BattlePhase(player, enemy);
                view = new BattlePhaseView();
                controller = new BattlePhaseController(battlePhase, view, player, enemy, mainFrame);
                controller.fight();
                controller.isBossSet(true);

                controller.view.mainFrame.addWindowListener(new java.awt.event.WindowAdapter(){

                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        
                        if(currentArea != 3){
                        
                        if(areaCleared != true){

                        exitUnlocked = controller.bossDefeated();
                        bossDefeated = controller.bossDefeated();

                        }

                        } else {

                            creditsOpen = controller.bossDefeated();
                        }

                        int runesGained = controller.getEnemyRunes();
                        heldRunes += runesGained;
                        runeDetail(runesGained, heldRunes);

                        updateAreaPanel();
                    }
                });

            }

          else if(checkForSpawnTiles() == true){
            
            if(tiles.tileInteract(playerPoint.x, playerPoint.y, currentArea, currentRoom) > 25 && currentArea != 3){

                // Normal enemies

                enemy = new Enemy();
                Random rand = new Random();
                int randomNum = rand.nextInt(3) + 1;

                if(currentArea == 1){
                enemy = enemy.getArea1Enemy(randomNum);
                } else if(currentArea == 2){
                    enemy = enemy.getArea2Enemy(randomNum);
                }

                battlePhase = new BattlePhase(player, enemy, currentArea);
                view = new BattlePhaseView();
                controller = new BattlePhaseController(battlePhase, view, player, enemy, mainFrame);
                controller.fight();

                controller.view.mainFrame.addWindowListener(new java.awt.event.WindowAdapter(){

                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        
                        int runesGained = controller.getEnemyRunes();
                        heldRunes += runesGained;
                        runeDetail(runesGained, heldRunes);
                    }
                });


            } else {


                int runesGained = tiles.treasureYield(currentArea);
                heldRunes += runesGained;

                runeDetail(runesGained, heldRunes);

            }

            updateAreaPanel();
        
        } else if (checkForDoor() == true){

            if(currentArea == 1){

            if(currentRoom == room1 && enteredDoor == door1){

                currentRoom = room2;
                updateAreaPanel();

            } else if(currentRoom == room2 && enteredDoor == door3){

                currentRoom = bossRoom;
                updateAreaPanel();

            } else if(currentRoom == room2 && enteredDoor == door2){

                currentRoom = room1;
                updateAreaPanel();

            } else if(currentRoom == bossRoom && enteredDoor == door4){

                currentRoom = room2;
                updateAreaPanel();
            }

            } else if(currentArea == 2){

                if(currentRoom == room1 && enteredDoor == door1){
                    
                    currentRoom = room2;
                    updateAreaPanel();
    
                } else if(currentRoom == room2 && enteredDoor == door2){
                    
                    currentRoom = room1;
                    updateAreaPanel();
    
                } else if(currentRoom == room2 && enteredDoor == door3){
                    
                    currentRoom = room3;
                    updateAreaPanel();
    
                } else if(currentRoom == room3 && enteredDoor == door4){
    
                    currentRoom = room2;
                    updateAreaPanel();

                } else if(currentRoom == room3 && enteredDoor == door5){
    
                    currentRoom = room4;
                    updateAreaPanel();
    
                } else if(currentRoom == room4 && enteredDoor == door6){
    
                    currentRoom = room3;
                    updateAreaPanel();
    
                } else if(currentRoom == room3 && enteredDoor == door7){
    
                    currentRoom = bossRoom;
                    updateAreaPanel();
    
                } else if(currentRoom == bossRoom && enteredDoor == door8){
    
                    currentRoom = room3;
                    updateAreaPanel();
    
                }
            } else if(currentArea == 3){

                if(currentRoom == room1 && enteredDoor == door1){
                    
                    currentRoom = bossRoom;
                    updateAreaPanel();
    
                } else if(currentRoom == bossRoom && enteredDoor == door2){
                    
                    currentRoom = room1;
                    updateAreaPanel();
    
                } else if(currentRoom == bossRoom && enteredDoor == door3){
                    
                    currentRoom = room3;
                    updateAreaPanel();
    
                } else if(currentRoom == room3 && enteredDoor == door4){
                    
                    currentRoom = bossRoom;
                    updateAreaPanel();
    
                }
            }

        } else if(playerPoint.x == exitPoint.x && playerPoint.y == exitPoint.y && currentRoom == bossRoom && exitUnlocked == true){
            
            player.addRunes(heldRunes);
            mainFrame.dispose();
            JOptionPane.showMessageDialog(null, "Gained " + heldRunes + " runes!", "Area Cleared!", JOptionPane.WARNING_MESSAGE);
            
            
        } else if(currentArea == 3 &&  currentRoom == room3 && creditsOpen == true){

            updateAreaPanel();  
            checkForCreditsTile();

        }else if(currentRoom == room1 && playerPoint.x == fastTravelPoint.x && playerPoint.y == fastTravelPoint.y){

            player.addRunes(heldRunes);
            mainFrame.dispose();
            JOptionPane.showMessageDialog(null, "Gained " + heldRunes + " runes!", "Fleed the Area!", JOptionPane.WARNING_MESSAGE);
        } else {

            updateAreaPanel();
        }

    }
    /**
     * Checks if the playerPoint is on a bossPoint.
     * 
     * @return returns true if playerPoint == bossPoint, else returns false.
     */
    private boolean checkForBossTile(){

        if(playerPoint.x == tiles.getBossPoint().x && playerPoint.y == tiles.getBossPoint().y && currentRoom == bossRoom){
        System.out.println("Boss encountered");
            return true;
        }

        return false;

    }
    /**
     * Checks if the playerPoint is on a spawnTilePoint.
     * 
     * @return returns true if playerPoint == spawnTilePoint, else returns false.
     */
    private boolean checkForSpawnTiles(){


        if(currentArea == 1){

        if(currentRoom == room1){

            for(Point point : room1SpawnTiles) {

                    if(point.x == playerPoint.x && point.y == playerPoint.y){

                        return true;
                    }
                }
                
            } else if(currentRoom == room2){

                for(Point point : room2SpawnTiles) {

                    if(point.x == playerPoint.x && point.y == playerPoint.y){
                        
                        return true;
                    }
                }
            } 

        } else if(currentArea == 2){

            if(currentRoom == room1){

                for(Point point : room1SpawnTiles) {
    
                        if(point.x == playerPoint.x && point.y == playerPoint.y){
    
                            return true;
                        }
                    }
                    
                } else if(currentRoom == room2){
    
                    for(Point point : room2SpawnTiles) {
    
                        if(point.x == playerPoint.x && point.y == playerPoint.y){
                            
                            return true;
                        }
                    }
                } else if(currentRoom == room3){
    
                    for(Point point : room3SpawnTiles) {
    
                        if(point.x == playerPoint.x && point.y == playerPoint.y){
                            
                            return true;
                        }
                    } 
                } else if(currentRoom == room4){
    
                    for(Point point : room4SpawnTiles) {
    
                        if(point.x == playerPoint.x && point.y == playerPoint.y){
                            
                            return true;
                        }
                    }
                } else if(currentRoom == bossRoom){
    
                    for(Point point : room5SpawnTiles) {
    
                        if(point.x == playerPoint.x && point.y == playerPoint.y){
                            
                            return true;
                        }
                    }
                } 
        } else if(currentArea == 3){

            if(currentRoom == room1){

                for(Point point : room1SpawnTiles){

                    if(point.x == playerPoint.x && point.y == playerPoint.y){

                        return true;
                    }
                }
            } else if(currentRoom == room3){

                for(Point point : room3SpawnTiles){

                    if(point.x == playerPoint.x && point.y == playerPoint.y){

                        return true;
                    }
                    
                }
            }
        }

        return false;

    }
    /**
     * Checks if the playerPoint is on a doorPoint.
     * 
     * @return returns true if playerPoint == doorPoint, else returns false.
     */
    private boolean checkForDoor(){
        
        if(currentArea == 1){

        if(playerPoint.x == door1.x && playerPoint.y == door1.y && currentRoom == room1){

            enteredDoor = door1;
            nextRoom(1);
            return true;

        } else if(playerPoint.x == door2.x && playerPoint.y == door2.y && currentRoom == room2){

            enteredDoor = door2;
            nextRoom(2);
            return true;

        }else if(playerPoint.x == door3.x && playerPoint.y == door3.y && currentRoom == room2){

            enteredDoor = door3;
            nextRoom(3);
            return true;

        }else if(playerPoint.x == door4.x && playerPoint.y == door4.y && currentRoom == bossRoom){

            enteredDoor = door4;
            nextRoom(4);
            return true;

        }

    } else if(currentArea == 2){

        if(playerPoint.x == door1.x && playerPoint.y == door1.y && currentRoom == room1){

            enteredDoor = door1;
            nextRoom(1);
            return true;

        } else if(playerPoint.x == door2.x && playerPoint.y == door2.y && currentRoom == room2){

            enteredDoor = door2;
            nextRoom(2);
            return true;

        } else if(playerPoint.x == door3.x && playerPoint.y == door3.y && currentRoom == room2){

            enteredDoor = door3;
            nextRoom(3);
            return true;

        } else if(playerPoint.x == door4.x && playerPoint.y == door4.y && currentRoom == room3){

            enteredDoor = door4;
            nextRoom(4);
            return true;

        } else if(playerPoint.x == door5.x && playerPoint.y == door5.y && currentRoom == room3){

            enteredDoor = door5;
            nextRoom(5);
            return true;

        } else if(playerPoint.x == door6.x && playerPoint.y == door6.y && currentRoom == room4){

            enteredDoor = door6;
            nextRoom(6);
            return true;

        } else if(playerPoint.x == door7.x && playerPoint.y == door7.y && currentRoom == room3){

            enteredDoor = door7;
            nextRoom(7);
            return true;

        } else if(playerPoint.x == door8.x && playerPoint.y == door8.y && currentRoom == bossRoom){

            enteredDoor = door8;
            nextRoom(8);
            return true;

        }

    } else if(currentArea == 3){

        if(playerPoint.x == door1.x && playerPoint.y == door1.y && currentRoom == room1){

            enteredDoor = door1;
            nextRoom(1);
            return true;

        } else if(playerPoint.x == door2.x && playerPoint.y == door2.y && currentRoom == bossRoom){

            enteredDoor = door2;
            nextRoom(2);
            return true;

        } else if(playerPoint.x == door3.x && playerPoint.y == door3.y && currentRoom == bossRoom){

            enteredDoor = door3;
            nextRoom(3);
            return true;

        } else if(playerPoint.x == door4.x && playerPoint.y == door4.y && currentRoom == room3){

            enteredDoor = door4;
            nextRoom(4);
            return true;

        }

    }
        return false;

    }
    /**
     * Checks what door number is entered by the player and returns the connecting doorPoint.
     * 
     * @param doorNum the door that was entered by the player.
     * @return returns the door number of the next room's doorPoint.
     */
    private void nextRoom(int doorNum){

        if(currentArea == 1){

            if(doorNum == 1){
            
            playerPoint.x = door2.x;
            playerPoint.y = door2.y;
            
            } else if(doorNum == 2){

            playerPoint.x = door1.x;
            playerPoint.y = door1.y;
            
            } else if(doorNum == 3){

            playerPoint.x = door4.x;
            playerPoint.y = door4.y;
           

            } else{
            
            playerPoint.x = door3.x;
            playerPoint.y = door3.y;
            
            }

        } else if(currentArea == 2){

            if(doorNum == 1){
            
            playerPoint.x = door2.x;
            playerPoint.y = door2.y;
            
            } else if(doorNum == 2){
    
            playerPoint.x = door1.x;
            playerPoint.y = door1.y;
                
            } else if(doorNum == 3){
    
            playerPoint.x = door4.x;
            playerPoint.y = door4.y;
               
    
            } else if(doorNum == 4){
    
            playerPoint.x = door3.x;
            playerPoint.y = door3.y;

            } else if(doorNum == 5){
    
            playerPoint.x = door6.x;
            playerPoint.y = door6.y;
               
            } else if(doorNum == 6){
    
            playerPoint.x = door5.x;
            playerPoint.y = door5.y;
                   
            } else if(doorNum == 7){
    
            playerPoint.x = door8.x;
            playerPoint.y = door8.y;
                   
        
            } else if(doorNum == 8){
    
            playerPoint.x = door7.x;
            playerPoint.y = door7.y;
                   
            }

        } if(currentArea == 3){

            if(doorNum == 1){
            
                playerPoint.x = door2.x;
                playerPoint.y = door2.y;
                
            } else if(doorNum == 2){
            
                playerPoint.x = door1.x;
                playerPoint.y = door1.y;
                
            } if(doorNum == 3){
            
                playerPoint.x = door4.x;
                playerPoint.y = door4.y;
                
            } if(doorNum == 4){
            
                playerPoint.x = door3.x;
                playerPoint.y = door3.y;
                
            }
        }
    }
    /**
     * Prints the player's current weapon's details.
     */
    protected void printDetails(){   

        if(area.equals("Area1")){

            areaName = new JLabel("Stormveil Castle");
            areaName.setBounds(240,30,400,100);
            areaName.setFont(new FontHolder().getFont(40));
            areaName.setForeground(Color.white);
            layeredPane.add(areaName, Integer.valueOf(3));

        } else if(area.equals("Area2")){

            areaName = new JLabel("Raya Lucaria Academy");
            areaName.setBounds(240,30,400,100);
            areaName.setFont(new FontHolder().getFont(40));
            areaName.setForeground(Color.white);
            layeredPane.add(areaName, Integer.valueOf(3));
            

        } else {

            areaName = new JLabel("The Elden Throne");
            areaName.setBounds(240,30,400,100);
            areaName.setFont(new FontHolder().getFont(40));
            areaName.setForeground(Color.white);
            layeredPane.add(areaName, Integer.valueOf(3));
        }

        weaponLbl = new JLabel(player.getInventory().getCurrentWeapon());
        weaponImg = new ImageIcon("Prog3MP/" + player.getInventory().getCurrentWeapon() + "Icon.png");
        weaponLbl.setBounds(0,0,250,100);
        weaponLbl.setVerticalTextPosition(JLabel.TOP);
        weaponLbl.setHorizontalTextPosition(JLabel.CENTER);
        weaponLbl.setFont(new FontHolder().getFont(20));
        weaponLbl.setForeground(Color.white);
        weaponLbl.setIcon(weaponImg);

        weaponPanel = new JPanel();
        weaponPanel.setBounds(700,90,400,250);
        weaponPanel.setOpaque(false);
        weaponPanel.add(weaponLbl);

        layeredPane.add(weaponPanel, Integer.valueOf(4));
        
        
    }
    /**
     * Checks if playerPoint == credits tile point, and if Area == Area3.
     */
    public void checkForCreditsTile(){

        if(playerPoint.x == exitPoint.x && playerPoint.y == exitPoint.y){

            creditsInitiated = true;
            mainFrame.dispose();

        }
    }
    /**
     * Checks if the player entered the credits tile, which will initiate the credits.
     * 
     * @return returns true if the credits have been touched to the game lobby, ending the game.
     */
    public boolean credits(){

        return creditsInitiated;
    }
    /**
     * Checks if the boss has been defeated.
     * 
     * @return returns true if the boss of the Area has been defeated, else returns false.
     */
    public boolean bossDefeated(){

        return bossDefeated;
    }
    /**
     * Detail component for runes.
     * 
     * @param runesGained Runes gained from the tile interaction/fight.
     * @param heldRunes Total runes held so far during the Area instance.
     */
    public void runeDetail(int runesGained, int heldRunes){

        if(detailHelper == false){

            details1 = new JLabel("Gained " + runesGained + " runes");
            details1.setBounds(580,560, 150, 50);
            details1.setFont(new FontHolder().getFont(20));
            details1.setForeground(Color.white);
            layeredPane.add(details1, Integer.valueOf(4));

            details2 = new JLabel("Holding " + heldRunes + " runes");
            details2.setBounds(580,535,200,50);
            details2.setFont(new FontHolder().getFont(20));
            details2.setForeground(Color.white);
            layeredPane.add(details2, Integer.valueOf(4));

            detailHelper = true;

        } else {

            layeredPane.remove(details1);
            layeredPane.remove(details2);
            details1 = new JLabel("Gained " + runesGained + " runes");
            details1.setBounds(580,560, 150, 50);
            details1.setFont(new FontHolder().getFont(20));
            details1.setForeground(Color.white);
            layeredPane.add(details1, Integer.valueOf(4));

            details2 = new JLabel("Holding " + heldRunes + " runes");
            details2.setBounds(580,530,200,50);
            details2.setFont(new FontHolder().getFont(20));
            details2.setForeground(Color.white);
            layeredPane.add(details2, Integer.valueOf(4));

        }
    }

}   
