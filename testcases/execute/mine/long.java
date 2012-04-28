class LongTest {
	public static void main(String[] args) {
		long l;
		long l2;
		long l3;
		l = 1099511627776L;
		l2 = 1099511627834L;
		l3 = l2 - l;

		System.out.println(l);

		if(l3 == 58L) {
			System.out.println(0);
		} else {
			if(l3 < 58L) {
				System.out.println(0-1);
			} else {
				System.out.println(1);
			}
		}
	}
}
