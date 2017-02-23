/*******************************************************************************
assignment-3
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 10/20/2016
********************************************************************************/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * Class name: PDGameAPP
 * Description: This class contains the main method. This class uses the methods from
 * 				PDGame and GameStat to implement the prisoner's dilemma game.
 */
@SuppressWarnings("serial")
public class PDGameApp extends JFrame implements ActionListener, ListSelectionListener {

	/*
	* Data members
	*/
	int rounds;
	int TR;        //total rounds
	String startTime;
	Map<String, GameStat> map = new HashMap<String, GameStat>();
	DefaultListModel<String> lmodel = new DefaultListModel<String>();
    
	final JPanel wpanel = new JPanel();
    final JPanel epanel = new JPanel();
	final JPanel spanel = new JPanel();
	final JPanel eNpanel = new JPanel();
    final JPanel eNNpanel = new JPanel();
    final JPanel eNSpanel = new JPanel();
    
    final JList<String> list =new JList<String>();
    
    final JLabel playedL = new JLabel("Rounds Played");
    final JTextField playedText = new JTextField();
    
    final JLabel straregyL = new JLabel("Computer Strategy");
    final JTextField straregyText = new JTextField();
    
    final JLabel PsentenceL = new JLabel("Player Sentence");
    final JTextField PsentenceText = new JTextField();
    
    final JLabel CsentenceL = new JLabel("Computer Sentence");
    final JTextField CsentenceText = new JTextField();
    
    final JLabel winnerL = new JLabel("Winner");
    final JTextField winnerText = new JTextField();
    
    final JButton startB = new JButton("Start New Game");
    final JLabel Decision = new JLabel("what is Your decision this round?");
    
    final JButton silentB = new JButton("Remain Silent");
    final JButton testifyB = new JButton("Testify");
    final JLabel lCombo = new JLabel("Computer Strategy:");
    
    final JComboBox<String> cStrategy;
    final JTextArea txt = new JTextArea();
    PDGame pdgame;
    GameStat gstat;
   
