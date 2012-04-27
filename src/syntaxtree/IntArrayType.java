package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class IntArrayType extends Type {

    public IntArrayType(int line) {
        super(line);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public int hashCode() {
        return "int[]".hashCode();
    }

    public String toString() {
        return "int[]";
    }
}
