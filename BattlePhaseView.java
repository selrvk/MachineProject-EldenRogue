package Prog3MP;

import javax.swing.*;
import java.awt.*;

/**
 * BattlePhaseView class is the BattlePhase view for the BattlePhase segment of the code.
 * 
 * @author Charles, Myrine.
 */
public class BattlePhaseView {
    
    public JFrame mainFrame;
    public JLabel backgroundLabel, detailLabels;
    private ImageIcon backgroundImage, enemyImg, phyDef, sorDef, incDef;
    public JLayeredPane layeredPane;
    private JProgressBar pBar;
    public JButton atkBtn, dodgeBtn, phyAtk, sorAtk, incAtk;
    public JComboBox<String> atkTypeBox;
    private FontHolder font = new FontHolder();

    /**
     * Empty constructor
     */
    public BattlePhaseView(){

    }

    /**
     * Prints the frame
     */
    public void printFrame(){

        mainFrame = new JFrame("Battle Phase");
        mainFrame.setSize(1280,720);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setUndecorated(true);
        mainFrame.setLocation(300, 160);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        backgroundImage = new ImageIcon("Prog3MP/DefaultBackgrounds.png");

        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0,1280,720);
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setOpaque(false);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,1280,720);

        phyDef = new ImageIcon("Prog3MP/PhyShield.png");
        sorDef = new ImageIcon("Prog3MP/SorShield.png");
        incDef = new ImageIcon("Prog3MP/IncShield.png");

    }
    /**
     * Prints the details of enemy and the fight.
     * 
     * @param player Player instance.
     * @param model  BattlePhase model instance.
     * @param enemy Enemy instance.
     */
    public void printDetails(Player player, BattlePhase model, Enemy enemy){
        
        layeredPane.removeAll();
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        //PLAYER HP BAR
        pBar = new JProgressBar(0,model.getMaxHP());
        pBar.setBounds(750, 200, 200, 50);
        pBar.setStringPainted(true);
        pBar.setForeground(Color.blue);
        pBar.setValue(model.getCurrentHP());        
        layeredPane.add(pBar, Integer.valueOf(1));

        // PLAYER HP TEXT
        detailLabels = new JLabel("HP : " + model.getCurrentHP());
        detailLabels.setBounds(750,150,300,50);
        detailLabels.setFont(font.getFont(30));
        detailLabels.setForeground(Color.white);
        layeredPane.add(detailLabels, Integer.valueOf(2));

        // ENEMY HP BAR
        pBar = new JProgressBar(0,model.getEnemyMaxHP());
        pBar.setBounds(250, 400, 300, 50);
        pBar.setStringPainted(true);
        pBar.setForeground(Color.red);
        pBar.setValue(model.getEnemyCurrentHP());        
        layeredPane.add(pBar, Integer.valueOf(1));

        detailLabels = new JLabel("HP : " + model.getEnemyCurrentHP());
        detailLabels.setBounds(250,450,300,50);
        detailLabels.setFont(font.getFont(20));
        detailLabels.setForeground(Color.white);
        layeredPane.add(detailLabels, Integer.valueOf(2));

        // INCOMING DMG
        detailLabels = new JLabel("Incoming Damage : " + model.takeIncomingDamage());
        detailLabels.setBounds(750,250,300,50);
        detailLabels.setFont(font.getFont(30));
        detailLabels.setForeground(Color.white);
        layeredPane.add(detailLabels, Integer.valueOf(2));

        enemyImg = new ImageIcon("Prog3MP/" + enemy.getName() + ".png");
        detailLabels = new JLabel();
        detailLabels.setIcon(enemyImg);
        detailLabels.setBounds(250,100,300,300);
        layeredPane.add(detailLabels, Integer.valueOf(1));

        if(model.getTurn() == 1){

            printInputs();

        } 

        detailLabels = new JLabel(enemy.getName());
        detailLabels.setFont(font.getFont(40));
        detailLabels.setBounds(250,50,600,50);
        detailLabels.setForeground(Color.white);
        layeredPane.add(detailLabels, Integer.valueOf(1));

        detailLabels = new JLabel("" + enemy.getPhyDef() * 100);
        detailLabels.setFont(font.getFont(40));
        detailLabels.setVerticalTextPosition(JLabel.CENTER);
        detailLabels.setHorizontalTextPosition(JLabel.CENTER);
        detailLabels.setIcon(phyDef);
        detailLabels.setBounds(270,500 , 200 , 200);
        layeredPane.add(detailLabels, Integer.valueOf(1));

        detailLabels = new JLabel("" + enemy.getSorDef() * 100);
        detailLabels.setFont(font.getFont(40));
        detailLabels.setVerticalTextPosition(JLabel.CENTER);
        detailLabels.setHorizontalTextPosition(JLabel.CENTER);
        detailLabels.setIcon(sorDef);
        detailLabels.setBounds(550,500 , 200 , 200);
        layeredPane.add(detailLabels, Integer.valueOf(1));
        
        detailLabels = new JLabel("" + enemy.getIncDef() * 100);
        detailLabels.setFont(font.getFont(40));
        detailLabels.setVerticalTextPosition(JLabel.CENTER);
        detailLabels.setHorizontalTextPosition(JLabel.CENTER);
        detailLabels.setIcon(incDef);
        detailLabels.setBounds(830,500 , 200 , 200);
        layeredPane.add(detailLabels, Integer.valueOf(1));

        mainFrame.add(layeredPane);

        mainFrame.revalidate();
        mainFrame.repaint();
        layeredPane.revalidate();
        layeredPane.repaint();

    }
    /**
     * Prints the buttons for inputs
     */
    public void printInputs(){

        String[] atkTypes = {"Strength", "Sorcerer", "Incantation"};
        atkBtn = new JButton("Attack");
        dodgeBtn = new JButton("Dodge");

        atkBtn.setBounds(750,400,150,50);
        atkBtn.setFont(font.getFont(30));
        dodgeBtn.setBounds(900,400,150,50);
        dodgeBtn.setFont(font.getFont(30));

        atkTypeBox = new JComboBox<String>(atkTypes);
        atkTypeBox.setBounds(750,350,150,50);

        layeredPane.add(atkTypeBox, Integer.valueOf(1));
        layeredPane.add(dodgeBtn, Integer.valueOf(1));
        layeredPane.add(atkBtn, Integer.valueOf(1));
    }
    /**
     * Closes the frame.
     */
    public void closeFrame(){

        mainFrame.dispose();
    }
    
}
