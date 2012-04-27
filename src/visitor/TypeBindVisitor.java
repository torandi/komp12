package visitor;

import error.ErrorMsg;
import java.util.ArrayList;
import mjc.JVMMain;
import symbol.SymbolTable;
import syntaxtree.*;

/**
 * Binds identifiers to variable types
 */
public class TypeBindVisitor implements TypeVisitor {

    private ErrorMsg error;
    private SymbolTable st = new SymbolTable();
    private Program curProgram = null;
    private ClassDecl curClass = null;
    private frame.VMFrame curFrame = null;

    public TypeBindVisitor(ErrorMsg e) {
        error = e;
    }

    public Type visit(Program n) {
        curProgram = n;
        //Main class
        n.m.record = JVMMain.frameFactory.newRecord(n.m.i1.s);
        n.m.mainMethodFrame = JVMMain.frameFactory.newFrame("main", new FormalList(), null);
        curFrame = n.m.mainMethodFrame;
        n.m.accept(this);
        curFrame = null;

        //other classes:
        for (ClassDecl c : n.cl.getList()) {
            curClass = c;
            c.record = JVMMain.frameFactory.newRecord(c.i.toString());
            c.accept(this);
        }
        return null;
    }

    public Type visit(MainClass n) {
        st.pushScope(n);

        for (VarDecl v : n.vl.getList()) {
            v.accept(this);
            v.i.sym.access = n.mainMethodFrame.allocLocal(v.i.s, v.t);
        }
        for (Statement s : n.sl.getList()) {
            s.accept(this);
        }

        st.popScope();
        return null;
    }

    public Type visit(ClassDeclSimple n) {
        st.pushScope(n);

        class_decl_visit(n);

        st.popScope();

        return null;
    }

    public void class_decl_visit(ClassDecl n) {
        for (VarDecl v : n.vl.getList()) {
            v.accept(this);
            //Allocate field:
            v.i.sym.access = curClass.record.allocField(v.i.s, v.t);
        }
        for (MethodDecl m : n.ml.getList()) {
            m.frame = JVMMain.frameFactory.newFrame(m.i.s, m.fl, m.t);
            curFrame = m.frame;
            curFrame.allocLocal("<this>", new IdentifierType(n, n.line_number));
            m.accept(this);
        }
        curFrame = null;

    }

    public Type visit(ClassDeclExtends n) {
        int num_pushes = 0;
        if(n.hasParent(n.toString())) {
            error.complain(n.toString()+" has cyclic inheritance",n.line_number);
        } else {
            ClassDecl parent = n.parent();
            while (parent instanceof ClassDeclExtends) {
                ClassDeclExtends p = (ClassDeclExtends) parent;
                ++num_pushes;
                st.pushScope(parent);
                parent = p.parent();
            }
            num_pushes += 2;
            st.pushScope(parent);
            st.pushScope(n);

            class_decl_visit(n);

            for (int i = 0; i < num_pushes; ++i) {
                st.popScope();
            }
        }
        return null;
    }

    public Type visit(VarDecl n) {
        n.t.accept(this);
        n.i.accept(this);
        return null; //Return value ignored
    }

    public Type visit(MethodDecl n) {
        st.pushScope(n);
        for (Formal f : n.fl.getList()) {
            f.accept(this);
            f.i.sym.access = n.frame.allocFormal(f.i.s, f.t);
        }
        for (VarDecl v : n.vl.getList()) {
            v.accept(this);
            v.i.sym.access = n.frame.allocLocal(v.i.s, v.t);
        }
        for (Statement s : n.sl.getList()) {
            s.accept(this);
        }
        //return statement
        Type ret_type = n.e.accept(this);
        if (!ret_type.equals(n.t)) {
            error.complain(st.toString(), "returning " + ret_type + " in method declared as " + n.t, n.e.line_number);
        }
        st.popScope();
        return n.t.accept(this);
    }

    public Type visit(Formal n) {
        n.i.accept(this);
        return n.t.accept(this);
    }

    public Type visit(IntArrayType n) {
        return n;
    }

    public Type visit(BooleanType n) {
        return n;
    }

    public Type visit(IntegerType n) {
        return n;
    }

