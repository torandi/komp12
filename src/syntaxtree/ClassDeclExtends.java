package syntaxtree;

import java.util.ArrayList;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ClassDeclExtends extends ClassDecl {

    public Identifier parent_id; //Parent
    public ClassDecl parent;

    public ClassDeclExtends(Identifier ai, Identifier aj,
            VarDeclList avl, MethodDeclList aml) {
        i = ai;
        parent_id = aj;
        vl = avl;
        ml = aml;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String parent() {
        return parent.fullName();
    }

    @Override
    public MethodDecl findMethod(Identifier id, ArrayList<Type> tl) {
        MethodDecl md = super.findMethod(id, tl);
        if(md == null)
            md = parent.findMethod(id, tl);
        
        return md;
    }
}
