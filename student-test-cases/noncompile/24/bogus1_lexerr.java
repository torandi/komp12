class Main {
    public static void main(String[] a){
	System.out.println(new Modulo().doit());
    }
}

class Modulo {

    public int doit(){
		return 3 modulo 5; //error unknown word/lexical token "modulo"
    }
}
