//Abygail Stiekman 9/14/2016
import java.util.Scanner; 
import java.util.Random;
class DiceStats
{
	public static Random rand= new Random();
    public static int rollDice() 
	{
        int dice;
        dice=(int)(Math.random()*6)+1;   
        return dice;              // returns the sum dice rolls
	}
	public static void main (String[] args)
	{	
		Scanner in = new Scanner (System.in);//used for input
		
		double percentage=0.0;
		
		System.out.print("How many dice will constitute one roll? ");
		int numdice = in.nextInt();  // takes in amount of dice user wants to roll
		System.out.print("\nHow many rolls? ");
		int rolls = in.nextInt(); // takes in amt of rolls user would like 
	
		int [] frequency = new int[6*numdice+1];
		
		System.out.println("Sum\t# of times\tPercentage\n"); 
//sets up table for output
			
			int sum=0;
			for (int i = 0; i<numdice; i++) //rolls dice, 
//adds 1 to frequency
			{
				sum+=rollDice();
				++frequency[sum];
			}
			
			int maxCount = 0;
			for (int i = 0; i<frequency.length; i++)
			{
				if (frequency[i]>maxCount)
					maxCount = frequency[i];
			}
			
			for (int i = numdice; i<frequency.length; i++)
			{
		percentage = (double)frequency[i]/rolls;
		System.out.printf("%d\t%d\t%f%%\n", i, frequency[i], percentage*100);
			}
			
			
		}			
	}

