// EXT:JVM

/**
 *
 * This program should fail during typechecking.
 */
class Main {
    public static void main(String[] args) {
        System.out.println(new our_typeerror().run());
    }

}
class our_typeerror {
    int num_chars;
    int[] chars;

    public int print() {
        int i;
        i = 0;
        while(i < num_chars) {
            if(chars[i]) {
                System.out.println(i);
            } else {

            }
            i = i + 1;
        }
        return 0;
    }

    public int run() {
        int i;
        int retv;
        num_chars = 27;
        chars = new int[num_chars];
        i = 0;
        while(i < s.length) {
            retv = this.check(s.charAt(i));
            i = i + 1;
        }
        return 0;
    }

    public int check(int c) {
        int retv;
        int diff;
        diff = (c - 1);
        if(0 < diff && diff < num_chars) {
            retv = this.mark(c - 2);
        } else {

        }
        diff = (c - 1);
        if(0 < diff && diff < num_chars) {
            retv = this.mark(c - 1);
        } else {

        }
        return 0;
    }

    public int mark(int i) {
        chars[i] = true;
        return 0;
    }
}
