/**
 * Bogus5.java
 *
 * A test case that should fail since the variable "namn"
 * is declared multiple times
 */

class Bogus5 {
    public static void main(String[] args) {
        if (false) {
            System.out.println(true);
        } else {
            System.out.println(new namn().namn(4));
        }
    }
}

class namn {
    public boolean namn(int namn) {
        int namn;
        boolean namn2;
        namn2 = false;
        namn = 7;
        if(namn < namn){
            namn2 = true;
        }
        return namn2;
    }
}
