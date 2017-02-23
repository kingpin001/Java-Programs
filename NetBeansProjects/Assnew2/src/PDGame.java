/*******************************************************************************
assignment-3
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 10/3/2016
********************************************************************************/
import java.util.*;

public class PDGame 
{
	
	/*
	 * This method is a array list of all the available computer strategies.
	 */
	@SuppressWarnings("serial")
	private static final ArrayList<String> strategies=new ArrayList<String>()
	{{
		add("From File");
		add("Tit-For-Tat");
		add("Tit-For-Two-Tats");
		add("Predict from history or random");
	}};
	private int strategy;	/* Computer Strategy */
	
	GameStat gs = new GameStat();	/* GameStat object */
	
	private ArrayList<Integer> H = new ArrayList<Integer>(); /* User preferences history */

	/*
	 * This method returns the computer strategies.
	 */
	public ArrayList<String> getStrategies() 
	{
        return strategies;
    }
	
	/*
	 * This method sets user strategy.
	 */
	public void setStrategy(int strategy) 
	{
		this.strategy = strategy;
		gs.setCompStat(strategies.get(strategy-1));
	}
	
	/*
	 * This method selects a random number.
	 */
	public int selectRandom()
	{	
		Random random = new Random();
		
	    if(random.nextBoolean()) {
	    	return 0;
	    } else {
	    	return 1;
	    }
	}
	
	/*
	 * This method returns the game statistics.
	 */
	public GameStat getStats() 
	{
		return gs;
	}
	
	/*
	 * This method contains the implementations of different computer strategies. 
	 */
	public int computerDecision()
	{
		int prev;
		int res = 0;
		
		switch(strategy)
		{
				/*
				 * From File.
				 */
		case 1: res = selectRandom();
				break;
				
				/*
				 * Tit-For-Tat 
				 */
		case 2: if(H.size() == 0) {
					res = 0;
				} else {
					prev = H.get(H.size()-1);
					if(prev == 0) {
						res = 0;
					} else {
						res = 1;
					}
				}
				break;
				
				/*
				 * Tit-For-Two-Tats.
				 */
		case 3: if(H.size() <= 2) {
					res = 0;
				} else {
					if(H.get(H.size() - 1) == 1 && H.get(H.size() - 2) == 1) {
						res = 1;
					} else {
						res = 0;
					}
				}
				break;
				
				/*
				 * Predict from history or random.
				 */
		case 4: if(H.size() <= 4) {
						res = ((((int)(Math.random()*10))%2 + 1)-1);
				}
				break;
				
		default: break;
		}
		return res;
	}
	
	/*
	 * This method awards sentences to user and computer.
	 */
	public String playRound(int decision)
	{
		int compDecision;
		int user;
		int computer;
		String res;
		
		compDecision = computerDecision();
		H.add(decision);
		
		if(decision == compDecision) {
			/*
			 * If user and computer co-operate and remain silent.
			 */
			if(decision == 0) {
				res = " You and your partner remain silent.\n You both get 2 years in prison.";
				user = 2;
				computer = 2;
			/*
			 * If user and computer betray and testify.
			 */
			} else {
				res = " You and your partner testify against each other.\n You both get 3 years in prison.";
				user = 3;
				computer = 3;
			}
		/*
		 * If user testifies and computer remains silent.
		 */
		} else if(decision > compDecision) {
			res = " you tesify and your partner remain silent.\n You get 1 year in prison and they get 5 years.";
			user = 1;
			computer = 5;
		/*
		 * If user remains silent and computer betrays.
		 */
		} else {
			res = " you remain silent and your partner tesify against you.\n You get 5 years in prison and your partner gets 1 year.";
			user = 5;
			computer = 1;
		}
		
		/*
		 * Update the sentences.
		 */
		gs.update(user, computer);
		return res;
	}
	
	/*
	 * This method sets the number of rounds.
	 */
	public void setRounds(int rounds)
	{
		gs.setRounds(rounds);
	}
	
	/*
	 * This method returns the summary of the game.
	 */
	public String getScores()
	{
		int user = gs.getUserSentence();
		int computer = gs.getCompSentence();
		
		String score = "Your total prison sentence is " + user + " years.\n"
				+ "Your partner's total prison sentence is "+ computer +" years."; 
		return score;
	}	
}