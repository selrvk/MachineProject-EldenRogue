package Prog3MP;

import javax.swing.*;

/**
 * ShopController class handles the controller for the Shop segment of the program.
 * 
 * @author Charles, Myrine.
 */

public class ShopController {
    
    private ShopView view;
    private Player player;
    
    /**
     * Constructs the shop controller.
     * @param model the shop model instance.
     * @param view  the shop view instance.
     * @param player the player instance.
     */
    public ShopController(Shop model, ShopView view, Player player){

        this.view = view;
        this.player = player;

        view.setPlayer(player);
        view.printFrame();
        btnListener();

    }

    /**
     * @return returns the player instance.
     */

    public Player getPlayer(){

        return player;
    }

    /**
     * Adds/Buys the weapon and adds it to the inventory, depending
     * on the passed String name.
     * @param name String name of the weapon.
     */

    public void buyWeapon(String name){

        player.getInventory().addToInventory(name);
        
    }

    /**
     * Button listeners.
     */
    public void btnListener(){

        view.refresh.addActionListener(e -> {

            view.printDetails();
            btnListener();
        });

        view.back.addActionListener(e -> {

            view.mainFrame.dispose();
            btnListener();
        });

        view.buy1.addActionListener(e -> {

            if((player.getDex() >= 15 || player.getDex() >= view.shopWeapons.get(0).getDex()) && player.getRunes() >= view.shopWeapons.get(0).getPrice()){

            player.deductRunes(view.shopWeapons.get(0).getPrice());
            buyWeapon(view.shopWeapons.get(0).getName());
            JOptionPane.showMessageDialog(null, "Weapon Bought!", "Notification", JOptionPane.WARNING_MESSAGE);
            view.printDetails();
            btnListener();

            } else if(player.getRunes() < view.shopWeapons.get(0).getPrice()){

                JOptionPane.showMessageDialog(null, "Not enough runes!", "Notification", JOptionPane.WARNING_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(null, "Not enough dexterity!", "Notification", JOptionPane.WARNING_MESSAGE);
            }

            
        });

        view.buy2.addActionListener(e -> {

            if((player.getDex() >= 15 || player.getDex() >= view.shopWeapons.get(1).getDex()) && player.getRunes() >= view.shopWeapons.get(1).getPrice()){

            player.deductRunes(view.shopWeapons.get(1).getPrice());
            buyWeapon(view.shopWeapons.get(1).getName());
            JOptionPane.showMessageDialog(null, "Weapon Bought!", "Notification", JOptionPane.WARNING_MESSAGE);
            view.printDetails();
            btnListener();
            } else if(player.getRunes() < view.shopWeapons.get(1).getPrice()){

                JOptionPane.showMessageDialog(null, "Not enough runes!", "Notification", JOptionPane.WARNING_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(null, "Not enough dexterity!", "Notification", JOptionPane.WARNING_MESSAGE);
                
            }

            
        });

        view.buy3.addActionListener(e -> {

            if((player.getDex() >= 15 || player.getDex() >= view.shopWeapons.get(2).getDex()) && player.getRunes() >= view.shopWeapons.get(2).getPrice()){

            player.deductRunes(view.shopWeapons.get(2).getPrice());
            buyWeapon(view.shopWeapons.get(2).getName());
            JOptionPane.showMessageDialog(null, "Weapon Bought!", "Notification", JOptionPane.WARNING_MESSAGE);
            view.printDetails();
            btnListener();
            } else if(player.getRunes() < view.shopWeapons.get(2).getPrice()){

                JOptionPane.showMessageDialog(null, "Not enough runes!", "Notification", JOptionPane.WARNING_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(null, "Not enough dexterity!", "Notification", JOptionPane.WARNING_MESSAGE);
            }

            
        });

        view.buy4.addActionListener(e -> {

            if((player.getDex() >= 15 || player.getDex() >= view.shopWeapons.get(3).getDex()) && player.getRunes() >= view.shopWeapons.get(3).getPrice()){

            player.deductRunes(view.shopWeapons.get(3).getPrice());
            buyWeapon(view.shopWeapons.get(3).getName());
            JOptionPane.showMessageDialog(null, "Weapon Bought!", "Notification", JOptionPane.WARNING_MESSAGE);
            view.printDetails();
            btnListener();
            } else if(player.getRunes() < view.shopWeapons.get(3).getPrice()){

                JOptionPane.showMessageDialog(null, "Not enough runes!", "Notification", JOptionPane.WARNING_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(null, "Not enough dexterity!", "Notification", JOptionPane.WARNING_MESSAGE);
            }

            
        });
    }

}
