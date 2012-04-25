package symbol;

import java.util.Iterator;
import java.util.LinkedList;
import syntaxtree.Identifier;
import syntaxtree.MethodDecl;

public class SymbolTable {
    private LinkedList<Scope> scopes=new LinkedList<Scope>();

    public void pushScope(Scope s) {
        scopes.addFirst(s);
    }

    public void popScope() {
        scopes.removeFirst();
    }
    
    public Symbol lookup(String id) {
        return lookup(id, false);
    }

    /*
     * finds the variable with name id in the current scope. 
     * @param onlyMethodScope Set to true to stop after method scope has been checked
     */
    public Symbol lookup(String id, boolean onlyMethodScope) {
        Symbol sym=null;
        for(Scope s : scopes) {
            sym = s.lookup(id);
            if(sym!=null)
                return sym;
            else if(onlyMethodScope && s instanceof MethodDecl) 
                return null;
        }
        return null;
    }

    public boolean addVariable(Identifier id, Symbol sym) {
        if(lookup(id.s, true) != null)
            return false;
        return scopes.getFirst().addVariable(id, sym);
    }

    public Scope peek() {
        return scopes.getFirst();
    }

    public String toString() {
        StringBuilder sb= new StringBuilder();
        Iterator<Scope> it = scopes.descendingIterator();
        while(it.hasNext()) {
            sb.append(it.next().toString());
            sb.append(".");
        }
        return sb.substring(0, sb.length()-1);
    }

}
