class Bogus1 {
	public static void main(String[] args){
		System.out.println(new Random().getRandom(67)*4*5*6*new Random().getRandom(true));
	}
}

class Random{
	public int getRandom(int value){
		return 4;
	}
}