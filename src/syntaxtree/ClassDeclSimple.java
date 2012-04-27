package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class ClassDeclSimple extends ClassDecl {

    public ClassDeclSimple(Program p, Identifier ai, VarDeclList avl, MethodDeclList aml) {
        super(p);
        i = ai;
        vl = avl;
        ml = aml;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public String parent_name() {
        return "java.lang.Object";
    }
}
