package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class IntegerType extends NumericType {

    public IntegerType(int line) {
        super(line);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public int hashCode() {
        return "int".hashCode();
    }

    public String toString() {
        return "int";
    }

    @Override
    public int precision() {
        return 4;
    }
    
    
}
