//EXT:LONG
class Long {
   public static void main(String[] args) {
      long l;
      int i;
      l = 10L;
      i = l; //Should fail, possible loss of precision
   }
}
