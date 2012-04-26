class Bogus3 {
	public static void main(String[] args){
		System.out.println(new Random().getRandom(5));
	}
}

class Random{
	public int getRandom(int v@lue){
		return 4;
	}
}