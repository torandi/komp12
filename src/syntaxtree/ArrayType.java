package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class ArrayType extends Type {
    public Type base_type;
    
    public ArrayType(Type base_type, int line) {
        super(line);
        this.base_type = base_type;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public int hashCode() {
        return (base_type.toString()+"[]").hashCode();
    }

    public String toString() {
        return base_type.toString()+"[]";
    }
}
