/**
 * Bogus test program 3 with syntax errors.
 */
class Test3 {
    public static void main(String[] args) {
	// Main method is not allowed to introduce new variables.
	int illegalVarDecl = 10;
    }
}

class Test3A {

    boolean x;
    int[] y;

    public boolean getX() {
	y = new int[10];

	// Unexpected AND operator.
	x = 10 < && true;

	// Missing semi-colon.
	y[0] = 4711

	return x;
    }

}
