package mjc;

import basic_tree.Start;
import error.ErrorMsg;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import parse.ParseException;
import parse.Parser;
import syntaxtree.AbstractTree;
import visitor.ASTPrintVisitor;
import visitor.AssemblerVisitor;
import visitor.TypeBindVisitor;
import visitor.TypeDefVisitor;


public class JVMMain {
    public static frame.VMFactory frameFactory;
    public static boolean assemble = true;
    public static boolean print_ast = false;
    public static boolean debug_symbols = true;
    public static boolean debug = false;
    
    ErrorMsg error;

    AbstractTree abstractTree;

    public static void main(String[] args) {
        String output_dir = ".";
        String srcfile = null;
        for(int i=0; i < args.length; ++i) {
            if(args[i].equals("-S")) {
                assemble = false;
            } else if(args[i].equals("-o")) {
                if(args.length >= i+2) {
                    output_dir = args[++i];
                } else {
                    System.out.println("Error: flag -o requires one argument");
                }
            } else if(args[i].equals("-h")) {
                System.out.println("Flags:\n"
                        + "-S : do not run jasmin (only output assembler)\n"
                        + "-o : set output directory\n"
                        + "-nd : don't generate debug symbols\n"
                        + "-d : Turn on compiler debuging\n"
                        + "-h : this help\n");
            } else if(args[i].equals("-fno-array-bounds-checks")) {
                //tigris uses this flag in performance tests, so turn of debug flags:
                debug_symbols = false;
            } else if(args[i].equals(("-nd"))) {
                debug_symbols = false;
            } else if(args[i].equals(("-d"))) {
                debug = true;
                   
            } else if(args[i].equals("-ast")) {
                print_ast = true;
            } else {
                srcfile = args[i];
            }
        }
        
        
        if(!output_dir.endsWith("/")) {
            output_dir += "/";
        }
        
        if(srcfile == null) {
            System.out.println("No source file specified");
            System.exit(1);
        }
        
        JVMMain main = new JVMMain();
        frameFactory = new jvm.Factory();
        if(!main.begin(srcfile, output_dir)) {
            System.out.println("Compilation failed: "+srcfile);
            System.exit(1);
        } else {
            System.exit(0);
        }
    }

    public JVMMain() {
    }

    public boolean begin(String file, String output_dir)  {
        try {
            String basename = new File(file).getName();
            
            error = new ErrorMsg(System.err, basename);
            
            Reader r = new FileReader(file);
            System.out.println("Building from "+file);
            Start basicTree = parse(r);
            abstractTree = new AbstractTree(basicTree,error);
            abstractTree.build();

            if(error.anyErrors)
               return false;

            
            if(print_ast) {
                //Build abstract tree
                PrintWriter pw = new PrintWriter (output_dir+basename+".ast");
                ASTPrintVisitor pv = new ASTPrintVisitor(pw);
                pv.visit(abstractTree.program);
                pw.close();
                System.out.println("AST saved to "+output_dir+basename+".ast");
            }

            
            //Check syntax and bind types
            TypeDefVisitor tdv= new TypeDefVisitor(error);
            tdv.visit(abstractTree.program);

            if(error.anyErrors)
               return false;
            
            //Bind symbols and allocate records, frames and accesses
            TypeBindVisitor tbv= new TypeBindVisitor(error);
            tbv.visit(abstractTree.program);
            
            if(error.anyErrors)
               return false;
            
            //Output assembly!
            AssemblerVisitor asmVisitor = new AssemblerVisitor(debug_symbols);
            
            ArrayList<String> asm_files = asmVisitor.run(abstractTree.program, output_dir, basename);
            System.out.println("Assembly outputed to "+asmVisitor.getOutputDir());
            
            if(assemble) {
                for(String asm : asm_files) {
                    try {
                        Runtime.getRuntime().exec("jasmin \""+asm+"\" -d \""+output_dir+"\"");
                    } catch (IOException ex) {
                        System.out.println("Jasmin execution failed!");
                    }
                }
            }

            return true;
        } catch (FileNotFoundException ex) {
            error.complain(ex.getMessage(),-1);
            return false;
        } catch (ParseException ex) {
            error.complain(ex.getMessage(), ex.currentToken.endLine);
            return false;
        }
    }

    private Start parse(Reader r) throws ParseException {
        Parser parser = new Parser(r);
        return parser.Start();
    }
}
