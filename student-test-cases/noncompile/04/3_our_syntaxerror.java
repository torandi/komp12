// EXT:JVM

/**
 *
 * This program should fail during syntax checking.
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
	(i < i);
	i = a = b.length;
	if(i = i) {
	    b = b;
	} else {
	    c = c;
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
