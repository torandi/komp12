package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class Assign extends Syntax implements Statement {

    public Identifier i;
    public Exp e;

    public Assign(Identifier ai, Exp ae) {
        i = ai;
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
