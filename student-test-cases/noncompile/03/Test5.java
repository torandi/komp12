/**
 * Bogus test program 5 with type errors.
 */
class Test5 {
    public static void main(String[] id) {
    }
}

class Test5A {

    boolean x;
    int y;
    int[] z;

    public int setX(int[] illegalX) {
	// Trying to assign int[] to a boolean variable.
	x = illegalX;

	return 0;
    }

    public boolean getX() {
	// Returning int when boolean was expected.
	return this.getY();
    }

    public int getY() {
	// Returning int[] when int was expected.
	return this.getZ();
    }

    public int[] getZ() {
	return z;
    }

}

class Test5B {

    boolean x;

    public boolean testScope() {
	int x;
	boolean x;
	{
	    x = 10;
	    {
		x = true;
	    }
	}
	// Returning an integer, but a boolean was expected.
	return x;
    }

    public boolean testCall(int a, boolean b, int[] c) {
	return a < c[0] && b;
    }

    public int testCall() {
	return 1337;
    }

    public int invokeCallTest() {
	// Trying to assign boolean to an int variable.
	int x;
	boolean y;
	Test5C doesNotExist;

	x = this.testCall(10, true, new int[10]);

	// Trying to assign int to a boolean variable.
	y = this.testCall();

	// Returning an object of type Test5A instead of an int.
	return new Test5A();
    }

}