    public Type visit(IdentifierType n) {
        n.c = curProgram.findClass(n.s);
        if (n.c == null) {
            error.complain(st.toString(), "Unknown type \"" + n.s + "\"", n.line_number);
            //Prevent nullpointerexceptions:
            n.c = new ClassDeclSimple(curProgram, new Identifier(n.s), new VarDeclList(), new MethodDeclList());
        }
        return n;
    }

    public Type visit(Block n) {
        st.pushScope(n);
        for (VarDecl v : n.vl.getList()) {
            v.accept(this);
            v.i.sym.access = curFrame.allocLocal(v.i.s, v.t);
        }
        for (Statement s : n.sl.getList()) {
            s.accept(this);
        }
        st.popScope();
        return null;
    }

    public Type visit(If n) {
        if (!(n.e.accept(this) instanceof BooleanType)) {
            error.complain(st.toString(), "If statement requires expression to return boolean", n.line_number);
        }
        n.s1.accept(this);
        n.s2.accept(this);
        return null;
    }

    public Type visit(While n) {
        if (!(n.e.accept(this) instanceof BooleanType)) {
            error.complain(st.toString(), "While statement requires expression to return boolean", n.line_number);
        }
        n.s.accept(this);
        return null;
    }

    public Type visit(Print n) {
        n.e.accept(this);
        return null;
    }

    public Type visit(Assign n) {
        Type t1 = n.e.accept(this);
        Type t2 = n.i.accept(this);

        if (!t1.equals(t2)) {
            error.complain(st.toString(), "Assigning variable of type " + t2 + " to \"" + n.i + "\", which is of type " + t1, n.line_number);

        }

        return t1.accept(this);
    }

