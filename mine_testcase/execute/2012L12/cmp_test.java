//EXT: CLE
//EXT: CGT
//EXT: CGE
//EXT: CEQ
//EXT: CNE

class CmpTest {
	public static void main(String[] a) {
		System.out.println(new CT().run());
	}
}

class CT {
	int a; 

	public int run() {
		int errors;
		errors = 0;

		if(10 == 10) {
		} else {
			System.out.println(2);
			errors = errors + 1;
		}
		if(11 == 10) {
			System.out.println(3);
			errors = errors + 1;
		} else {
		}
		if(10 != 10) {
			System.out.println(4);
			errors = errors + 1;
		} else {
		}
		if(11 != 10) {
		} else {
			System.out.println(5);
			errors = errors + 1;
		}


		a = 0;
		if(false && this.setA()) {
		} else {
		}

		if(a == 1) {
			System.out.println(6);
			errors = errors + 1;
		} else {
		}

		if(a == 1) {
			System.out.println(7);
			errors = errors + 1;
		} else {
		}

		if(this.equal(1, 0)) {
			System.out.println(8);
			errors = errors + 1;
		} else {
		}

		if(this.equal(1, 1)) {
		} else {
			System.out.println(9);
			errors = errors + 1;
		}

		if(this.equal(0, 1)) {
			System.out.println(10);
			errors = errors + 1;
		} else {
		}

		return errors; 
	}

	public boolean setA() {
		a = 1;
		return true;
	}

	public boolean equal(int a, int b) {
		return !(a < b) && !(b < a);
	}
}
