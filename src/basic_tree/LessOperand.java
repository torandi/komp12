package basic_tree;

import java.util.ArrayList;


public class LessOperand {
    private ArrayList<Term> operands = new ArrayList<Term>();

    public LessOperand(Term op) {
        operands.add(op);
    }

	public ArrayList<Term> getOperands() {
		return operands;
	}

	public void addOperand(Term op) {
		operands.add(op);
	}

        public String toString() {
            String ret="[LessOperand: ";
            for(Term t : operands) {
                ret+=t.toString()+" ";
            }
            ret+="]";
            return ret;
        }
}
