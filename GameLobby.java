package Prog3MP;


import java.awt.*;
import javax.swing.*;

/**
* This class handles the GameLobby segment of the program.
*
*@author Charles, Myrine.
*/

public class GameLobby {

    private JFrame mainFrame;
    private JLabel backgroundLabel, nameLabel, classLabel, lvLabel, charImgLabel, characterBorderLbl, runesLabel;
    private ImageIcon backgroundImage, charImg, characterBorder;
    private JButton travelButton, lvlButton, invButton, shopButton, quitButton;
    private JLayeredPane layeredPane;
    private Player player;
    private JComboBox<String> areaComboBox;
    private boolean area3Unlocked, area1Cleared, area2Cleared;
    private int chosenArea;
    private String area3Status;
    private Shop shop;
    private ShopView shopView;
    private Area1 area1;
    private Area2 area2;
    private Area3 area3;
    private FontHolder font = new FontHolder();

    /** 
     * Empty Constructor.
     */
    public GameLobby(){

    }
    
    /**
     * Constructs the game lobby.
     * 
     * @param player Instance of the player.
     * @param area3Unlocked Checks if Area 3 is accesible.
     */
    public GameLobby(Player player){

        area3Unlocked = false;
        this.player = player;

        printGameLobby();

    }
    /**
     * Prints the gameLobby's JFrame and background.
     */
    public void printGameLobby(){

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,1280,720);
        
        // FRAME

        mainFrame = new JFrame("Game Lobby");
        mainFrame.setSize(1280,720);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setUndecorated(true);
        mainFrame.setLocation(300, 160);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        printDetails();

