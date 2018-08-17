//  Abygail Stiekman 9/13/2016 
import java.util.Scanner;


class Reverse
{
   public static void main(String args[])
   {
      int num=1; //set both values to 0
      int rev = 0;
	  
	
      System.out.print("Please enter a long integer (0 to quit): "); 
	  //if user inputs 0 it will leave program
  
      Scanner in = new Scanner(System.in); //used for i/o
      num = in.nextInt(); // assigns input to variable num
     
     while (num!=0) //cancels if user inputs 0
	{
		rev = rev * 10; //formula used to reverse the string
        rev = rev + num % 10;
        num = num / 10;
	}
	System.out.println("\nThe number reversed is: "+rev); //prints reversed num
		/*if (num ==0) //CANT GET THIS TO WORK!!!!!!!
			System.out.println("Goodbye!");*/
	}
}
