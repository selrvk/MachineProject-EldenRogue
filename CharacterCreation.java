package Prog3MP;

import java.awt.*;
import javax.swing.*;

/**
 * CharacterCreation class is the class that handles the creation of the character
 * at the start of the game.
 * 
 * @author Charles, Myrine.
 */
public class CharacterCreation {

    private JFrame mainFrame;
    private ImageIcon image, characterImage, characterBorder;
    private JLabel backgroundLabel, characterLabel, characterBorderLbl, diffLabel, diffDesc, nameLabel, jobLabel;
    private JComboBox<String> jobClassBox, difficultyBox;
    private JLayeredPane layeredPane;
    private JButton inputNameButton, confirmButton, backButton;
    private String name, jobClass, finalName, selectedClass, imagePath, difficulty;
    private Player player;
    private FontHolder font;
    private int startingRunes;

    /**
     * Constructor
     */
    public CharacterCreation(){

        layeredPane = new JLayeredPane();

        printCharacterCreation();
        printInputs();
    }
    /**
     * Prints the background and JFrame of CharacterCreation.
     */
    public void printCharacterCreation(){

        font = new FontHolder();
        layeredPane.setBounds(0,0,1280,720);
        
        mainFrame = new JFrame("Character Creation");
        mainFrame.setSize(1280,720);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setUndecorated(true);
        mainFrame.setLocation(300, 160);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        image = new ImageIcon("Prog3MP/DefaultBackgroundsCC.png");

        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0,1280,720);
        backgroundLabel.setIcon(image);
        backgroundLabel.setOpaque(false);
        layeredPane.add(backgroundLabel, Integer.valueOf(-1));

