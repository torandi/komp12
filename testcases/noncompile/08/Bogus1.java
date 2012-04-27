class Bogus1{
	public static void main(String[] argv){
		int i;
		A a;
		a = new A();
		i = ((((a).a(a)).a(new A().a(new A())).b().a().b()).a().thisA().b()).getLength()[5];
	}
}
class A {
	int a;
	public A a(A a){
		return new A();
	}
	public B b(){
		return new B();
	}
	public A thisA(){
		return this;
	}

}

class B {

	public A a() {
		return new A();
	}

	public int getLength(){
		return new int[17].length;
	}
}
