class PrimeFactorizer {
    public static void main(String[] args) {
	System.out.println(new Factorizer().factor(17*5));
	System.out.println(new Factorizer().factor(3571*3559));
	System.out.println(new Factorizer().factor(10093*8887));
    }
}

class Factorizer {
    public int factor(int n) {
	Utils u;
	int p;
	boolean cond;
	int[] d;
	p = 2;
	u = new Utils();
	cond = true;
	while(cond) {
	    d = u.div(n, p);
	    if(u.eq(d[1], 0)) {
		System.out.println(p);
		System.out.println(d[0]);
		cond = false;
	    } else {
		p = p + 1;
	    }
	}
	return 0;
    }

}

class Utils {
    public int[] div(int d, int n) {
	int res;
	int bres;
	int rem;
	int brem;
	int[] ret;
	res = 0;
	bres = res;
	rem = d;
	brem = rem;
	while((0-1) < rem) {
	    brem = rem;
	    bres = res;
	    rem = rem - n;
	    res = res + 1;
	}
	ret = new int[2];
	ret[0] = bres;
	ret[1] = brem;
	return ret;
    }

    public boolean eq(int a, int b) {
	return (!(a < b)) && (!(b < a));
    }
}
