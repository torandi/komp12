class Test7{
    public static void main(String[] a){
	System.out.println(new methodA().init(3));
    }
}

class methodA{

	int i;

	public int init(int a) {

		i = methodC.init(a);

		return i;
	}
}

class methodB {

	int i;

	public int init(int a) {

		i = i + a;

		return i;
	}
}
