class F5
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
		Mergesort m;
		int[] a;

		a = new int[20];
		m = new Mergesort();

		a[0] = 11;
		a[1] = 52;
		a[2] = 39;
		a[3] = 11;
		a[4] = 66;
		a[5] = 64;
		a[6] = 60;
		a[7] = 5;
		a[8] = 53;
		a[9] = 53;
		a[10] = 17;
		a[11] = 93;
		a[12] = 59;
		a[13] = 51;
		a[14] = 62;
		a[15] = 12;
		a[16] = 64;
		a[17] = 11;
		a[18] = 67;
		a[19] = 63;

		a = m.mergesort(a);

		System.out.println(m.avg(1, 2));
		System.out.println(m.avg(1, 3));
		System.out.println(m.avg(1, 4));

		System.out.println(0);
		return 0;
	}
}

class Mergesort
{

	int[] a;

	public int[] mergesort(int[] ar)
	{
		int tmp;
		a = ar;
		tmp = this.sort(0, a.length);
		tmp = this.print(a);
		return a;
	}

	public int sort(int s, int t,)
	{
		int len;
		int av;
		int tmp;
		len = t - s;

		if (len < 2)
		{
			len = len;
		}
		else
		{
			av = this.avg(s, t);
			tmp = this.sort(s, av);
			tmp = this.sort(av, t);
			tmp = this.merge(s, av, t);
		}

		return 0;

	}

	public int merge(int s, int m, int t)
	{
		int tmp;
		int i;
		int j;
		int c;
		int[] ta;

		ta = new int[t - s];
		c = 0;
		i = s;
		j = m;


		while (i < m && j < t)
		{

			if (a[i] < a[j])
			{
				ta[c] = a[i];
				i = i + 1;
				c = c + 1;
			}
			else
			{
				ta[c] = a[j];
				j = j + 1;
				c = c + 1;
			}
		}

		if (i < m)
		{
			while (c < t - s)
			{
				ta[c] = a[i];
				i = i + 1;
				c = c + 1;
			}
		}
		else
		{
			while (c < t - s)
			{
				ta[c] = a[j];
				j = j + 1;
				c = c + 1;
			}
		}

		c = 0;
		while (c < t - s)
		{
			a[s + c] = ta[c];
			c = c + 1;
		}

		return 0;
	}

	public int avg(int a, int b)
	{
		while (a + 1 < b)
		{
			a = a + 1;
			b = b - 1;
		}

		return b;
	}

	public int print(int[] a)
	{
		int i;
		i = 0;
		while (i < a.length)
		{
			System.out.println(a[i]);
			i = i + 1;
		}

		return 0;
	}
}
