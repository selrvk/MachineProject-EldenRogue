package Prog3MP;

import javax.swing.*;

    /**
     * This class handles the TitleScreen, the beginning of the code.
     * 
     * @author Charles, Myrine.
     */
public class TitleScreenView{

    private JFrame mainFrame;
    private JButton startButton, exitButton;
    private JLabel backgroundLabel;
    private ImageIcon backgroundImage;

    /**
     * Constructor.
     */
    public TitleScreenView(){

        printTitleScreen();
        printTitleButtons();

    }
    /**
     * Prints the title screen's background and JFrame.
     */
    public void printTitleScreen(){

        mainFrame = new JFrame("Elden Rogue");
        mainFrame.setSize(1280,720);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setUndecorated(true);
        mainFrame.setLocation(300, 160);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundLabel = new JLabel();
        backgroundImage = new ImageIcon("Prog3MP/TitleScreenBG.gif");
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0,0,1280,720);
        mainFrame.add(backgroundLabel);

        mainFrame.setVisible(true);


    }
    /**
     * Prints the title screen's buttons. ( Start or Exit )
     */
    public void printTitleButtons(){

        startButton = new JButton();
        exitButton = new JButton();

        // Start Button
        startButton.setBounds(74,480,287,163);
        startButton.setFocusable(false);
        startButton.setOpaque(false);
        startButton.addActionListener(e -> {

            new CharacterCreation();
            mainFrame.dispose();
            
        });

        mainFrame.add(startButton);

        // Exit Button
        exitButton.setBounds(909,480,287,163);
        exitButton.setFocusable(false);
        exitButton.setOpaque(false);
        exitButton.addActionListener(e -> mainFrame.dispose());

        mainFrame.add(exitButton);


    }

}

