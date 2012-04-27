package syntaxtree;

import basic_tree.Start;
import basic_tree.Expression;
import basic_tree.AndOperand;
import basic_tree.BooleanPrimary;
import basic_tree.ExpressionPrimary;
import basic_tree.LessOperand;
import basic_tree.MinusTerm;
import basic_tree.Factor;
import basic_tree.IdPrimary;
import basic_tree.IndexSufix;
import basic_tree.IntPrimary;
import basic_tree.LengthSufix;
import basic_tree.MethodSufix;
import basic_tree.NewIntPrimary;
import basic_tree.NewPrimary;
import basic_tree.NotExpressionPrimary;
import basic_tree.Sufix;
import basic_tree.Term;
import basic_tree.Variable;

import error.ErrorMsg;
import parse.ParserConstants;
import parse.Token;


public class AbstractTree {
    private ErrorMsg error;
    private Start basicTree;
    public Program program;

    public AbstractTree(Start basicTree, ErrorMsg error) {
        this.error=error;
        this.basicTree=basicTree;
    }


    public void build() {
        program = new Program();
        program.m = buildMainClass();
        program.cl = buildClassDeclList();
    }

    private MainClass buildMainClass() {
        Identifier i1,i2;
        basic_tree.Class mainClass = basicTree.getMainClass();

        basic_tree.Method m = mainClass.getMethods().get(0);
       
        
        i1=id(mainClass.getName());
        i2=id(m.getParameters().get(0).getName());
        
        MainClass mc = new MainClass(i1,i2);
        
        mc.line_number = mainClass.line_number;

        for(Variable v : m.getVariables()) {
            mc.vl.addElement(variable(v));
        }

        for(basic_tree.Statement s : m.getStatements()) {
            mc.sl.addElement(statement(s));
        }

        return mc;
    }

    private ClassDeclList buildClassDeclList() {
        ClassDeclList cdl = new ClassDeclList();
        for(basic_tree.Class c: basicTree.getClasses()) {
            cdl.addElement(convertClass(c));
        }
        return cdl;
    }

    private ClassDecl convertClass(basic_tree.Class in) {
        ClassDecl cd;
        VarDeclList vdl= new VarDeclList();
        MethodDeclList mdl = new MethodDeclList();

        for(Variable v : in.getVariables()) {
            vdl.addElement(variable(v));
        }

        for(basic_tree.Method m : in.getMethods()) {
            mdl.addElement(method(m));
        }
        
        if(in.hasSuperClass()) {
            cd = new ClassDeclExtends(program,id(in.getName()),id(in.getSuperClass()),vdl, mdl);
        } else {
            cd = new ClassDeclSimple(program,id(in.getName()),vdl, mdl);
        }
        
        cd.line_number = in.line_number;
        
        return cd;

    }

    private MethodDecl method(basic_tree.Method in) {
        FormalList fl = new FormalList();
        VarDeclList vdl = new VarDeclList();
        StatementList sl = new StatementList();

        for(Variable v : in.getParameters()) {
            fl.addElement(formal(v));
        }

        for(Variable v : in.getVariables()) {
            vdl.addElement(variable(v));
        }

        for(basic_tree.Statement s : in.getStatements()) {
            sl.addElement(statement(s));
        }

        MethodDecl md = new MethodDecl(type(in.getReturnType()),
                id(in.getName()),
                fl,vdl,sl,
                exp(in.getReturnExpression()));
        
        md.line_number = in.line_number;
        
        return md;
    }

    private Identifier id(String s) {
        return new Identifier(s);
    }

