/**
 * Sort X (= 10 default) integers.
 *
 *
 */
class InsertionSort {
	public static void main(String[] args) {
		System.out.println(new Sorter().sort(10));
	}
}

class Sorter {
  public int sort(int n) {
      int N;
      int[] data;

	  int i;
	  int j;

	  int temp;

	  N = n;
	  data = new int[N];

	  i = 0;
	  j = 0;
	  temp = 0;

	  //Generate a list with N, N-1,....0 as numbers
	  while(0 < N) {
		  data[n-N] = N;
		  N = N - 1;
	  }

	  //Print out before
	  N = n;
      while (0 < N) {
	System.out.println(data[n-N]);
		N = N - 1;
	  }

	  N = n;
      // insertion sort
      while ( i < N ) {
		  j = i;
	 while ( 0 < j) {
	    if (data[j] < data[j-1]) {
	       temp = data[j];
	       data[j] = data[j-1];
	       data[j-1] = temp;
	    }
	    else {
		System.out.println(999);
	    }
			j = j - 1;
	 }
		 i = i + 1;
      }

	  //Print out after
	  N = n;
      while (0 < N) {
	System.out.println(data[n-N]);
		N = N - 1;
	  }
      return 0;
   }
}
