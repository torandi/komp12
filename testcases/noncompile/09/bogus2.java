class Bogus2 {
    public static void main (String[] args){
	System.out.println(new Test().testing());
    }
}

class Test {

    public int testing() {
	int b;
	b=this.bool(4);
	return b;
    }

    public int bool(int a){
	boolean t;
	boolean b;
	t=true;
	b=t+a; //Attempting to sum boolean and integer
	return 1;
    }
}
