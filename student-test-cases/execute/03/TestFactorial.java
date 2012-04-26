class TestFactorial {
    public static void main(String[] argv) {

	if (new TestFactorialHelper().run()) {
	} else {
	}

    }
}

class TestFactorialHelper {
    public boolean run() {
	Factorial fac;
	int i;
	int j;
	int recResult;
	int itResult;
	recResult = 0;
	itResult = 0;
	i = 1;
	fac = new Factorial();

	while (i < 14) {
	    j = 0;
	    while (j < 2000000) {
		recResult = fac.recFac(i);
		itResult = fac.itFac(i);
		j = j + 1;
	    }

	    System.out.println(recResult);
	    System.out.println(itResult);

	    i = i + 1;
	}

	return true;
    }
}

class Factorial {

    public int recFac(int n) {
	int ret;
	ret = 1;
	if (0 < n && n < 2) {
	    ret = 1;
	}
	else {
	    ret = n * this.recFac(n-1);
	}
	return ret;
    }
    public int itFac(int n) {
	int res;
	int i;
	res = 1;
	i = 1;
	while (i < n+1) {
	    res = res * i;
	    i = i + 1;
	}
	return res;
    }
}
