package visitor;

import parse.StackedTabPrinter;
import syntaxtree.*;

public class ASTSymbolPrintVisitor implements Visitor {
    private StackedTabPrinter stream;

    public ASTSymbolPrintVisitor(StackedTabPrinter stream) {
        this.stream=stream;
    }

    // MainClass m;
    // ClassDeclList cl;
    public void visit(Program n) {
	n.m.accept(this);
	for ( int i = 0; i < n.cl.size(); i++ ) {
	    n.cl.elementAt(i).accept(this);
	}
    }
  
    // Identifier i1,i2;
    // Statement s;
    public void visit(MainClass n) {
	stream.println("MainClass: "+n.i1.s+" {"+n.record+"}");
        stream.add_tab();
        stream.println("void main {"+n.mainMethodFrame+"}");
        stream.add_tab();
        n.s.accept(this);
        stream.reset_tabs();
    }

    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclSimple n) {
	stream.println("Class: "+n.toString());
        stream.add_tab();
        stream.println("Fields: ");
        stream.add_tab();
	for (VarDecl v : n.vl.getList()) {
	    stream.println(v.t.toString()+" "+v.i.s+"{"+v.i.sym.access+"}");
	}
        stream.del_tab();
  
        stream.println("\tMethods: ");
        stream.add_tab();
	for ( MethodDecl m : n.ml.getList()) {
            m.accept(this);
	}
        stream.del_tab();
    }
 
    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclExtends n) {
	/*n.i.accept(this);
	n.j.accept(this);
	for ( int i = 0; i < n.vl.size(); i++ ) {
	    n.vl.elementAt(i).accept(this);
	    if ( i+1 < n.vl.size() ) 
		stream.print(", ");
	}
	for ( int i = 0; i < n.ml.size(); i++ ) {
	    stream.println();
	    if ( i+1 < n.ml.size() ) 
		stream.println(", ");
	}*/
    }

    // Type t;
    // Identifier i;
    public void visit(VarDecl n) {  }

    // Type t;
    // Identifier i;
    // FormalList fl;
    // VarDeclList vl;
    // StatementList sl;
    // Exp e;
    public void visit(MethodDecl n) {
	stream.println(n.t.toString()+" "+n.i.s+" {"+n.frame+"}");
        stream.add_tab();
        stream.println("Formals:");
        stream.add_tab();
	for ( Formal f : n.fl.getList() ) {
	    stream.println(f.t.toString()+" "+f.i.s+" {"+f.i.sym.access+"}");
	}
        stream.del_tab();
        
	stream.println("Locals:");
        stream.add_tab();
	for ( VarDecl v : n.vl.getList()) {
            stream.println(v.t.toString()+" "+v.i.s+" {"+v.i.sym.access+"}");
        }
        
        stream.del_tab();
        stream.println();
	for ( Statement s : n.sl.getList()) {
            s.accept(this);
	}
    }

    // Type t;
    // Identifier i;
    public void visit(Formal n) { }

    public void visit(IntArrayType n) {  }

    public void visit(BooleanType n) { }

    public void visit(IntegerType n) {   }

    // String s;
    public void visit(IdentifierType n) { }

    // StatementList sl;
    public void visit(Block n) {
        stream.println("Block:");
        stream.add_tab();
	for ( VarDecl v : n.vl.getList()) {
            stream.println(v.t.toString()+" "+v.i.s+" {"+v.i.sym.access+"}");
        }
        
	for ( Statement s : n.sl.getList()) {
            s.accept(this);
	}
    }

    // Exp e;
    // Statement s1,s2;
    public void visit(If n) { }

    // Exp e;
    // Statement s;
    public void visit(While n) {    }

    // Exp e;
    public void visit(Print n) {    }
  
    // Identifier i;
    // Exp e;
    public void visit(Assign n) {    }

    // Identifier i;
    // Exp e1,e2;
    public void visit(ArrayAssign n) {    }

    // Exp e1,e2;
    public void visit(And n) {    }

    // Exp e1,e2;
    public void visit(LessThan n) { }

    // Exp e1,e2;
    public void visit(Plus n) {  }

    // Exp e1,e2;
    public void visit(Minus n) {  }

    // Exp e1,e2;
    public void visit(Times n) {  }

    // Exp e1,e2;
    public void visit(ArrayLookup n) {  }

    // Exp e;
    public void visit(ArrayLength n) {  }

    // Exp e;
    // Identifier i;
    // ExpList el;
    public void visit(Call n) {    }

    // int i;
    public void visit(IntegerLiteral n) { }

    public void visit(True n) {   }

    public void visit(False n) { }

    // String s;
    public void visit(IdentifierExp n) { }

    public void visit(This n) { }

    // Exp e;
    public void visit(NewArray n) { }

    // Identifier i;
    public void visit(NewObject n) { }

    // Exp e;
    public void visit(Not n) { }

    // String s;
    public void visit(Identifier n) { }
}
