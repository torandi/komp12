package syntaxtree;
import symbol.Scope;
import visitor.Visitor;
import visitor.TypeVisitor;

public class MainClass extends Scope{
  public Identifier i1,i2; //i1: Class name
  public VarDeclList vl;
  public StatementList sl;
  public frame.VMRecord record;
  public frame.VMFrame mainMethodFrame;

  public MainClass(Identifier ai1, Identifier ai2) {
    i1=ai1; i2=ai2;
    vl = new VarDeclList();
    sl = new StatementList();
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

