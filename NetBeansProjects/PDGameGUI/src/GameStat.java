/*
 * Assignment: 3
 * Program: Prisoner's Dilemma
 * Programmers: Sreeharsha kollipara (Z1712190)
 * 				Pravallika Gunduboina (Z1711762)
 * Due Date: 10/15/2014
 */

/*
 * Class name: GameStat
 * Description: This class handles all the game statistics.
 */
public class GameStat {
	private int rounds;			/* Number of rounds in a game */
	private int userSentence ;	/* Prison sentence of user */
	private int compSentence ;	/* Prison sentence of computer */
	private String compStat;	/* Computer Strategy */
	
	/*
	 * This method sets the number of rounds.
	 */
	public void setRounds(int rounds) {
		this.rounds = rounds;
	}
	
	/*
	 * This method returns the number of rounds completed. 
	 */
	public int getRounds() {
		return rounds;
	}

	/*
	 * This method returns the computer Strategy being used.
	 */
	public String getCompStat() {
		return compStat;
	}

	/*
	 * This method sets the computer strategy.
	 */
	public void setCompStat(String compStat) {
		this.compStat = compStat;
	}

	/*
	 * This method updates the sentences of user and computer. 
	 */
	public void update(int userSentence, int compSentence)
	{
		this.userSentence = this.userSentence + userSentence;
		this.compSentence = this.compSentence + compSentence;	
	}
	
	/*
	 * This method returns the user sentence.
	 */
	public int getUserSentence() 
	{
		return userSentence;
	}

	/*
	 * This method returns the computer sentence.
	 */
	public int getCompSentence() 
	{
		return compSentence;
	}

	/*
	 * This method returns the winner and the strategy used.
	 */
	public String getWinner()
	{
		if(userSentence > compSentence) {	
			return "Winner -- computer. The computer used " + compStat.substring(3);
		} else if(compSentence > userSentence) {	
			return "Winner -- player. The computer used " + compStat.substring(3);
		} else {
			return "Winner -- tie game. The computer used " + compStat.substring(3);
		}
	}	
}

