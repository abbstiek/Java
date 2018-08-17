//Abygail Stiekman
//12.01.2016
//COP3252 Extra Credit

import java.util.Arrays;


public class ArrayTester<T> {

    private T[] myArray;
    CustomTest<T> test;

    public ArrayTester(T[] tArray, CustomTest<T> cTest)
    {
        myArray = tArray.clone();
        test = cTest;
        Arrays.sort(myArray);
    }

    public int countIfValid()
    {
        int count = 0;

        for(int i = 0; i < myArray.length; i++)
        {
            if(test.test(myArray[i]))
                count++;
        }
        return count;
    }

	    public void printIfValid()
    {
        for(int i = 0; i < myArray.length; i++)
        {
            if(test.test(myArray[i]))
                System.out.print(myArray[i] + " ");
        }
        System.out.println();
    }

}