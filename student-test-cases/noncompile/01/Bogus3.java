/**
 * Bogus3.java
 *
 * A test case that tries to create a var with a reserved name and therefore
 * should fail.
 */

class Bogus3 {
    public static void main(String[] args) {
        if (false) {
        } else {
            System.out.println(new A().test());
        }
    }
}

class A {
    public int test() {
        boolean boolean;
        boolean = true;
        return 0;
    }
}
