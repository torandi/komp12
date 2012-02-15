package basic_tree;

import java.util.ArrayList;

public class AndOperand {
    private ArrayList<LessOperand> operands = new ArrayList<LessOperand>();

    public AndOperand(LessOperand op) {
        operands.add(op);
    }

	public ArrayList<LessOperand> getOperands() {
		return operands;
	}

	public void addOperand(LessOperand op) {
		operands.add(op);
	}

	public String toString() {
            String ret="[AndOperand: ";
            for(LessOperand op : operands) {
                ret+=op.toString()+" ";
            }
            ret+="]";
            return ret;
        }
	
}
