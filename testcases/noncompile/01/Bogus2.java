/**
 * Bogus2.java
 *
 * A test case that tries to create two classes with the same name.
 */

class Bogus2 {
    public static void main(String[] args) {
        System.out.println(new Bogus2().test());
    }
}

class Bogus2 {
    public int test() {
        return 0;
    }
}
