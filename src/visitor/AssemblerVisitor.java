package visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import jvm.Label;
import syntaxtree.*;

/**
 * A visitor that converts the syntax tree (with bound records, formals an accessess)
 * to jasmin assembler code
 */
public class AssemblerVisitor implements Visitor{
    private File current_file;
    private PrintWriter current_output = null;
    private String dir;
    
    private int next_label = 0;

    private String convert_classname(String str) {
        return str.replace('.', '/');
    }
    
    public String getOutputDir() {
        return dir;
    }
    
    private void create_constructor(ClassDecl c) {
        String superclass = null;
        if(c != null) {
            superclass = convert_classname(c.parent());
        } else {
            superclass = convert_classname("java.lang.Object");
        }
        directive(".method public <init>()V");
        instr("aload_0");
        instr("invokespecial "+superclass+"/<init>()V");
        instr("return");
        directive(".end method");
    }
     
    private void output(String str) {
        current_output.println(str);
    }
   
    private void label(String str) {
        output(str);
    }
    
    private void directive(String str) {
        output(str);
    }
    
    private void instr(String str) {
        output("\t"+str);
    }
    
    private void load_class_output(String cls) {
        if(current_output != null)
            current_output.close();
        current_file = new File(dir+"/"+cls+".j");
        next_label = 0;
        try {
            current_output = new PrintWriter(current_file);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open output: "+ex);
        }
    }

    private Label create_label() {
        return new Label(next_label++);
    }
    
    public AssemblerVisitor() {}

    public void visit(Program n) {
        dir = "output/"+n.m.i1.s;
        new File(dir).mkdirs();
        
        n.m.accept(this);

        for(ClassDecl c :  n.cl.getList()) {
            c.accept(this);
        }
        current_output.close();
    }

    public void visit(MainClass n) {
        load_class_output(n.i1.s);
        directive(".class "+convert_classname(n.i1.s));
        directive(".super java/lang/Object");
        create_constructor(null);
        directive(".method public static main([Ljava/lang/String;)V");
        directive(".limit locals "+n.mainMethodFrame.numberOfLocals());
        n.s.accept(this);
        instr("return");
        directive(".end method");
    }

    public void visit(ClassDeclSimple n) {
        load_class_output(n.i.s);
        directive(".class "+convert_classname(n.i.s));
        directive(".super java/lang/Object");
        create_constructor(n);
        for(VarDecl v : n.vl.getList()) {
            v.accept(this);
        }

        for(MethodDecl m : n.ml.getList()) {
            m.accept(this);
        }
    }

    public void visit(ClassDeclExtends n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void visit(VarDecl n) {
        directive(n.i.sym.access.declare());
    }

    public void visit(MethodDecl n) {
        directive(".method public "+n.frame.procEntry());
        directive(".limit locals "+(n.frame.numberOfLocals()+n.frame.numberOfFormals()));
        for(Formal f : n.fl.getList()) {
            f.accept(this);
        }
        for(VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        for(Statement s : n.sl.getList()) {
            s.accept(this);
        }
        instr("return");
        directive(".end method");
    }

    public void visit(Formal n) {
        directive(n.i.sym.access.declare());
    }
    
    public void visit(Block n) {
        for(VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        for(Statement s : n.sl.getList()) {
            s.accept(this);
        }
    }

    public void visit(If n) {
        Label l_false, l_end;
        l_false = create_label();
        l_end = create_label();
        n.e.accept(this);
        instr("ifeq "+l_false.name());
        n.s1.accept(this);
        instr("goto "+l_end.name());
        label(l_false.declare());
        n.s2.accept(this);
        label(l_end.declare());
    }

    public void visit(While n) {
        Label l_start, l_end;
        l_start = create_label();
        l_end = create_label();
        label(l_start.declare());
        n.e.accept(this);
        instr("ifeq "+l_end.name());
        n.s.accept(this);
        instr("goto "+l_start.name());
        label(l_end.declare());
    }

    public void visit(IntArrayType n) {}
    public void visit(BooleanType n) {}
    public void visit(IntegerType n) {}
    public void visit(IdentifierType n) {}
    public void visit(Print n) {
        instr("getstatic java/lang/System/out Ljava/io/PrintStream;");
        n.e.accept(this);
        instr("invokevirtual java/io/PrintStream/println(I)V");
    }
    
    public void visit(Assign n) {
        n.e.accept(this);
        instr(n.i.sym.access.store());
    }
    public void visit(ArrayAssign n) { }
    public void visit(And n) {}
    public void visit(LessThan n) {}
    public void visit(Plus n) { }
    public void visit(Minus n) {}
    public void visit(Times n) { }
    public void visit(ArrayLookup n) { }
    public void visit(ArrayLength n) { }
    public void visit(Call n) { }
    
    public void visit(IntegerLiteral n) {
        if(n.i == -1) {
            instr("iconst_m1");
        } else if(n.i > -1 && n.i < 6) {
            instr("iconst_"+n.i);
        } else {
            if(((short)n.i) == n.i) {
                instr("sipush "+n.i);
            } else {
                instr("ldc "+n.i);
            }
        }
    }
    
    public void visit(True n) {
        instr("iconst_1");
    }
    public void visit(False n) {
        instr("iconst_0");
    }
    
    public void visit(IdentifierExp n) {}
    public void visit(This n) {}
    public void visit(NewArray n) {}
    public void visit(NewObject n){}
    public void visit(Not n) {
        n.e.accept(this);
        instr("ixor 1 ; boolean not");
    }
    public void visit(Identifier n) {}

}
