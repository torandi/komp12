package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class LongType extends NumericType {

    public LongType(int line) {
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
        return "long".hashCode();
    }

    public String toString() {
        return "long";
    }
    
    public int precision() {
        return 8;
    }
}
