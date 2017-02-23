/*
 * Assignment: 3
 * Program: Prisoner's Dilemma
 * Programmers: Sreeharsha kollipara (Z1712190)
 * 				Pravallika Gunduboina (Z1711762)
 * Due Date: 10/15/2014
 */

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
	int total_rounds;
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
    final JLabel lblPlayed = new JLabel("Rounds Played");
    final JTextField txtPlayed = new JTextField();
    final JLabel lblStrategy = new JLabel("Computer Strategy");
    final JTextField txtStrategy = new JTextField();
    final JLabel lblPSentence = new JLabel("Player Sentence");
    final JTextField txtPSentence = new JTextField();
    final JLabel lblCSentence = new JLabel("Computer Sentence");
    final JTextField txtCSentence = new JTextField();
    final JLabel lblWinner = new JLabel("Winner");
    final JTextField txtWinner = new JTextField();
    final JButton btnStart = new JButton("Start New Game");
    final JLabel lblDecision = new JLabel("Your decision this round?");
    final JButton btnSilent = new JButton("Remain Silent");
    final JButton btnTestify = new JButton("Testify");
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
	public static void createAndShowGUI() {
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
		spanel.add(lblPlayed);
		spanel.add(txtPlayed);
		spanel.add(lblStrategy);
		spanel.add(txtStrategy);
		spanel.add(lblPSentence);
		spanel.add(txtPSentence);
		spanel.add(lblCSentence);
		spanel.add(txtCSentence);
		spanel.add(lblWinner);
		spanel.add(txtWinner);
		
		/*
		 * Text fields are set to non-Editable
		 */
		txtPlayed.setEditable(false);
		txtStrategy.setEditable(false);
		txtPSentence.setEditable(false);
		txtCSentence.setEditable(false);
		txtWinner.setEditable(false);
		
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
		for(int i=0;i< strategies.size();i++) {
			model.addElement(strategies.get(i));
		}
		cStrategy = new JComboBox<String>(model);
		cStrategy.setSelectedIndex(0);
      
		/*
		 * Decision buttons
		 */
		btnSilent.setEnabled(false);
		btnTestify.setEnabled(false);
		c= new Color(230,204,153);
		
		/*
		 * Adding combo box and start game button to the panel
		 */
		eNNpanel.setLayout(new FlowLayout());
		eNNpanel.setBackground(c);
		eNNpanel.add(lCombo);
		eNNpanel.add(cStrategy);
		eNNpanel.add(btnStart);
		
		/*
		 * Adding silent and testify buttons to panel
		 */
		eNSpanel.setLayout(new FlowLayout());
		eNSpanel.setBackground(c);
		eNSpanel.add(lblDecision);
		eNSpanel.add(btnSilent);
		eNSpanel.add(btnTestify);
	
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
		btnSilent.setEnabled(false);
		btnTestify.setEnabled(false);
		btnStart.setEnabled(true);
		lblDecision.setEnabled(false);
		lCombo.setEnabled(true);
		cStrategy.setEnabled(true);
	}
   
   
  
	/*
	 * Adding the Action Listeners
	 */
	public void addListeners() {
		btnStart.addActionListener(this);
		btnSilent.addActionListener(this);
		btnTestify.addActionListener(this);
		list.addListSelectionListener( this);
	}
   
	/*
	 * Performs the action
	 */
	public void valueChanged(ListSelectionEvent l) {
		
		if(l.getSource() == list) {
			String s = list.getSelectedValue();
			gstat = map.get(s);
			
			txtPlayed.setText(Integer.toString(gstat.getRounds()));
			txtStrategy.setText(gstat.getCompStat());
			txtCSentence.setText(Integer.toString(gstat.getCompSentence()));
			txtPSentence.setText(Integer.toString(gstat.getUserSentence()));
			txtWinner.setText(gstat.getWinner());	
		}
	}
   
	/*
	 * performing the required action when the button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			startTime = (new Date()).toString();
			btnSilent.setEnabled(true);
			btnTestify.setEnabled(true);
			btnStart.setEnabled(false);
			lblDecision.setEnabled(true);
			lCombo.setEnabled(false);
			cStrategy.setEnabled(false);
			pdgame = new PDGame();
			
			/*
			 * gets the random number of rounds
			 */
			do {
				total_rounds = (int)(Math.random() * 10);
			}while(total_rounds < 1);
    	  
			txt.setText("");
			txt.append("Round 1 :\n 1. Cooperate and remain silent.\n2. Betray and testify against. \n\nWhat is your decision this round?\n"); 
			rounds=1;
			/*
			 * set the rounds and strategy
			 */
			pdgame.setRounds(total_rounds);
			pdgame.setStrategy(cStrategy.getSelectedIndex()+1);
			
		} else if (e.getSource() == btnSilent) { 
			/*
			 * user selects silent
			 */
			String result = pdgame.playRound(0);
			txt.append("\n"+result+"\n");
			
			if(rounds==total_rounds) {
				getFinal();
			} else {
				++rounds;
				txt.append("\n\nRound "+rounds+":\n1. Cooperate and remain silent.\n2. Betray and testify against. \nWhat is your decision this round?\n"); 
			}    		  
		} else if(e.getSource() == btnTestify) { 
			/*
			 * user selects testify
			 */
			String result = pdgame.playRound(1);
			txt.append("\n"+result+"\n");
			
			if(rounds==total_rounds) {
				getFinal();
			} else {
				++rounds;
				txt.append("\n\nRound "+rounds+":\n1. Cooperate and remain silent.\n2. Betray and testify against. \nWhat is your decision this round?\n");  
			}
		}
	}
   
	/*
	 * prints the final result
	 */
	public void getFinal() {
		txt.append("\n"+pdgame.getScores()+"\n");
		gstat = pdgame.getStats();
		map.put(startTime,gstat);
		lmodel.addElement(startTime);
		btnSilent.setEnabled(false);
		btnTestify.setEnabled(false);
		btnStart.setEnabled(true);
		lblDecision.setEnabled(false);
		lCombo.setEnabled(true);
		cStrategy.setEnabled(true);   
	}
}