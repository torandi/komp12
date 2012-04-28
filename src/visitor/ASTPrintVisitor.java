/** ASTPrintVisitor - prints the abstract syntax tree in a constructor
 * style with parenthesized comma-separated lists. This visitor can
 * help check for the correctness of a MiniJava abstract syntax tree.
 * The implementation is based on that of PrettyPrintVisitor, and the
 * author was tempted to call it UglyPrintVisitor.
 *
 * Author: Todd Neller, Gettysburg College 1/29/04 */ 

package visitor;

import java.io.PrintWriter;
import mjc.StackedTabPrinter;
import syntaxtree.*;

public class ASTPrintVisitor implements Visitor {
    private StackedTabPrinter stream;

    public ASTPrintVisitor(PrintWriter stream) {
        this.stream=new StackedTabPrinter(stream);
    }

    // MainClass m;
    // ClassDeclList cl;
    public void visit(Program n) {
	stream.println("Program(");
	n.m.accept(this);
        stream.add_tab();
	stream.println("ClassDeclList(");
        stream.add_tab();
	for ( int i = 0; i < n.cl.size(); i++ ) {
	    if (i>0) stream.println(", ");
	    n.cl.elementAt(i).accept(this);
	}
        stream.del_tab();
	stream.println("))");
    }
  
