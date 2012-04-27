class MainClass {
	public static void main(String[] args) {
		if(new A().doStuff()) {
			System.out.println(1337);
		} else {
			System.out.println(-1337);
		}

		if(!new A().doStuff()) {
			System.out.println(1337);
		} else {
			System.out.println(-1337);
		}

	}
}

class booleanarray {
	int[] array;

	public boolean create(int len)
	{
		array = new int[len];
		return true;
	}
	public int getLength()
	{
		return array.length;
	}
	public boolean set(int pos, boolean value)
	{
		boolean success;
		if( 0 < pos+1 && pos < this.getLength() )
		{
			if(value)
				array[pos] = 1;	// 1 is true
			else
				array[pos] = 0;	// 0 is false

			success = true;
		} else
			success = false;

		return success;
	}
	public boolean get(int pos)
	{
		boolean retval;
		if(array[pos] < 1)	// Then it is zero
			retval = false;
		else
			retval = true;

		return retval;
	}
	public int[] getArray()
	{
		return array;
	}
}

class A {
	public boolean doStuff()
	{
		// Variable declarations
		int i;
		booleanarray ba;
		boolean start;
		boolean junk;

		// Assigning stuff
		i = 0;
		ba = new booleanarray();
		start = true;

		junk = ba.create(10);

		while(i < ba.getLength() && ba.set(i, start))
		{
			start = !start;
			i = i + 1;
		}

		// Should be true
		junk = ba.get(2);
		// Will become false
		if( ba.set(2, false) )
			junk = ba.get(2);			// Should be false
		else
			System.out.println(43110);				// Should not happen

		return ba.get(8);
	}
}
