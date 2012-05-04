//EXT: LONG
//EXT: NBD
//EXT: CNE
//EXT: ISC
//EXT: ICG
//EXT: IWE

class Main {
   public static void main(String[] args) {
      StackOperator stack;
      Stackable item;
      boolean tmp;

      stack = new StackOperator();
      item = new Stackable();
      tmp = item.set(10L);
      stack.init(item);

      item = new Stackable();
      tmp = item.set(100L);
      stack.push(item);

      stack.push(new AddOperator());

      item = stack.exec();
      System.out.println(item.value());

      if(item.value() != stack.exec().value()) {
         System.out.println(0);
      }


      item = new Stackable();
      tmp = item.set(100L);
      stack.push(item);

      item = new Stackable();
      tmp = item.set(100L);
      stack.push(item);

      item = new Stackable();
      tmp = item.set(100L);
      stack.push(item);

      stack.push(new AddOperator());
      stack.push(new AddOperator());

      item = stack.exec();
      System.out.println(item.value());
      if(item.value() != stack.exec().value()) {
         System.out.println(1);
      }
   }
}

/**
 * Actually a linked list..
 */
class StackOperator {
   Stackable last_item;

   public boolean init(Stackable first) {
      last_item = first;
      return first.mark_first();
   }

   public boolean push(Stackable item) {
      last_item = last_item.push(item);
      return true;
   }
   
   public Stackable exec() {
      last_item = last_item.exec();
      return last_item;
   }
}

class Stackable {
   Stackable prev;
   boolean is_first;
   long value;

   public boolean set(long v) {
      value = v;
      return true;
   }

   public boolean mark_first() {
      is_first = true;
      return true;
   }

   public Stackable push(Stackable item) {
      boolean derp;
      derp = item.set_prev(this);
      return item;
   }

   public boolean set_prev(Stackable item) {
      prev = item;
      return true;
   }

   public Stackable exec() {
      return this; //do nothing
   }

   public Stackable prev() {
      return prev;
   }

   public long value() {
      return value;
   }
}

class AddOperator extends Stackable {
   public Stackable exec() {
      Stackable ret;
      Stackable op1;
      Stackable op2;
      boolean tmp;

      ret = new Stackable();

      op1 = prev.exec();
      op2 = op1.prev().exec();
      {
         long v1;
         long v2;
         long res;

         v1 = op1.value();
         v2 = op2.value();
         res = v1 + v2;
         tmp = ret.set(res);
      }
      tmp = ret.set_prev(op2.prev());
      return ret;
   }
}
