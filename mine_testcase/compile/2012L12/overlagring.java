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
	public B f(int y) {
		return new B(); 
	} 
}
class B extends C { }
class C extends D { }

class D { 
	int x;
	public D f(int y) {
		return new D(); 
	}
}
class E extends B {
	public E f(int y) {
		return new E();
	} 
}

