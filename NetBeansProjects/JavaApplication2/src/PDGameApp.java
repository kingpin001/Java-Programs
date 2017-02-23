
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class PDGameApp 
{
	
public static void main(String[] args) {
	
int CS;		/* Computer strategy */
String NG; 	/* To know if a new game has to be started */
		
PDGame pg ;		/* PDGame class object to call the methods from PDGame.java */
GameStat GS;		/* GameStat class object to call the methods from GameStat.java */
String file = "strategy.txt";	
Map<String, GameStat> map = new HashMap<>();	/* To store statistics */
		
Scanner read = new Scanner(System.in);

do
{
    
pg = new PDGame();
GS = pg.getStats();
int rounds = 5;
int decision;
System.out.println("***Starting A Game of Prisoner's Dilemma *** 5 rounds in a game");
System.out.println("--HERE ARE STRATEGIES AVAILABLE FOR THE COMPUTER");
for (String str : pg.getStrategies()) 
{
System.out.println(str);
}
System.out.println();

//Try - catch block to catch the exceptions
	        try
	        {
	        	System.out.print("Select a strategy from above for the Computer to use in the rounds : ");
	        	CS = read.nextInt();
	        	pg.setStrategy(CS);
                        break;
	        }
	        catch(InputMismatchException im) 
                {
	        	System.out.println("Input is not a valid integer.");
	        }
	        catch(Exception e) 
                {
	        	System.out.println("Error in the opposing strategy: "+ e.toString());
	        }
//To implement random number of rounds in each game.
				 
//For each round, display the user options
			int i = 0;
                        while(i< rounds)
                        {
                            i++;
				System.out.println("BEGIN A ROUND - Here are your 2 choices");
				System.out.println("1. Cooperate and remain silent.");
				System.out.println("2. Betray and testify against.\n");
// Try - catch Block to catch exceptions.
				try 
                                {
					System.out.print("What is your decision this round?");
					decision = read.nextInt();
//Validate the user options and display message for invalid input.
					switch(decision) 
                                        {
					case 1: 
					case 2: 
                                            if(decision == 1||decision == 2)
                                            System.out.println();
                                            String result = pg.playRound(decision-1);
					    System.out.println(result);
					    break;
					default:
                                            i--;
				        System.out.println("This is not a valid decision.");
					}
				}
				catch(InputMismatchException im) {
		        	System.out.println("Input is not a valid integer.");
		        }
				catch(Exception e) {
		        	System.out.println("Error in the opposing strategy: "+ e.toString());
		        }
			}
			
			System.out.println();
			System.out.println(pg.getScores());
			GS = pg.getStats();
			map.put((new Date()).toString(),GS);
			System.out.println();
// Ask opinion of the user if he wants to play another game.
			do 
			{
				System.out.print("do you want to play again? y/n");
				NG = read.next();
				System.out.println();
//Display error message if user input is not y and n.
				if((NG.compareToIgnoreCase("y") != 0) && (NG.compareToIgnoreCase("n") != 0))
				{
					System.out.println("Invalid Input.\nPlease enter y or n");
				}
			} 
                        while((NG.compareToIgnoreCase("y") != 0) && (NG.compareToIgnoreCase("n") != 0));			
		} 
while((NG.compareToIgnoreCase("n") != 0));
		
		Iterator it = map.entrySet().iterator();
// Display the win - loss statistics of all the games the user has played.
		while (it.hasNext()) 
		{
			Map.Entry pairs = (Map.Entry)it.next();
			GS = (GameStat) pairs.getValue();
			System.out.println(pairs.getKey() + ": " + GS.getWinner());
			it.remove(); 
		}
	read.close();
	}
}