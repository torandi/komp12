class Error3 {
    public static void main(String[] args) {
        System.out.println(new A().a());
    }
}

class A {
    A a;
    public A a(A a) {
        A a; // Error, can't declare local variable with same name as param
        return new A();
    }
}
