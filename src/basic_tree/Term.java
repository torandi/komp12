package basic_tree;

import java.util.ArrayList;

public class Term {

    private ArrayList<Factor> operands = new ArrayList<Factor>();

    public Term(Factor op) {
        operands.add(op);
    }
    
    protected Term(Term t) {
    	operands = t.operands;
    }

	public ArrayList<Factor> getOperands() {
		return operands;
	}

	public void addOperand(Factor op) {
		operands.add(op);
	}

        public String toString() {
            return toString("");
        }

        public String toString(String type) {
            String ret="[Term"+type+": ";
            for(Factor f : operands) {
                ret+=f.toString()+" ";
            }
            ret+="]";
            return ret;
        }

	
}