        mainFrame.add(layeredPane);
        mainFrame.setVisible(true);

    }
    /**
     * Prints the inputs of CharacterCreation.
     */
    public void printInputs(){

        layeredPane.setBounds(0,0,1280,720);
  
        nameLabel = new JLabel();
        nameLabel.setBounds(520,100,400,50);
        nameLabel.setFont(font.getFont(30));

        layeredPane.add(nameLabel, Integer.valueOf(1));

        jobLabel = new JLabel("Select a class");
        jobLabel.setBounds(600,260,400,50);
        jobLabel.setFont(font.getFont(30));
        jobLabel.setForeground(Color.white);

        layeredPane.add(jobLabel, Integer.valueOf(1));


        // JOB CLASS

        String[] jobs = {"Vagabond", "Samurai", "Warrior", "Hero", "Astrologer", "Prophet"};
        jobClassBox = new JComboBox<>(jobs);
        jobClassBox.setBounds(600,300,130,50);
        jobClassBox.setFont(new FontHolder().getFont(20));
        printCharacterImage(jobClassBox);

        layeredPane.add(jobClassBox, Integer.valueOf(1));

        // DIFFICULTY

        String[] difficulties = {"Easy", "Medium", "Hard"};
        difficultyBox = new JComboBox<>(difficulties);
        difficultyBox.setBounds(600,400,130,50);
        difficultyBox.setFont(new FontHolder().getFont(20));

        layeredPane.add(difficultyBox, Integer.valueOf(1));

        diffLabel = new JLabel("Select Difficulty");
        diffLabel.setBounds(600, 360, 160, 50);
        diffLabel.setFont(new FontHolder().getFont(20));
        diffLabel.setForeground(Color.white);
        
        layeredPane.add(diffLabel, Integer.valueOf(1));

        diffDesc = new JLabel("Start with 5000 runes");
        diffDesc.setBounds(600, 440, 200, 50);
        diffDesc.setFont(new FontHolder().getFont(20));
        diffDesc.setForeground(Color.white);
        
        layeredPane.add(diffDesc, Integer.valueOf(1));

        difficultyBox.addActionListener(e -> {

            updateDiff((String) difficultyBox.getSelectedItem());


        });

        // ENTER NAME

        inputNameButton = new JButton("Enter Name");
        inputNameButton.setBounds(445,80,400,50);
        inputNameButton.setFont(new FontHolder().getFont(40));
        inputNameButton.setFocusable(false);

            inputNameButton.addActionListener(e -> {
                
                String sName = JOptionPane.showInputDialog("Enter your name:");
                if (sName != null) {
                    
                    updateNameField(sName);
                    name = sName;

                } else {
                }
            
            });

        layeredPane.add(inputNameButton);

        // CONFIRM BUTTON

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(920, 500, 120,50);
        confirmButton.setFont(new FontHolder().getFont(20));

            confirmButton.addActionListener(e -> {


                if(verifyInputs(jobClassBox) == true){

                    mainFrame.dispose();
                }
                
            });

        layeredPane.add(confirmButton);

        // BACK BUTTON

        backButton = new JButton("Back");
        backButton.setBounds(920, 600, 80, 50);
        backButton.setFont(new FontHolder().getFont(20));

            backButton.addActionListener(e -> {
                
                new TitleScreenView();
                mainFrame.dispose();
                
            });

        layeredPane.add(backButton);
        mainFrame.add(layeredPane);
        
    }
    /**
     * Prints the character image of the player.
     * 
     * @param comboBox contains the different job classes for the player to choose from.
     * @param selectedClass the variable that holds the selected class of the player.
     */
    public void printCharacterImage(JComboBox<String> comboBox){

        selectedClass = (String) comboBox.getSelectedItem();

        characterLabel = new JLabel();
        characterImage = new ImageIcon("Prog3MP/" + selectedClass + "Class.png");
        characterLabel.setIcon(characterImage);

        characterBorderLbl = new JLabel();
        characterBorder = new ImageIcon("Prog3MP/CharacterBorder.png");
        characterBorderLbl.setIcon(characterBorder);

        comboBox.addActionListener(e -> {

            selectedClass = (String) comboBox.getSelectedItem();
            imagePath = "Prog3MP/"  + selectedClass + "Class.png";
            ImageIcon newIcon = new ImageIcon(imagePath);
            characterLabel.setIcon(newIcon);
            characterLabel.repaint();

        });
        
        characterLabel.setBounds(400,230,170,400);
        layeredPane.add(characterLabel, Integer.valueOf(1));

        characterBorderLbl.setBounds(400,230,180,410);
        layeredPane.add(characterBorderLbl, Integer.valueOf(2));

    }
    /**
     * Checks if the inputs are valid, i.e the player put a proper name.
     * @param jobClass the jobClass of the player inside the comboBox.
     * @param comboBox checks if the comboBox is a valid job class chosen by the player.
     */
    public boolean verifyInputs(JComboBox<String> comboBox){
        
        difficulty = (String)difficultyBox.getSelectedItem();
        jobClass = (String)comboBox.getSelectedItem();

        if(difficulty.equals("Easy")){

            startingRunes = 5000;

        } else if(difficulty.equals("Medium")){

            startingRunes = 2000;

        } else {

            startingRunes = 500;

        }

        if(name != null){
        if(name.length() >= 1 && jobClass != null){

            if(name.length() > 25){
                
                finalName = name.substring(0, 25);

            } else 
            {
                finalName = name;
            }
            
            if(jobClass == "Vagabond"){

                player = new Player(finalName, jobClass, 9, startingRunes);
                new GameLobby(player);

                player.addHp(15);
                player.addDex(13);
                player.addInt(9);
                player.addEnd(11);
                player.addStr(14);
                player.addFth(9);
    
    
            } else if(jobClass == "Samurai"){
                
                player = new Player(finalName, jobClass, 9, startingRunes);
                new GameLobby(player);

                player.addHp(12);
                player.addDex(15);
                player.addInt(9);
                player.addEnd(13);
                player.addStr(12);
                player.addFth(8);
    
                
            } else if(jobClass == "Warrior"){
                
                player = new Player(finalName, jobClass, 8, startingRunes);
                new GameLobby(player);

                player.addHp(11);
                player.addDex(16);
                player.addInt(10);
                player.addEnd(11);
                player.addStr(10);
                player.addFth(8);
    
                
            } else if(jobClass == "Hero"){
                
                player = new Player(finalName, jobClass, 7, startingRunes);
                new GameLobby(player);

                player.addHp(14);
                player.addDex(9);
                player.addInt(7);
                player.addEnd(12);
                player.addStr(16);
                player.addFth(8);
    
                
            } else if(jobClass == "Astrologer"){
                
                player = new Player(finalName, jobClass, 6, startingRunes);
                new GameLobby(player);

                player.addHp(9);
                player.addDex(12);
                player.addInt(16);
                player.addEnd(9);
                player.addStr(8);
                player.addFth(7);
                
            } else if(jobClass == "Prophet"){
                
                player = new Player(finalName, jobClass, 7, startingRunes);
                new GameLobby(player);

                player.addHp(10);
                player.addDex(10);
                player.addInt(7);
                player.addEnd(8);
                player.addStr(11);
                player.addFth(16);
                
            }

            return true;        

        } 

    }

            JOptionPane.showMessageDialog(null, "Invalid Input!", "Error Message", JOptionPane.WARNING_MESSAGE);
        

        return false;
    }
    /**
     * Updates the difficulty label.
     * 
     * @param diffString the difficulty string.
     */
    public void updateDiff(String diffString){

        if(diffString.equals("Easy")){

            layeredPane.remove(diffDesc);

            diffDesc = new JLabel("Start with 5000 runes");
            diffDesc.setBounds(600, 440, 200, 50);
            diffDesc.setFont(new FontHolder().getFont(20));
            diffDesc.setForeground(Color.white);
        
            layeredPane.add(diffDesc, Integer.valueOf(1));

        } else if(diffString.equals("Medium")){

            layeredPane.remove(diffDesc);

            diffDesc = new JLabel("Start with 2000 runes");
            diffDesc.setBounds(600, 440, 200, 50);
            diffDesc.setFont(new FontHolder().getFont(20));
            diffDesc.setForeground(Color.white);
        
            layeredPane.add(diffDesc, Integer.valueOf(1));

        } else {

            layeredPane.remove(diffDesc);

            diffDesc = new JLabel("Start with 500 runes");
            diffDesc.setBounds(600, 440, 200, 50);
            diffDesc.setFont(new FontHolder().getFont(20));
            diffDesc.setForeground(Color.white);
        
            layeredPane.add(diffDesc, Integer.valueOf(1));
        }

    }

    /**
     * Updates the TextField containing the player's name. Also adjusts the name label
     * depending on the length of the name.
     * 
     * @param name the inputted name of the player.
     */
    public void updateNameField(String sName){

        layeredPane.remove(nameLabel);

        nameLabel = new JLabel(sName);

        int len = sName.length();
        int x = 626;

        if((len % 2) == 0){
            x += 8.5;
        }

        len /= 2;

        len *= 17;

        x -= len;

        nameLabel.setBounds(x,130,400,50);
        nameLabel.setFont(font.getFont(40));
        nameLabel.setForeground(Color.white);

        layeredPane.add(nameLabel, Integer.valueOf(1));
    }


}
