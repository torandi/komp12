class Main
{
    public static void main(String[] s) 
    {
	System.out.println(new A().go().geta().me(2).getv()[1]);
    }
}


class A { 
    A a;
    int[] v;
    public int[] getv() { return v;  }
    public A me(int n) { return this; }
    public A geta()  { return a;  }
    public A go() { a = new A(); v = new int[10];  return this; }

    public boolean complex(int a) 
    {
	int x;	

	if (a < this.getv()[a] && ! (a < 0)) x=1;
	else x=0-1;	
	return !!!(!(a+a*a+x < 123*3-1) && v && !v);
	
    }

}


