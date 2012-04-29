package visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import jvm.Hardware;
import jvm.Label;
import mjc.JVMMain;
import syntaxtree.*;

/**
 * A visitor that converts the syntax tree (with bound records, formals and accesses)
 * to jasmin assembler code
 */
public class AssemblerVisitor implements Visitor {

    private File current_file;
    private PrintWriter current_output = null;
    private String dir;
    private HashMap<MethodDecl, Integer> stack_size = new HashMap<MethodDecl, Integer>();
    private int current_stack_size = 0;
    private int current_max_stack_size = 0;
    private int pass = 1;
    private int next_label = 0;
    private int current_line = -1;
    private ArrayList<String> output_files;
    private String src;
    private boolean debug_symbols;

    public AssemblerVisitor(boolean debug_symbols) {
        this.debug_symbols = debug_symbols;
    }

    public ArrayList<String> run(Program program, String output_dir, String src) {
        this.src = src;
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

    private void line(int line) {
        if (debug_symbols) {
            if (pass == 2 && line > 0 && line != current_line) {
                current_line = line;
                directive(".line " + line);
            }
        }
    }

    private void push() {
        push(1);
    }

    private void pop() {
        pop(1);
    }

    private void push(int num_vars) {
        current_stack_size += num_vars;
        if (current_stack_size > current_max_stack_size) {
            current_max_stack_size = current_stack_size;
        }
        if(JVMMain.debug && pass == 2) {
            directive("; push("+num_vars+")");
        }
    }

    private void pop(int num_vars) {
        current_stack_size -= num_vars;
        if (current_stack_size < 0) {
            System.out.println("Warning, stack poped below 0");
        }
        if(JVMMain.debug && pass == 2) {
            directive("; pop("+num_vars+")");
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
        if (c != null) {
            superclass = convert_classname(c.parent_name());
        } else {
            superclass = convert_classname("java.lang.Object");
        }
        directive(".method public <init>()V");
        instr("aload_0");
        instr("invokespecial '" + superclass + "/<init>()V'");
        instr("return");
        directive(".end method\n");
    }

    private void output(String str) {
        if (pass == 2) {
            current_output.println(str);
        }
    }

    private void label(String str) {
        output(str);
    }

    private void directive(String str) {
        output(str);
    }

    private void instr(String str) {
        output("\t" + str);
    }

    private void load_class_output(String cls) {
        if (current_output != null) {
            current_output.close();
        }
        current_file = new File(dir + cls + ".j");
        output_files.add(dir + cls + ".j");
        next_label = 0;
        try {
            current_output = new PrintWriter(current_file);
            directive(".source '" + src + "'");
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open output: " + ex);
            System.exit(1);
        }
    }

    private Label create_label() {
        return create_label(null);
    }

    private Label create_label(String hint) {
        return new Label(next_label++, hint);
    }

    public void visit(Program n) {
        n.m.accept(this);

        for (ClassDecl c : n.cl.getList()) {
            c.accept(this);
        }
        current_output.close();
    }

    public void visit(MainClass n) {
        load_class_output(n.i1.s);
        directive(".class '" + convert_classname(n.i1.s)+"'");
        directive(".super java/lang/Object");

        create_constructor(null);
        if (pass == 2) {
            directive(".method public static main([Ljava/lang/String;)V");
            directive(".limit locals " + (n.mainMethodFrame.numberOfLocals() + 1));
            directive(".limit stack " + stack_size.get(null));
        }

        for (VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        for (Statement s : n.sl.getList()) {
            s.accept(this);
        }

        instr("return");
        directive(".end method");

        if (pass == 1) {
            stack_size.put(null, current_max_stack_size); //Null is main class method
        }
    }

    public void visit(ClassDeclSimple n) {
        class_decl_visit(n);
    }

    public void class_decl_visit(ClassDecl n) {
        load_class_output(n.i.s);
        directive(".class '" + convert_classname(n.fullName())+"'");
        directive(".super '" + convert_classname(n.parent_name()) + "'\n");

        for (VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        output("");
        create_constructor(n);
        for (MethodDecl m : n.ml.getList()) {
            m.accept(this);
        }
    }

    public void visit(ClassDeclExtends n) {
        class_decl_visit(n);
    }

    public void visit(VarDecl n) {
        directive(n.i.sym.access.declare());
    }

    public void visit(MethodDecl n) {
        if (pass == 1) {
            current_max_stack_size = 0;
            current_stack_size = 0;
        } else {
            directive(".method public " + n.frame.procEntry());
            directive(".limit locals " + (n.frame.numberOfLocals() + n.frame.numberOfFormals() + 1));
            directive(".limit stack " + stack_size.get(n));

            line(n.line_number);
            directive(".var 0 is <this> L" + convert_classname(n.cls.fullName()) + ";");
        }

        for (Formal f : n.fl.getList()) {
            f.accept(this);
        }
        for (VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        for (Statement s : n.sl.getList()) {
            s.accept(this);
        }

        if(! (n.t instanceof VoidType) ) {
            n.e.accept(this);
        }

        if (pass == 2) {
            if (n.t instanceof IntegerType || n.t instanceof BooleanType) {
                instr("ireturn");
            } else if(n.t instanceof LongType) {
                instr("lreturn");
            } else if(n.t instanceof VoidType) {
                instr("return");
            } else {
                instr("areturn");
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
        for (VarDecl v : n.vl.getList()) {
            v.accept(this);
        }
        for (Statement s : n.sl.getList()) {
            s.accept(this);
        }
    }
    
    public void visit(ExpressionStatement n) {
        n.exp.accept(this);
        //Check if the expression pushed something
        //If it did we must undo it
        if(n.exp.type instanceof LongType) {
            instr("pop2");
            pop(2);
        } else if(! (n.exp.type instanceof VoidType)) {
            instr("pop");
            pop();
        }
    }

    public void visit(IfElse n) {
        visit((If)n);
    }
     
    public void visit(If n) {
        Label l_true, l_false, l_end;
        l_true = create_label("if_true");
        l_end = create_label("if_end");
        line(n.line_number);

        if (n.e instanceof Compare) {
            Compare compare = (Compare) n.e;
            compare.e1.accept(this);
            compare.e2.accept(this);
            line(n.line_number);
            compare_instruction(compare.op, l_true, compare.e1.type);
        } else {
            n.e.accept(this);
            line(n.line_number);
            instr("ifne " + l_true.name());
            pop();
        }


        instr("; if false");
        if (n instanceof IfElse) {
            ((IfElse) n).else_statement.accept(this);
        }
        instr("goto " + l_end.name());
        //true
        label(l_true.declare());
        n.s1.accept(this);
        label(l_end.declare());
    }

    public void visit(While n) {
        Label l_start, l_end;
        l_start = create_label("while_begin");
        l_end = create_label("while_end");

        line(n.line_number);
        label(l_start.declare() + " ; begin while");
        n.e.accept(this);
        line(n.line_number);
        //@TODO: Optimize for lt
        instr("ifeq " + l_end.name() + " ; while condition");
        n.s.accept(this);
        instr("goto " + l_start.name() + " ; loop while");
        label(l_end.declare() + " ; end while");
    }

    public void visit(ArrayType n) {}

    public void visit(BooleanType n) {}

    public void visit(IntegerType n) {}
    
    public void visit(VoidType n) {}
    
    public void visit(LongType n) {}
    
    public void visit(IdentifierType n) {}

    public void visit(Print n) {
        line(n.line_number);
        instr("getstatic java/lang/System/out Ljava/io/PrintStream;");
        push();
        n.e.accept(this);
        line(n.line_number);
        instr("invokevirtual java/io/PrintStream/println("+Hardware.signature(n.e.type)+")V");
        pop(2);
        if(n.e.type instanceof LongType)
            pop();
    }

    public void visit(Assign n) {
        line(n.line_number);
        n.e.accept(this);
        line(n.line_number);
        push();
        instr(n.i.sym.access.store());
        pop(1+n.i.sym.access.words());
    }

    public void visit(ArrayAssign n) {
        line(n.line_number);
        instr(n.i.sym.access.load());
        push(n.i.sym.access.words());
        n.e1.accept(this);
        n.e2.accept(this);
        line(n.line_number);
        ArrayType t = (ArrayType)n.i.sym.type; 
        if(t.base_type instanceof LongType) {
            instr("lastore");
            pop(4);
        } else if(t.base_type instanceof IdentifierType) {
            instr("aastore");
            pop(3);
        } else if(t.base_type instanceof IntegerType) {
            instr("iastore");
            pop(3);
        } else if(t.base_type instanceof BooleanType) {
            instr("bastore");
        } else {
            throw new InternalError("Unhandled type in array assignment on line "+n.line_number);
        }
    }

    //Set label l_true to null to fall through on true
    public void create_and(Exp e1, Exp e2, Label l_true, Label l_false) {
        e1.accept(this);
        instr("ifeq " + l_false.name());
        pop();
        e2.accept(this);
        instr("ifeq " + l_false.name());
        pop();
        if (l_true != null) {
            instr("goto " + l_true.name());
        }
    }

    public void visit(And n) {
        Label l_false = create_label("and_false");
        Label l_end = create_label("and_end");

        line(n.line_number);
        create_and(n.e1, n.e2, null, l_false); //Null => fall through on true
        line(n.line_number);
        instr("iconst_1");
        instr("goto " + l_end.name());
        label(l_false.declare());
        instr("iconst_0");
        label(l_end.declare());

        push();
    }

    private void compare_instruction(Compare.Operator op, Label label, Type type) {
        if(!(type instanceof LongType)) {
            switch (op) {
                case LT:
                    instr("if_icmplt " + label.name());
                    break;
                case LTEQ:
                    instr("if_icmple " + label.name());
                    break;
                case GT:
                    instr("if_icmpgt " + label.name());
                    break;
                case GTEQ:
                    instr("if_icmpge " + label.name());
                    break;
                case EQ:
                    instr("if_icmpeq " + label.name());
                    break;
                case NEQ:
                    instr("if_icmpne " + label.name());
                    break;
                default:
                    throw new InternalError("Unknown comparison operator "+op);
            }
            pop(2);
        } else {
            //Long need special treatment
            instr("lcmp");
            switch (op) {
                 case LT:
                    instr("iflt " + label.name());
                    break;
                case LTEQ:
                    instr("ifle " + label.name());
                    break;
                case GT:
                    instr("ifgt " + label.name());
                    break;
                case GTEQ:
                    instr("ifge " + label.name());
                    break;
                case EQ:
                    instr("ifeq " + label.name());
                    break;
                case NEQ:
                    instr("ifne " + label.name());
                    break;
                default:
                    throw new InternalError("Unknown comparison operator "+op);
            }
            pop(2);
        }
    }

    public void visit(Compare n) {

        line(n.line_number);

        n.e1.accept(this);
        n.e2.accept(this);
        Label l_true, l_end;
        l_true = create_label();
        l_end = create_label();

        line(n.line_number);

        compare_instruction(n.op, l_true,n.e1.type);

        instr("iconst_0");
        instr("goto " + l_end.name());
        label(l_true.declare());
        instr("iconst_1");
        label(l_end.declare());
        push(); //Either 0 or 1
    }

    public void visit(Plus n) {
        line(n.line_number);
        n.e1.accept(this);
        n.e2.accept(this);
        line(n.line_number);
        if(n.type instanceof LongType) {
            instr("ladd");
            pop(2);
        } else {
            instr("iadd");
            pop();
        }
    }

    public void visit(Minus n) {
        line(n.line_number);
        n.e1.accept(this);
        n.e2.accept(this);
        line(n.line_number);
        if(n.type instanceof LongType) {
            instr("lsub");
            pop(2);
        } else {
            instr("isub");
            pop();
        }
    }

    public void visit(Times n) {
        line(n.line_number);
        n.e1.accept(this);
        n.e2.accept(this);
        line(n.line_number);
        if(n.type instanceof LongType) {
            instr("lmul");
            pop(2);
        } else {
            instr("imul");
            pop();
        }
    }

    public void visit(ArrayLookup n) {
        line(n.line_number);
        n.e1.accept(this);
        n.e2.accept(this);
        line(n.line_number);
        
        if(n.type instanceof LongType) {
            instr("laload");
        } else if(n.type instanceof IdentifierType) {
            instr("aaload");
            pop();
        } else if(n.type instanceof IntegerType) {
            instr("iaload");
            pop();
        } else if(n.type instanceof BooleanType) {
            instr("baload");
            pop();
        } else {
            throw new InternalError("Unhandled type in array assignment on line "+n.line_number);
        }
    }

    public void visit(ArrayLength n) {
        line(n.line_number);
        n.e.accept(this);
        line(n.line_number);
        instr("arraylength");
    }

    public void visit(Call n) {
        line(n.line_number);
        //Add instructions for pushing the object to call the method on:
        n.e.accept(this);
        //Run through argument list
        for (Exp e : n.el.getList()) {
            e.accept(this);
        }
        line(n.line_number);
        instr("invokevirtual '" + convert_classname(n.method.fullName()) + Hardware.methodSignature(n.method.fl, n.method.t)+"'");
        pop(n.method.fl.size()); //pop(1+fl.size()); push(1) (result)
    }

    public void visit(IntegerLiteral n) {
        line(n.line_number);
        if (n.i == -1) {
            instr("iconst_m1");
        } else if (n.i > -1 && n.i < 6) {
            instr("iconst_" + n.i);
        } else {
            if (n.i > Short.MIN_VALUE && n.i < Short.MAX_VALUE) {
                instr("sipush " + n.i);
            } else {
                instr("ldc " + n.i);
            }
        }
        push();
    }
    
    public void visit(LongLiteral n) {
        line(n.line_number);
        if (n.i > -1 && n.i < 2) {
            instr("lconst_" + n.i);
        } else {
            instr("ldc2_w " + n.i);
        }
        push(2);
    }

    public void visit(True n) {
        line(n.line_number);
        instr("iconst_1");
        push();
    }

    public void visit(False n) {
        line(n.line_number);
        instr("iconst_0");
        push();
    }

    public void visit(IdentifierExp n) {
        line(n.line_number);
        instr(n.sym.access.load());
        push(n.sym.access.words());
    }

    public void visit(This n) {
        line(n.line_number);
        instr("aload_0 ; this");
        push();
    }

    public void visit(NewArray n) {
        line(n.line_number);
        n.e.accept(this);
        line(n.line_number);
        if(!(n.base_type instanceof IdentifierType)) {
            instr("newarray "+n.base_type.toString());
        } else {
            IdentifierType it = (IdentifierType)n.base_type;
            instr("anewarray '"+convert_classname(it.get_class().fullName())+"'");
        }
    }

    public void visit(NewObject n) {
        line(n.line_number);
        instr("new '" + convert_classname(n.cls.fullName())+"'");
        instr("dup");
        push(2);
        instr("invokespecial '" + convert_classname(n.cls.fullName()) + "/<init>()V'");
        pop();
    }

    public void visit(Not n) {
        line(n.line_number);
        instr("; not start");
        n.e.accept(this);
        line(n.line_number);
        instr("iconst_1");
        instr("ixor ; boolean not");
    }

    public void visit(Identifier n) {} //Never called in this visitor

}
