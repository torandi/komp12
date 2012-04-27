//EXT:CGT

class Garbage {
	public static void main ( String [ ] id )
	{
	}
}


class Quad {

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



class Field {

	int i;
	Field f;
	int[] a;

	public int getI() {
		return i;
	}

	public int setI(int v) {
		i = v;
		return 0;
	}

	public int init() {
		f = new Field();
		i = f.init();
		a = new int[10];
		return 0;
	}
	public int hej(int j) {
		j = f.setI(101);
		j = f.getI();
		return j;
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
	 ´

	 return r;
    }




}
