class Bogus4 {
    public static void main (String[] args){
	System.out.println(new Test().testing());
    }
}

class Test{

    boolean b;

    public int testing() {
	int c;
	c=this.input(0); //Attempt to call a method that doesn't exist
	c=this.set(true);
	return c;
    }

    public int set(boolean a) {
	b=a;
	return 1;
    }

    public int output(int i){
	System.out.println(b);
	return 1;
    }

}
