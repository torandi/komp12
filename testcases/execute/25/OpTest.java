//EXT:IWE
//EXT:CLE
//EXT:CGT
//EXT:CGE
//EXT:CEQ
//EXT:CNE

class OpTest {
//this should print 1,2,3,4,5,6,7,8,9
	public static void main ( String [ ] id )
	{
		System.out.println( new TestOp().t());
	}

}


class TestOp{
	public int t() {
			int a;
		int b;
		int c;

		a = 2;
		b = 2;
		c = 3;

		if (!(!(!(!(!(false)))))) {
		  System.out.println(1);
		}

		System.out.println(a+a-(2*c)+(a*2));

		if( a <= b ) {
			System.out.println(3);
		}

		if( b < c ) {
			System.out.println(4);
		}

		if( a == b ) {
			System.out.println(5);
		}

		if( c != a ) {
			System.out.println(6);
		}


		if( a >= b ) {
			System.out.println(7);
		}

		if( ((a*2 != 3) && (a <=3)) && (4 == 4) ) {
			System.out.println(8);
		}

		if( (a*a) > 100 ) {
			System.out.println(22);
		} else {
			if( (a*b*c) <= 12 )
				System.out.println(9);
		}

		return 0;
	}
}
