/*/////////////////////////////////////////////////////////////////////////////
// Bogus1.java
//
// This test case should be properly parsed, but should fail in the typechecker
// due to end() returning int.
/////////////////////////////////////////////////////////////////////////////*/

class Bogus1 {
    public static void main(String[] args) {
	System.out.println(new Boguser().init().test1(1).test2(2).test3(3).
		test4(4).test5(5).foo(6).test7(7, 0, 1, 2, 3, 4, 5, 6, 7).end().fail());
    }
}

class Boguser {
    int foo;

    public Boguser init() {
	foo = -1;
	return this;
    }

    public int increaseFoo() {
	foo = foo + 1;
	return 0;
    }

    public Boguser test1(int foo) {
	Boguser obj;
	foo = foo;

	if (foo < 0)
	    System.out.println(foo);
	else {
	    foo = this.increaseFoo();
	}

	return this;
    }

    public Boguser test2(int bar) {
	if (!(!false && !(true && true && !(false)) && !(1 < 0 && 5 < 5)))
	    foo = foo + 1;
	else
	    System.out.println(0);

	return this;
    }

    public Boguser test3(int a) {
	foo = foo + 1;
	return this;
    }

    public Boguser test4(int bar) {
	int[] array;

	array = new int[1];
	array[0] = array[0];
	foo = bar + foo + array.length - array.length*4 + array[0];

	return this;
    }

    public Boguser test5(int bar) {
	if
	(
	    bar < bar
	) foo = 2-1;
	else foo = foo + 1;
	return this;
    }

    public Boguser foo(int foo) {
	if (foo < foo)
	    foo = foo;
	else if (0 < foo)
	    foo = foo + 1;
	else
	    foo = this.increaseFoo();

	return this;
    }

    public Boguser test7(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9) {
	int q;
	q = a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8 + a9;
	foo = foo + 1;
	return this;
    }

    public int end() {
	return foo;
    }

    public int fail() {
	return 0;
    }
}
