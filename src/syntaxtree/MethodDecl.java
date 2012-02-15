package syntaxtree;
import java.util.ArrayList;
import symbols.Scope;
import visitor.Visitor;
import visitor.TypeVisitor;

public class MethodDecl extends Scope{
  public Type t;
  public Identifier i;
  public FormalList fl;
  public VarDeclList vl;
  public StatementList sl;
  public Exp e;

  public MethodDecl(Type at, Identifier ai, FormalList afl, VarDeclList avl, 
                    StatementList asl, Exp ae) {
    t=at; i=ai; fl=afl; vl=avl; sl=asl; e=ae;
  }
 
  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public boolean compareParameters(ArrayList<Type> tl) {
      if(tl.size()!=fl.size()) {
          System.out.print("WS...");
          return false;
      }
      for(int i=0;i<tl.size();++i) {
          if(!tl.get(i).equals(fl.getTypeList().get(i))) {
              System.out.print(tl.get(i)+"!="+fl.getTypeList().get(i)+"...");
              return false;
          }
      }
      return true;
  }

  public String toString() {
      return i.s;
  }
}
