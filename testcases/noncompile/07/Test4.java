class Test4{
    public static void main(String[] a){
	System.out.println(-new B().boo(10, 2, new Fac().ComputeFac(2), false).ilt());

    }
}

class Fac {
	int shared;
    public int ComputeFac(int num){

		int num_aux ;
		if (num < 1)
			num_aux = 1 ;
		else
			num_aux = num * (this.ComputeFac(num-1)) ;
		return num_aux ;
		}

	public int doStuff{
		return ((((((((((((((((((((((((((((((((((1))))))))))))))))))))))))))))))))));
	}

}


class B {
	boolean success;
	public B boo(int b, int c, int p boolean co){

		boolean q;
		success = true;
		q = success;
		return new B();
	}
	public B foo(){
		return new B();
	}

	public int ilt(){
		return 1337;
	}
}

class A {
	public int foo(){
		B b;
		b = new B;
		return b.foo().foo().foo().foo().foo().foo().ilt();
	}
}

class O {























}
