// Test 1.1: type error: return value of method test in class Test is of type int, expected boolean (line 13).

class Test1_1 {
	public static void main(String[] args) {}
}

class Test {

	int[] a;

	public boolean test(int i) {
		a = new int[i];
		return a[i];
	}
}
