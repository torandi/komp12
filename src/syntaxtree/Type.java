package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public abstract class Type {
    public int line_number;
    
    protected Type(int line) {
        line_number = line;
    }
    
    /**
     * This method assumes that the left hand argument is trying to be fit into
     * the right hand argument
     * @param tp
     * @return 
     */
    public  boolean equals(Type tp)
    {
	return getClass().equals(tp.getClass());
    }
    
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
}
