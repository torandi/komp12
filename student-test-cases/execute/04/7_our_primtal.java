// EXT:JVM

/**
 *
 * This program calculates a number of primes and adds them to an array.
 * The return code of the program is printed after the list of primes.
 * Expected output:
 *
 */
class Main {
    public static void main(String[] args) {
	System.out.println(new our_primtal().run());
    }

}
class our_primtal {
    int[] primes;
    int count;
    int number;

    public int print() {
	int i;
	i = 0;
	while(i < number) {
	    System.out.println(primes[i]);
	    i = i + 1;
	}
	return 0;
    }

    public int run() {
	int test;
	boolean retbool;
	int retint;
	test = 3;
	number = 100;
	primes = new int[number];
	count = 0;

	retbool = this.add_prime(2);

	while(count < number) {
	    if(this.is_prime(test)) {
		retbool = this.add_prime(test);
	    } else {

	    }
	    test = test + 1;
	}
	retint = this.print();
	return 0;
    }

    public boolean is_prime(int x) {
	int i;
	boolean retbool;
	retbool = true;
	i = 0;
	while(i < count && retbool) {
	    if(this.mod(x, primes[i])) {
		retbool = false;
	    } else {
		retbool = true;
	    }
	    i = i + 1;
	}
	return retbool;
    }

    public boolean add_prime(int p) {
	boolean retbool;
	if(number < count) {
	    retbool = false;
	} else {
	    primes[count] = p;
	    count = count + 1;
	    retbool = true;
	}
	return retbool;
    }

    public boolean mod(int x, int m) {
	int rest;
	boolean retbool;
	rest = x;
	while(!(rest < m)) {
	    rest = rest - m;
	}
	if(rest < 1 && !(rest < 0)) {
	    retbool = true;
	} else {
	    retbool = false;
	}
	return retbool;
    }
}
