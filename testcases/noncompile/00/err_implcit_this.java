/* Error: implicit this in method call
 */
class Main
{
    public static void main(String[] s) 
    {
	System.out.println(1);
    }
}


class A  
{
    public int f(int n) 
    {
	int r;
	if (1<n) 
	    r = n*f(n-1);
	else r=1;
	
	return r;
    }
    
}


