//Abygail Stiekman 9/23/2016

import java.util.Scanner;


class Fraction{
	private int num = 0;
	private int den = 1;
	
	  public Fraction()
  {
  }

  public Fraction(int n, int d)
  {
    if (set(n,d)==false)
	set(0,1);
  }

  public boolean set(int n, int d)
  {
    if (d > 0)
    {
	num = n;
	den = d;
	return true;
    }
    else
	return false;
  }
  
  public String toString()
  {
    return (num + "/" + den);
  }

  public int getNumerator()
  {
    return num;
  }

  public int getDenominator()
  {
    return den;
  }

  public double decimal()
  {
    return (double)num / den;
  }
	public int GCD()
	{
		int gcd = 0;
		for (int i = 1; i <= num && i <= den; i++)
		{
			if (num % i ==0 && den % i ==0)
				gcd = i;
		}
		return gcd;
		
	}
	public Fraction simplify ()
	{
		Fraction f1 = new Fraction();
		int gcd = GCD();
		
		f1.den = den / gcd;
		f1. num = num / gcd;
		return f1;
		
		/*
			int ayy=0;
			int start=num;
			Boolean nope = false;
			
			if (num<0)
			{
				num=(num*-1);
				nope=true;
			}
			for (int i=1; i<=num && i<= den; i++)
			{
				if (num%i==0 && den%1 == 0)
					ayy=i;
			}
			if (ayy>0)
			{	f1.num=num/ayy;
				f1.den=den/ayy;
			}
			if (num==0)
				f1.den =1;
			if (nope==true)
				f1.num=(num*-1);
			return f1;*/
			/*
			Fraction f = new Fraction();
			int k, i; 
			if(f.num > f.den) 
				k = f.den; 
			else 
				k = f.num; 
			for (i = k; i >= 0; i--)
			{ if(f.num% k == 0 && f.den% k == 0)
			{ f.num = f.num / k; f.den = f.den / k; } 
		} return f;*/
	}
	
	
	public Fraction add(Fraction f)
	{
		Fraction f1 = new Fraction(); //f holds the results
		Fraction f2 = new Fraction();
			//load result Fraction with sum of adjusted numerators
		f.num=(f1.num*f2.den)+(f2.num*f1.den);
		//load result with the common denominator
		f.den= f1.den * f2.den;
		
		return  f;
	}
	
	public Fraction subtract(Fraction f)
	{
		Fraction f1 = new Fraction(); //f holds the results
		Fraction f2 = new Fraction();
			//load result Fraction with subtraction of adjusted nums
		f.num=(f1.num * f2.den)-(f2.num*f1.den);
		f.den=(f1.den*f2.den);
		
		return f;
	}
	
	public Fraction multiply (Fraction f)
	{
		Fraction f1 = new Fraction();
		Fraction f2 = new Fraction();//placeholder, f holds results
		//multiplies given num by other given num
		f.num=f1.num * f2.num;
		f.den=f1.den * f2.den; //mults both dens together
		return f;
	}
	
	public Fraction divide (Fraction f)
	{
		Fraction f1 = new Fraction();
		Fraction f2 = new Fraction();
		
		f.num=f1.num*f2.den;
		f.den=f1.den*f2.num;
		
		return f;
	}
}