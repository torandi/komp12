// EXT:JVM
// EXT:ISC
// EXT:ICG

// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE

class Test6{
    public static void main(String[] a){
	System.out.println(-new B().boo(10, 2, new Fac().ComputeFac(2), false).ilt());
	System.out.println(new A().ilt());

	System.out.println(new A().notEq(2,0));
	System.out.println(1 -new A().notEq(2,2));

	System.out.println(new A().eq(2,2));
	System.out.println(1-new A().eq(2,4));

	System.out.println(new A().less(1,2));
	System.out.println(1-new A().less(2,2));

	System.out.println(new A().lessEq(2,2));
	System.out.println(new A().lessEq(2,2));
	System.out.println(1 - new A().lessEq(3,2));

	System.out.println(new A().more(4,2));
	System.out.println(1-new A().more(2,2));


	System.out.println(new A().moreEq(-4,-4));
	System.out.println(new A().moreEq(10000,2));
	System.out.println(1-new A().moreEq(-10000,2));

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

	public int doStuff(){
		return ((((((((((((((((((((((((((((((((((1))))))))))))))))))))))))))))))))));
	}

}


class B{
	boolean success;
	int ret;
	public B boo(int b, int c, int p, boolean co){
		boolean q;
		success = true;
		q = success;
		return new B();
	}
	public B fooB(){
		return new B();
	}

	public int ilt(){
		ret = 1337;
		return ret;
	}
}

class C extends B{

	public int notEq(int ldc, int nop){
		int L1;
		if(ldc != nop){
			L1 = 1;
		}
		else{
			L1 = 0;
		}

		return L1;
	}

	public int less(int ldc, int nop){
		int L1;
		if(ldc < nop){
			L1 = 1;
		}
		else{
			L1 = 0;
		}

		return L1;
	}

	public int lessEq(int ldc, int nop){
		int L1;
		if(ldc <= nop){
			L1 = 1;
		}
		else{
			L1 = 0;
		}

		return L1;
	}

	public int eq(int ldc, int nop){
		int L1;
		if(ldc == nop){
			L1 = 1;
		}
		else{
			L1 = 0;
		}

		return L1;
	}

	public int more(int ldc, int nop){
		int L1;
		if(ldc > nop){
			L1 = 1;
		}
		else{
			L1 = 0;
		}

		return L1;
	}
	public int moreEq(int ldc, int nop){
		int L1;
		if(ldc >= nop){
			L1 = 1;
		}
		else{
			L1 = 0;
		}

		return L1;
	}


}


class A extends C{
	public int foo(){
		B b;
		b = new B();
		return b.fooB().fooB().fooB().fooB().fooB().fooB().ilt();
	}
}
