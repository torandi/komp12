package syntaxtree;

import java.util.ArrayList;
import java.util.List;

public class ExpList {
   private List<Exp> list;

   public ExpList() {
      list = new ArrayList<Exp>();
   }

   private ExpList(List<Exp> list) {
       this.list = list;
   }

   public void addElement(Exp n) {
      list.add(n);
   }

   public Exp elementAt(int i)  { 
      return list.get(i);
   }

   public int size() { 
      return list.size(); 
   }

   public ExpList sublist() {
       return new ExpList(list.subList(1, size()));
   }

  public List<Exp> getList() {
       return list;
   }
}
