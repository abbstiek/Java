//****Abygail Stiekman****//
//******11.25.2016********//
//*****COP3252 Proj6******//

import java.io.*;
import java.awt.*;
import java.util.*;
 

public class StudentHandler implements java.io.Serializable{
	//static String first, last, filename; //used to hold students info
	//filename is used for user inserted filename with serialization
	static int count = 0; //used to count through students	//holds students grades
	public static ArrayList<Student> arrlist;
	static String filename, first, last;
	static double exgrade = 0;
	static double hwgrade = 0;
	 
	 
	 
	StudentHandler ()
	{//constructor   
		arrlist = new ArrayList<Student>();
	}
	
	 
	public static void main(String[] args){
	StudentHandler fun = new StudentHandler();
	int input = 0;  //for menu choice
	Scanner scan = new Scanner(System.in);
	while (input != 6)
	{
		//prints menu options as long as input != quit
		System.out.println();
		System.out.println("1: Print out all loaded students");
		System.out.println("2: Add student");
		System.out.println("3: Clear students");
		System.out.println("4: Save students to file");
		System.out.println("5: Load students from file");
		System.out.println("6: Quit");
		System.out.println();
		//user inputs choice
		System.out.print("Please input the number of your choice: ");
		input = scan.nextInt();
		//takes in user input
		
		// takes users input and performs menu option
		
			if (input == 1){
				fun.printAllStudents();
			} 
			else if (input == 2){
				fun.addStudent(scan);
			}
			else if (input == 3){
				fun.clearAllStudents();
			}
			else if (input == 4){
				fun.saveStudents(scan);
			}
			else if(input == 5){
				fun.loadStudents(scan);
			}
			else if(input == 6){
				System.out.println("Goodbye!");
				System.exit(0);
			}
			else 
				System.out.println("Invalid input. try again");
			
		}
	}
	//serializing data
	 /* public static void writeObject(String fname, ArrayList<Student> student) {
		
		try{//creates FOS and OOS
		FileOutputStream fileout = new FileOutputStream(fname);
		ObjectOutputStream objout = new ObjectOutputStream(fileout);
		
		for (int i = 0; i<student.size(); i++)
		{ //goes through and writes member data
			objout.writeObject(student.get(i));
		} //closes streams
		fileout.close();
		objout.close();
		} catch (IOException ex)
		{
			System.out.println(ex);
		}
	  }
	//deserializing data
    public static void readObject(String filename){
		try{
			ObjectInputStream objin = new ObjectInputStream(new FileInputStream(filename));
			while (true)
			{
				arrlist.add((Student)objin.readObject());
				Student.incrementStudentCount();
			}
		}catch (ClassNotFoundException cnfe)
		{
			System.out.println("Student Class could not be found");
			cnfe.printStackTrace();
			return;
		}catch (IOException io)
		{
			io.printStackTrace();
			return;
		}/*catch (EOFException ex)
		{
			return;
		}*/

/*This method will ask the user for a filename to save under
	and save the entire collection of students held in this StudentHandler as serialized objects*/
	void saveStudents(Scanner s)/* throws IOException*/{	
				//File fileName;
					try{
					System.out.print("Please input the filename to save as: ");
					//fileName = new File(s.nextLine());
					filename = s.nextLine();
					s.nextLine();
					
					/*
					if (fileName.exists()){
						System.out.println("File already exists. Please try again.");
					}*/
					FileOutputStream fileOutStr = new FileOutputStream(filename);		 
					//Create object output stream and write object
					ObjectOutputStream objOutStr = new ObjectOutputStream(fileOutStr);
					objOutStr.writeObject(arrlist);
					objOutStr.close();
					fileOutStr.close();
					//objOutStr.writeObject(fileName);
					//writeObject(fileName, arrlist);
				//	fileName.createNewFile();
					}catch (Exception ex){
						//ex.printStackTrace();
						System.out.println("Invalid input value, please try again");
					}/*catch (FileNotFoundException ex)
					{
						ex.printStackTrace();
						System.out.println("Invalid file, please try again");
					} */
	}
	
	
	/*This method will first clear all students out of the StudentHandler,
	then ask the user for a filename to load and load an entire collection of students into the
	StudentHandler*/
		void loadStudents(Scanner s){
		clearAllStudents();
		try {
				System.out.print("Please input the filename to load from: ");
				filename = s.nextLine();
				s.nextLine();
				
				//readObject(fileName);
				FileInputStream fileInStr = new FileInputStream(filename);		 
				//Create object output stream and write object
				ObjectInputStream objInStr = new ObjectInputStream(fileInStr); 
				//arrlist=(ArrayList<Student>)objInStr.readObject();
				objInStr.close();
				fileInStr.close();
				//objInStr.readObject(filename);
		/*}catch (ClassNotFoundException ex){
			ex.printStackTrace();
			System.out.println("Student class not found, please try again");
		*/}catch (IOException ex){
			ex.printStackTrace();
			System.out.println("Invalid input value, please try again");
		}
	}
	
