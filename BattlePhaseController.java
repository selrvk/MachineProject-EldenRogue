package Prog3MP;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * BattlePhaseController class is the BattlePhase controller for the BattlePhase segment of the code.
 * 
 * @author Charles, Myrine.
 */
public class BattlePhaseController {
    
    public BattlePhaseView view;
    private BattlePhase model;
    private Player player;
    private Enemy enemy;
    private JFrame frame;
    private String atkType;
    private boolean issuedDodge;
    private boolean bossDefeated;
    private boolean isBoss = false;
    private int heldRunes = 0;

    /**
     * Constructs a new BattlePhaseController for the BattlePhase segment of the program.
     * 
     * @param model the BattlePhase model instance.
     * @param view  the BattlePhase view instance.
     * @param player the Player instance.
     * @param enemy the Enemy instance.
     * @param frame the BattlePhase frame.
     */
    public BattlePhaseController(BattlePhase model, BattlePhaseView view, Player player, Enemy enemy, JFrame frame){

        this.model = model;
        this.view = view;
        this.player = player;
        this.enemy = enemy;
        this.frame = frame;
        this.bossDefeated = false;
        view.printFrame();

    }   
    /**
     * The fight method handles the setting and getting of turns, as well as handling
     * damage taken and when the fight ends.
     */
    public void fight(){

        if(model.getCurrentHP() > 0 && model.getEnemyCurrentHP() > 0){

        if(model.getTurn() == 1){

            model.setIncomingDamage(model.calculateEnemyDamage());
            view.printDetails(player, model, enemy);

            atkType = "Strength";
            
            view.atkTypeBox.addActionListener(e -> {

                atkType = (String) view.atkTypeBox.getSelectedItem();

            });

            view.atkBtn.addActionListener(e-> {

                model.doDamage(model.playerAttacks(atkType));
                model.setTurn(2);
                fight();

            });
            
            view.dodgeBtn.addActionListener(e -> {

                model.calculateDodge();
                issuedDodge = true;
                model.setTurn(2);
                fight();
            });


        } else {

            
            view.printDetails(player, model , enemy);

            try {

                Thread.sleep(1500);

            } catch (InterruptedException e) {
                    
                e.printStackTrace();
            } 

            if(model.getDodged() == true && issuedDodge == true){

                issuedDodge = false;
                System.out.println("Attack Dodged!");
                model.setTurn(1);

            } else if(model.getDodged() == false && issuedDodge == true){
            
            issuedDodge = false;
            System.out.println("Dodge Failed!");
            model.takeDamage(model.takeIncomingDamage());
            model.setTurn(1);

            } else {
            
            System.out.println("Took " + model.takeIncomingDamage() + " Damage");
            model.takeDamage(model.takeIncomingDamage());
            model.setTurn(1);

            }

            fight();
            
        }

        } else if(model.getCurrentHP() > 0 && model.getEnemyCurrentHP() <= 0){
            
            bossDefeated = true;
            heldRunes += (model.getEnemyMaxHP() * 2);
            view.closeFrame();

            if(isBoss == true){

                JOptionPane.showMessageDialog(null, "Great enemy felled!", "Gained " + (model.getEnemyMaxHP() * 2) + " Runes", JOptionPane.WARNING_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(null, "Lesser enemy felled!", "Gained " + (model.getEnemyMaxHP() * 2) + " Runes", JOptionPane.WARNING_MESSAGE);
            }

        } else {

            frame.dispose();
            view.closeFrame();
            JOptionPane.showMessageDialog(null, "You died! Lost all runes...", "Notification", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Checks if the boss has been defeated.
     * 
     * @return returns true if the boss has been defeated, else returns false.
     */
    public boolean bossDefeated(){

        return bossDefeated;
    }
    /**
     * Calculates the player's damage.
     * 
     * @return returns the calculated player damage, total of
     * player stats and player weapon stats.
     */
    public int calculatePlayerDamage(){

        int damage = player.getStr() + player.getInventory().getWeaponStr();

        return damage;
    }
    /**
     * Sets if the enemy is a boss or not.
     * 
     * @param bool boolean of whether the enemy generated is a boss or not.
     */
    public void isBossSet(boolean bool){

        isBoss = bool;
    }
    /**
     * Gets the enemy's dropped runes depending on the enemy HP.
     * 
     * @return returns the amount of runes dropped by the enemy.
     */
    public int getEnemyRunes(){

        return heldRunes;

    }

}
