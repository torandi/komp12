/**
 * Bogus test program 2 with syntax errors.
 */
class Test2 {
    // Missing MainClass because this is not a valid main method declaration;
    // it should have been named "main" and not "mainFail".
    public static void mainFail(String[] args) {
    }
}

class Test2A {

    boolean b;
    int i;
    int[] p;

    public boolean getB() {
	return this.b;
    }

    public int getI() {
	return this.i;
	// A MethodDecl must end with "return Exp;" and thus the following line
	// is unexpected.
	i = 17 + 4711;
    }

    public int[] getP() {
	return this.p;
    }

    // This is not a valid FormalList, since ", " is not a complete Formalrest.
    public boolean setB(boolean newValue, ) {
	return true;
    }

    // Variable declarations (VarDecl*) after method declarations (MethodDecl*)
    // are not allowed.
    boolean c;
    int j;
    int[] q;

    // Statements must be wrapped in methods, so this is an error.
    System.out.println(j);

}