    public Type visit(ArrayAssign n) {
        Type t = n.i.accept(this);
        Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);
        if (!(t instanceof IntArrayType)) {
            error.complain(st.toString(), "Trying to assign a value to an index of non-array variable of type " + t, n.line_number);
        }
        if (!(t1 instanceof IntegerType)) {
            error.complain(st.toString(), "Expression for index of (" + n.i + ") in " + st + " returned an " + t1 + " but should return an integer", n.line_number);
        }
        if (!(t2 instanceof IntegerType)) {
            error.complain(st.toString(), "Assigning " + t2 + " to \"" + n.i + "[...]\" which is of type int", n.line_number);
        }
        return t.accept(this);
    }

    public Type visit(And n) {
        Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);
        if (!(t1 instanceof BooleanType && t2 instanceof BooleanType)) {
            error.complain(st.toString(), "Operator && can not be applied to " + t1 + ", " + t2, n.line_number);
        }
        return new BooleanType(n.line_number);
    }

    public Type visit(Compare n) {
        Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);
        if (n.op != Compare.Operator.EQ && n.op != Compare.Operator.NEQ && !(t1 instanceof IntegerType && t2 instanceof IntegerType)) {
            error.complain(st.toString(), "Operator " + Compare.op_to_str(n.op) + " can not be applied to " + t1 + ", " + t2, n.line_number);
        } else if ((n.op == Compare.Operator.EQ || n.op == Compare.Operator.NEQ) && !((t1 instanceof IntegerType || t1 instanceof BooleanType)
                && (t2 instanceof IntegerType || t2 instanceof BooleanType))) {
            error.complain(st.toString(), "Operator " + Compare.op_to_str(n.op) + " can not be applied to " + t1 + ", " + t2, n.line_number);
        }
        return new BooleanType(n.line_number);
    }

    public Type visit(Plus n) {
        Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);
        if (!(t1 instanceof IntegerType && t2 instanceof IntegerType)) {
            error.complain(st.toString(), "Operator + can not be applied to " + t1 + ", " + t2, n.line_number);
        }
        return new IntegerType(n.line_number);
    }

    public Type visit(Minus n) {
        Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);
        if (!(t1 instanceof IntegerType && t2 instanceof IntegerType)) {
            error.complain(st.toString(), "Operator - can not be applied to " + t1 + ", " + t2, n.line_number);
        }
        return new IntegerType(n.line_number);
    }

    public Type visit(Times n) {
        Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);
        if (!(t1 instanceof IntegerType && t2 instanceof IntegerType)) {
            error.complain(st.toString(), "Operator * can not be applied to " + t1 + ", " + t2, n.line_number);
        }
        return new IntegerType(n.line_number);
    }

    public Type visit(ArrayLookup n) {
        Type t = n.e1.accept(this);
        if (!(t instanceof IntArrayType)) {
            error.complain(st.toString(), "Can not look up index in type " + t, n.line_number);
        }
        if (!(n.e2.accept(this) instanceof IntegerType)) {
            error.complain(st.toString(), "Index must be an integer", n.line_number);
        }
        return new IntegerType(n.line_number);
    }

    public Type visit(ArrayLength n) {
        if (!(n.e.accept(this) instanceof IntArrayType)) {
            error.complain(st.toString(), " .length can only be applied to int[]", n.line_number);
        }
        return new IntegerType(n.line_number);
    }

    public Type visit(Call n) {
        Type objectType = n.e.accept(this);
        if (objectType instanceof IdentifierType) {
            ClassDecl c = ((IdentifierType) objectType).c;
            ArrayList<Type> tl = new ArrayList<Type>(n.el.size());
            for (Exp e : n.el.getList()) {
                tl.add(e.accept(this));
            }
            MethodDecl m = c.findMethod(n.i, tl);
            if (m != null) {
                n.method = m;
                return m.t.accept(this);
            } else {
                error.complain(st.toString(), "Unknown method " + c.i + "." + n.i + "(" + typeListToString(tl) + ")", n.line_number);
                System.exit(-1); //Unrecoverable error
                return null;
            }
        } else {
            error.complain(st.toString(), "Can not call method " + n.c + "(...) on non-object " + objectType, n.line_number);
            System.exit(-1); //Unrecoverable error
            return null;
        }
    }

    public Type visit(IntegerLiteral n) {
        return new IntegerType(n.line_number);
    }

    public Type visit(True n) {
        return new BooleanType(n.line_number);
    }

    public Type visit(False n) {
        return new BooleanType(n.line_number);
    }

    public Type visit(IdentifierExp n) {
        n.sym = st.lookup(n.s);
        if (n.sym == null) {
            error.complain(st.toString(), "Undeclared variable " + n.s, n.line_number);
            System.exit(-1); //Unrecoverable error
            return null;
        } else {
            return n.sym.type.accept(this);
        }
    }

    public Type visit(This n) {
        if (curClass == null) {
            error.complain(st.toString(), "Calling this is not allowed in main method", n.line_number);
            System.exit(-1); //Unrecoverable error
            return null;
        } else {
            IdentifierType it = new IdentifierType(curClass, n.line_number);
            it.c = curClass;
            return it;
        }
    }

    public Type visit(NewArray n) {
        if (!(n.e.accept(this) instanceof IntegerType)) {
            error.complain(st.toString(), "Size of array must be an integer", n.line_number);
        }
        return new IntArrayType(n.line_number);
    }

    public Type visit(NewObject n) {
        ClassDecl c = curProgram.findClass(n.i.s);
        if (c == null) {
            error.complain(st.toString(), "Can not create new object of unknown class " + n.i.s, n.line_number);
            System.exit(-1); //Unrecoverable error
            return null;
        } else {
            IdentifierType it = new IdentifierType(c, n.line_number);
            it.c = c;
            n.cls = c;
            return it;
        }
    }

    public Type visit(Not n) {
        Type t = n.e.accept(this);
        if (!(t instanceof BooleanType)) {
            error.complain(st.toString(), "Operator ! can not be applied to " + t, n.line_number);
        }
        return new BooleanType(n.line_number);
    }

    public Type visit(Identifier n) {
        n.sym = st.lookup(n.s);
        if (n.sym == null) {
            error.complain(st.toString(), "Undeclared variable " + n.s, -1);
            System.exit(-1); //Unrecoverable error
            return null;
        } else {
            return n.sym.type.accept(this);
        }
    }

    public static String typeListToString(ArrayList<Type> tl) {
        StringBuilder sb = new StringBuilder();
        for (Type t : tl) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(t.toString());
        }
        return sb.toString();
    }
}
