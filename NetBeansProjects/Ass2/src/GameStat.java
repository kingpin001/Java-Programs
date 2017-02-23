/*******************************************************************************
assignment-3
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 10/20/2016
********************************************************************************/

 //This class handles all the game statistics.
 
public class GameStat 
{
	private int rounds;		// Number of rounds in the game 
	private int userSentence ;	// Prison sentence ofthe user 
	private int compSentence ;	// Prison sentence of computer 
	private String compStat;	//Computer Strategy
	
	
	public void setRounds(int rounds) //sets the number of rounds 
	{
		this.rounds = rounds;
	}
	
	
	public int getRounds() // returns the number of rounds completed.
	{
		return rounds;
	}

	
	public String getCompStat()//returns the computer Strategy being used.
	{
	{
		return compStat;
	}

	
	public void setCompStat(String compStat)//sets the computer strategy.
	{
		this.compStat = compStat;
	}

	
	public void update(int userSentence, int compSentence)//updates the sentences of user and computer.
	{
		this.userSentence = this.userSentence + userSentence;
		this.compSentence = this.compSentence + compSentence;	
	}
	
	
	public int getUserSentence() //returns the user sentence. 
	{
		return userSentence;
	}

	
	public int getCompSentence() //returns the computer sentence. 
	{
		return compSentence;
	}

	
	public String getWinner()//returns the winner and the strategy used.
	{
		if(userSentence > compSentence) {	
			return "computer";
		} else if(compSentence > userSentence) {	
			return "player";
		} else {
			return "tie game";
		}
	}	
}