	/*– This method will prompt the user for the various pieces of a Student object 
	(name, grades, etc.) and create a new object and add it to the StudentHandler*/
	void addStudent(Scanner s){
				ArrayList HWgrades = new ArrayList();
				ArrayList examGrades = new ArrayList();
				
				System.out.println("Please input a first name: ");
					first = s.next();
					
					s.nextLine();
		
				System.out.println("Please input a last name: ");
					last = s.next();
				
				Student stud = new Student(first, last);
				
				System.out.print("Please input student homework grades one at a time (negative value to finish): ");
					hwgrade = s.nextDouble();
					
					if (hwgrade >= 0)
						stud.addHW(hwgrade);
					
					while(hwgrade >= 0)
					{
							System.out.print("Please add another homework grade (negative value to finish):");
							hwgrade = s.nextDouble();
							if (hwgrade >= 0)
								stud.addHW(hwgrade);
					}
		
		
						System.out.print("Please input student test grades one at a time (negative value to finish): ");
						exgrade = s.nextDouble();
					if (exgrade >= 0)
						stud.addTest(exgrade);
					
					while (exgrade>=0)
					{
						System.out.print("Please add another test grade (negative value to finish):");
							exgrade = s.nextDouble();
						if (exgrade >= 0)
							stud.addTest(exgrade);
					}
					
					stud.calcGrade();
					arrlist.add(stud);
					
					HWgrades.clear();
					examGrades.clear();
					System.out.println();
					//double grade = Student.calcGrade();
				//	StudentHandler student = new StudentHandler ();
					
				/*}catch (Exception ex)
				{
					ex.printStackTrace();
					System.out.println("Invalid input value, please try again");
				}/*catch (FileNotFoundException ex)
				{
					ex.printStackTrace();
					System.out.println("Invalid file, please try again");
				}*/
				
				
			/*	//arrlist.add(new Student(last, first, grade));
				{
					System.out.println("Invalid input, please try inputting the student again");
				}
			*/	
}
	
	
	
	/*This method will print all of the students, sorted by name (last
	name first, if those are the same, then sort by first name) as well as the count of the number of
	student records (you are REQUIRED to use the static totalStudents member data for this
	count)*/
	void printAllStudents() {
		if (arrlist.size()== 0)
		{// checks to make sure there are students in arrlist, if not 
			//prints error message
			System.out.println("\nThere aren't any students in your list!");
			System.out.println("Try again.\n\n");
			System.out.println();
			return;
		}
		
		PrintNames(arrlist);
		for (int i = 0; i < arrlist.size(); i++)
		{ //increments through arraylist and pulls students information
			System.out.println("First name: " + arrlist.get(i).getFirst());
			System.out.println("Last name: " + arrlist.get(i).getLast());
			System.out.println("Final Grade: " + arrlist.get(i).getGrade());	
			System.out.println();
		}//gets amt of students that have been added to arrlist
		System.out.println("Printed " + arrlist.get(0).getNumStudents() + " Student Records");
	}
	void clearAllStudents() {
		arrlist.clear();
	}
	
	void PrintNames(ArrayList<Student> arg)
	{
		  Collections.sort(arg, new Comparator<Student>() {

            public int compare(Student a1, Student a2) {
                int comp = a1.getLast().compareToIgnoreCase(a2.getFirst());
                if (comp != 0)
                    return comp;
                return a1.getFirst().compareToIgnoreCase(a2.getFirst());
            }
        });
	}


}