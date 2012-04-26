class Bubble{
	public static void main(String[] argv){
		if(!new bool().bool(false))
			System.out.println(new DD().DD());
		else
			System.out.println(new DD().DD()+-2147483648);
	}
}

class bool {
	public boolean bool(boolean bool){
		return bool;
	}
}

class DD {
	public int DD(){
		int[] array;
		int k;
		int i;
		BSort bsort;
		array = new int[10];
		k = 5;
		i = 0;
		while(i < array.length){
			array[i] = k;
			k = k-1;
			i = i+1;
		}
		bsort = new A().a(new A(),false).b(new A(),false).bsort();
		array = new BSort().rSort(bsort.sort(new BSort().rSort(bsort.sort(array))));

		i = 0;
		while(i < array.length && array[i] < 1000){
			System.out.println(array[i]);
			i = i + 1;
		}
		return 0;
	}
}

class BSort{
	public int[] sort(int[] array){
		int i;
		boolean done;
		int tmp;
		done = false;
		while(!done){
			done = true;
			i = 0;
			while(i < array.length-1 && array[i+1] < 1000){
				if(array[i] < array[i+1]){
				}else{
					tmp = array[i];
					array[i] = array[i+1];
					array[i+1] = tmp;
					done = false;
				}
				i = i+1;
			}
		}
		return array;
	}

	public int[] rSort(int[] array){
		int i;
		int tmp;
		array = this.sort(array);
		i = 0;
		while(i*2 < array.length){
			tmp = array[i];
			array[i] = array[array.length-1-i];
			array[array.length-1-i] = tmp;
			i = i+1;
		}
		return array;
	}
}

class A{
	public A a(A a, boolean i){
		if(i)
			a = new A();
		else
			a = this;
		return a;
	}


	public B b(A a, boolean i){
		B b;
		b = new B();
		if(i)
			b = new B();
		else
			b = b;
		return b;
	}
}

class B{
boolean a;
	public A a(){
		return new A().a(new A(),a).b(new A(),false).a();
	}

	public BSort bsort(){
		return new BSort();
	}
}
