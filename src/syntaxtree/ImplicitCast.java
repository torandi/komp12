
package syntaxtree;

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
