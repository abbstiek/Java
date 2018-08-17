//Abygail Stiekman 9/14/2016
import java.util.Scanner; 
class DiceStats
{
	public static void main (Strings[] args)
	{
		int numdice, rolls; //variables for dice roll
		int[] rollCount;
		int maxCount; //largest # in rollCount
		DiceList dice;
		Scanner in = new Scanner (System.in);
		
		System.out.print("How many dice will constitute one roll?");
		numdice = in.nextInt();  // takes in amount of dice user 
//wants to roll
		System.out.print("\nHow many rolls?");
		rolls = in.nextInt(); // takes in amt of rolls user 
//would like 
		
		dice = new DiceList(numdice);
		rollCount= new int[numdice*6+1];
		
		System.out.println("Sum\t# of times\tPercentage"); 
//sets up table for output
			
			for (int i = 0; i<rolls; i++)
 //rolls dice, adds 1 to rollcount
			{
				dice.roll();
				int total = dice.getTotal();
				rollCount[total]+=1;
			}
			
			maxCount = 0;
			for (int i = 0; i<rollCount.length; i++)
			{
				if (rollCount[i]>maxCount)
					maxCount = rollCount[i];
			}
			
			System.out.println("Sum	\t# of times\t Percentage");
			for (int i = numdice; i< rollCount.length; i++)
			{
	double percentage = (double)rollCount[i]/rolls;
	System.out.printf("%5d\t%9d\t%7.2f%%", i, rollCount[i], 
percentage*100);
			}
			
			
		}			
}

