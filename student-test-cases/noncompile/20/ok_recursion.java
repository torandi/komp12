// EXT:CEQ
// EXT:CGE
// EXT:CGT

class Recursion {
	public static void main(String[] args) {
		System.out.println(new SerieCreator().getFibSeries(new Recursive(), 10));
	}
}

class Recursive {
	public int fib(int n) {
		int res;
		if(n < 2) res = n;
		else res = this.fib(n - 1) + this.fib(n - 2);
		return res;
	}

	public int square(int n) {
		int res;
		if(n < 2) res = n + 1;
		else res = 2*this.square(n-1);
		return res;
	}

	public int bincof(int n, int k) {
		int res;
		if(k == 0 && n >= 0) res = 1;
		else if(n == 0 && k > 0) res = 0;
		else res = this.bincof(n - 1, k - 1) + this.bincof(n - 1, k);
		return res;
	}
}

class SerieCreator {
	public int[] getFibSeries(Recursive rec, int n) {
		int[] res;
		res = new int[n];
		for(int i=0; i<n; i++) {
			res[i] = rec.fib(i);
		}
		return res;
	}

	public int[] getSquareSeries(Recursive rec, int n) {
		int[] res;
		res = new int[n];
		for(int i=0; i<n; i++) {
			res[i] = rec.square(i);
		}
		return res;
	}

	public int[] getBinCofSeries(Recursive rec, int n) {
		int[] res;
		res = new int[n+1];
		for(int i=0; i<n+1; i++) {
			res[i] = rec.bincof(n, i);
		}
		return res;
	}
}
