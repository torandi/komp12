package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class IdentifierType extends Type {

    public String s;
    public ClassDecl c;

    /**
     * This method assumes that the left hand argument is trying to be fit into
     * the right hand argument
     * @param tp
     * @return 
     */
    @Override
    public boolean equals(Type tp) {
        if (!(tp instanceof IdentifierType)) {
            return false;
        }

        IdentifierType itp = (IdentifierType) tp;
        return c.hasParent(itp.s);
    }

    public IdentifierType(Program p, String as, int line) {
        super(line);
        s = as;
        c = p.findClass(s);
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
