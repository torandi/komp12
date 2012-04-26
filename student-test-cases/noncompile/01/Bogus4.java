/*
 * Bogus4.java
 *
 * A test case that should not be parsed correctly since the
 * main class is not declared first
 */

class A {
    public int test() {
        boolean bool;
        bool = true;
        return 0;
    }
}

class Bogus4 {
    public static void main(String[] args) {
        if (false) {
            System.out.println(666);
        } else {
            System.out.println(new A().test());
        }
    }
}
