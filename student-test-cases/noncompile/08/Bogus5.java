class Bogus5 {
	public static void main(String[] argv){
		int i;
		int pi;
		int pie;
		boolean c;
		pi = 314;
		pie = 5-7;
		c = !!!!!false;
		i = new A().a(pi,pie,c,!!c,new A().b(new A()),new int[4711]);
		i = new A().a(i,i,true,false,new A(),new int[17],new int[35505]);
	}
}

class A{
	public int a(int a,int b, boolean c, boolean d, A e, int[] f){
		return 5;
	}

	public A b(A a){
		return this;
	}

}
