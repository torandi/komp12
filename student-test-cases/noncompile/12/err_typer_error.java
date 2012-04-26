class MainClass {
	public static void main(String[] args) {
		System.out.println(new Klass1().main(1, 2, 3, new Klass2().getTrue(), 5, 6,
				new Klass1().main(0, 0, 0, false, 0, 0, true)));
	}
}
class Klass1 {
	public Klass2 get() {
		return new Klass2();
	}
	public boolean main(int i, int j, int l, boolean m, int o, int p, boolean q) {
		Klass2 k;
		k = this.get();
		return false;
	}
}
class Klass2 {
	// hihi
	public int getTrue() {
		return 1;
	}
}

