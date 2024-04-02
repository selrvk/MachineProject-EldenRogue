package Prog3MP;

import java.awt.*;
import javax.swing.*;

    /**
     * This class handles the LevelUp section of the program.
     * @author Charles, Myrine.
     */
public class LevelUp {

    private int hp=0,dex=0,inte=0,end=0,str=0,fth=0, runes, runeCost, runeSpent=0,  playerTempLvl=0;
    public JFrame mainFrame;
    private JLayeredPane layeredPane;
    private JLabel backgroundLabel, playerRunes, detailsLabel, tempHp, tempDex, tempInt, tempEnd, tempStr, tempFth, levelLabel;
    private ImageIcon backgroundImage;
    private JButton confirmButton ,backButton, lvlHp, lvlDex, lvlInt, lvlEnd, lvlStr, lvlFth, resetBtn;
    private Player player;
    private FontHolder font;

    public LevelUp(){

    }
    /**
     * Prints the levelUp background and JFrame.
     * @param player passes in the player instance from gameLobby.
     */
    public void printLevelUp(Player player){

        this.player = player;
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,1280,720);

        // FRAME

        mainFrame = new JFrame("Character Creation");
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

        runes = player.getRunes();

    }
    /**
     * Prints levelUp's buttons.
     */
    public void printButtons(){

        resetBtn = new JButton("Reset");
        resetBtn.setBounds(400,600,100,50);
        layeredPane.add(resetBtn);

        resetBtn.addActionListener(e -> {

            hp=0;
            dex=0;
            inte=0;
            end=0;
            str=0;
            fth=0;
            runeSpent=0;
            playerTempLvl=0;
            runes = player.getRunes();

            printDetails();
        });
        
        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(750,600,100,50);
        layeredPane.add(confirmButton);

        confirmButton.addActionListener(e -> {

            player.addHp(hp);
            player.addDex(dex);
            player.addInt(inte);
            player.addEnd(end);
            player.addStr(str);
            player.addFth(fth);
            player.deductRunes(runeSpent);
            player.addLevel(playerTempLvl);
            
            hp=0;
            dex=0;
            inte=0;
            end=0;
            str=0;
            fth=0;
            runeSpent=0;
            playerTempLvl=0;
            
            mainFrame.dispose();
        });

        backButton = new JButton("Back");
        backButton.setBounds(900,600,100,50);
        layeredPane.add(backButton);

        backButton.addActionListener(e -> {

            hp=0;
            dex=0;  
            inte=0;
            end=0;
            str=0;
            fth=0;
            runeSpent=0;
            playerTempLvl=0;

            mainFrame.dispose();

        });

        lvlHp = new JButton("+1");
        lvlHp.setBounds(530,200,50,50);
        lvlHp.addActionListener(e -> {

            if(deductRunes() == true){

                hp += 1;
                runeSpent += runeCost;
                playerTempLvl ++;
                printDetails();

            } 
        });

        layeredPane.add(lvlHp);

        lvlDex = new JButton("+1");
        lvlDex.setBounds(530,300,50,50);
        lvlDex.addActionListener(e -> {

            if(deductRunes() == true){

                dex += 1;
                runeSpent += runeCost;
                playerTempLvl ++;
                printDetails();
    
            } 
        });

        layeredPane.add(lvlDex);

        lvlInt = new JButton("+1");
        lvlInt.setBounds(530,400,50,50);
        lvlInt.addActionListener(e -> {

            if(deductRunes() == true){

                inte += 1;
                runeSpent += runeCost;
                playerTempLvl ++;
                printDetails();
    
            }
        });
        
        layeredPane.add(lvlInt);

        lvlEnd = new JButton("+1");
        lvlEnd.setBounds(930,200,50,50);
        lvlEnd.addActionListener(e -> {

            if(deductRunes() == true){

                end += 1;
                runeSpent += runeCost;
                playerTempLvl ++;
                printDetails();
    
            }
        });
        
        layeredPane.add(lvlEnd);

        lvlStr = new JButton("+1");
        lvlStr.setBounds(930,300,50,50);
        lvlStr.addActionListener(e -> {

            if(deductRunes() == true){

                str += 1;
                runeSpent += runeCost;
                playerTempLvl ++;
                printDetails();

            }
        });
        
        layeredPane.add(lvlStr);

        lvlFth = new JButton("+1");
        lvlFth.setBounds(930,400,50,50);
        lvlFth.addActionListener(e -> {

            if(deductRunes() == true){

                fth += 1;
                runeSpent += runeCost;
                playerTempLvl ++;
                printDetails();
    
            }
        });
        
        layeredPane.add(lvlFth);

    }
    /**
     * Prints the player's details.
     */
    public void printDetails(){

        layeredPane.removeAll();
        backgroundImage = new ImageIcon("Prog3MP/DefaultBackgrounds.png");

        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0,1280,720);
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setOpaque(false);
        layeredPane.add(backgroundLabel, Integer.valueOf(-1));

        font = new FontHolder();
        playerRunes = new JLabel("Runes: " + runes);
        playerRunes.setFont(font.getFont(40));
        playerRunes.setForeground(Color.white);
        playerRunes.setBounds(800,100,400,50);

        levelLabel = new JLabel("Level: " + player.getLevel() + " (+" + playerTempLvl +")");
        levelLabel.setFont(font.getFont(40));
        levelLabel.setForeground(Color.white);
        levelLabel.setBounds(300,100,400,50);

        detailsLabel = new JLabel("Health       : " + player.getHp());
        detailsLabel.setBounds(300,200, 300, 50);
        layeredPane.add(detailsLabel);
        detailsLabel.setFont(font.getFont(30));
        detailsLabel.setForeground(Color.white);

            tempHp = new JLabel("(" + hp + ")");
            tempHp.setBounds(590,200,100,50);
            tempHp.setFont(font.getFont(30));
            tempHp.setForeground(Color.green);
            layeredPane.add(tempHp);

        detailsLabel = new JLabel("Dexterity    : " + player.getDex());
        detailsLabel.setBounds(300,300, 300, 50);
        layeredPane.add(detailsLabel);
        detailsLabel.setFont(font.getFont(30));
        detailsLabel.setForeground(Color.white);

            tempDex = new JLabel("(" + dex + ")");
            tempDex.setBounds(590,300,100,50);
            tempDex.setFont(font.getFont(30));
            tempDex.setForeground(Color.green);
            layeredPane.add(tempDex);

        detailsLabel = new JLabel("Intelligence : " + player.getInte());
        detailsLabel.setBounds(300,400, 300, 50);
        layeredPane.add(detailsLabel);
        detailsLabel.setFont(font.getFont(30));
        detailsLabel.setForeground(Color.white);
        
            tempInt = new JLabel("(" + inte + ")");
            tempInt.setBounds(590,400,100,50);
            tempInt.setFont(font.getFont(30));
            tempInt.setForeground(Color.green);
            layeredPane.add(tempInt);

        detailsLabel = new JLabel("Endurance    : " + player.getEnd());
        detailsLabel.setBounds(700,200, 250, 50);
        layeredPane.add(detailsLabel);
        detailsLabel.setFont(font.getFont(30));
        detailsLabel.setForeground(Color.white);

            tempEnd = new JLabel("(" + end + ")");
            tempEnd.setBounds(990,200,100,50);
            tempEnd.setFont(font.getFont(30));
            tempEnd.setForeground(Color.green);
            layeredPane.add(tempEnd);

        detailsLabel = new JLabel("Strength     : " + player.getStr());
        detailsLabel.setBounds(700,300, 250, 50);
        layeredPane.add(detailsLabel);
        detailsLabel.setFont(font.getFont(30));
        detailsLabel.setForeground(Color.white);

            tempStr = new JLabel("(" + str + ")");
            tempStr.setBounds(990,300,100,50);
            tempStr.setFont(font.getFont(30));
            tempStr.setForeground(Color.green);
            layeredPane.add(tempStr);

        detailsLabel = new JLabel("Faith         : " + player.getFth());
        detailsLabel.setBounds(700,400, 250, 50);
        layeredPane.add(detailsLabel);
        detailsLabel.setFont(font.getFont(30));
        detailsLabel.setForeground(Color.white);

            tempFth = new JLabel("(" + fth + ")");
            tempFth.setBounds(990,400,100,50);
            tempFth.setFont(font.getFont(30));
            tempFth.setForeground(Color.green);
            layeredPane.add(tempFth);

            printButtons();

        layeredPane.add(levelLabel);
        layeredPane.add(playerRunes);
    }
    /**
     * Deducts the runes depending on the player level. Calculates it also inside this method.
     * @param runeCost calculates the real rune cost depending on the player's level.
     * @return returns true if runeCost < currentRunes else returns false.
     */
    public boolean deductRunes(){
        
        runeCost = ((player.getLevel() + playerTempLvl)* 100) / 2;
        
        if(runes >= runeCost){

            runes -= runeCost;  
            return true;

        } else {

            return false;
        }
    }

}
