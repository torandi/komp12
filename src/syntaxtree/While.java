package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class While extends Syntax implements Statement {

    public Exp e;
    public Statement s;

    public While(Exp ae, Statement as) {
        e = ae;
        s = as;
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
}
