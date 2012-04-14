package parse;

import basic_tree.Start;
import error.ErrorMsg;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import syntaxtree.AbstractTree;
import visitor.ASTSymbolPrintVisitor;
import visitor.ASTPrintVisitor;
import visitor.AssemblerVisitor;
import visitor.TypeBindVisitor;
import visitor.TypeDefVisitor;


public class Main {
    public static frame.VMFactory frameFactory;
    
    ErrorMsg error;

    AbstractTree abstractTree;

    public static void main(String[] args) {
        for(String s : args) {
            Main main = new Main();
            frameFactory = new jvm.Factory();
            if(!main.begin(s)) {
                System.out.println("Compilation failed: "+s);
            }
        }
    }

    public Main() {
        error = new ErrorMsg(System.err);
    }

    public boolean begin(String file) {
        try {
            Reader r = new FileReader(file);
            System.out.println("Building from "+file);
            Start basicTree = parse(r);
            abstractTree = new AbstractTree(basicTree,error);
            abstractTree.build();

            if(error.anyErrors)
               return false;

            //Build abstract tree
	    PrintWriter pw = new PrintWriter (file+".syntax");
            ASTPrintVisitor pv = new ASTPrintVisitor(pw);
            pv.visit(abstractTree.program);
            pw.close();
            System.out.println("Syntax saved to "+file+".syntax");

            //Check syntax and bind types
            TypeDefVisitor tdv= new TypeDefVisitor(error);
            tdv.visit(abstractTree.program);

            //Bind symbols and allocate records, frames and accesses
            TypeBindVisitor tbv= new TypeBindVisitor(error);
            tbv.visit(abstractTree.program);
            
            
            if(error.anyErrors)
               return false;
            
            pw = new PrintWriter(file+".symbols");
            ASTSymbolPrintVisitor symbolpv= new ASTSymbolPrintVisitor(new StackedTabPrinter(pw));
            symbolpv.visit(abstractTree.program);
            System.out.println("SymbolTable saved to "+file+".symbols");
            

            pw.close();
            
            if(error.anyErrors)
               return false;
            
            //Output assembly!
            AssemblerVisitor asmVisitor = new AssemblerVisitor();
            System.out.println("Begin assembling!");
            asmVisitor.run(abstractTree.program);
            System.out.println("Assembly outputed to "+asmVisitor.getOutputDir());

            return true;
        } catch (FileNotFoundException ex) {
            error.complain(file+" not found.");
            return false;
        } catch (ParseException ex) {
            error.complain("Parse exception in line "+ex.currentToken.beginLine+": "+ex);
            ex.printStackTrace();
            return false;
        }
    }

    private Start parse(Reader r) throws ParseException {
        Parser parser = new Parser(r);
        return parser.Start();
    }
}
