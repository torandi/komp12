package visitor;

import error.ErrorMsg;
import symbol.Symbol;
import symbol.SymbolTable;
import syntaxtree.*;

/**
 * A visitor that handles type definitions
 * This class should create all the 
 */
public class TypeDefVisitor implements Visitor{
    private ErrorMsg error;
    private SymbolTable st=new SymbolTable();
    
    public TypeDefVisitor(ErrorMsg e) {
        error=e;
    }

    public void visit(Program n) {

        //Add the main class as a class without methods or variables
        n.addClass(n.m.i1, new ClassDeclSimple(n.m.i1, new VarDeclList(),new MethodDeclList()));

        n.m.accept(this);

        for(ClassDecl c :  n.cl.getList()) {
            if(!n.addClass(c.i, c)) {
                error.complain("Class "+c.i+" is already defined.");
            }
            c.accept(this);
        }
    }

    public void visit(MainClass n) {
        st.pushScope(n);
        n.s.accept(this);
        st.popScope();
    }

    public void visit(ClassDeclSimple n) {
        st.pushScope(n);
        for(VarDecl v : n.vl.getList()) {
            v.accept(this);
        }

        for(MethodDecl m : n.ml.getList()) {
            if(!n.addMethod(m.i, m.fl.getTypeList(), m)) {
                error.complain("Method "+m.t+" "+m.i+m.fl+" already defined in class "+n);
            }
            m.accept(this);
        }

        st.popScope();
    }

    public void visit(ClassDeclExtends n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(VarDecl n) {
        n.i.sym = new Symbol(n.t);
        if(!st.addVariable(n.i,n.i.sym)) {
            error.complain(n.i+" is already defined in current scope ("+st+")");
        }
    }

    public void visit(MethodDecl n) {
        st.pushScope(n);
        for(Formal f : n.fl.getList()) {
            f.accept(this);
        }
        for(VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        for(Statement s : n.sl.getList()) {
            s.accept(this);
        }
        st.popScope();
    }

    public void visit(Formal n) {
        n.i.sym = new Symbol(n.t);
        if(!st.addVariable(n.i,n.i.sym)) {
            error.complain(n.i+" in formal list is already defined in current scope ("+st+")");
        }
    }
    
    public void visit(Block n) {
        st.pushScope(n);
        for(VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        for(Statement s : n.sl.getList()) {
            s.accept(this);
        }
        st.popScope();
    }

    public void visit(If n) {
        n.s1.accept(this);
        n.s2.accept(this);
    }

    public void visit(While n) {
        n.s.accept(this);
    }

    public void visit(IntArrayType n) {}
    public void visit(BooleanType n) {}
    public void visit(IntegerType n) {}
    public void visit(IdentifierType n) {}
    public void visit(Print n) {}
    public void visit(Assign n) { }
    public void visit(ArrayAssign n) { }
    public void visit(And n) {}
    public void visit(LessThan n) {}
    public void visit(Plus n) { }
    public void visit(Minus n) {}
    public void visit(Times n) { }
    public void visit(ArrayLookup n) { }
    public void visit(ArrayLength n) { }
    public void visit(Call n) { }
    public void visit(IntegerLiteral n) { }
    public void visit(True n) {}
    public void visit(False n) {}
    public void visit(IdentifierExp n) {}
    public void visit(This n) {}
    public void visit(NewArray n) {}
    public void visit(NewObject n){}
    public void visit(Not n) {}
    public void visit(Identifier n) {}

}
