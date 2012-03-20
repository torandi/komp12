
package symbol;

import syntaxtree.Type;


public class Symbol {
    public frame.VMAccess access;
    
    public Type type;
    
 
    public Symbol(Type t) {
        type = t;
    }
}
