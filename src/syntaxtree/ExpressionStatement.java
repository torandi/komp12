package syntaxtree;

import symbol.Scope;
import visitor.Visitor;
import visitor.TypeVisitor;

/**
 * A statement consisting of only an expression
 * Note that an expression returns something, but that the output shall
 * be ignored
 * @author torandi
 */
public class ExpressionStatement extends Scope implements Statement {

    public Exp exp;

    public ExpressionStatement(Exp e) {
        exp = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public String toString() {
        return "expression statement";
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