    // Identifier i1,i2;
    // Statement s;
    public void visit(MainClass n) {
	stream.print("MainClass(");
        stream.add_tab();
	n.i1.accept(this);
	stream.print(", ");
	n.i2.accept(this);
	stream.print(", ");
	stream.println(" (");
        stream.add_tab();
	for ( int i = 0; i < n.vl.size(); i++ ) {
	    n.vl.elementAt(i).accept(this);
	    if ( i+1 < n.vl.size() )
		stream.print(", ");
	}
        stream.println();
        stream.del_tab();
	stream.println("), (");
        stream.add_tab();
	for ( int i = 0; i < n.sl.size(); i++ ) {
	    n.sl.elementAt(i).accept(this);
	    if ( i+1 < n.sl.size() ) 
		stream.println(", ");
	}
        stream.del_tab();
	stream.println(")");
    }

    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclSimple n) {
	stream.print("ClassDeclSimple(");
	n.i.accept(this);
        class_decl_visit(n);
        
    }
    
    public void class_decl_visit(ClassDecl n) {
        stream.println(", (");
        stream.add_tab();
	for ( int i = 0; i < n.vl.size(); i++ ) {
	    n.vl.elementAt(i).accept(this);
	    if ( i+1 < n.vl.size() ) 
		stream.print(", ");
	}
        stream.println();
        stream.del_tab();
	stream.println("),");
	stream.println("(");
        stream.add_tab();
	for ( int i = 0; i < n.ml.size(); i++ ) {
	    n.ml.elementAt(i).accept(this);
	    if ( i+1 < n.ml.size() ) 
		stream.println(", ");
	}
        stream.del_tab();
	stream.println("))");
    }
 
    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclExtends n) {
	stream.print("ClassDeclExtends(");
	n.i.accept(this);
	stream.print(" {extends} ");
	n.parent_id.accept(this);
        class_decl_visit(n);
    }

    // Type t;
    // Identifier i;
    public void visit(VarDecl n) {
	stream.print("VarDecl(");
	n.t.accept(this);
	stream.print(", ");
	n.i.accept(this);
	stream.print(")");
    }

    // Type t;
    // Identifier i;
    // FormalList fl;
    // VarDeclList vl;
    // StatementList sl;
    // Exp e;
    public void visit(MethodDecl n) {
	stream.println("MethodDecl(");
	n.t.accept(this);
	stream.println(", ");
	n.i.accept(this);
	stream.println(", (");
        stream.add_tab();
	for ( int i = 0; i < n.fl.size(); i++ ) {
	    n.fl.elementAt(i).accept(this);
	    if (i+1 < n.fl.size()) 
		stream.print(", ");
	}
        stream.println();
        stream.del_tab();
	stream.println("), (");
        stream.add_tab();
	for ( int i = 0; i < n.vl.size(); i++ ) {
	    n.vl.elementAt(i).accept(this);
	    if ( i+1 < n.vl.size() )
		stream.print(", ");
	}
        stream.println();
        stream.del_tab();
	stream.println("), (");
        stream.add_tab();
	for ( int i = 0; i < n.sl.size(); i++ ) {
	    n.sl.elementAt(i).accept(this);
	    if ( i+1 < n.sl.size() ) 
		stream.println(", ");
	}
        stream.del_tab();
	stream.println("), Return: ");
	n.e.accept(this);
	stream.println(")");
    }

    // Type t;
    // Identifier i;
    public void visit(Formal n) {
	stream.print("Formal(");
	n.t.accept(this);
	stream.print(", ");
	n.i.accept(this);
	stream.print(")");
    }

    public void visit(ArrayType n) {
	stream.print("ArrayType("+n.base_type+")");
    }

    public void visit(BooleanType n) {
	stream.print("BooleanType()");
    }

    public void visit(IntegerType n) {
	stream.print("IntegerType()");
    }
    
    public void visit(VoidType n) {
	stream.print("VoidType()");
    }
    
    // String s;
    public void visit(IdentifierType n) {
	stream.print("IdentifierType(" + n.s + ")");
    }

    // StatementList sl;
    public void visit(Block n) {
	stream.println("Block((");
        stream.add_tab();
	for ( int i = 0; i < n.sl.size(); i++ ) {
	    n.sl.elementAt(i).accept(this);
	    if ( i+1 < n.sl.size()) 
		stream.println(",");
	}
        stream.println();
        stream.del_tab();
	stream.println("))");
    }
    
    public void visit(ExpressionStatement n) {
        stream.println("ExpressionStatement(");
        stream.add_tab();
        n.exp.accept(this);
        stream.del_tab();
        stream.println(")");
    }
    
    // Exp e;
    // Statement s1,s2;
    public void visit(IfElse n) {
	stream.print("IfElse(");
	n.e.accept(this);
	stream.println(",");
	n.s1.accept(this);
	stream.println(",");
	n.else_statement.accept(this);
	stream.print(")");
    }

    // Exp e;
    // Statement s1;
    public void visit(If n) {
	stream.print("If(");
	n.e.accept(this);
	stream.println(",");
	n.s1.accept(this);
	stream.print(")");
    }
    
    // Exp e;
    // Statement s;
    public void visit(While n) {
	stream.print("While(");
	n.e.accept(this);
	stream.println(",");
	n.s.accept(this);
	stream.print(")");
    }

    // Exp e;
    public void visit(Print n) {
	stream.print("Print(");
	n.e.accept(this);
	stream.print(")");
    }
  
    // Identifier i;
    // Exp e;
    public void visit(Assign n) {
	stream.print("Assign(");
	n.i.accept(this);
	stream.print(", ");
	n.e.accept(this);
	stream.print(")");
    }

    // Identifier i;
    // Exp e1,e2;
    public void visit(ArrayAssign n) {
	stream.print("ArrayAssign(");
	n.i.accept(this);
	stream.print(", ");
	n.e1.accept(this);
	stream.print(", ");
	n.e2.accept(this);
	stream.print(")");
    }

    // Exp e1,e2;
    public void visit(And n) {
	stream.print("And(");
	n.e1.accept(this);
	stream.print(", ");
	n.e2.accept(this);
	stream.print(")");
    }

    // Exp e1,e2;
    public void visit(Compare n) {
	stream.print("Compare(");
	n.e1.accept(this);
	stream.print(", ");
	n.e2.accept(this);
	stream.print(", ");
        stream.print(Compare.op_to_str(n.op));
	stream.print(")");
    }

    // Exp e1,e2;
    public void visit(Plus n) {
	stream.print("Plus(");
	n.e1.accept(this);
	stream.print(", ");
	n.e2.accept(this);
	stream.print(")");
    }

    // Exp e1,e2;
    public void visit(Minus n) {
	stream.print("Minus(");
	n.e1.accept(this);
	stream.print(", ");
	n.e2.accept(this);
	stream.print(")");
    }

    // Exp e1,e2;
    public void visit(Times n) {
	stream.print("Times(");
	n.e1.accept(this);
	stream.print(", ");
	n.e2.accept(this);
	stream.print(")");
    }

    // Exp e1,e2;
    public void visit(ArrayLookup n) {
	stream.print("ArrayLookup(");
	n.e1.accept(this);
	stream.print(", ");
	n.e2.accept(this);
	stream.print(")");
    }

    // Exp e;
    public void visit(ArrayLength n) {
	stream.print("ArrayLength(");
	n.e.accept(this);
	stream.print(")");
    }

    // Exp e;
    // Identifier i;
    // ExpList el;
    public void visit(Call n) {
	stream.print("Call(");
	n.e.accept(this);
	stream.print(", ");
	n.i.accept(this);
	stream.print(", (");
	for ( int i = 0; i < n.el.size(); i++ ) {
	    n.el.elementAt(i).accept(this);
	    if ( i+1 < n.el.size() ) { stream.print(", "); }
	}
	stream.print("))");
    }

    // int i;
    public void visit(IntegerLiteral n) {
	stream.print("IntegerLiteral(" + n.i + ")");
    }

    public void visit(True n) {
	stream.print("True()");
    }

    public void visit(False n) {
	stream.print("False()");
    }

    // String s;
    public void visit(IdentifierExp n) {
	stream.print("IdentifierExp(" + n.s + ")");
    }

    public void visit(This n) {
	stream.print("This()");
    }

    // Exp e;
    public void visit(NewArray n) {
	stream.print("NewArray(");
	n.e.accept(this);
	stream.print(")");
    }

    // Identifier i;
    public void visit(NewObject n) {
	stream.print("NewObject(");
	stream.print(n.i.s);
	stream.print(")");
    }

    // Exp e;
    public void visit(Not n) {
	stream.print("Not(");
	n.e.accept(this);
	stream.print(")");
    }

    // String s;
    public void visit(Identifier n) {
	stream.print("Identifier(" + n.s + ")");
    }

    @Override
    public void visit(LongType n) {
	stream.print("LongType()");
    }

    @Override
    public void visit(LongLiteral n) {
        stream.print("LongLiteral(" + n.i + ")");
    }
}
