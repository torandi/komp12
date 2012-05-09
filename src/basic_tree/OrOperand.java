package basic_tree;

import java.util.ArrayList;

public class OrOperand {
    private ArrayList<AndOperand> operands = new ArrayList<AndOperand>();
    public int line_number=-1;

    public OrOperand(AndOperand op) {
        operands.add(op);
        line_number = op.line_number;
    }

	public ArrayList<AndOperand> getOperands() {
		return operands;
	}

	public void addOperand(AndOperand op) {
		operands.add(op);
	}

    public String toString() {
        String ret="[OrOperand: ";
        for(AndOperand op : operands) {
            ret+=op.toString()+" ";
        }
        ret+="]";
        return ret;
    }	
}
