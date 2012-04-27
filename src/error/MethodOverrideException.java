package error;

import syntaxtree.MethodDecl;

/**
 *
 * @author torandi
 */
public class MethodOverrideException extends Exception {
    public String found;
    public MethodDecl parent;
    
    public MethodOverrideException(MethodDecl parent, String found) {
        this.parent = parent;
        this.found = found;
    }
}
