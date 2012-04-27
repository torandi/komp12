//Creates an array and quicksorts it

class Good1 {

    public static void main(String[] args){
	System.out.println(new Good1Sorter().testing());
    }
}

class Good1Sorter {
    int refr;
    int lb;
    int hb;
    int tmp;
    int dump;

    public int testing() {
	int[] a;
	int waste;

	a=new int[5];

	a[0]=7;
	a[1]=2;
	a[2]=1;
	a[3]=3;
	a[4]=10;

	waste = this.printArray(a);
	waste = this.quickSort(a,0,4);
	waste = this.printArray(a);
	waste = this.quickSort(a,0,a.length - 1);
	waste = this.printArray(a);
	return waste;
    }

    public int printArray(int[] a) {
	int i;
	i = 0;
	while(i < a.length ) {
	    System.out.println(a[i]);
	    i = i + 1;
	}
	return i;
    }

    public int quickSort(int[] array, int start, int end){
	int ret;
	ret = 0;
	if ( (end-start) < 1 ){
	    ret = 0;
	}

	else {
	    refr=array[start];
	    lb=start;
	    hb=end+1;

	    while(lb+1 < hb){
		if( (array[lb+1]-refr) < 0 ){
		    lb=lb+1;
		}
		else {
		    hb=hb-1;
		    tmp=array[hb];
		    array[hb]=array[lb+1];
		    array[lb+1]=tmp;
		}
	    }
	    array[start]=array[lb];
	    array[lb]=refr;
	    dump = this.quickSort(array,start,lb-1);
	    dump = this.quickSort(array,lb+1,end);
	    ret = 0;
	}
	return ret;
    }
}
