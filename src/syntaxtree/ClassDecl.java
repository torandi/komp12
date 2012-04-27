package syntaxtree;

import java.util.ArrayList;
import java.util.HashMap;
import symbol.Scope;
import visitor.Visitor;
import visitor.TypeVisitor;

public abstract class ClassDecl extends Scope {

    public Program program;
    
    public Identifier i;
    public VarDeclList vl;
    public MethodDeclList ml;
    public frame.VMRecord record;
    public HashMap<String, ArrayList<MethodDecl>> methods =
            new HashMap<String, ArrayList<MethodDecl>>();

    public ClassDecl(Program p) {
        program = p;
    }
    
    public abstract void accept(Visitor v);

    public abstract Type accept(TypeVisitor v);

    /**
     * Adds the specified method to the method list.
     * @return True if successfull, False if the method was already declared
     */
    public boolean addMethod(Identifier id, ArrayList<Type> tl, MethodDecl m) {
        ArrayList<MethodDecl> container = methods.get(id.s.intern());
        if (container == null) {
            container = new ArrayList<MethodDecl>();
            methods.put(id.s.intern(), container);
        }
        if (findMethod(container, tl) == null) {
            container.add(m);
            m.cls = this;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return The method or null if it doesn't exist
     */
    public MethodDecl findMethod(Identifier id, ArrayList<Type> tl) {
        ArrayList<MethodDecl> container = methods.get(id.s.intern());
        if (container != null) {
            return findMethod(container, tl);
        } else {
            return null;
        }

    }

    public String toString() {
        return i.s;
    }

    private MethodDecl findMethod(ArrayList<MethodDecl> list, ArrayList<Type> tl) {

        for (MethodDecl m : list) {
            if (m.compareParameters(tl)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Return name of superclass
     */
    public abstract String parent_name();

    public String fullName() {
        //Add package name here if packages are to be used
        return i.s;
    }
    
    /**
     * Return true if this class has a parent named cls_name (any steps down in the chain)
     */
    public boolean hasParent(String cls_name) {
        if(this instanceof ClassDeclExtends){
            ClassDeclExtends cde = (ClassDeclExtends)this;
            if(cde.parent().toString().equals(cls_name)) {
                return true;
            } else {
                return cde.parent().hasParent(cls_name);
            }
        } else {
            return false;
        }
    }
}
