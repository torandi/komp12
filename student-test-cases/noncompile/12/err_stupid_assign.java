class MainClass {
	public static void main(String[] args) {
		System.out.println(new Klass1().doStuff());
	}
}
class Klass1 {
	public Klass2 get() {
		return new Klass2();
	}
	public int doStuff() {
		new Klass2() = this.get().get();
		return 120;
	}
}
class Klass2 {
	public Klass2 get() {
		return new Klass2();
	}
}

