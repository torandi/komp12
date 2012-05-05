package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class VoidType extends Type {

    public VoidType(int line) {
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
        return "void".hashCode();
    }

    public String toString() {
        return "void";
    }
}
