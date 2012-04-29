package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class BooleanType extends NumericType {

    public BooleanType(int line) {
        super(line);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public int hashCode() {
        return "boolean".hashCode();
    }

    public String toString() {
        return "boolean";
    }
}
