package syntaxtree;
import symbols.Scope;
import visitor.Visitor;
import visitor.TypeVisitor;

public class MainClass extends Scope{
  public Identifier i1,i2;
  public Statement s;

  public MainClass(Identifier ai1, Identifier ai2, Statement as) {
    i1=ai1; i2=ai2; s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public String toString() {
      return i1.s+".main()";
  }
}

