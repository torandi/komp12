package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Or extends Exp {
  public Exp e1,e2;
  
  public Or(Exp ae1, Exp ae2) { 
    e1=ae1; e2=ae2;
    line_number = AbstractTree.interpolate_line_number(e1, e2);
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
