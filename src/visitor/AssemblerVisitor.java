package visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import jvm.Hardware;
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
    private HashMap<MethodDecl, Integer> stack_size = new HashMap<MethodDecl, Integer>();
    private int current_stack_size = 0;
    private int current_max_stack_size = 0;
    private int pass = 1;
    
    private int next_label = 0;
    
    public void run(Program program) {
        dir = "output/"+program.m.i1.s;
        new File(dir).mkdirs();
        
        pass = 1;
        visit(program);
        next_label = 0;
        pass = 2;
        visit(program);
    }

    private void push() {
        push(1);
    }
    
    private void pop() {
        pop(1);
    }
    
    private void push(int num_vars) {
        current_stack_size +=num_vars;
        if(current_stack_size > current_max_stack_size)
            current_max_stack_size = current_stack_size;
    }
    
    private void pop(int num_vars) {
        current_stack_size-=num_vars;
    }
    
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
        if(pass == 2)
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
        if(pass == 2) {
            directive(".method public static main([Ljava/lang/String;)V");
            directive(".limit locals "+(n.mainMethodFrame.numberOfLocals()+1));
            directive(".limit stack "+stack_size.get(null));
        }
        n.s.accept(this);
        
        instr("return");
        directive(".end method");
        
        if(pass == 1)
            stack_size.put(null, current_max_stack_size); //Null is main class method
    }

    public void visit(ClassDeclSimple n) {
        load_class_output(n.i.s);
        directive(".class "+convert_classname(n.fullName()));
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
        if(pass == 1) {
            current_max_stack_size = 0;
            current_stack_size = 0;
        } else {
            directive(".method public "+n.frame.procEntry());
            directive(".limit locals "+(n.frame.numberOfLocals()+n.frame.numberOfFormals()+1));
            directive(".limit stack "+stack_size.get(n));
            directive(".var 0 is <this> L"+convert_classname(n.cls.fullName())+";");
        }
        
        for(Formal f : n.fl.getList()) {
            f.accept(this);
        }
        for(VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        for(Statement s : n.sl.getList()) {
            s.accept(this);
        }
        n.e.accept(this);
        
        if(pass == 2) {
            instr("return");
            directive(".end method");
        } else {
            stack_size.put(n, current_max_stack_size);
        }
        
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
        Label l_true, l_end;
        l_true = create_label();
        l_end = create_label();
        
        if(n.e instanceof LessThan) {
            LessThan lt = (LessThan) n.e;
            instr("; begin lt if ");
            lt.e1.accept(this);
            lt.e2.accept(this);
            instr("if_icmplt "+l_true.name());
            pop(2);
        } else {
            instr("; begin if expression");
            n.e.accept(this);
            instr("ifne "+l_true.name()+" ; if jump");
            pop();
        }
        instr("; if false");
        n.s2.accept(this);
        instr("goto "+l_end.name());
        label(l_true.declare()+" ; if true");
        n.s1.accept(this);
        label(l_end.declare()+" ; end if");
    }

    public void visit(While n) {
        Label l_start, l_end;
        l_start = create_label();
        l_end = create_label();
        label(l_start.declare());
        n.e.accept(this);
        instr("ifeq "+l_end.name()+" ; begin While");
        n.s.accept(this);
        instr("goto "+l_start.name()+" ; loop while");
        label(l_end.declare()+" ; end while");
    }

    public void visit(IntArrayType n) {}
    
    public void visit(BooleanType n) {}
    
    public void visit(IntegerType n) { }
    
    public void visit(IdentifierType n) {}
    
    public void visit(Print n) {
        instr("getstatic java/lang/System/out Ljava/io/PrintStream;");
        push();
        n.e.accept(this);
        instr("invokevirtual java/io/PrintStream/println(I)V");
        pop(2);
    }
    
    public void visit(Assign n) {
        n.e.accept(this);
        instr(n.i.sym.access.store());
    }
    
    public void visit(ArrayAssign n) { }
    
    public void visit(And n) {
        n.e1.accept(this);
        n.e2.accept(this);
        instr("iand");
        pop(); //Actually pop(2) push(1)
    }
    
    public void visit(LessThan n) {
        n.e1.accept(this);
        n.e2.accept(this);
        Label l_true, l_end;
        l_true = create_label();
        l_end = create_label();
        instr("ifcmplt "+l_true.name());
        pop(2);
        instr("iconst_0");
        instr("goto "+l_end.name());
        label(l_true.declare());
        instr("iconst_1");
        label(l_end.declare());
        push(); //Either 0 or 1
    }
    
    public void visit(Plus n) {
        n.e1.accept(this);
        n.e2.accept(this);
        instr("iadd");
        pop();
    }
    public void visit(Minus n) {
        n.e1.accept(this);
        n.e2.accept(this);
        instr("isub");
        pop();
    }
    
    public void visit(Times n) {
        n.e1.accept(this);
        n.e2.accept(this);
        instr("imul");
        pop();
    }
    
    public void visit(ArrayLookup n) { }
    
    public void visit(ArrayLength n) { }
    
    public void visit(Call n) {
        //Add instructions for pushing the object to call the method on:
        n.e.accept(this);
        //Run through argument list
        for(Exp e : n.el.getList()) {
            e.accept(this);
        }
        instr("invokevirtual "+convert_classname(n.method.fullName())+Hardware.methodSignature(n.method.fl, n.method.t));
        pop(n.method.fl.size()); //pop(1+fl.size()); push(1) (result)
    }
    
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
        push();
    }
    
    public void visit(True n) {
        instr("iconst_1");
        push();
    }
    public void visit(False n) {
        instr("iconst_0");
        push();
    }
    
    public void visit(IdentifierExp n) {
        instr(n.sym.access.load());
        push();
    }
    
    public void visit(This n) {
        instr("aload_0 ; this");
        push();
    }
    
    public void visit(NewArray n) {}
    
    public void visit(NewObject n){
        instr("new "+convert_classname(n.cls.fullName()));
        instr("dup");
        push(2);
        instr("invokespecial "+convert_classname(n.cls.fullName())+"/<init>()V");
        pop();
    }

    public void visit(Not n) {
        n.e.accept(this);
        instr("ixor 1 ; boolean not");
    }
    
    public void visit(Identifier n) { }
    
}
