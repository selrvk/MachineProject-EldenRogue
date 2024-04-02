package Prog3MP;

import javax.swing.*;

/**
 * CreditsView is simply the frame the shows the credits.
 * 
 * @author Charles, Myrine.
 */
public class CreditsView {
    
    private JFrame mainFrame;
    private JLabel detailsLabel;
    private ImageIcon nameIcon;
    private JButton continueBtn;

    /** 
     * Constructor
    */
    public CreditsView(){

        printCredits();
    }
    /**
     * Prints the credits frame.
     */
    public void printCredits(){

        mainFrame = new JFrame("Credits");
        mainFrame.setSize(1280,720);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setUndecorated(true);
        mainFrame.setLocation(300, 160);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        printDetails();
    
    }
    /**
     * Prints the details.
     */
    public void printDetails(){

        nameIcon = new ImageIcon("Prog3MP/CreditsTitle.png");
        detailsLabel = new JLabel();
        detailsLabel.setIcon(nameIcon);
        detailsLabel.setBounds(0,0,1280,720);

        mainFrame.add(detailsLabel);

        printInputs();
    }
    /**
     * Prints the inputs.
     */
    public void printInputs(){

        continueBtn = new JButton("Continue");
        continueBtn.setBounds(1030,650,200,50);
        continueBtn.setFont(new FontHolder().getFont(20));
        continueBtn.setFocusable(false);

        mainFrame.add(continueBtn);

    }
    /**
     * 
     * @return returns the button.
     */
    public JButton getBtn(){

        return continueBtn;
    }
    /**
     * 
     * @return returns the frame.
     */
    public JFrame getFrame(){

        return mainFrame;
    }

}

