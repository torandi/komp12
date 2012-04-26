/**
 * Test program for field and local variable types.
 */
class TestSymbolTable {
    public static void main(String[] args) {
        if (new TestSymbolTableMain().runTest()) {
        } else {
        }
    }
}

class TestSymbolTableMain {
    public boolean runTest() {
        TestSymbolTableHelper tester;
        tester = new TestSymbolTableHelper();

        System.out.println(tester.testLocal());
        System.out.println(tester.setField());
        System.out.println(tester.testField());

        return true;
    }
}

class TestSymbolTableHelper {
    int b;

    public int testLocal() {
        int ret;
        boolean b;
        b = false;
        if (b) {
            // Should not happen!
            ret = 0;
        } else {
            // This is correct.
            ret = 1;
        }
        return ret;
    }

    public int setField() {
        b = 1;
        return b;
    }

    public int testField() {
        return b;
    }
}
