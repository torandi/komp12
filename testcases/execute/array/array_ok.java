class ArrayTest {
	public static void main(String[] args) {
		System.out.println(new AT().run());
	}
}

class AT {
	public int run() {
		int[] a;
		long[] b;
		boolean[] c;
		int i;

		a = new int[5];
		b = new long[5];
		c = new boolean[5];

		i = 0;

		while(c[4] != true) {
			a[i] = i;
			//b[i] = 5L*2L;
			c[i] = true;
			System.out.println(i);
			i = i + 1;
		}
		return 0;
	}
}
