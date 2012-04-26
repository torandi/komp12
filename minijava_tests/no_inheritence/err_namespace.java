/* parameter and local has same name
 */
class Main
{
    public static void main(String[] s) 
    {
	System.out.println(1);
    }
}


class x 
{
    x x;
    public x x(x y) 
    {
	x y;
	return this;
    }
}


