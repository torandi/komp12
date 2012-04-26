/**
 * Finds all the factors for some integer X (= 100 default).
 *
 */
class FactorInteger {
	public static void main(String[] args) {
		System.out.println(new Factoriser().init(100));
	}

}

class Factoriser {

   int number;
   int factor;

   public int init(int aNum) {
      number = aNum;
      factor = 2;

      while(1 < number) {
	  System.out.println(this.nextFactor());
      }

      return 0;
   }

   /**
    *  Find the next factor of a value.
    **/
   public int findNextFactor()
   {
	while((factor < number) && (0 < this.modulo(number,factor))) {
			factor = factor + 1;
		}
	return factor;
   }

   /**
    * Apply the next factor of a value.
    **/
   public int nextFactor() {
       int result;
	   int q;

	   result  = this.findNextFactor();
	   q = 0;

       if (result < number + 1) {
		   q = this.division(number, result);
	   number = q; //number = number / result

       } else {
	   System.out.println(999);
       }
       return result;
   }

   /**
      Determine whether or not there are more factors.
      @return true there are more factors
   */
   public boolean hasMoreFactors()
   {
	return (this.findNextFactor() < (number+1));
   }

   /**
    * Calculates a % b.
    */
   public int modulo(int a, int b) {

	  if(a < b) {
		  a = a;
	  }
	  else {
		  while(b < a+1) {
			  a = a - b;
		  }
	  }
	  return a;
   }


   /**
    * Calculates n / d
    * No neg.numbers.
    * Rounds downwards.
    */
   public int division(int n, int d) {
	  int quotient;
	  quotient = 0;

	  if(n < d) {
		  quotient = 0;
	  }
	  else {
		  while(d < n+1) {
			  quotient = quotient + 1;
			  n = n - d;
		  }
	  }
	  return quotient;
   }
}
