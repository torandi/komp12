// EXT:ISC

class MainClass{
    public static void main(String[] args){
	System.out.println(1);
    }
}

class U {}
class V extends U {}	//ok

class W extends W {}	//error - circular

class A extends C {}	//error - circular
class B extends A {}
class C extends B {}
