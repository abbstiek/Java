// Abygail Stiekman 9/25/2016

public class IntegerSet 
 {
     private static final int max_size = 101; // will contain numbers 0-100
     
     private final boolean[] boolean_array = new boolean[max_size];
  
     public static IntegerSet union( IntegerSet iSet1, IntegerSet iSet2)
     {
         IntegerSet union = new IntegerSet();
         System.arraycopy(iSet1.boolean_array, 0, union.boolean_array, 0, max_size);
         for (int i = 0; i < max_size; i++) //runs through entire array 
         {
             if (iSet2.boolean_array[i])
             {
                 union.boolean_array[i] = true;
             }
         }
         return union;
     }
     
     public static IntegerSet intersection(IntegerSet iSet1, IntegerSet iSet2)
     {
         IntegerSet intersection = new IntegerSet();
         for (int i = 0; i < max_size; i++)
         {
             intersection.boolean_array[i] = iSet1.boolean_array[i] && iSet2.boolean_array[ i ];
         }
         return intersection;
     }
     
     public void insertElement(int data) {boolean_array[data] = true;}
     
     public void deleteElement(int data) {boolean_array [data] = false;}
     
     public String toString()
     { //returns a string containing he set elements 
	 //as a list of numbers in ascending order
         boolean isEmpty = true;
         StringBuilder string = new StringBuilder();
         string.append("{ ");
         for (int i = 0; i < max_size; i++)
         { //only inclues elements present in the set 
             if ( boolean_array[i] )
             {
                 isEmpty = false;
                 string.append(i).append(' ');
             }
         }//if set is empty, prints "---"
         return isEmpty ? "---" : string.append( '}').toString();
     }
     
     public boolean isEqualto(IntegerSet iSet)
     {
         for ( int i = 0; i < max_size; i++ )
         {
             if (boolean_array[i] != iSet.boolean_array[i] )
                 return false;//if two sets are not equal
         }
         return true;//if two sets are equal 
     }


	
 } 