class Test5{
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

	public int doStuff(){
		return 1;
	}

}


class B {
	boolean success;
	public B boo(int b, int c, int p, boolean co){

		int q;
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
		b = new B();
		return b.foo().boo().foo().foo().foo().foo().ilt();
	}
}

class P {
	public int time(){
		int k;
		int d;
		k = 0;
		d = k;
		while(true){
			k = k + (1 + (2 / 2) *3)/4;
			if(k == 100){
				return k;
			}
			else{
				d = d-1;
			}
		}
	}
}
