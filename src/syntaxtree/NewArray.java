package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class NewArray extends Exp {
  public Type base_type;
  public Exp e;
  
  public NewArray(Type base_type, Exp ae) {
    e=ae; 
    this.base_type = base_type;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
