/*******************************************************************************
assignment-5
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 12/07/2016
********************************************************************************/
import java.awt.GridLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class QuizApp extends JFrame {

	public static void main(String args[]) {
		QuizApp quizapp = new QuizApp();
		quizapp.group();
	}
    
    /*
     * group() method places both QuizPanel onto the frame and sets the frame properties.
     */
	public void group() {
        /*
         * Create Instances of QuizPanel
         */
		QuizPanel quPanel = new QuizPanel();
        
		this.setLayout(new GridLayout(1,2));
		
        /*
         * Add the Panels to the Frame
         */
		this.add(quPanel);
        
		
        /*
         * Set the Frame Properties
         */
        this.setVisible(true);
		this.setSize(600,550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}