/*******************************************************************************
assignment-5
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 12/07/2016
********************************************************************************/
import java.awt.Dimension;
import java.awt.Graphics;

public abstract class Question 
{
	String inquestion;
	String inanswer;

   

    /*
     * draw(Graphics g, Dimension d) is the abstract method for draw method
     */
    public abstract void draw(Graphics g, Dimension d);

	/*
	 * judgeAnswer(String userAnswer) method compares the user answers with the actual answers and returns the score
	 */
    public int judgeAnswer(String userAnswer) {
		int score = 0;

		if (inanswer.equalsIgnoreCase(userAnswer.trim())) {
			score = 1;
		}
		return score;
	}
    
    public String getAnswer() {
        return inanswer;
    }
    /*
     * Constructor for the Question
     */
	Question(String question, String answer) {
		this.inquestion= question;
		this.inanswer = answer;
	}
}
