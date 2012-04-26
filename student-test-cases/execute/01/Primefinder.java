/**
 * A small program that produces all prime numbers between 2 and 10000
 *
 * uses the MiniJava language
 *
 * Is extended with the extensions ISC, ICG, JVM, IWE, BMO and NBD
 */

//EXT: ISC
//EXT: ICG
//EXT: JVM
//EXT: IWE
//EXT: NBD
//EXT: BMO

class Primefinder{
    public static void main(String[] args){
        if(new PrimeFinderSub().find_primes()){
            System.out.println(0);
        }
    }
}

class PrimeFinderSub{
    Arithmetical a;
    Arithmetical d;
    Arithmetical m;
    Comparer c;

    public boolean find_primes(){
        boolean retval;
        retval = true;
        {
            a = new Adder();
            d = new Divider();
            m = new Multiplyer();
            c = new Comparer();
            {
                int i;
                i = 2;
                while(!c.equal(i, 10001)){
                    int j;
                    j = 2;
                    {
                        boolean prime;
                        prime = true;
                        while(!c.equal(j, i)){
                            int times;
                            times = d.divide(i, j);
                            {
                                int back;
                                back = m.multiply(times, j);
                                if(c.equal(back, i))
                                    prime = false;
                            }
                            j = a.add(j, 1);
                        }
                        if(prime){
                            System.out.println(i);
                        }
                    }
                    i = a.add(i, 1);
                }
            }
        }
        return retval;
    }
}

class Arithmetical{
    public int add(int a, int b){ return 0; }
    public int divide(int a, int b){ return 4711; }
    public int multiply(int a, int b){ return 666; }
}

class Adder extends Arithmetical {
    public int add(int a, int b){
        int c;
        c = (a + b);
        return c;
    }
}

class Divider extends Arithmetical {
    public int divide(int a, int b){
        int c;
        Comparer comp;
        comp = new Comparer();
        c = 0;
        while(b < a){
            c = c + 1;
            a = a - b;
        }
        if(comp.equal(a, b)){
            c = c + 1;
        }
        return c;
    }
}

class Comparer {
    public boolean equal(int a, int b){
        boolean b1;
        boolean b2;
        boolean retval;
        b1 = a < b;
        b2 = b < a;
        retval = !b1 && !b2;
        return retval;
    }
}

class Multiplyer extends Divider {
    public int multiply(int a, int b){
        int c;
        c = 1 * a * b * 1;
        return c;
    }
}
