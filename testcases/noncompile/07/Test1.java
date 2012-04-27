class Test1{
	public static void main(String[] args){
		System.out.println(new makeList().f(10));
	}
}

class makeList{
	int[] a;

	public int f(int x){
		a = new int[20];
		a[0]=20;
		a[1]=19;
		a[2]=18;
		a[3]=17;
		a[4]=16;
		a[5]=15;
		a[6]=14;
		a[7]=13;
		a[8]=12;
		a[9]=11;
		a[10]=10;
		a[11]=9;
		a[12]=8;
		a[13]=7;
		a[14]=6;
		a[15]=5;
		a[16]=4;
		a[17]=3;
		a[18]=2;
		a[19]=1;

		this.print();
		this.sort();
		this.print();

		return 1;
	}

	public int sort(){
		int x;
		int y;
		int t;

		x=0;
		y=0;
		t=0;

		while(x<a.length){
			while(y<a.length){
				if(a[x]<a[y]){
					t=a[x];
					a[x]=a[y];
					a[y]=t;
				}
				else{
					t=0;
				}
				y=y+1;
			}
			y=0;
			x=x+1;
		}

		int d;
		d=1;
		return d;
	}

	public int print(){
		int counter;

		counter=0;

		while(counter<a.length){
			System.out.println(a[counter]);
			counter=counter+1;
		}
		return 1;
	}
}
