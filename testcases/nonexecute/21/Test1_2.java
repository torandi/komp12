// Test 1.2: call on void target runtime exception

class Test1_2 {
	public static void main(String[] args) {
		System.out.println(new Test().test());
	}
}

class Test {

	Test t;

	public int test() {
		return t.test();
	}
}
