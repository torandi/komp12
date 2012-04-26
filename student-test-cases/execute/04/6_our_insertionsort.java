// EXT:JVM

/**
 *
 *  This program sorts an array of integers using insertionsort.
 *  The return code of the program is printed after the sorted array.
 *
 * */
class Main {
    public static void main(String[] args) {
        System.out.println(new our_insertionsort().run());
    }

}
class our_insertionsort {

    public int run() {
        int i;
        int rett;
        int[] unsorted;
        i = 0;
        unsorted = new int[19];
        unsorted[0] = 3;
        unsorted[1] = 2;
        unsorted[2] = 3;
        unsorted[3] = 6;
        unsorted[4] = 5;
        unsorted[5] = 234;
        unsorted[6] = 545;
        unsorted[7] = 6;
        unsorted[8] = 67;
        unsorted[9] = 4;
        unsorted[10] = 3;
        unsorted[11] = 24;
        unsorted[12] = 23;
        unsorted[13] = 54;
        unsorted[14] = 65;
        unsorted[15] = 656;
        unsorted[16] = 2;
        unsorted[17] = 234;
        unsorted[18] = 4;
        rett = this.insertion_srt(unsorted);
        while(i < unsorted.length) {
            System.out.println(unsorted[i]);
            i = i + 1;
        }
        return 0;
    }


    public int insertion_srt(int[] array){
        int n;
        int i;
        int value;
        int j;
        i = 1;
        n = array.length;
        while (i < n){
            j = i;
            value = array[i];
            while (!(j < 1) && !(array[j-1] < value + 1)){
                array[j] = array[j-1];
                j = j - 1;
            }
            array[j] = value;
            i = i + 1;
        }
        return 0;
    }

}
