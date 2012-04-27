// EXT:!CLE

class Main {
    public static void main(String[] a){
	System.out.println(new Modulo().doit());
    }
}

class Modulo {

    public int doit(){
		int a;
		int[] b;

		a = 3;
		b = new int[a*3-3*a+3];
		b[0] = 1;
		b[1] = 2;
		b[2] = 3;

		if(b[0] < 32) {
			a = new A().getarray()[a];
		}
		else {
			if(b[0] < 31) {
				a = new A().getarray()[new A().getarray()[b[0]]];
			}
			else {
				if(b[0] <= 30) { //no <= defined in base language.
					System.out.println(1);
				}
				else {
					a = 0;
				}
			}
		}

		return a;
    }
}

class A {
	public int[] getarray() {
		int[] a;
		a = new int[5];
		a[0] = 1;
		a[1] = 2;
		a[2] = 3;
		a[3] = 4;
		a[4] = 5;

		return a;
	}
}
