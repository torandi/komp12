// EXT:BMO

class TestCalls {

    public static void main(String[] args) {
        System.out.println((new TestCallsA()).test());
    }

}

class TestCallsA {

    TestCallsB testCallsB;

    public int test() {
        int test1;
        int test2;
        test1 = this.test1(100000, 200000, 300000);
        test2 = 999999;

        testCallsB = new TestCallsB();
        System.out.println(testCallsB.test());

        return test1;
    }

    public int test1(int a, int b, int c) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        return 1;
    }

    public int test1(int a, int b) {
        System.out.println(a);
        System.out.println(b);
        return 2;
    }

}

class TestCallsB {

    public int test() {
        return 1337;
    }

}
