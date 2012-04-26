class Bogus5 {
    public static void main (String[] args){
	System.out.println(new Test().testing());
    }
}

class Test {

    public int testing() {
	boolean b;
	b=this.paren();
	return 1;
    }

    public boolean paren ( ) {
	boolean c = ( ( 5 < 4 ) && ( 105 < 4 && 70 < 83 && (4711 <
	89089089 ) ) ; //parenthesis mismatch
	return c ;
    }
}
