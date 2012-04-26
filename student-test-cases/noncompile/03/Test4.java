/**
 * Bogus test program 4 with duplicate variable definitions.
 */
class Test4 {
    public static void main(String[] id) {
    }
}

class Test4A {

    int a;
    // a has already been defined.
    int a;

    int b;
    // b has already been defined.
    boolean b;
    // b has already been defined.
    int[] b;

    public Test4B getTest4B() {
        int a;
        int b;
        // b has already been defined.
        boolean b;
        // b has already been defined.
        int[] b;

        return new Test4B();
    }

}

class Test4B {
}