    /**
     * Converts a basic_tree statement to an abstract statement
     * @param in
     * @return
     */
    private Statement statement(basic_tree.Statement in) {
        Statement ret=null;

        if(in instanceof basic_tree.BasicStatement) {
            StatementList sl=new StatementList();
            VarDeclList vl = new VarDeclList();
            basic_tree.BasicStatement bs = (basic_tree.BasicStatement)in;
            for(basic_tree.Statement s: bs.getStatements()) {
                sl.addElement(statement(s));
            }
            for(basic_tree.Variable v : bs.getVariables()) {
                vl.addElement(variable(v));
            }
            ret = new Block(sl,vl);
        } else if(in instanceof basic_tree.IfStatement) {
            basic_tree.IfStatement ifs = (basic_tree.IfStatement)in;
            ret = new If(exp(ifs.getExpression()),
                    statement(ifs.getIfStatement()),
                    statement(ifs.getElseStatement()));

        } else if(in instanceof basic_tree.WhileStatement) {
            basic_tree.WhileStatement wfs = (basic_tree.WhileStatement)in;
            ret = new While(exp(wfs.getExpression()),statement(wfs.getStatement()));
        } else if(in instanceof basic_tree.SOPLStatement) {
            basic_tree.SOPLStatement pfs = (basic_tree.SOPLStatement)in;
            ret = new Print(exp(pfs.getExpression()));
        } else if(in instanceof basic_tree.SetStatement) {
            basic_tree.SetStatement sfs = (basic_tree.SetStatement)in;
            ret = new Assign(id(sfs.getId()), exp(sfs.getExpression()));
        } else if(in instanceof basic_tree.SetIndexStatement) {
            basic_tree.SetIndexStatement sfs = (basic_tree.SetIndexStatement)in;
            ret = new ArrayAssign(id(sfs.getId()), exp(sfs.getBracketExpression()),exp(sfs.getExpression()), in.line_number);
        }
        
        ret.set_line_number(in.line_number);

        return ret;
    }

    private Type type(basic_tree.Type in) {
        if(in instanceof basic_tree.IntegerType) {
            return new IntegerType(in.line_number);
        } else if(in instanceof basic_tree.IntegerArrayType) {
            return new IntArrayType(in.line_number);
        } else if(in instanceof basic_tree.BooleanType) {
            return new BooleanType(in.line_number);
        } else if(in instanceof basic_tree.CustomType) {
            return new IdentifierType(program,((basic_tree.CustomType)in).getId(), in.line_number);
        } else { //VoidType
            return null;
        }
    }

    private VarDecl variable(basic_tree.Variable in) {
        VarDecl vd = new VarDecl(type(in.getType()),id(in.getName()));
        vd.line_number = in.line_number;
        return vd;
    }

    private Formal formal(basic_tree.Variable in) {
        Formal f =  new Formal(type(in.getType()), id(in.getName()));
        f.line_number = in.line_number;
        return f;
    }

    /*
     * Expression conversion below
     */

    private Exp exp(basic_tree.Expression in) {
        ExpList el = new ExpList();
        for(AndOperand op : in.getOperands()) {
            el.addElement(and(op));
        }
        Exp e = buildAnd(el);
        e.line_number = in.line_number;
        return e;
    }
    private Exp buildAnd(ExpList el) {
        if(el.size()>1) {
            return new And(el.elementAt(0),buildAnd(el.sublist()));
        } else {
            return el.elementAt(0);
        }
    }

    private Exp and(basic_tree.AndOperand in) {
        ExpList el = new ExpList();
        for(LessOperand op : in.getOperands()) {
            el.addElement(less(op));
        }
        return buildCompare(el, in.getOperator());
    }
    private Exp buildCompare(ExpList el, Token operator) {
        Compare.Operator op = tokenToOperator(operator);
        if(el.size()>1) {
            return new Compare(el.elementAt(0),buildCompare(el.sublist(), operator), op);
        } else {
            return el.elementAt(0);
        }
    }
    
    private Compare.Operator tokenToOperator(Token t) {
        if(t == null)
            return Compare.Operator.NOP;
        switch(t.kind) {
            case ParserConstants.LT:
                return Compare.Operator.LT;
            case ParserConstants.LEQ:
                return Compare.Operator.LTEQ;
            case ParserConstants.GT:
                return Compare.Operator.GT;
            case ParserConstants.GEQ:
                return Compare.Operator.GTEQ;
            case ParserConstants.EQ:
                return Compare.Operator.EQ;
            case ParserConstants.NEQ:
                return Compare.Operator.NEQ;
            default:
                error.complain("Parser error: Invalid comparison operator "+t.image,t.beginLine);
                return null;
        }
    }   

