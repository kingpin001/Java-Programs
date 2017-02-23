/*******************************************************************************
assignment-5
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 12/07/2016
********************************************************************************/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class QuizPanel extends JPanel implements ActionListener {
    /*
     * initialization of the panels , textfields and buttons used for the layout 
     */
	private JPanel bottomPanel;
	private JLabel ansLabel;
	private JTextField ansField;
	private JButton submitButton;
	private TimerPanel timePanel;
	private QuestionPanel quesPanel;
	private JTextField durationField;
	private JLabel timerLabel;
	private JButton startButton;
	private Thread t1;
    
    /*
     * Constructor 
     */
	public QuizPanel() {
		Border borderLine = BorderFactory.createLineBorder(Color.black);
		
        /*
         * initialization of Fields in Timer Panel
         */
        timePanel = new TimerPanel();
		timerLabel = new JLabel("Total Duration(mins)");
		durationField = new JTextField(10);
		startButton = new JButton("Start");
        
        /*
         * Addition of fields to TimerPanel
         */
        timePanel.add(timerLabel);
		timePanel.add(durationField);
		timePanel.add(startButton);
		startButton.addActionListener(this);
		timePanel.timerQuiz();
        /*
         * Instantialization of Fields in Bottom Panel
         */
        bottomPanel = new JPanel();
		ansLabel = new JLabel("Your answer:");
		ansField = new JTextField(30);
		submitButton = new JButton("Submit");
        submitButton.setEnabled(false);
        /*
         * Adding the fields to BottomPanel and setting the layout
         */
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(ansLabel);
		bottomPanel.add(ansField);
		bottomPanel.add(submitButton);
		submitButton.addActionListener(this);
        /*
         * Adding the QuestionPanel, TimerPanel and BottomPanel to QuizPanel
         */
		quesPanel = new QuestionPanel(this);
		quesPanel.setBorder(borderLine);
		this.setBorder(borderLine);
		this.setLayout(new BorderLayout());
		this.add(timePanel, BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(quesPanel, BorderLayout.CENTER);
	}
    /*
	 * isValidTime(String time) method validates the quiz time and returns false if it is not proper.
	 */
	private boolean isValidTime(String time) {
		int userInputDuration;
		
        try {
			userInputDuration = Integer.parseInt(time);
		} 
		catch (NumberFormatException e) {
			return false;
		}
		
		if(userInputDuration >= 1 && userInputDuration <= 20) {
            return true;
		} else {
            return false;
		}
	}
    /*
     * clearFields() method clears all the fields when quiz is completed and nullifies the thread.
     */
	public void clearFields() {
		durationField.setText("");
		durationField.setEnabled(true);
		startButton.setEnabled(true);
        submitButton.setEnabled(false);
		ansField.setText("");
		t1 = null;
	}
	
    /*
     * isTimeRemaining() method checks the remaining time.
     */
	public boolean isTimeRemaining() {
		return timePanel.isTimeUp();
	}
	
    /*
     * stopCurrentTimer() method stops the timer.
     */
	public void stopCurrentTimer() {
		timePanel.stopTimer();
	}
    /*
     * actionPerformed(ActionEvent e) method handles the action events for start and submit buttons.
     */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {
			if(ansField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Submission not successful. Please provide answer");
			} else {	
				quesPanel.verifyAnswer(ansField.getText());
				ansField.setText("");
			}
		}

		if (e.getSource() == startButton) {
			if(isValidTime(durationField.getText())) {
				durationField.setEnabled(false);
				startButton.setEnabled(false);
                                submitButton.setEnabled(true);
				timePanel.startTimer(Integer.parseInt(durationField.getText()));
				t1 = new Thread(quesPanel);
				t1.start();
			} else {
				JOptionPane.showMessageDialog(null, "Duration should be between 1 to 20 mins");
			}
		}
	}
}