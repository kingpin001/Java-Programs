/*******************************************************************************
assignment-5
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 12/07/2016
********************************************************************************/
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.FontMetrics;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class QuestionPanel extends JPanel implements Runnable {
	
	private String inquestion;
	private String inanswer;
	/*
	 * ArrayList that stores all the contents read in the file
	 */
	private ArrayList<Question> qL = new ArrayList<Question>();
	private Question currentQuestion;
	private int currentQuestionNum = -1;
    private int totalPoints = 0;
	public boolean timeIsUp = false;
	private boolean doneMessage = false;
	private boolean firstTime = true;
	private int noOfQuestions;
	private QuizPanel quizPanel;

    /*
     * Constructor for Quiz Panel 
     */
	public QuestionPanel(QuizPanel qp) {
		quizPanel = qp;
	}
    
    /*
     * run() method starts the quiz.A thread is also created which runs the quiz.
     */
	public void run() {
		prepareNewQuiz();
		
		while ((!quizPanel.isTimeRemaining()) && (!doneMessage)) {
			try {
				Thread.sleep(1000);
			}
            catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
        timeIsUp = quizPanel.isTimeRemaining();
		
        if (!timeIsUp) {
			quizPanel.stopCurrentTimer();
        } else {
			repaint();
		}
        endCurrentQuiz();
	}
    
	/*
	 * readFile() method reads the file and displays an error if it is not accessible.
	 */
	public boolean readFile() {
		try {
			String fileName = "quiz.txt";
			boolean imageQues = false;
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			
            while (scanner.hasNextLine()) {
				inquestion = scanner.nextLine();
				inanswer = scanner.nextLine();
				imageQues = inquestion.contains(".jpg") || inquestion.contains(".gif");
				if (imageQues) {
					ImageQuestion iq = new ImageQuestion(inquestion, inanswer, this);
					qL.add(iq);
				} else {
					TextQuestion tq = new TextQuestion(inquestion, inanswer);
					qL.add(tq);
				}
			}
			
            noOfQuestions = qL.size();
			scanner.close();
			return true;
		} 
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Sorry, we are experiencing issues now, please try later");
			return false;
		}
	}
    
    /*
     * paintComponent(Graphics g) method displays the questions and displays all answers.
     */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = this.getSize();
        
        
		if (firstTime || currentQuestionNum == -1)
			return;
		
       
        if (!timeIsUp && currentQuestionNum < noOfQuestions) {
			currentQuestion = (Question) qL.get(currentQuestionNum);
			currentQuestion.draw(g, d);
        
        /*
         * draw scores and correct answers if time up or all the questions are completed
         */
        } else {
			doneMessage = true;
            FontMetrics fm = g.getFontMetrics();
            int x = (d.width - fm.stringWidth("Quiz is finished. Your total score is " + totalPoints + ".")) / 2;
            int y = (d.height - fm.getHeight()) / 2;
            g.drawString("Quiz is finished. Your total score is "
                         + totalPoints + ".", x,y);
            
            for(int i =0; i< noOfQuestions; i++) {
                StringBuffer buffer = new StringBuffer();
                Question questionAnswers = (Question) qL.get(i);
                String str = questionAnswers.getAnswer();
                buffer.append("Correct answer for Question ");
                buffer.append(i+1 + ": ");
                buffer.append(str);
                y = ((d.height - fm.getHeight()) / 2) + ((i+1)*20);
                g.drawString(buffer.toString(), x,y);
            }                  
		}
	}
    
    /*
     * verifyAnswer(String answer) Method verifies user answers and assigning points accordingly
     */
	public void verifyAnswer(String answer) {
		int points = currentQuestion.judgeAnswer(answer);
		totalPoints = totalPoints + points;
		currentQuestionNum++;
		repaint();
	}
	
    /*
     * prepareNewQuiz() method Reinitializes variables for every new quiz and after every question.
     */
	private void prepareNewQuiz() {
		currentQuestionNum++;
		qL.clear();
		firstTime = false;
		timeIsUp = false;
		doneMessage = false;
		totalPoints = 0;
		
        if(readFile()) {
		  repaint();
        } else {
          doneMessage = true;
        }
	}
	
    /*
     * endCurrentQuiz() method clears all the fields once the quiz is completed and enables all the buttons.
     */
	private void endCurrentQuiz() {	
		quizPanel.clearFields();
		currentQuestionNum = -1;	
	}
}