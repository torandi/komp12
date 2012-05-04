//EXT: IWE

class IweTest  {
	public static void main(String[] a) {
		System.out.println(new CT().run());
	}
}

class CT {

	public int run() {
      
      if(1 < 2) {
         System.out.println(1);
         if(4 < 6) {
            System.out.println(2);
            if( 6 < 4) {
               System.out.println(3);
            }
         }
      }

      if(1 < 2)
         System.out.println(4);
      System.out.println(5);
      if(6 < 2) 
         System.out.println(6);
      System.out.println(7);


      return 0;
   }
}
