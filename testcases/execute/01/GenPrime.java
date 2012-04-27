// EXT:IWE

/**
 * GenPrime.java
 *
 * A prime number generator written in the MiniJava language.
 */

class GenPrime {
    public static void main(String[] args) {
	System.out.println(new PrimeGenerator().calculate(20000));
    }
}

/**
 * Class PrimeGenerator
 *
 * The class which does the actual prime generating job.
 */

class PrimeGenerator {
    boolean inited;
    int[] primes;

    public int numPrimes(int testSize) {
	if (!inited)
	    inited = this.init(testSize);
	else { }

	return primes.length;
    }

    public boolean init(int testSize) {
	int i;
	int j;
	int[] notIsPrime;
	int numPrimes;

	notIsPrime = this.sieve(testSize);

	numPrimes = 0;
	i = 0;
	while (i < notIsPrime.length) {
	    if (this.isEqual(notIsPrime[i], 0)) {
		numPrimes = numPrimes + 1;
	    }

	    i = i + 1;
	}

	primes = new int[numPrimes];

	i = 0;
	j = 0;

	while (i < notIsPrime.length) {
	    if (this.isEqual(notIsPrime[i], 0)) {
		primes[j] = i;
		j = j + 1;
	    } else { }
	    i = i + 1;
	}

	i = this.printPrimes(primes);

	return true;
    }

    public boolean isEqual(int a, int b) {
	return !(a < b) && !(b < a);
    }

    public int printPrimes(int[] primes) {
	int i;

	i = 0;
	while (i < primes.length) {
	    System.out.println(primes[i]);
	    i = i + 1;
	}

	return 0;
    }

    public int[] sieve(int testSize) {
	int[] notIsPrime;
	int prime;
	int i;

	notIsPrime = new int[testSize];
	notIsPrime[0] = 1;
	notIsPrime[1] = 1;

	prime = 2;
	while (prime < notIsPrime.length) {
	    i = 2*prime;
	    while (i < notIsPrime.length) {
		notIsPrime[i] = 1;
		i = i + prime;
	    }

	    i = prime+1;
	    while (i < notIsPrime.length && !this.isEqual(notIsPrime[i], 0))
		i = i + 1;

	    prime = i;
	}

	return notIsPrime;
    }

    public int calculate(int testSize) {
	int i;
	int j;
	boolean t;
	int startNum;

	t = this.init(testSize);
	startNum = 3;

	i = 0;
	while (i < 2000) {
	    j = 1;
	    while (0 < j) {
		startNum = startNum * (startNum-1);
		j = j * 2;
	    }

	    if (startNum < 0)
		startNum = startNum * (1-2);
	    else { }

	    while (1000000 < startNum)
		startNum = startNum - 10000;

	    System.out.println(startNum);
	    t = this.printDivisors(startNum);
	    i = i + 1;
	}

	return i;
    }

    public boolean printDivisors(int num) {
	int i;
	int tmp;
	int prime;

	i = 0;

	while (1 < num && i < primes.length) {
	    prime = primes[i];
	    tmp = this.testDivide(num, prime);

	    if (!this.isEqual(tmp, 0)) {
		num = tmp;
		System.out.println(prime);
	    } else { }

	    i = i + 1;
	}

	return true;
    }

    public int testDivide(int num, int divisor) {
	int quotient;
	int left;

	quotient = 0;

	while (0 < num) {
	    num = num - divisor;
	    quotient = quotient + 1;
	}

	if (num < 0)
	    quotient = 0;
	else { }

	return quotient;
    }
}
