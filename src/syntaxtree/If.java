package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class If extends Syntax implements Statement {
    public Exp e;
    public Statement s1;

    
     public If(Exp ae, Statement as1) {
        e = ae;
        s1 = as1;
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

