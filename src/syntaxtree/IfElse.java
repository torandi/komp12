
package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

/**
 *
 * @author torandi
 */
public class IfElse extends If {
    public Statement else_statement;
    
    public IfElse(Exp ae, Statement as1, Statement as2) {
        super(ae, as1);
        else_statement = as2;
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