        mainFrame.add(layeredPane);
        mainFrame.setVisible(true);

    }
    /**
     * Prints the gameLobby's details.
     */
    public void printDetails(){

        layeredPane.removeAll();

        runesLabel = new JLabel("Runes: " + player.getRunes());
        runesLabel.setBounds(840,70,400,50);
        runesLabel.setFont(new FontHolder().getFont(40));
        runesLabel.setForeground(Color.white);
        
        layeredPane.add(runesLabel, Integer.valueOf(4));


        backgroundImage = new ImageIcon("Prog3MP/DefaultBackgrounds.png");
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0,1280,720);
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setOpaque(false);
        layeredPane.add(backgroundLabel, Integer.valueOf(-1));

        nameLabel = new JLabel("Name: " + player.getName());
        nameLabel.setBounds(300,10,700,100);
        nameLabel.setFont(font.getFont(40));
        nameLabel.setForeground(Color.white);
        layeredPane.add(nameLabel);

        classLabel = new JLabel("Class " + player.getCClass());
        classLabel.setBounds(300,120,700,50);
        classLabel.setFont(font.getFont(40));
        classLabel.setForeground(Color.white);
        layeredPane.add(classLabel);

        lvLabel = new JLabel("Level " + player.getLevel());
        lvLabel.setBounds(840,120,700,50);
        lvLabel.setFont(font.getFont(40));
        lvLabel.setForeground(Color.white);
        layeredPane.add(lvLabel);

        characterBorderLbl = new JLabel();
        characterBorder = new ImageIcon("Prog3MP/CharacterBorder.png");
        characterBorderLbl.setIcon(characterBorder);
        characterBorderLbl.setBounds(300,200,180,410);
        layeredPane.add(characterBorderLbl, Integer.valueOf(2));

        charImg = new ImageIcon("Prog3MP/" + player.getCClass() + "Class.png");
        charImgLabel = new JLabel();
        charImgLabel.setIcon(charImg);
        charImgLabel.setBounds(300,200,180,410);
        layeredPane.add(charImgLabel);

        printButtons();
    }
    /**
     * Prints the gameLobby's buttons.
     */
    public void printButtons(){


        if(area3Unlocked == false){

            area3Status = "Locked";

        }   else {

            area3Status = "Area 3";
        }

        String areas[] = {"Select", "Area 1", "Area 2" , "" + area3Status};
        areaComboBox = new JComboBox<>(areas);

        travelButton = new JButton("Fast Travel");
        travelButton.setFont(font.getFont(20));
        travelButton.setFocusable(false);

        lvlButton = new JButton("Level Up");
        lvlButton.setFont(font.getFont(20));
        lvlButton.setFocusable(false);

        invButton = new JButton("Inventory");
        invButton.setFont(font.getFont(20));
        invButton.setFocusable(false);

        shopButton = new JButton("Shop");
        shopButton.setFont(font.getFont(20));
        shopButton.setFocusable(false);

        quitButton = new JButton("Quit");
        quitButton.setFont(font.getFont(20));
        quitButton.setFocusable(false);


        areaComboBox.setBounds(900,200,70,30);
        layeredPane.add(areaComboBox);

        travelButton.setBounds(700,200,200,60);
        layeredPane.add(travelButton);

        lvlButton.setBounds(700,300,200,60);
        layeredPane.add(lvlButton);

        invButton.setBounds(700,400,200,60);
        layeredPane.add(invButton);

        shopButton.setBounds(700,500,200,60);
        layeredPane.add(shopButton);

        quitButton.setBounds(700,600,200,60);
        layeredPane.add(quitButton);

            invButton.addActionListener(e -> {
                
                player.getInventory().printInventory(player);
            
                }
                
            );

            areaComboBox.addActionListener(e -> chosenArea = areaComboBox.getSelectedIndex());

            travelButton.addActionListener(e -> {

                if(chosenArea == 1) {

                    area1 = new Area1(player, area1Cleared);

                    area1.mainFrame.addWindowListener(new java.awt.event.WindowAdapter(){
        
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            
                            if(area1Cleared != true){
                            area1Cleared = area1.bossDefeated();
                            }
                            
                            if(area1Cleared == true && area2Cleared == true && area3Unlocked != true){
                                area3Unlocked = true;
                            }

                            printDetails();
                            
                        
                        }
                    });

                } else if (chosenArea == 2) {

                    area2 = new Area2(player, area2Cleared);
                    area2.mainFrame.addWindowListener(new java.awt.event.WindowAdapter(){
        
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            
                            if(area2Cleared != true){
                            area2Cleared = area2.bossDefeated();
                            }

                            if(area1Cleared == true && area2Cleared == true && area3Unlocked != true){
                                area3Unlocked = true;
                            }
                            
                            printDetails();
                            
                        
                        }
                    });
                    
                } else if(chosenArea == 3 && area3Unlocked == true) {

                    area3 = new Area3(player);
                    area3.mainFrame.addWindowListener(new java.awt.event.WindowAdapter(){
        
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            
                            if(area3.credits() == true){

                                mainFrame.dispose();
                                CreditsView cView = new CreditsView();
                                new CreditsController(cView);
                            }
                            
                        
                        }
                    });

                }

            });

            shopButton.addActionListener(e -> {
                
                shop = new Shop();
                shopView = new ShopView();
                new ShopController(shop, shopView, player);

            });

            lvlButton.addActionListener(e -> {

                
                player.getLevelUp().printLevelUp(player);
                player.getLevelUp().printDetails();
                
                player.getLevelUp().mainFrame.addWindowListener(new java.awt.event.WindowAdapter(){
        
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        updatePlayerLvl();

                    }
                });

            });


            quitButton.addActionListener(e -> {

                new TitleScreenView();
                mainFrame.dispose();

            });


    }
    /**
     * Updates the level of the player.
     */
    public void updatePlayerLvl(){

        layeredPane.remove(lvLabel);
        lvLabel = new JLabel("Level " + player.getLevel());
        lvLabel.setBounds(920,300,700,50);
        lvLabel.setFont(font.getFont(40));
        lvLabel.setForeground(Color.white);
        layeredPane.add(lvLabel);

    }

}
