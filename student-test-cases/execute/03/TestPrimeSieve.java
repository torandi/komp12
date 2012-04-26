class TestPrimeSieve {
    public static void main(String[] argv) {
	System.out.println((new PrimeSieveHelper()).initPrimeSieve());
    }
}

class PrimeSieveHelper  {
    int slask;
    public int initPrimeSieve() {
	int i;
	PrimeSieve ps;
	ps = new PrimeSieve();
	slask = ps.run();
	i = 0;
	while (i < ps.getSize()) {
	    if (ps.isPrime(i)) {
		System.out.println(i);
	    }
	    else {}
	    i = i + 1;
	}
	return 1;
    }
}

class PrimeSieve {
    int[] is_prime;
    int size;
    boolean slask;
    public int run() {
	size = 1000000;
	is_prime = new int[size]; // Based on that java sets all ints to 0
	is_prime[0] = 1;
	is_prime[1] = 1;
	slask = this.sieve();
	return 1;
    }

    public boolean sieve() {
	int i;
	int j;
	i = 2;
	while (i < size) {
	    if (is_prime[i] < 1 && (0-1) < is_prime[i]) {
		j = i * 2;
		while (j < size) {
		    is_prime[j] = 1;
		    j = j + i;
		}
	    }
	    else {}
	    i = i + 1;
	}
	return true;
    }

    public boolean isPrime(int i) {
	return is_prime[i] < 1;
    }

    public int getSize() {
	return size;
    }
}
