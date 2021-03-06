package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class Print extends Syntax implements Statement {

    public Exp e;

    public Print(Exp ae) {
        e = ae;
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
