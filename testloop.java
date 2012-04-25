class TestLoop  {
	public static void main (String[] args) {
		System.out.println(new TL().run());
	}
}

class TL {
	public int run() {
		int i;
		i = 0;
		while(i < 10) {
			System.out.println(i);
			i = i+1;
		}
		return 0;
	}
}
