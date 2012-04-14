package syntaxtree;

import java.util.ArrayList;

public class VarDeclList {
   private ArrayList<VarDecl> list;

   public VarDeclList() {
      list = new java.util.ArrayList<VarDecl>();
   }

   public void addElement(VarDecl n) {
      list.add(n);
   }

   public VarDecl elementAt(int i)  { 
      return list.get(i); 
   }
   
   public void push_front(VarDecl n) {
       list.add(0, n);
   }

   public int size() { 
      return list.size(); 
   }

   public ArrayList<VarDecl> getList() {
       return list;
   }
}
