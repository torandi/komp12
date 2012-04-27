class Main
{
    public static void main(String[] s) 
    {
	System.out.println(1);
    }
}


class A1 extends B1 { }
class A2 extends A1 { }
class A3 extends A2 { }
class A1 extends B1 { }
class B1 extends B2 { }
class B2 extends B3 { }
class B3 extends B1 { }
class C1 extends B2 { }
class C2 extends C1 { }

