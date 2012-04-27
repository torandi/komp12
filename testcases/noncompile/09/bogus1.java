class Bogus1 {

    public static void main (String[] args){
	System.out.println(new Test().testing());
    }
}

class Test {
    public int testing() {
	int b;
	b=this.t(0);
	return b;
    }

    public int t(int i){
	int a;
	int b;
	a=0;
	b=1;
	a=a+b;
	int c;     //Variable declaration following a statement
	System.out.println(a);
	return 1;
    }
}
