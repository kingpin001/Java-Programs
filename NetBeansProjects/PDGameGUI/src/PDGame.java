/*
 * Assignment: 3
 * Program: Prisoner's Dilemma
 * Programmers: Sreeharsha kollipara (Z1712190)
 * 				Pravallika Gunduboina (Z1711762)
 * Due Date: 10/15/2014
 */


import java.util.*;

/*
 * Class name: PDGame
 * Description: This class contains the methods that implement the computer strategy and uses user game history.
 */
public class PDGame {
	
	/*
	 * This method is a array list of all the available computer strategies.
	 */
	@SuppressWarnings("serial")
	private static final ArrayList<String> strategies=new ArrayList<String>()
	{{
		add("1. From File");
		add("2. Tit-For-Tat");
		add("3. Tit-For-Two-Tats");
		add("4. Tit-For-Tat with Forgiveness");
		add("5. Predict from history or random");
	}};
	private int strategy;	/* Computer Strategy */
	
	GameStat gs = new GameStat();	/* GameStat object */
	
	private ArrayList<Integer> history = new ArrayList<Integer>(); /* User preferences history */

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
		int result = 0;
		
		switch(strategy)
		{
				/*
				 * From File.
				 */
		case 1: result = selectRandom();
				break;
				
				/*
				 * Tit-For-Tat 
				 */
		case 2: if(history.size() == 0) {
					result = 0;
				} else {
					prev = history.get(history.size()-1);
					if(prev == 0) {
						result = 0;
					} else {
						result = 1;
					}
				}
				break;
				
				/*
				 * Tit-For-Two-Tats.
				 */
		case 3: if(history.size() <= 2) {
					result = 0;
				} else {
					if(history.get(history.size() - 1) == 1 && history.get(history.size() - 2) == 1) {
						result = 1;
					} else {
						result = 0;
					}
				}
				break;
				
				/*
				 * Tit-For-Tat with Forgiveness.
				 */
		case 4:	if(history.size() == 0) {
					result = 0;
				} else {
					prev = history.get(history.size() - 1);
					if(prev == 0) {
						result = 0;
					} else {
						result = selectRandom();
					}
				}
				break;
				
				/*
				 * Predict from history or random.
				 */
		case 5: if(history.size() <= 4) {
						result = selectRandom();	
				} else {
					List<Integer> temphistory = history.subList(history.size() - 4, history.size() - 1);
					for(int i = 0 ; i < history.size() - 2 ; i++) {
						if(history.get(i) == temphistory.get(0) && history.get(i+1) == temphistory.get(1) && history.get(i+2) == temphistory.get(2)) {
							if(history.get(i+3) != null) {
								result = history.get(i+3);
							} else {
								result = selectRandom();
							}	
						}
					}
					
					result = selectRandom();
				}
				break;
				
		default: break;
		}
		return result;
	}
	
	/*
	 * This method awards sentences to user and computer.
	 */
	public String playRound(int decision)
	{
		int compDecision;
		int user;
		int computer;
		String result;
		
		compDecision = computerDecision();
		history.add(decision);
		
		if(decision == compDecision) {
			/*
			 * If user and computer co-operate and remain silent.
			 */
			if(decision == 0) {
				result = " You and your partner remain silent.\n You both get 2 years in prison.";
				user = 2;
				computer = 2;
			/*
			 * If user and computer betray and testify.
			 */
			} else {
				result = " You and your partner testify against each other.\n You both get 3 years in prison.";
				user = 3;
				computer = 3;
			}
		/*
		 * If user testifies and computer remains silent.
		 */
		} else if(decision > compDecision) {
			result = " you tesify and your partner remain silent.\n You get 1 year in prison and they get 5 years.";
			user = 1;
			computer = 5;
		/*
		 * If user remains silent and computer betrays.
		 */
		} else {
			result = " you remain silent and your partner tesify against you.\n You get 5 years in prison and your partner gets 1 year.";
			user = 5;
			computer = 1;
		}
		
		/*
		 * Update the sentences.
		 */
		gs.update(user, computer);
		return result;
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