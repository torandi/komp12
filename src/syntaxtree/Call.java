package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Call extends Exp {
  public Exp e;
  public String c=null; // class of e
    
  public Identifier i;
  public ExpList el;
  public MethodDecl method; //Method that will be called
  
  public Call(Exp ae, Identifier ai, ExpList ael, int line) {
    e=ae; i=ai; el=ael;
    line_number = line;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
