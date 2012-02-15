package symbols;

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

    public Type lookup(String id) {
        Type t=null;
        for(Scope s : scopes) {
            t = s.lookup(id);
            if(t!=null)
                return t;
        }
        return null;
    }

    public boolean addVariable(Identifier id, Type t) {
        return scopes.getFirst().addVariable(id, t);
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
