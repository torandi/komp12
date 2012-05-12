
package error;

import error.TypeException;
import syntaxtree.Type;

/**
 *
 * @author torandi
 */
public class ImplicitCast extends TypeException {
    public Type cast_to;
    
    public ImplicitCast(Type to ) {
        cast_to = to;
    }
    
}
