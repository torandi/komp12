class Error5 {
    public static void main(String[] args) {}
}

class A {
    int b;
    boolean b; // Error, can't have a variable with the same name

    public int a() {
        int b;
        boolean b; // Error, can't have a variable with the same name
        return 0;
    }

    public boolean a() { // Error, no method overloading in MiniJava
        return false;
    }
}

class A { // Error, a class with the same name is already defined
}
