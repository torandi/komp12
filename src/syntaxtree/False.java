package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class False extends Exp {
    public False() {
        type = new BooleanType(-1);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
