package Prog3MP;

import java.awt.*;
import java.io.*;

/** 
 * Font holder class for custom font.
 * 
 * @author Charles, Myrine.
 */
public class FontHolder {

    /** 
     * Empty Constructor.
     */
    public FontHolder(){

    }
    /**
     * Gets the custom font.
     * @param size size of the font, text.
     * @return return type Font, returns the font.
     */
    public Font getFont(float size){
        
        try{

            File fontFile = new File("Prog3MP/VecnaBold-4YY4.ttf");
            FileInputStream fontStream = new FileInputStream(fontFile);
            
            return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size);

            }  catch (IOException | FontFormatException e) {

                e.printStackTrace();
                
                return new Font("SANS_SERIF", Font.PLAIN , 20);
            }
    }
    
}