    /*
     * Main function which implements the run function.
     */
    public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() { 
			public void run() {
				createAndShowGUI();           
			}
		});
	}
   
    /*
     * function to implement the GUI
     */
	public static void createAndShowGUI() 
        {
		/*
		 * Creating and setting up the window
		 */
		PDGameApp frame = new PDGameApp(); 
		frame.addListeners();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setName("Prisoner's Dilemma");
		frame.pack();
		/*
		 * Display the window.
		 */
		frame.setVisible(true);      
	}

	public PDGameApp() {
            super("*****Prisoner's Dilemma*****");
		setLayout(new BorderLayout());
		Color c =new Color(102,120,175);
		
		list.setModel(lmodel);
		/*
		 * sets the selection mode.
		 */
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(10);
		list.setFixedCellWidth(350);
		JScrollPane scrollPane = new JScrollPane(list);
		
		/*
		 * GridLayout for the text fields that are used to display Game Statistics.
		 */
		GridLayout grid = new GridLayout(5,2);
		grid.setVgap(5);
		spanel.setLayout(grid);
		spanel.setBackground(c);     
		spanel.add(playedL);
		spanel.add(playedText);
		spanel.add(straregyL);
		spanel.add(straregyText);
		spanel.add(PsentenceL);
		spanel.add(PsentenceText);
		spanel.add(CsentenceL);
		spanel.add(CsentenceText);
		spanel.add(winnerL);
		spanel.add(winnerText);
		
		/*
		 * Text fields are set to non-Editable
		 */
		playedText.setEditable(false);
		straregyText.setEditable(false);
		PsentenceText.setEditable(false);
		CsentenceText.setEditable(false);
		winnerText.setEditable(false);
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("List of Games");
		wpanel.setBorder(title);
		wpanel.setBackground(c);
		wpanel.setLayout(new BorderLayout());
		wpanel.add(scrollPane,BorderLayout.NORTH);
		wpanel.add(spanel,BorderLayout.SOUTH);
		
		
		/*
		 * selecting computer strategy
		 */
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		pdgame = new PDGame();
		ArrayList<String> strategies = pdgame.getStrategies();
		for(int i=0;i< strategies.size();i++) 
                {
			model.addElement(strategies.get(i));
		}
		cStrategy = new JComboBox<String>(model);
		cStrategy.setSelectedIndex(0);
      
		/*
		 * Decision buttons
		 */
		silentB.setEnabled(false);
		testifyB.setEnabled(false);
		c= new Color(230,204,153);
		
		/*
		 * Adding combo box and start game button to the panel
		 */
		eNNpanel.setLayout(new FlowLayout());
		eNNpanel.setBackground(c);
		eNNpanel.add(lCombo);
		eNNpanel.add(cStrategy);
		eNNpanel.add(startB);
		
		/*
		 * Adding silent and testify buttons to panel
		 */
		eNSpanel.setLayout(new FlowLayout());
		eNSpanel.setBackground(c);
		eNSpanel.add(Decision);
		eNSpanel.add(silentB);
		eNSpanel.add(testifyB);
	
		eNpanel.setLayout(new GridLayout(2,1));
		eNpanel.add(eNNpanel);
		eNpanel.add(eNSpanel);
		
		txt.setRows(15);
		txt.setColumns(35);
		txt.setEditable(false);
		JScrollPane scrollTxt = new JScrollPane(txt);
		
		/*
		 * Add elements to east panel
		 */
		epanel.setLayout(new BorderLayout());
		epanel.add(eNpanel,BorderLayout.NORTH);
		epanel.add(scrollTxt,BorderLayout.SOUTH);
		
		/*
		 * Adding the panels to the frame
		 */
		add(wpanel,BorderLayout.WEST);
		add(epanel,BorderLayout.EAST);
		
		/*
		 * setEnables to false for some buttons and labels
		 */
		silentB.setEnabled(false);
		testifyB.setEnabled(false);
		startB.setEnabled(true);
		Decision.setEnabled(false);
		lCombo.setEnabled(true);
		cStrategy.setEnabled(true);
	}
   
   
  
	/*
	 * Adding the Action Listeners
	 */
	public void addListeners() 
	{
		startB.addActionListener(this);
		silentB.addActionListener(this);
		testifyB.addActionListener(this);
		list.addListSelectionListener( this);
	}
   
	/*
	 * Performs the action
	 */
	public void valueChanged(ListSelectionEvent l) {
		
		if(l.getSource() == list) {
			String s = list.getSelectedValue();
			gstat = map.get(s);
			
			playedText.setText(Integer.toString(gstat.getRounds()));
			straregyText.setText(gstat.getCompStat());
			CsentenceText.setText(Integer.toString(gstat.getCompSentence()));
			PsentenceText.setText(Integer.toString(gstat.getUserSentence()));
			winnerText.setText(gstat.getWinner());	
		}
	}
   /*
	 * prints the final result
	 */
	public void getFinal() 
	{
		txt.append("\n"+pdgame.getScores()+"\n");
		gstat = pdgame.getStats();
		map.put(startTime,gstat);
		lmodel.addElement(startTime);
		silentB.setEnabled(false);
		testifyB.setEnabled(false);
		startB.setEnabled(true);
		Decision.setEnabled(false);
		lCombo.setEnabled(true);
		cStrategy.setEnabled(true);   
	}
	/*
	 * performing the required action when the button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startB) {
			startTime = (new Date()).toString();
			silentB.setEnabled(true);
			testifyB.setEnabled(true);
			startB.setEnabled(false);
			Decision.setEnabled(true);
			lCombo.setEnabled(false);
			cStrategy.setEnabled(false);
			pdgame = new PDGame();
			
			do {
				TR = 5;
			}
                        while(TR < 1);
    	  
			txt.setText("");
			txt.append("Round 1 :\n 1. Cooperate and remain silent.\n2. Betray and testify against. \n\nWhat is your decision this round?\n"); 
			rounds=1;
			/*
			 * set the rounds and strategy
			 */
			pdgame.setRounds(TR);
			pdgame.setStrategy(cStrategy.getSelectedIndex()+1);
			
		}
		else if (e.getSource() == silentB) 
		{ 
			/*
			 * user selects silent
			 */
			String result = pdgame.playRound(0);
			txt.append("\n"+result+"\n");
			
			if(rounds==TR) {
				getFinal();
			} else {
				++rounds;
				txt.append("\n\nRound "+rounds+":\n1. Cooperate and remain silent.\n2. Betray and testify against. \nWhat is your decision this round?\n"); 
			}    		  
		} 
		else if(e.getSource() == testifyB)
			{ 
			/*
			 * user selects testify
			 */
			String result = pdgame.playRound(1);
			txt.append("\n"+result+"\n");
			
			if(rounds==TR)
			{
				getFinal();
			} 
			else 
			{
				++rounds;
				txt.append("\n\nRound "+rounds+":\n1. Cooperate and remain silent.\n2. Betray and testify against. \nWhat is your decision this round?\n");  
			}
		}
	}
   
	
}