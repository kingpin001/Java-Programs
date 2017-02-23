/*******************************************************************************
assignment-5
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 12/07/2016
********************************************************************************/
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class TimerPanel extends JPanel implements ActionListener 
{
	
	private int delay = 1000;
	private JTextField timeRemaining;
	private Timer timer;
	private int enteredDuration;
	private long startTime;
	private boolean timeUp;
    
    /*
     * timerQuiz() method is used to set the Layout for TimerPanel and to add the textField for timeRemaining
     */
	public void timerQuiz() {
		timeRemaining = new JTextField(10);
		timeRemaining.setEditable(false);
		this.setLayout(new FlowLayout());
		this.add(timeRemaining);
	}
    
    /*
     * startTimer(int userEnteredDuration) method starts the Timer and count down begins and will be called when the Start button is clicked
     */
    public void startTimer(int userEnteredDuration) 
    {
                timeUp = false;
		enteredDuration = userEnteredDuration;
		timer = new Timer(delay, this);
		timer.start();
		startTime = Calendar.getInstance().getTimeInMillis();
	}
    
    /*
     * actionPerformed(ActionEvent e)  Method handles timer events.
     */
	public void actionPerformed(ActionEvent e) 
        {
		long now = Calendar.getInstance().getTimeInMillis();
		Long timeSpent = now - startTime;
		Long remainingTime = enteredDuration * 60000 - timeSpent;
		
		if (remainingTime <= 0) 
                {
			timeUp = true;
			stopTimer();
		} 
                else 
                {
		  timeRemaining.setText(String.format("%02d : %02d ", TimeUnit.MILLISECONDS.toMinutes(remainingTime), TimeUnit.MILLISECONDS.toSeconds(remainingTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainingTime))));
       }
	}
    /*
     * isTimeUp()  Method returns the timeUp flag
     */
	public boolean isTimeUp() 
        {
		return timeUp;
	}
    /*
     * stopTimer() Method stops the Timer and sets the timeRemaining field empty.
     */
	public void stopTimer() 
        {
		timer.stop();
		timeRemaining.setText("");
	}

    
}