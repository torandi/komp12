class Bogus4 {
	public static void main(String[] args){
		System.out.println(new Random().getArray(5)[new Random().getArray(17).length == 17]);
	}
}

class Random{
	public int[] getArray(int value){
		int[] ret;
		ret = new int[5];
		ret[0] = 3;
		ret[1] = 1;
		ret[2] = 4;
		ret[3] = ret[1];
		ret[4] = ret.length;
		return ret;
	}
}