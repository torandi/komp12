package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public abstract class Type {
    public  boolean equals(Type tp)
    {
	return getClass().equals(tp.getClass());
    }
    
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
}
