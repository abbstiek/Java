// Abygail Stiekman 9/13/2016 
import java.util.Scanner; 
class Pi
{
	public static void main (String[] args)
	{	
	int terms = 0; // takes in number of terms user would like to see
	int count = 0;; //uses a double for percentages
	double pi =4.0;
	
	Scanner in = new Scanner(System.in); //used for i/o
	
	System.out.print("Compute to how many terms of the series? ");
	terms = in.nextInt();
	
		if (terms > 0)
		{
		    System.out.print("terms\t\t");
			System.out.print("PI approximation\n");
			for (int i = 0; i < terms+1; i++)
			{
				if (count% 2 == 0)
				{
				pi = pi - (4.0/(i+2.0) + (4.0/(i+4.0)));
				System.out.printf("%d\t\t", terms); 
//outputs term 
				System.out.printf("%f\n", pi);
				}
				else 
				{
					pi +=4.0/(double)i;
					System.out.printf("%d\t\t", terms);
					System.out.printf("%f\n", pi);
				}
				count ++;
			}
		}
		else  //error message
		{
			System.out.print("Error. please enter more terms");
 // user needs to enter more than 1 pi term 
		}
	}
}

