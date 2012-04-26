//EXT:IWE
//EXT:CLE
//EXT:CGT
//EXT:CGE
//EXT:CEQ
//EXT:CNE

class Sort{
	public static void main(String[] args) {

		System.out.println(new Tester().sort());
	}

}

class Tester {
	public int sort() {
		int[] a;
		int r;
		int i;
		QuickSort q;

		a = new int[15];

		a[0] = 4;
		a[1] = 6;
		a[2] = 1;
		a[3] = 15;
		a[4] = 14;
		a[5] = 10;
		a[6] = 11;
		a[7] = 12;
		a[8] = 2;
		a[9] = 3;
		a[10] = 9;
		a[11] = 5;
		a[12] = 7;
		a[13] = 8;
		a[14] = 13;



		q = new QuickSort();
		r = q.quicksort(a,0, a.length - 1);
		i=0;

		//will print numbers in order, if implemented correct!
		while(i < a.length) {
			System.out.println(a[i]);
			i = i + 1;
		}
		return 0;
	}

}

class QuickSort
{




    public int swap( int [ ] a, int index1, int index2 )
    {
	int tmp;
		tmp = a[ index1 ];
	a[ index1 ] = a[ index2 ];
	a[ index2 ] = tmp;
		return 0;
    }



    public int quicksort( int [ ] a, int left, int right )
    {
    boolean sorting;
    int r;
	int pivoindex;
	int pivot;
    int i;
	int j;

    r = 0;
    sorting = true;

    if(left==right)
    {
	//1 element array... do nothing
    } else {

    //2 element array... just check order
    if(right-left == 1)
	{
		if(a[left] > a[right]) {
			r = this.swap(a,left,right);
		}
	} else {

			//3 elements or more... GO!


			//static pivot
			i = 0;
			r = (right-left);
			//this is divison without division operator =)
			while( r > 0 ) {
				r = r -2;
				i = i +1;
			}


			pivoindex= left+i;

			pivot = a[pivoindex];



		//Place pivo rightmost
		r =this.swap(a,pivoindex, right);


	    // Begin partitioning
			i = left;
			j = right-1;

	    while(  sorting )
	    {
		while( a[ i ] < pivot) { i=i+1; }
		while( (a[ j ] > pivot) && (j>=1) ) { j=j-1; }

		if( i < j ) {
			r= this.swap( a, i, j );
				} else {
					sorting = false;
				}
	  }
		r = this.swap( a, i, right);   // Restore pivot

		if( (i > 1) && (left<i) ) {
				r =this.quicksort( a, left, i - 1 );    // Sort small elements
		}
			if( (i+1) < right) {
				r =this.quicksort( a, i + 1, right );   // Sort large elements
			}
	}
	 }


	 return r;
    }




}
