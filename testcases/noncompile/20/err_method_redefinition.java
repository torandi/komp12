class MainClass{
    public static void main(String[] args){
	System.out.println(1);
    }
}

class TestClass {
	public int test(int a, boolean b) {return 1;}
	public int test(boolean b, int a) {return 1;} //ok
	public boolean test(int a, boolean b) {return true;} //error
}
