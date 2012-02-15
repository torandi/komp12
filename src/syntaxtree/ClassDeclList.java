package syntaxtree;

import java.util.ArrayList;

public class ClassDeclList {
   private ArrayList<ClassDecl> list;

   public ClassDeclList() {
      list = new ArrayList<ClassDecl>();
   }

   public void addElement(ClassDecl n) {
      list.add(n);
   }

   public ClassDecl elementAt(int i)  {
      return list.get(i);
   }

   public ArrayList<ClassDecl> getList() {
       return list;
   }

   public int size() { 
      return list.size(); 
   }
}
