package syntaxtree;
import symbols.Scope;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Block extends Scope implements Statement {
  public StatementList sl;
  public VarDeclList vl;

  public Block(StatementList asl,VarDeclList avl) {
    sl=asl;
    vl=avl;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public String toString() {
      return "{}";
  }

}

