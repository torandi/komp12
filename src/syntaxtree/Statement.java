package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public interface Statement {
    
    public int line_number();
    public void set_line_number(int line);
    
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
}
