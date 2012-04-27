class PQTest {
    public static void main(String[] args) {
	System.out.println(new PQ().test());
    }
}

class PQ {

    int dummy;

    int[] pq; // store elements at index 1 to N
    int N;    // number of elements

    // set initial size of heap to hold size elements
    public int init(int size) {
	pq = new int[size + 1];
	N = 0;

	return 0;
    }

    public boolean isEmpty() {
	return !(0 < N);
    }

    public int size() {
	return N;
    }

    public int arrayCopy(int[] src, int[] dst, int n) {
	int i;

	i = 0;
	while (i < n) {
	    dst[i] = src[i];
	    i = i + 1;
	}

	return 0;
    }

    public int insert(int item) {
	int[] newPq;

	// double size of array if necessary
	if (pq.length - 2 < N) {
	    newPq = new int[2 * (N + 1)];
	    dummy = this.arrayCopy(pq, newPq, N + 1);
	    pq = newPq;
	} else {
	}

	N = N + 1;
	pq[N] = item;
	dummy = this.swim(N);

	return 0;
    }

    public int delMax() {
	dummy = this.exch(1, N);
	dummy = this.sink(1, N - 1);
	N = N - 1;
	return pq[N + 1];
    }

    public int swim(int k) {
	while (1 < k && this.less(this.div(k, 2), k)) {
	    dummy = this.exch(k, this.div(k, 2));
	    k = this.div(k, 2);
	}

	return 0;
    }

    public int sink(int k, int N) {
	boolean done;
	int j;

	done = false;

	while (!done && 2 * k < N + 1) {
	    j = 2 * k;

	    if (j < N && this.less(j, j + 1)) {
		j = j + 1;
	    } else {
	    }

	    if (!this.less(k, j)) {
		done = true;
	    } else {
		dummy = this.exch(k, j);
		k = j;
	    }
	}

	return 0;
    }

    public boolean less(int i, int j) {
	return pq[i] < pq[j];
    }

    public int exch(int i, int j) {
	int tmp;
	tmp = pq[i];
	pq[i] = pq[j];
	pq[j] = tmp;
	return 0;
    }

    public int div(int nominator, int denominator) {
	int changeSign;
	int current;

	changeSign = 1;

	if (nominator < 0) {
	    changeSign = changeSign * (0-1);
	    nominator = 0 - nominator;
	} else {
	}

	if (denominator < 0) {
	    changeSign = changeSign * (0-1);
	    denominator = 0 - denominator;
	} else {
	}


	current = 1;
	while (current * denominator - 1 < nominator)
	    current = current + 1;

	return changeSign * (current - 1);
    }

    public int test() {
	int c;
	int i;

	dummy = this.init(10);

	c = 0;
	while (c < 3) {

	    i = 0;
	    while (i < 10000) {
		dummy = this.insert(i * (c + 17) * 3 - i);
		i = i + 1;
	    }

	    while (!this.isEmpty())
		System.out.println(this.delMax());

	    c = c + 1;
	}

	return 0 - 1;
    }
}
