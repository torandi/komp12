package syntaxtree;

import error.TypeException;
import error.MethodOverrideException;
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
            if(parent == null)
                throw new InternalError("Parent of class "+toString()+" ("+parent_id.s+") can't be found");
        }
        return parent;
    }

    @Override
    public MethodDecl findMethod(Identifier id, ArrayList<Type> tl, boolean strict) {
        MethodDecl md = super.findMethod(id, tl, strict);
        if(md == null)
            md = parent().findMethod(id, tl, strict);
        
        return md;
    }
    
    
    @Override
    public boolean addMethod(Identifier id, ArrayList<Type> tl, MethodDecl m) throws MethodOverrideException {
        //For inheriting classes we must verify that no super class declare it, or 
        // that the return type match
        MethodDecl parent_method = findMethod(id, tl, true);

        if(parent_method == null) {
            return super.addMethod(id, tl, m);
        } else {
            try {
                if(m.t.equals(parent_method.t)) {
                    return super.addMethod(id, tl, m);
                }
            } catch (TypeException e) {
                
            }
            throw new MethodOverrideException(parent_method, m.t.toString());
        }
    }
}
