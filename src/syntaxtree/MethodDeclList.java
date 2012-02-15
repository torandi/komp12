package syntaxtree;

import java.util.ArrayList;

public class MethodDeclList {
   private ArrayList<MethodDecl> list;

   public MethodDeclList() {
      list = new ArrayList<MethodDecl>();
   }

   public void addElement(MethodDecl n) {
      list.add(n);
   }

   public MethodDecl elementAt(int i)  { 
      return list.get(i);
   }

   public int size() { 
      return list.size(); 
   }

   public ArrayList<MethodDecl> getList() {
       return list;
   }
}
