
package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

/**
 *
 * @author torandi
 */
public class TypeCast extends Exp {

    public Exp exp;
    public Type target_type;
    

    public TypeCast(Type target, Exp e, int line) {
        target_type = target;
        exp = e;
        line_number = line;
        type = target_type;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    public String toString() {
        return "expression statement";
    }

}
