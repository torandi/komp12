package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ArrayAssign extends Syntax implements Statement {
    public Identifier i;
    public Exp e1,e2;

    public ArrayAssign(Identifier ai, Exp ae1, Exp ae2, int line) {
        i=ai; e1=ae1; e2=ae2;
        line_number = line;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public int line_number() {
        return line_number;
    }
    
    @Override
    public void set_line_number(int line) {
        line_number = line;
    }

}

