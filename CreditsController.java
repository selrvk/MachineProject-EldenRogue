package Prog3MP;

/** Simple controller class for credits 
 * 
 * @author Charles, Myrine.
*/
public class CreditsController {
    
    public CreditsController(CreditsView view){

        view.getBtn().addActionListener(e -> {

            view.getFrame().dispose();
        });
    }

}
