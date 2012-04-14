
package symbol;

import syntaxtree.Type;


public class Symbol {
    public frame.VMAccess access;
    
    public Type type;
    public boolean initialized = false;
    
 
    public Symbol(Type t) {
        type = t;
    }
}
