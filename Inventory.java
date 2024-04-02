package Prog3MP;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
* This class handles the Inventory segment of the program.
*
* @author Charles, Myrine.
*/
public class Inventory{

    private JFrame mainFrame;
    private JLabel backgroundLabel, weapongImgLbl, hpStat, dexStat, intStat, endStat, strStat, fthStat, legendLabel, playerDex, extraLabel;
    private ImageIcon backgroundImage, weaponImg, extraImgIcon;
    private JLayeredPane layeredPane;
    private JPanel weaponPanel, backPanel;
    private ArrayList<Weapons> inventoryList = new ArrayList<Weapons>();
    private Weapons weapons;
    private JButton setActiveBtn, backButton, dropActiveWeap;
    private int activeWeapIndex = 0,  h = 0, i = 50, j = 50, k = 0;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private Player player;

    /**
     * Constructor. Sets initial starting weapons (FOR DEMO ONLY).
     */
    public Inventory(){

        weapons = new Weapons();
        inventoryList.add(weapons.weaponList.get(weapons.getWeapon("Demo Shroom")));
        inventoryList.add(weapons.weaponList.get(weapons.getWeapon("Short Sword")));
        inventoryList.add(weapons.weaponList.get(weapons.getWeapon("Moonveil")));
        inventoryList.add(weapons.weaponList.get(weapons.getWeapon("Thorned Whip")));
        inventoryList.add(weapons.weaponList.get(weapons.getWeapon("Maliketh's Black Blade")));
    }
    /**
     * Prints the Inventory's background and JFrame.
     */
    public void printInventory(Player player){

        this.player = player;
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,1280,720);

        // FRAME

        mainFrame = new JFrame("Inventory");
        mainFrame.setSize(1280,720);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setUndecorated(true);
        mainFrame.setLocation(300, 160);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.add(layeredPane);
        mainFrame.setVisible(true);

