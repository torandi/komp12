package visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    
    private ArrayList<String> output_files;
    
    public ArrayList<String> run(Program program, String output_dir) {
        dir = output_dir;
        output_files = new ArrayList<String>();
        new File(dir).mkdirs();
        
        pass = 1;
        visit(program);
        next_label = 0;
        pass = 2;
        visit(program);
        return output_files;
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
        if(current_stack_size < 0) {
            System.out.println("Warning, stack poped below 0");
        }
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
        directive(".end method\n");
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
        current_file = new File(dir+cls+".j");
        output_files.add(dir+cls+".j");
        next_label = 0;
        try {
            current_output = new PrintWriter(current_file);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open output: "+ex);
        }
    }

    private Label create_label() {
        return create_label(null);
    }
    
    private Label create_label(String hint) {
        return new Label(next_label++, hint);
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
        
        for(VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        for(Statement s : n.sl.getList()) {
            s.accept(this);
        }
        
        instr("return");
        directive(".end method");
        
        if(pass == 1)
            stack_size.put(null, current_max_stack_size); //Null is main class method
    }

    public void visit(ClassDeclSimple n) {
        load_class_output(n.i.s);
        directive(".class "+convert_classname(n.fullName()));
        directive(".super java/lang/Object\n");
        for(VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        output("");
        create_constructor(n);
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
            if(n.t != null) {
                if(n.t instanceof IntegerType || n.t instanceof BooleanType) {
                    instr("ireturn");
                } else {
                    instr("areturn");
                }
            } else {
                instr("return");
            }
            directive(".end method\n");
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
        Label l_true, l_false, l_end;
        l_true = create_label("if_true");
        l_end = create_label("if_end");

        if(n.e instanceof Compare) {
            Compare compare = (Compare) n.e;
            compare.e1.accept(this);
            compare.e2.accept(this);
            compare_instruction(compare.op, l_true);
        } else {
            n.e.accept(this);
            instr("ifne "+l_true.name());
            pop();
        }
        
        
        instr("; if false");
        n.s2.accept(this);
        instr("goto "+l_end.name());
        //true
        label(l_true.declare());
        n.s1.accept(this);
        label(l_end.declare());
        
    }

    public void visit(While n) {
        Label l_start, l_end;
        l_start = create_label("while_begin");
        l_end = create_label("while_end");
        label(l_start.declare()+" ; begin while");
        n.e.accept(this);
        //@TODO: Optimize for lt
        instr("ifeq "+l_end.name()+" ; while condition");
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
    
    public void visit(ArrayAssign n) {
        instr(n.i.sym.access.load());
        push();
        n.e1.accept(this);
        n.e2.accept(this);
        instr("iastore");
        pop(3);
    }

    //Set label l_true to null to fall through on true
    public void create_and(Exp e1, Exp e2, Label l_true, Label l_false) {
        e1.accept(this);
        instr("ifeq "+l_false.name());
        pop();
        e2.accept(this);
        instr("ifeq "+l_false.name());
        pop();
        if(l_true != null) {
            instr("goto "+l_true.name());
        }
    }
        
    public void visit(And n) {
        Label l_false = create_label("and_false");
        Label l_end = create_label("and_end");
        
        create_and(n.e1, n.e2, null, l_false); //Null => fall through on true
        instr("iconst_1");
        instr("goto "+l_end.name());
        label(l_false.declare());
        instr("iconst_0");
        label(l_end.declare());
        
        push();
    }
    
    private void compare_instruction(Compare.Operator op, Label label) {
        switch(op) {
            case LT:
                instr("if_icmplt "+label.name());
                pop(2);
                break;
            case LTEQ:
                instr("if_icmple "+label.name());
                pop(2);
                break;
            case GT:
                instr("if_icmpgt "+label.name());
                pop(2);
                break;
            case GTEQ:
                instr("if_icmpge "+label.name());
                pop(2);
                break;
            case EQ:
                instr("if_icmpeq "+label.name());
                pop(2);
                break;
            case NEQ:
                instr("if_icmpne "+label.name());
                pop(2);
                break;
        }
    }
    
    public void visit(Compare n) {
        n.e1.accept(this);
        n.e2.accept(this);
        Label l_true, l_end;
        l_true = create_label();
        l_end = create_label();
        
        compare_instruction(n.op, l_true);
        
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
    
    public void visit(ArrayLookup n) {
        n.e1.accept(this);
        n.e2.accept(this);
        instr("iaload");
        pop();
    }
    
    public void visit(ArrayLength n) {
        n.e.accept(this);
        instr("arraylength");
    }
    
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
            if(n.i > Short.MIN_VALUE && n.i < Short.MAX_VALUE) {
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
    
    public void visit(NewArray n) {
        n.e.accept(this);
        instr("newarray int");
    }
    
    public void visit(NewObject n){
        instr("new "+convert_classname(n.cls.fullName()));
        instr("dup");
        push(2);
        instr("invokespecial "+convert_classname(n.cls.fullName())+"/<init>()V"); 
        pop();
    }

    public void visit(Not n) {
        instr("; not start");
        n.e.accept(this);
        instr("iconst_1");
        instr("ixor ; boolean not");
    }
    
    public void visit(Identifier n) { } //Never called in this visitor
    
}
