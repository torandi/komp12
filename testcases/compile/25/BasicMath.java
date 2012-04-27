// EXT:CEQ

class BasicMath{
	public static void main(String[] args) {
		System.out.println(new Quad().calc());
	}

}

class Quad {

	public int calc() {
		int[] a;
		int i;
		DivChecker d;
		 Quad q;
		 q = new Quad();
		d = new DivChecker();
		a = new int[10];


		while(i < a.length) {

			if(  d.divisableBy(i, 2) )  {
				a[i] = d.moduluBy(i,5);
			}
			else {
				a[i] = (0-1);
			}
			i = i + 1;

		}
		i = 0;

		while(i < a.length) {
			if( 0 < a[i] ) {
				a[i] = q.quad(a[i]);
			} else {
				a[i] = q.pow(a[i],2);
			}
			i= i +1;
		}
		return 0;
	}

	public int quad(int i) {
		return i*i;
	}

	public int pow(int i,int p) {
		while( 0<p) {
			i = i *i;
			p = p -1;
		}
		return i;
	}
}

class DivChecker {
	public boolean divisableBy(int i, int div) {
		while( 0 < i) {
			i = i - div;
		}

		return (i == 0);
	}
	public int moduluBy(int i, int div) {
		boolean divide;
		divide = true;
		while( divide ) {
			if( (0-1) < (i - div)   ) {
				i = i - div;
			} else {
				divide = false;
			}
		}

		return i;
	}
}
