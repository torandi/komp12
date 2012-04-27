//EXT:ISC
//EXT:ICG
//EXT:JVM

class InheritAndVirtual {
//This test is successfull if parsed correctly and prints 12\n13\n
	public static void main ( String [ ] id )
	{
		System.out.println(new TestClass().testIt());
	}

}


class TestClass{

	public int testIt() {
		int r;
		TestClass c;
		c = new TestClass2();
		r = c.createSelf().helloClassWorld(3,9);
		System.out.println(r);
		System.out.println(new TestClass().createSelf().helloClassWorld(3,9));
		return 0;
	}

	public int helloClassWorld(int k, int g) {
		int r;
		r = 1;
		return k+r+g;
	}

	public TestClass createSelf() {
		return new TestClass();
	}
}

class TestClass2 extends TestClass {

	public int helloClassWorld(int k, int g) {
		int r;
		r = 2;
		return k+g;
	}

	public TestClass createSelf() {
		return this;
	}

}
