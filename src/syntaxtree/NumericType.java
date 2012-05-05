package syntaxtree;

public abstract class NumericType extends Type {
    
    protected NumericType(int line) {
        super(line);
    }
    
    public boolean equals(Type t) {
        return t instanceof NumericType;
    }
}