    private Exp less(basic_tree.LessOperand in) {
        ExpList pl = new ExpList();
        ExpList ml = new ExpList();
        for(Term op : in.getOperands()) {
            if(op instanceof MinusTerm) {
                ml.addElement(term(op));
            } else { //PlusTerm and Term
                pl.addElement(term(op));
            }
        }
        Exp plus = buildPlus(pl);
        if(ml.size()>0) {
            Exp minus = buildPlus(ml);
            return new Minus(plus,minus);
        } else {
            return plus;
        }
    }
    private Exp buildPlus(ExpList el) {
        if(el.size()>1) {
            return new Plus(el.elementAt(0),buildPlus(el.sublist()));
        } else {
            return el.elementAt(0);
        }
    }

    private Exp term(basic_tree.Term in) {
        ExpList el = new ExpList();
        for(Factor op : in.getOperands()) {
            el.addElement(factor(op));
        }
        return buildMult(el);
    }
    private Exp buildMult(ExpList el) {
        if(el.size()>1) {
            return new Times(el.elementAt(0),buildMult(el.sublist()));
        } else {
            return el.elementAt(0);
        }
    }
    private Exp factor(basic_tree.Factor in) {
        Exp cur = primary(in.getPrimary());
        
        for(Sufix s : in.getSufix()) {
            cur = sufix(cur,s);
        }
        return cur;
    }

    private Exp sufix(Exp cur, Sufix in) {
        if(in instanceof LengthSufix) {
            return new ArrayLength(cur, in.line_number());
        } else if(in instanceof IndexSufix) {
            return new ArrayLookup(cur,exp(((IndexSufix)in).getExpression()), in.line_number());
        } else if(in instanceof MethodSufix) {
            MethodSufix ms= (MethodSufix) in;
            ExpList el=new ExpList();
            for(Expression e : ms.getParameters()) {
                el.addElement(exp(e));
            }
            return new Call(cur,id(ms.getMethodName()),el, in.line_number());
        }
        error.complain("Internal error: Unknown sufix!",in.line_number());
        return null;
    }

    private Exp primary(basic_tree.Primary in) {
        Exp e;
        if(in instanceof IntPrimary) {
            IntPrimary ip = (IntPrimary) in;
            e = new IntegerLiteral(ip.getInt());
        } else if(in instanceof IdPrimary) {
            IdPrimary ip = (IdPrimary) in;
            if(ip.getId().equals("this")) {
                e = new This();
            } else {
                e = new IdentifierExp(ip.getId());
            }
        } else if(in instanceof BooleanPrimary) {
            BooleanPrimary bp = (BooleanPrimary) in;
            if(bp.getBoolean()) {
                e = new True();
            } else {
                e = new False();
            }
        } else if(in instanceof NewIntPrimary) {
            NewIntPrimary ip = (NewIntPrimary) in;
            e = new NewArray(exp(ip.getExpression()));
        } else if(in instanceof NewPrimary) {
            NewPrimary ip = (NewPrimary) in;
            e = new NewObject(id(ip.getId()));
        } else if(in instanceof NotExpressionPrimary) {
            NotExpressionPrimary nep = (NotExpressionPrimary) in;
            e = new Not(and(nep.getOperand()));
        } else if(in instanceof ExpressionPrimary) {
            ExpressionPrimary ep = (ExpressionPrimary) in;
            e = exp(ep.getExpression());
        } else {
            error.complain("Internal error: Unknown primary "+in+"!", in.line_number);
            return null;
        }
        e.line_number = in.line_number;
        return e;
    }
    
    protected static int interpolate_line_number(Exp e1, Exp e2) {
        if(e1.line_number == e2.line_number)
            return e1.line_number;
        
        if(e1.line_number == -1)
            return e2.line_number;
        
        if(e2.line_number == -1)
            return e1.line_number;
        
        return (e1.line_number+e2.line_number)/2;
    }
}
