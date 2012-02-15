package basic_tree;

import java.util.ArrayList;

public class Factor {
    private Primary primary;
    private ArrayList<Sufix> sufix = new ArrayList<Sufix>();

    public Factor(Primary prim) {
        primary=prim;
    }
    
    public void addSufix(Sufix s) {
    	sufix.add(s);
    }
    
    public ArrayList<Sufix> getSufix() {
    	return sufix;
    }

    public Primary getPrimary() {
    	return primary;
    }
    

    public String toString() {
        String ret="[Factor: "+primary.toString()+", sufix: [";
        for(Sufix s : sufix) {
            ret+=s.toString()+" ";
        }
        ret+="]]";
        return ret;
    }
}
