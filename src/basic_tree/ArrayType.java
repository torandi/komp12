package basic_tree;

public class ArrayType extends Type {
    private Type base_type;
    
    public ArrayType(Type base_type, int line) {
        super(line);
        this.base_type = base_type;
    }
    
    public static final boolean validate_base_type(Type t) {
        //Check if base type is of a type that we allow arrays of:
        if(t instanceof IntegerType) {
            return true;
        } else if(t instanceof BooleanType) {
            return true;
        } else if(t instanceof LongType) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean valid() {
        return ArrayType.validate_base_type(base_type);
    }
    
    public Type getBaseType() {
        return base_type;
    }
}
