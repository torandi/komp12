package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public interface Statement {
  public void accept(Visitor v);
  public Type accept(TypeVisitor v);
}
