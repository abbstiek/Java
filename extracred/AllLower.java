//Abygail Stiekman
//12.01.2016
//COP3252 Extra Credit

public class AllLower implements CustomTest<String> {
public boolean test(String string)
	{
		String lowerc = string.toLowerCase();
		return string.equals(lowerc);
	}			
}