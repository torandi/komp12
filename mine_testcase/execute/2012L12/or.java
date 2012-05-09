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

      if(true || true ) {
         System.out.println(3);
      } else {}

      if(false || true ) {
         System.out.println(4);
      } else {}

      if(true || false ) {
         System.out.println(5);
      } else {}


      if(!false || true) {
         System.out.println(6);
      } else {}
   }
}
