class Bogus3 {
    public static void main (String[] args){
	System.out.println(new Test().testing());
    }
}

class Test {

    public int testing() {
	int b;
	b=this.div(3,2);
	return b;
    }

    public int div(int a, int b){
	System.out.println(a/b); //division operator is not allowed in grammar
	return 1;
    }
}
