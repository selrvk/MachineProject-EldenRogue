package Prog3MP;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * ShopView class handles the view for the Shop segment of the program.
 * 
 * @author Charles, Myrine.
 */
public class ShopView {
    
    public JFrame mainFrame;
    private JLabel weaponImgLabel, playerRunes, backgroundLabel, price, dex, hp, end, str, inte, fth, weaponPanelLbl;
    private ImageIcon weaponIcon, backgroundImage, weaponPanel;
    public JButton refresh, back, buy1, buy2, buy3, buy4;
    private JLayeredPane layeredPane, weaponPane;
    private Player player;
    public ArrayList<Weapons> shopWeapons;
    private int randomNum;

    /**
     * Constructor.
     */
    public ShopView(){


    }
    /**
     * Prints the shop JFrame.
     */
    public void printFrame(){

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,1280,720);

        mainFrame = new JFrame("Shop");
        mainFrame.setSize(1280,720);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setUndecorated(true);
        mainFrame.setLocation(300, 160);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        mainFrame.add(layeredPane);

        printDetails();

    }
    /**
     * Prints the shop details.
     */
    public void printDetails(){

        layeredPane.removeAll();
        backgroundImage = new ImageIcon("Prog3MP/DefaultBackgrounds.png");
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0,1280,720);
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setOpaque(false);
        
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        playerRunes = new JLabel("Runes: " + player.getRunes());
        playerRunes.setBounds(800,20,300,50);
        playerRunes.setFont(new FontHolder().getFont(40));
        playerRunes.setForeground(Color.white);
        layeredPane.add(playerRunes, Integer.valueOf(1));

        printWeapons();
        printInputs();
    }
    /**
     * Prints the shop weapons.
     */
    public void printWeapons(){


        Weapons weapons = new Weapons();
        int x = 235;
        int y = 30;
        shopWeapons = new ArrayList<Weapons>();

            for(int i = 0 ; i < 4; i++){
            
            weaponPanelLbl = new JLabel();
            weaponPanel = new ImageIcon("Prog3MP/weaponShopPanel.png");
            weaponPanelLbl.setIcon(weaponPanel);
            weaponPanelLbl.setBounds(0,0,250,320);

            weaponPane = new JLayeredPane();
            weaponPane.setBounds(x,y, 250,320);
            weaponPane.setOpaque(true);
            weaponPane.setBackground(Color.gray);
            weaponPane.setLayout(null);

            weaponPane.add(weaponPanelLbl, Integer.valueOf(0));

            Random rand = new Random();
            randomNum = rand.nextInt(24) + 1;

            weapons.weaponList.get(randomNum);
            weaponImgLabel = new JLabel();
            weaponIcon = new ImageIcon("Prog3MP/" + weapons.weaponList.get(randomNum).getName() + "Icon.png");
            weaponImgLabel.setIcon(weaponIcon);
            weaponImgLabel.setBounds(0,20,250,130);
            weaponImgLabel.setText(weapons.weaponList.get(randomNum).getName());
            weaponImgLabel.setHorizontalAlignment(JLabel.CENTER);
            weaponImgLabel.setVerticalAlignment(JLabel.TOP);
            weaponImgLabel.setHorizontalTextPosition(JLabel.CENTER);
            weaponImgLabel.setVerticalTextPosition(JLabel.TOP);
            weaponImgLabel.setForeground(Color.white);

            if(weapons.weaponList.get(randomNum).getName().length() > 20){

                weaponImgLabel.setFont(new FontHolder().getFont(22));

            } else {

                weaponImgLabel.setFont(new FontHolder().getFont(25));

            }
            weaponPane.add(weaponImgLabel, Integer.valueOf(1));

            price = new JLabel("" + weapons.weaponList.get(randomNum).getPrice() + " Runes");
            price.setBounds(75,140,150,50);
            price.setFont(new FontHolder().getFont(20));
            price.setForeground(Color.white);
            weaponPane.add(price, Integer.valueOf(1));

            
            if(player.getDex() >= weapons.weaponList.get(randomNum).getDex() || player.getDex() >= 15){

            dex = new JLabel("Dexterity    : " + weapons.weaponList.get(randomNum).getDex());
            dex.setBounds(20,170,150,50);
            dex.setFont(new FontHolder().getFont(20));
            dex.setForeground(Color.green);
            weaponPane.add(dex, Integer.valueOf(1));

            } else {

                dex = new JLabel("Dexterity    : " + weapons.weaponList.get(randomNum).getDex());
                dex.setBounds(20,170,150,50);
                dex.setFont(new FontHolder().getFont(20));
                dex.setForeground(Color.red);
                weaponPane.add(dex, Integer.valueOf(1));
            }

            hp = new JLabel("Health      : " + weapons.weaponList.get(randomNum).getHp());
            hp.setBounds(20,200,150,50);
            hp.setFont(new FontHolder().getFont(20));
            hp.setForeground(Color.white);
            weaponPane.add(hp, Integer.valueOf(1));

            end = new JLabel("Endurance   : " + weapons.weaponList.get(randomNum).getEnd());
            end.setBounds(20,220,150,50);
            end.setFont(new FontHolder().getFont(20));
            end.setForeground(Color.white);
            weaponPane.add(end, Integer.valueOf(1));

            str = new JLabel("Strength    : " + weapons.weaponList.get(randomNum).getStr());
            str.setBounds(20,240,150,50);
            str.setFont(new FontHolder().getFont(20));
            str.setForeground(Color.white);
            weaponPane.add(str, Integer.valueOf(1));

            inte = new JLabel("Intelligence : " + weapons.weaponList.get(randomNum).getInt());
            inte.setBounds(20,260,150,50);
            inte.setFont(new FontHolder().getFont(20));
            inte.setForeground(Color.white);
            weaponPane.add(inte, Integer.valueOf(1));

            fth = new JLabel("Faith        : " + weapons.weaponList.get(randomNum).getFth());
            fth.setBounds(20,280,150,50);
            fth.setFont(new FontHolder().getFont(20));
            fth.setForeground(Color.white);
            weaponPane.add(fth, Integer.valueOf(1));

            layeredPane.add(weaponPane, Integer.valueOf(1));
            shopWeapons.add(weapons.weaponList.get(randomNum));
            x += 280;

            if((i + 1) % 2 == 0){
                x = 235;
                y += 350;
            }
            }
        printBuys();
    }
    /**
     * Prints the shop refresh and back buttons.
     */
    public void printInputs(){

        refresh = new JButton("Refresh");
        refresh.setBounds(900, 500, 100,50);
        layeredPane.add(refresh, Integer.valueOf(2));

        back = new JButton("Back");
        back.setBounds(900,650,100,50);
        layeredPane.add(back, Integer.valueOf(2));        
            
    }
    /**
     * Prints the shop buy buttons.
     */
    public void printBuys(){

        buy1 = new JButton("Buy");
        buy1.setBounds(410,295,70,50);
        buy1.setFocusable(false);
        buy1.setFont(new FontHolder().getFont(20));
        layeredPane.add(buy1, Integer.valueOf(2));

        buy2 = new JButton("Buy");
        buy2.setBounds(690,295,70,50);
        buy2.setFocusable(false);
        buy2.setFont(new FontHolder().getFont(20));
        layeredPane.add(buy2, Integer.valueOf(2));

        buy3 = new JButton("Buy");
        buy3.setBounds(410,645,70,50);
        buy3.setFocusable(false);
        buy3.setFont(new FontHolder().getFont(20));
        layeredPane.add(buy3, Integer.valueOf(2));

        buy4 = new JButton("Buy");
        buy4.setBounds(690,645,70,50);
        buy4.setFocusable(false);
        buy4.setFont(new FontHolder().getFont(20));
        layeredPane.add(buy4, Integer.valueOf(2));
    }
    /**
     * Sets the player instance for the shop, to add weapons
     * to the player's instanced inventory.
     * @param player instance of the player.
     */
    public void setPlayer(Player player){

        this.player = player;
    }

}
