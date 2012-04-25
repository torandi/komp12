class ArrayTest {
	public static void main (String[] args) {
		{
			ArrayTestCls atc;
			atc = new ArrayTestCls();
			System.out.println(atc.test_arrays());
		}
	}
}

class ArrayTestCls {
	public int test_arrays() {
		int[] a;
		a = new int[5];
		a[0] = 1;
		a[1] = 2;
		a[2] = 3;
		return a.length+a[0]+a[6];
	}
}
