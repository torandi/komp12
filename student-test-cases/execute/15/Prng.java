class Prng {
    public static void main(String[] args) {
	System.out.println(new Lehmer().test(999));
	System.out.println(new FourTap().test(999));
    }
}


class Lehmer {

    int dummy;
    int a;
    int b;
    int c;
    int d;
    int z;

    public int init() {
	a = 2007;
	b = 4194;
	c = 205603;
	return b;
    }

    public int lehmer(int s) {
	z = this.mod(a + b * z, c);
	return this.mod(z, s);
    }

    public int mod(int a, int b) {
	while (b < a+1)
	    a = a - b;
	return a;
    }

    public int test(int n) {
	z = this.init();

	while (0 < n) {
	    System.out.println(this.lehmer(1000));
	    n = n - 1;
	}

	return this.lehmer(1000);
    }
}



class FourTap {

    int dummy;
    int index;
    int a;
    int b;
    int c;
    int d;
    int[] seq;

    public int init(int size) {
	Lehmer bootstrap;

	a = 2;
	b = 3;
	c = 5;
	d = 7;
	seq = new int[size + d + 1];

	bootstrap = new Lehmer();
	dummy = bootstrap.init();

	dummy = d;
	while (0 < dummy + 1) {
	    seq[dummy] = bootstrap.lehmer(1000);
	    dummy = dummy - 1;
	}

	return d;
    }


    // For positive integers only.
    public int div(int nominator, int denominator) {
	int current;

	current = 1;
	while (current * denominator - 1 < nominator)
	    current = current + 1;

	return current - 1;
    }


    public int mod(int a, int b) {
	while (b < a+1)
	    a = a - b;
	return a;
    }


    public int lsb(int i) {
	return this.mod(i, 2);
    }


    // For positive values only.
    public int xor(int i, int j) {

	int result;
	int pow;

	result = 0;
	pow = 1;

	while (0 < i + j) {
	    result = pow * this.lsb(this.lsb(i) + this.lsb(j)) + result;
	    pow = pow * 2;
	    i = this.div(i, 2);
	    j = this.div(j, 2);
	}

	return result;
    }


    public int xor4(int i, int j, int k, int l) {
	return this.xor(i, this.xor(j, this.xor(k, l)));
    }

    public int fourTap() {
	seq[index] = this.xor4(seq[index-a], seq[index-b], seq[index-c], seq[index-d]);
	index = index + 1;
	return seq[index-1];
    }

    public int test(int n) {
	index = this.init(n);

	while (0 < n) {
	    System.out.println(this.fourTap());
	    n = n - 1;
	}

	return this.fourTap();
    }
}
