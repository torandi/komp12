class MainClass{
    public static void main(String[] args){
	System.out.println((new B()).createB());
    }
}

class A {}
class B {
	public B createB() {
		return new A();		//error
	}
}
