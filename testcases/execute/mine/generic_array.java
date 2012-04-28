//EXT: GEA
//EXT: VOID
//EXT: EXS

class ArrayTest {

	public static void main(String[] args) {
		System.out.println(new AT().run());
	}
}

class AT {
	public int run() {
		Foo[] bar;
		int i;
		int errors;
		errors = 0;
		i = 0;

		bar = new Foo[10];
		while(i < 10) {	
			bar[i] = new Foo();
			bar[i].init(i, 10*i);
			i = i + 1;
		}
		bar[0].getX();
	
		i = 0;
		while(i < 10) {
			if(!(bar[i].getX() == i && bar[i].getY() == i*10)) {
				errors = errors + 1;
			}
			i = i + 1;
		}

		return errors;
	}
}

class Foo {
	int x;
	int y;

	public void init(int x1, int y1) {
		x = x1;
		y = y1;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
