package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class IdentifierType extends Type {

    public String s;
    private ClassDecl c;
    private Program program;

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
        if(itp.s.equals(s)) {
            return true;
        } else {
            return get_class().hasParent(itp.s);
        }
    }

    public IdentifierType(Program p, String as, int line) {
        super(line);
        s = as;
        program = p;
    }

    public IdentifierType(ClassDecl c, int line) {
        super(line);
        s = c.i.s;
        this.c = c;
        program = c.program;
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

    public ClassDecl get_class() {
        if(c == null) {
            c = program.findClass(s);
        }
        return c;
    }
    
    public void set_class(ClassDecl cls) {
        c = cls;
    }
}
