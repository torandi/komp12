package syntaxtree;
import symbol.Symbol;
import visitor.Visitor;
import visitor.TypeVisitor;

public class IdentifierExp extends Exp {
  public String s;
  public Symbol sym=null; //Set in symbol bind
  
  public IdentifierExp(String as) { 
      s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
