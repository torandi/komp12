/* kolla typfel vid objekt-tilldelning
 *
 */
class Main
{
    public static void main(String[] s) 
    {
	System.out.println(1);
    }
}


class A { 
}

class B 
{
    public int f1(B a) 
    {
	B b;
	b = a;	
	return 0;
    }
    
    public int f2(B b) 
    {
	B a;
        int i;
	i = this.f1(b);
	a = new B();	
	return 0;
    }
    
}


