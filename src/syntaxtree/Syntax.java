
package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

/**
 *
 * @author torandi
 */
public abstract class Syntax {
    public int line_number=-1;
    
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor v);
}
