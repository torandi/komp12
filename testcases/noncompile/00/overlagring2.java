//EXT: ISC
//EXT: ICG

class Main
{
    public static void main(String[] s) 
    {
	System.out.println(1);
    }
}


class A extends B {
	int y;
	public int f(int y) {
		return x; 
	} 
}
class B extends C { }
class C extends D { }
class D { 
	int x;
	public boolean f(int y) {
		return true; 
	}
}
class E extends B {
	public int f(int y) {
		return 0-7;
	} 
}

