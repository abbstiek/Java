//Abygail Stiekman
//COP3252 HW#4

import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.nio.file.*;
import java.lang.*;

public class FileViewer{
String directory;

public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		if ((args.length == 0) || args[0].equals("-i"))
		{
			if(args.length == 0)
				{
				File dir = new File(".");
				String[] filesindir = dir.list();
					System.out.println ("Size	Filename");
					
					for (int i = 0; i<filesindir.length;i++){
						System.out.println (filesindir[i].length() +"\t" + filesindir[i]);
				}
			}
			if (args.length>=2)
			{
				File file = new File(args[1]);
				boolean isFile = file.isFile();
				boolean isDirectory = file.isDirectory();
				boolean Executable = file.canExecute();
				if (isFile== true)
				{
				System.out.println("File Path: " + file.getAbsolutePath());
				System.out.println("Is executable? " + Executable);
				System.out.println("Size: " + file.length() + " bytes");
				
				SimpleDateFormat x = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				System.out.println("Last Modified Date: " + x.format(file.lastModified()));
				}
				else if (isDirectory == true)
				{
					File dir = new File(args[1]);
					String[] filesindir = dir.list();
					System.out.println ("Size	Filename");
					for (int i = 0; i<filesindir.length;i++){
						if (file.isFile()){
						System.out.println (file.length() +"\t" + filesindir[i]);
					}
					}
				}
				else 
					System.out.println("Invalid parameter");
			}
			
		}
		else if (args[0].equals("-v"))
		{
		if (args.length == 2){
			File file = new File(args[1]);
			boolean isFile = file.isFile();
			if (isFile== true){
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[1]));
			String line;
			while ((line = br.readLine()) != null) {
				StringBuilder sb = new StringBuilder();
				sb.append(line);
				if (line != null) {
					sb.append(System.lineSeparator());
				}
					System.out.print(sb);
			}
			}catch(FileNotFoundException br){
				System.out.println(br);
			}catch(IOException br){
				System.out.println(br);
			}
		}
		else 
			System.out.println("File does not exist");
		}
		else {
			System.err.println("Invalid command line, exactly one argument required");
			System.exit(1);
			}
		}
	
		else if (args[0].equals("-c"))
		{
			Path file1 = Paths.get(args[1]);
			Path file2 = Paths.get(args[2]);
			try {
				Files.copy(file1, file2);
				System.out.println("File " + file1 + " was copied to file " + file2 + " successfully");
			} catch(FileAlreadyExistsException e) {
				System.out.println("File " + file2 + " already exists.");
			} catch (IOException e) {
				System.out.println("Error");
				e.printStackTrace();
			}
		}
		else if (args[0].equals("-d"))
		{
			try{
			{
			  FileInputStream f1 = new FileInputStream(args[1]);
			  FileInputStream f2 = new FileInputStream(args[2]);
			   
			   
			  DataInputStream in1= new DataInputStream(f1);
			  DataInputStream in2= new DataInputStream(f2);
			   
			  BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
			  BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
			   
			  String strLine1, strLine2;
			Path file1 = Paths.get(args[1]);
			Path file2 = Paths.get(args[2]);
			  if((strLine1 = br1.readLine()) != null && (strLine2 = br2.readLine()) != null){
				  if(strLine1.equals(strLine2))
					  System.out.println("The two files "+file1+" and "+file2+" are the same.");
				else
					System.out.println("The two files "+file1+" and "+file2+" are not the same.");
					   
				}
			}
			}catch(IOException e){
				System.out.println("Error.");
			}
		}
		else
		{
			System.out.println("Usage: java -jar hw4.jar [-i[<file>|<directory>]|-v <file>|-c <sourceFile> <destFile>]");
			System.exit(0);
		}
}
}