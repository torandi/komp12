class T2
{
	public static void main(String[] args)
	{
		System.out.println((new Caller()).call());
	}
}

class Caller
{
	public int call()
	{
		int i;
		Utils u;
		u = new Utils();
		i = 1;

		System.out.println(u.sub(6, 3));

		while (i < 100000)
		{
			System.out.println(u.sum(u.range(0, i * 2)));
			i = i * 10;
		}

		if (!!true && 1 < 0)
		{
			System.out.println(1);
		}
		else
		{
			System.out.println(0);
		}

		return 0;
	}
}

class Abc
{
	int a;
	int b;

	public int add(int x, int y)
	{
		int result;
		a = x;
		b = y;
		result = a + b;
		return result;
	}
}

class Utils
{

	int a;
	int b;

	public int add(int x, int y)
	{
		int result;
		a = x;
		b = y;
		while (0 < b)
		{
			b = b - 1;
			a = a + 1;
		}
		result = a + b;
		return result;
	}

	public int sub(int x, int y)
	{
		int result;
		a = x;
		b = y;
		while (0 < b)
		{
			b = b - 1;
			a = a - 1;
		}
		result = a - b;
		return result;
	}

	public int sum(int[] a)
	{
		int len;
		int i;
		int s;
		len = a.length;

		i = 0;
		s = 0;
		while (i < len)
		{
			s = this.add(s, a[i]);
			i = i + 1;
		}

		return s;
	}

	public int[] range(int start, int stop)
	{
		int[] a;
		int len;
		int i;
		len = stop - start;
		a = new int[len];
		i = 0;
		while (i < len)
		{
			a[i] = i + start;
			i = i + 1;
		}

		return a;
	}
}
