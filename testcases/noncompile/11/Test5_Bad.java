class Test5{
    public static void main(String[] a){
	System.out.println(new AddArray().init());
    }
}

class AddArray{


	int[] array_1;
	int[] array_2;

	int integer;

	public int init() {
		int a;
		array_1[0] = 0;
		array_2[1] = 1;
		array_2[0] = 2;
		array_2[1] = 3;
		a = this.add();

		return 0;
	}


	public int add() {
		int i;
		i = 0;
		while(i < 1){
			integer = array_1[i] + array[i];
			i = i + 1;
		}
		return integer;
	}

}
