package syntaxtree;

import java.util.ArrayList;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ClassDeclExtends extends ClassDecl {

    public Identifier parent_id; //Parent
    private ClassDecl parent;

    public ClassDeclExtends(Program p, Identifier ai, Identifier aj,
            VarDeclList avl, MethodDeclList aml) {
        super(p);
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

    public String parent_name() {
        return parent().fullName();
    }
    
    public ClassDecl parent() {
        if(parent == null) {
            parent = program.findClass(parent_id.s);
        }
        return parent;
    }

    @Override
    public MethodDecl findMethod(Identifier id, ArrayList<Type> tl) {
        MethodDecl md = super.findMethod(id, tl);
        if(md == null)
            md = parent().findMethod(id, tl);
        
        return md;
    }
}
