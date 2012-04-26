class Test3{
    public static void main(String[] a){
	System.out.println(new Fibonacci().fibonacci(5));
    }
}
// This class contains the array of cards with different values.
// We have two methods to initialize search the array to find max value.
class Fibonacci{

	public int fibonacci(int n) {

		int f;


		if (n < 3) {
			f = 1;
		}

		else {
			f = this.fibonacci(n-1) + this.fibonacci(n-2);
		}
		return f;
	}

}
