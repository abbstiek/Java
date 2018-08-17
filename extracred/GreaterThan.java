public class GreaterThan<T extends Comparable<? super T>>  implements 
CustomTest<T> 
{

    T b;

    @Override
    public boolean test(T t) {
        int comparison = t.compareTo(b);
        if(comparison == 1)
        {
            return true;
        }else{
            return false;
        }
    }

    public GreaterThan(T t)
    {
        b = t;
    }

}
