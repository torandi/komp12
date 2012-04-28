package basic_tree;

public class ArrayType extends Type {
    private Type base_type;
    
    public ArrayType(Type base_type, int line) {
        super(line);
        this.base_type = base_type;
    }

    
    public Type getBaseType() {
        return base_type;
    }
}
