package syntaxtree;

import java.util.ArrayList;

public class FormalList {
   private ArrayList<Formal> list = new ArrayList<Formal>();
   private ArrayList<Type> tl = new ArrayList<Type>();


   public void addElement(Formal n) {
      list.add(n);
      tl.add(n.t);
   }

   public Formal elementAt(int i)  { 
      return list.get(i);
   }

   public int size() { 
      return list.size(); 
   }

   public ArrayList<Formal> getList() {
       return list;
   }

   public ArrayList<Type> getTypeList() {
        return tl;
   }

   public String toString() {
       StringBuilder sb = new StringBuilder();
       boolean first=true;
       sb.append("(");
       for(Formal f : list) {
           if(!first)
             sb.append(",");
           else
             first=false;
           sb.append(sb);
       }
       sb.append(")");
       return sb.toString();
   }

}
