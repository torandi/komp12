class ThisInMain {
    public static void main(String[] args) {
	System.out.println(new OtherClass().method(this));
    }
}

class OtherClass {
    public int method(MainClass obj) {
	return 0;
    }
}
