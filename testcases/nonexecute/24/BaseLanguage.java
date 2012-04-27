// EXT:ABC

/**
 * Generates all primes from 2 to X (= 10000 default). Allocates an int[] of that size as well
 * so be careful with memory.
 *
 */
class BaseLanguage {
	public static void main(String[] args) {
		System.out.println(new ComputePrimes().generatePrimes(10000));
	}
}

class ComputePrimes {

	int stop;
	int[] primes;

	public int[] somePrimes(int max) {
	    int n;
	    int k;

	    n = 5;
	    k = 1;

	    stop = max;
	    primes = new int[stop];

	    //first primes
	    primes[0] =2;
	    primes[1] =3;

	    while (n < stop) {
		if(this.isPrime(n, primes, k)) {
			k = k + 1;
			primes[k] = n;
		}
		else {
			System.out.println(99);
		}
		n = n + 2;
	    }
		return primes;
	}

	public int generatePrimes(int n) {
		int[] ps;
		int k;

		k = n;

		ps = this.somePrimes(n);

		while(0 < n) {
			System.out.println(ps[n - k]);
			k = k - 1;
		}
		return 1;
	}

	public boolean isPrime(int p, int[] primes, int k) {
		boolean ans;
		int tmp;
		tmp = 0;
		ans = true;
		while(0 < k) {

			tmp = this.modulo(p,primes[k]);

			//Since we don't have == operator to check if
			//the remainder is == 0 we do it this way. Either it's
			//greater than 0 or it must be zero
			if(0 < tmp) {
				ans = false;
			}
			else {
				ans = true;
			}
			k = k - 1;
		}
		return true;
	}

  /**
   * Calculates a % b.
   */
  public int modulo(int a, int b) {

	  if(a < b) {
		  a = a;
	  }
	  else {
		  while(b < a + 1) {
			  a = a - b;
		  }
	  }
	  return a;
  }

}
