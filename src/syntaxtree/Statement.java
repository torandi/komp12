package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public interface Statement {
    
    public int line_number();
    
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
}
