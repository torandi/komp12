package symbols;

import java.util.HashMap;
import syntaxtree.Identifier;
import syntaxtree.Type;

public class Scope {
    private HashMap<String,Type> variables = new HashMap<String,Type>();
    /**
     * Finds the specified identifier in this scope
     * @return The type of the identifier or null
     */
    public Type lookup(String id) {
      return variables.get(id.intern());
    }

    public Scope() {}

    /**
    * Adds the specified variable to this scope
    * @return True if successfull, False if the variable was already declared
    */
    public boolean addVariable(Identifier id,Type t) {
      return (variables.put(id.s.intern(), t)==null);
    }
}
