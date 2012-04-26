// EXT:JVM

/**
 *
 * This program should fail during typechecking.
 */
class Main {
    public static void main(String[] args) {
        System.out.println(new our_overloading().run());
    }

}
class our_overloading {
    boolean a;
    boolean b;
    boolean c;

    public int run() {
        int retv;
        int a;
        int b;
        a = 0;
        b = 1;
        retv = this.check(a,b);
        return retv;
    }

    public int check(int a, int b) {
        int c;
        c = a < b && a + b;
        return a && b;
    }

}
