class Error4 {
    public static void main(String[] args) {
    }
}

class A {
    public int[] ar() {
        int[] ar;
        ar = new int[5];
        return ar;
    }
}

class B {
    public int a() {
        return new A().ar()[new int[3]]; // array index has to be an integer
    }
}
