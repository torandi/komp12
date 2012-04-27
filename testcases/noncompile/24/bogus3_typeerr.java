class Main {
    public static void main(String[] a){
	System.out.println(new Modulo().doit());
    }
}

class Modulo {

    public int doit(){
		int a;
		int b;

		a = 3;
		b = new int[a*3-3*a+10]; //int[] to int assignment

		if(a < 5) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

		return a+3;
    }
}
