// EXT:!IWE

class Main {
    public static void main(String[] a){
	System.out.println(new Modulo().doit());
    }
}

class Modulo {

    public int doit(){
		int a;
		int[] b;

		a= 3;
		b = new int[a*3-3*a+10];

		if(a < 5) {
			System.out.println(1);
		} //else is missing

		return a+3;
    }
}
