package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ArrayLookup extends Exp {
  public Exp e1,e2;

  /**
   *
   * @param ae1 Expression that should evaluate to an ArrayType
   * @param ae2 Expression in []
   */
  public ArrayLookup(Exp ae1, Exp ae2, int line) { 
    e1=ae1; e2=ae2;
    
    line_number = line;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
