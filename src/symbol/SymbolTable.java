package symbol;

import java.util.Iterator;
import java.util.LinkedList;
import syntaxtree.Identifier;
import syntaxtree.Type;

public class SymbolTable {
    private LinkedList<Scope> scopes=new LinkedList<Scope>();

    public void pushScope(Scope s) {
        scopes.addFirst(s);
    }

    public void popScope() {
        scopes.removeFirst();
    }

    public Symbol lookup(String id) {
        Symbol sym=null;
        for(Scope s : scopes) {
            sym = s.lookup(id);
            if(sym!=null)
                return sym;
        }
        return null;
    }

    public boolean addVariable(Identifier id, Symbol sym) {
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
