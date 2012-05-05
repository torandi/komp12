package syntaxtree;

import java.util.ArrayList;
import symbol.Scope;
import visitor.Visitor;
import visitor.TypeVisitor;

public class MethodDecl extends Scope {

    public Type t;
    public Identifier i;
    public FormalList fl;
    public VarDeclList vl;
    public StatementList sl;
    public Exp e; //return statement
    public frame.VMFrame frame;
    public ClassDecl cls; //Class this method belongs to

    public MethodDecl(Type at, Identifier ai, FormalList afl, VarDeclList avl,
            StatementList asl, Exp ae) {
        t = at;
        i = ai;
        fl = afl;
        vl = avl;
        sl = asl;
        e = ae;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public boolean compareParameters(ArrayList<Type> tl, boolean strict)  {
        if (tl.size() != fl.size()) {
            return false;
        }

        for (int i = 0; i < tl.size(); ++i) {
            Type arg_type = tl.get(i);
            Type formal_type = fl.getTypeList().get(i);
            try {
                if (!arg_type.equals(formal_type)) {
                    return false;
                }
            } catch (LossOfPrecision e) {
                return false;
            } catch (TypeException e) {
                if(strict) 
                    return false;
            }
        }
        return true;
    }

    public String fullName() {
        return cls.fullName() + "." + i.s;
    }

    public String toString() {
        return i.s;
    }
    
    public String signature() {
        return i.s+fl.toString();
    }
}
