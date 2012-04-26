// EXT:JVM

/**
 *
 * This program should fail lexical analysis.
 */
class Main {
    public static void main(String[] args) {
	System.out.println(new our_syntaxerror().run());
    }

}
class our_syntaxerror {
    public int run() {
	A a;
	A c;
	B b;
	B d;
	int i;

	a = new A();
	b = new B();
	c = new A();
	d = new B();

	a.b = new B();
	a.b.a = 4;
	a.b.c = 5;
	a.c = 6;
	a.a = 7;

	System.out.println(a.b.a);
	System.out.println(a.b.c);
	System.out.println(a.c);
	System.out.println(a.a);
	System.out.println();

	c.b = new B();
	c.b.d = new int[10];

	i = 0;
	while(i < 10) {
	    c.b.d[i] = i;
	    i = i + 1;
	}

	i = 0;
	while(i < 10) {
	    System.out.println(c.b.d[i]);
	    i = i + 1;
	}

	return 0;
    }
}

class A {
    int a;
    B b;
    int c;
    int[] d;
}

class B {
    int a;
    int c;
    int[] d;
}
