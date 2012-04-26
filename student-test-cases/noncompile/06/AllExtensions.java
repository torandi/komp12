// EXT:CEQ
// EXT:NBD
// EXT:ISC
// EXT:CGE
// EXT:CLE
// EXT:CGT
// EXT:CNE
// EXT:BDJ

class Main {
	public static void main(String[] args){
		{
			AnotherClass ac;
			boolean dummy;
			int derp;
			ac = new AnotherClass();
			derp = ac.setup(100);
			derp = ac.testOperators(5);

			dummy = ac.sideEffectFunction(5) == 101 || ac.sideEffectFunction(5) == 4711;
			if(ac.getSideEffect(5) == 101)
				System.out.println(101);
			else
				System.out.println(-101);

			derp = ac.setup(200);
			dummy = ac.sideEffectFunction(5) == 200 && ac.sideEffectFunction(5) == 4711;
			if(ac.getSideEffect(5) == 201)
				System.out.println(201);
			else
				System.out.println(-201);

			{
			AnotherClass ac;
			int derp;
			ac = new AnotherClass();
			derp = ac.setup(200);

			if(ac.getSideEffect(5) == 200)
				System.out.println(300);
			else
				System.out.println(-300);
			}

		}
	}
}

class AnotherClass extends KlassSomInteFinns {
	int sideEffect;

	public int getSideEffect(int dummy){
		return sideEffect;
	}

	public int setup(int value){
		sideEffect = value;
		return 4711;
	}

	public int sideEffectFunction(int dummy){
		sideEffect = sideEffect + 1;
		return sideEffect;
	}

	public int testOperators(int value){
		int ret;
		ret = 0;

		if(ret == 0)
			System.out.println(1);
		else
			System.out.println(-1);

		if(ret >= 0)
			System.out.println(2);
		else
			System.out.println(-2);

		if(ret <= 0)
			System.out.println(3);
		else
			System.out.println(-3);

		if(ret > 0)
			System.out.println(-4);
		else
			System.out.println(4);

		if(ret < 0)
			System.out.println(-5);
		else
			System.out.println(5);

		if(ret != 0)
			System.out.println(-6);
		else
			System.out.println(6);

		if(ret + 1 == 1)
			System.out.println(7);
		else
			System.out.println(-7);

		if(ret - 1 == -1)
			System.out.println(8);
		else
			System.out.println(-8);

		if(4*2 == 8)
			System.out.println(9);
		else
			System.out.println(-9);

		if(false || true)
			System.out.println(10);
		else
			System.out.println(-10);

		if(true && false)
			System.out.println(-11);
		else
			System.out.println(11);

		return 4711;
	}
}
