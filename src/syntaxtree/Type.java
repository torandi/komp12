package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public abstract class Type {
    public int line_number;
    
    protected Type(int line) {
        line_number = line;
    }
    
    public  boolean equals(Type tp) throws ImplicitCast, LossOfPrecision {
	return getClass().equals(tp.getClass());
    }
    
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
}
