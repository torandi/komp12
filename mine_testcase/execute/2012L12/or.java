//EXT: BDJ

class Test {
   public static void main(String[] args) {
      
      if(!(false && true)) {
         System.out.println(1);
      } else {}

      if(!false && true) {
         System.out.println(2);
      } else {}

      if(!(false || true)) {
         System.out.println(3);
      } else {}

      if(!false || true) {
         System.out.println(4);
      } else {}
   }
}
