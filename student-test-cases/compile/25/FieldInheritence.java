//EXT:ISC
//EXT:JVM

class FieldInheritence{
	public static void main(String[] args) {
		System.out.println(new Field().test());
	}

}

class Field2 extends Field {
	public int getI() {
		return i;
	}

}

class Field {

	int i;
	Field f;
	int[] a;

	public int test() {
		Field2 f;
		int r;
		f = new Field2();

		r =f.setI(101);
		r = f.getI();
		return 0;
	}

	public int setI(int v) {
		i = v;
		return 0;
	}

}
