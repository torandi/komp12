package symbol;

import java.util.HashMap;
import syntaxtree.Identifier;

public abstract class Scope {
    private HashMap<String,Symbol> variables = new HashMap<String,Symbol>();
    /**
     * Finds the specified identifier in this scope
     * @return The symbol of the identifier or null
     */
    public Symbol lookup(String id) {
      return variables.get(id);
    }
    
    /**
    * Adds the specified variable to this scope
    * @return True if successfull, False if the variable was already declared
    */
    public boolean addVariable(Identifier id,Symbol s) {
      return (variables.put(id.toString(), s)==null);
    }
    
}
