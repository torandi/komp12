package syntaxtree;

public abstract class NumericType extends Type {
    public abstract int precision();
    
    protected NumericType(int line) {
        super(line);
    }
    
    public boolean equals(Type t) {
        return t instanceof NumericType;
    }
}
