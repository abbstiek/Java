//Abygail Stiekman
//12.01.2016
//COP3252 Extra Credit


public class IsEven <T extends Number> implements CustomTest<T>{
//<T extends Number> 
    @Override
    public boolean test(T t) {
		return t.intValue() % 2 ==0;
    }
}