        printDetails();

    }

    /** 
     * Prints the inventory details, also calls printWeapons()
     */
    public void printDetails(){

        backgroundImage = new ImageIcon("Prog3MP/DefaultBackgrounds.png");

        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0,1280,720);
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setOpaque(false);
        layeredPane.add(backgroundLabel, Integer.valueOf(-1));

        legendLabel = new JLabel("Legend:");
        legendLabel.setFont(new FontHolder().getFont(20));
        legendLabel.setBounds(230,650,150,50);
        legendLabel.setForeground(Color.white);
        layeredPane.add(legendLabel, Integer.valueOf(2));

        legendLabel = new JLabel("Common");
        legendLabel.setFont(new FontHolder().getFont(20));
        legendLabel.setBounds(300,650,150,50);
        legendLabel.setForeground(new Color(0,127,168,255));
        layeredPane.add(legendLabel, Integer.valueOf(2));

        legendLabel = new JLabel("Rare");
        legendLabel.setFont(new FontHolder().getFont(20));
        legendLabel.setBounds(380,650,150,50);
        legendLabel.setForeground(new Color(64,154,45,255));
        layeredPane.add(legendLabel, Integer.valueOf(2));

        legendLabel = new JLabel("Legendary");
        legendLabel.setFont(new FontHolder().getFont(20));
        legendLabel.setBounds(440,650,150,50);
        legendLabel.setForeground(new Color(101,61,196,255));
        layeredPane.add(legendLabel, Integer.valueOf(2));

        legendLabel = new JLabel("Mythic");
        legendLabel.setFont(new FontHolder().getFont(20));
        legendLabel.setBounds(540,650,150,50);
        legendLabel.setForeground(new Color(130,61,49,255));
        layeredPane.add(legendLabel, Integer.valueOf(2));

        printWeapons();

    }
    /**
     * Prints the weapons inside the inventory.
     */
    public void printWeapons(){

        backPanel = new JPanel();
        backPanel.setBounds(218,0,843,725);
        backPanel.setOpaque(false);
        backPanel.setLayout(null);
        layeredPane.add(backPanel);

        dropActiveWeap = new JButton("Drop active weapon");
        dropActiveWeap.setBounds(480,600,200,50);
        dropActiveWeap.setFont(new FontHolder().getFont(20));
        dropActiveWeap.setFocusable(false);
        backPanel.add(dropActiveWeap);

        dropActiveWeap.addActionListener(e -> {

            if(inventoryList.get(activeWeapIndex).getName() != "Short Sword" || activeWeapIndex != 1){

            int option = JOptionPane.showConfirmDialog(null, "Drop active weapon?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION);
            

            if (option == JOptionPane.YES_OPTION) {

                inventoryList.remove(activeWeapIndex);
                updateInventory();

            }
            

            } else {

                JOptionPane.showMessageDialog(null, "Can't drop starting weapon!", "Warning!", JOptionPane.WARNING_MESSAGE);
            }
            
        });

        playerDex = new JLabel("Your dexterity: " + player.getDex());
        playerDex.setBounds(10,10,200,50);
        playerDex.setFont(new FontHolder().getFont(20));
        playerDex.setForeground(Color.white);
        backPanel.add(playerDex);

        backButton = new JButton("Back");
        backButton.setFont(new FontHolder().getFont(20));
        backButton.setBounds(700,600,100,50);
        backButton.setFocusable(false);
        backPanel.add(backButton);

        for(Weapons weapon : inventoryList){

            setActiveBtn = new JButton(weapon.getName());

            setActiveBtn.setFont(new FontHolder().getFont(17));
            setActiveBtn.setFocusable(false);
            setActiveBtn.setBounds(0,100,100,20);

            buttons.add(setActiveBtn);

            setActiveBtn.setActionCommand("Button" + k);
            k++;

            extraLabel = new JLabel();
            extraImgIcon = new ImageIcon("Prog3MP/inventoryPanel.png");
            extraLabel.setIcon(extraImgIcon);
            extraLabel.setBounds(0,0,100,230);

            weapongImgLbl = new JLabel();
            weaponImg = new ImageIcon("Prog3MP/" + weapon.getName() + "Icon.png");
            weapongImgLbl.setIcon(weaponImg);
            weapongImgLbl.setBounds(0,0,100,100);

            weaponPanel = new JPanel();
            weaponPanel.setLayout(null);
            weaponPanel.setBounds(i,j,100,230);

            if(activeWeapIndex == h){

                weaponPanel.setBackground(Color.green);
                setActiveBtn.setText("Active");
                setActiveBtn.setEnabled(false);

                weaponPanel.add(weapongImgLbl , Integer.valueOf(1));

                hpStat  = new JLabel("Health       : " + weapon.getHp());
                dexStat = new JLabel("Dexterity    : " + weapon.getDex());
                intStat = new JLabel("Intelligence : " + weapon.getInt());
                endStat = new JLabel("Endurance    : " + weapon.getEnd());
                strStat = new JLabel("Strength     : " + weapon.getStr());
                fthStat = new JLabel("Faith        : " + weapon.getFth());
                hpStat.setBounds(0,135,100,15);
                weaponPanel.add(hpStat , Integer.valueOf(1));
                dexStat.setBounds(0, 150, 100, 15);
                weaponPanel.add(dexStat , Integer.valueOf(1));
                intStat.setBounds(0, 165, 100, 15);
                weaponPanel.add(intStat , Integer.valueOf(1));
                endStat.setBounds(0, 180, 100, 15);
                weaponPanel.add(endStat , Integer.valueOf(1));
                strStat.setBounds(0, 195, 100 ,15);
                weaponPanel.add(strStat , Integer.valueOf(1));
                fthStat.setBounds(0, 210, 100 ,15);
                weaponPanel.add(fthStat , Integer.valueOf(1));
                weaponPanel.add(setActiveBtn, Integer.valueOf(2));

            } else {


                weaponPanel.add(weapongImgLbl , Integer.valueOf(1));

                hpStat  = new JLabel("Health       : " + weapon.getHp());
                dexStat = new JLabel("Dexterity    : " + weapon.getDex());
                intStat = new JLabel("Intelligence : " + weapon.getInt());
                endStat = new JLabel("Endurance    : " + weapon.getEnd());
                strStat = new JLabel("Strength     : " + weapon.getStr());
                fthStat = new JLabel("Faith        : " + weapon.getFth());
                hpStat.setBounds(0,135,100,15);
                weaponPanel.add(hpStat , Integer.valueOf(1));
                dexStat.setBounds(0, 150, 100, 15);
                weaponPanel.add(dexStat , Integer.valueOf(1));
                intStat.setBounds(0, 165, 100, 15);
                weaponPanel.add(intStat , Integer.valueOf(1));
                endStat.setBounds(0, 180, 100, 15);
                weaponPanel.add(endStat , Integer.valueOf(1));
                strStat.setBounds(0, 195, 100 ,15);
                weaponPanel.add(strStat , Integer.valueOf(1));
                fthStat.setBounds(0, 210, 100 ,15);
                weaponPanel.add(fthStat , Integer.valueOf(1));
                weaponPanel.add(setActiveBtn, Integer.valueOf(2));

                weaponPanel.add(extraLabel, Integer.valueOf(0));

            }

            h++;

            backPanel.add(weaponPanel);
            
            if(i > 700){

                i = 50;
                j += 230;

            } else {

                i += 120;
            }

            backButton.addActionListener(e -> {

                h = 0;
                i = 50;
                j = 50;
                k = 0;
                mainFrame.dispose();
            });

            setActiveBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand(); 
                    
                    for (int z = 0 ; z <= h ; z++){
    
                        if(command.equals("Button" + z)){
                            
                            if(player.getDex() < weapon.getDex() ){

                                JOptionPane.showMessageDialog(null, "Not enough dexterity!", "Notification", JOptionPane.WARNING_MESSAGE);

                            } else {

                            activeWeapIndex = z;

                            h = 0;
                            i = 50;
                            j = 50;
                            k = 0;

                            backPanel.removeAll();
                            printWeapons();

                            }

                        }
                    }
                }
            });
        }

        for(JButton button : buttons){

            String initialText = button.getText() + " ";

            if(!button.getText().equals("Active")){

            Timer timer = new Timer(200, new ActionListener() {

                int index = 0;
    
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    String newText = initialText.substring(index) + initialText.substring(0, index);
                    button.setText(newText);
                    index = (index + 1 + initialText.length()) % initialText.length();

                }
            });

            timer.start();
        }

    }

    }
    /**
     * Gets the current weapon equipped by the player.
     * @param inventoryList the ArrayList containing the weapons, the inventory of the player.
     * @return returns the String of the active weapon depending on its index inside the list.
     */
    public String getCurrentWeapon(){

        return inventoryList.get(activeWeapIndex).getName();

    }
    /**
     * Adds weapon to inventory depending on the passed String name.
     * @param name the name of the weapon.
     */
    public void addToInventory(String name){

        inventoryList.add(weapons.weaponList.get(weapons.getWeapon(name)));
    }
    /**
     * @return returns weapon HP.
     */
    public int getWeaponHP(){

        return inventoryList.get(activeWeapIndex).getHp();
    }
    /**
     * @return returns weapon Strength.
     */
    public int getWeaponStr(){

        return inventoryList.get(activeWeapIndex).getStr();
    }
    /**
     * @return returns weapon Intelligence.
     */
    public int getWeaponInt(){

        return inventoryList.get(activeWeapIndex).getInt();
    }
    /**
     * @return returns weapon Faith.
     */
    public int getWeaponFth(){

        return inventoryList.get(activeWeapIndex).getFth();
    }
    /**
     * @return returns weapon Endurance.
     */
    public int getWeaponEnd(){

        return inventoryList.get(activeWeapIndex).getFth();
    }
    /**
     * @return returns weapon returns the instance of Weapons.
     */
    public Weapons getWeapons(){

        return weapons;
    }
    /**
     * Updates the inventory panel.
     */
    public void updateInventory(){

        h = 0;
        i = 50;
        j = 50;
        k = 0;

        while(inventoryList.get(activeWeapIndex - 1).getDex() > player.getDex()){

            activeWeapIndex--;

        }

        activeWeapIndex--;

        layeredPane.removeAll();
        backPanel.removeAll();

        printDetails();

    }
}


