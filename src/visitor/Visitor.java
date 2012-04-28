/** ASTPrintVisitor - prints the abstract syntax tree in a constructor
 * style with parenthesized comma-separated lists. This visitor can
 * help check for the correctness of a MiniJava abstract syntax tree.
 * The implementation is based on that of PrettyPrintVisitor, and the
 * author was tempted to call it UglyPrintVisitor.
 *
 * Author: Todd Neller, Gettysburg College 1/29/04 */ 

package visitor;

import syntaxtree.*;

public interface Visitor {

    // MainClass m;
    // ClassDeclList cl;
    public void visit(Program n);
  
    // Identifier i1,i2;
    // Statement s;
    public void visit(MainClass n);

    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclSimple n);
 
    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclExtends n);

    // Type t;
    // Identifier i;
    public void visit(VarDecl n);

    // Type t;
    // Identifier i;
    // FormalList fl;
    // VarDeclList vl;
    // StatementList sl;
    // Exp e;
    public void visit(MethodDecl n);

    // Type t;
    // Identifier i;
    public void visit(Formal n);

    public void visit(ArrayType n);

    public void visit(BooleanType n);

    public void visit(IntegerType n);

    public void visit(VoidType n);
    
    public void visit(LongType n);
    
    // String s;
    public void visit(IdentifierType n);

    // StatementList sl;
    public void visit(Block n);
    
    public void visit(ExpressionStatement n);
    
    // Exp e;
    // Statement s1;
    public void visit(If n);
    
    public void visit(IfElse n);

    // Exp e;
    // Statement s;
    public void visit(While n);

    // Exp e;
    public void visit(Print n);
  
    // Identifier i;
    // Exp e;
    public void visit(Assign n);

    // Identifier i;
    // Exp e1,e2;
    public void visit(ArrayAssign n);

    // Exp e1,e2;
    public void visit(And n);

    // Exp e1,e2;
    public void visit(Compare n);

    // Exp e1,e2;
    public void visit(Plus n);

    // Exp e1,e2;
    public void visit(Minus n);

    // Exp e1,e2;
    public void visit(Times n);

    // Exp e1,e2;
    public void visit(ArrayLookup n);

    // Exp e;
    public void visit(ArrayLength n);

    // Exp e;
    // Identifier i;
    // ExpList el;
    public void visit(Call n);

    // int i;
    public void visit(IntegerLiteral n);
    
    //long l;
    public void visit(LongLiteral n);

    public void visit(True n);

    public void visit(False n);

    // String s;
    public void visit(IdentifierExp n);

    public void visit(This n);

    // Exp e;
    public void visit(NewArray n);

    // Identifier i;
    public void visit(NewObject n);

    // Exp e;
    public void visit(Not n);

    // String s;
    public void visit(Identifier n);
}
