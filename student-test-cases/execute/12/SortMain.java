class SortMain{
/*
Out should be:
0
0
on success.
*/
 /*( (new Sort().init()+1)+2147483647+1)*/
public static void main(String[] f){
	System.out.println(new Sort().init(33)[99]);
}

}

class Sort{
	Sort sortVar;
int[] data;

public int[] init(int ett){
	int i;
	int j;
	int temp;
	int special_pos;
	special_pos = 55;
	data = new int[100];
	i = 0;
	while(i<data.length){

		if( !(i < special_pos)){
			data[i] = i*((new int[5235234]).length-5235234*i)+1;

		}else{
		data[i] = 2147483647;
		}
		//System.out.println(data[i] + " i" + i);
	i = i+1;
	}

	//This defines the min element
	data[22] = (2147483647+1);
	//System.out.println(data[22] + " i" + 22);
	i = 0;
	j = 0;
	while( i < data.length){

		j = 0;
		while ( j < data.length-1 ){
			if(data[j] < data[j]){
				temp = data[j];
				data[j] = data[j+1];
				data[j+1] = temp;
			}else{
			}
		j = j+1;
		}
		i = i +1;

	}

	//This should output 0
	System.out.println((data[0])+2147483647+1);



	return data;
	//data[99];
}

public boolean bool(){
	return true;
}
}
