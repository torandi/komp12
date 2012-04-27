package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class IdentifierType extends Type {

    public String s;
    public ClassDecl c;

    @Override
    public boolean equals(Type tp) {
        if (!(tp instanceof IdentifierType)) {
            return false;
        }
        return ((IdentifierType) tp).s.equals(s);
    }

    public IdentifierType(String as, int line) {
        super(line);
        s = as;
    }

    public IdentifierType(ClassDecl c, int line) {
        super(line);
        s = c.i.s;
        this.c = c;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public String toString() {
        return s;
    }
}
