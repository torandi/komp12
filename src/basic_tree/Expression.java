package basic_tree;


import java.util.ArrayList;

public class Expression {
    private ArrayList<OrOperand> operands = new ArrayList<OrOperand>();
    public int line_number=-1;

    public Expression(OrOperand op) {
        operands.add(op);
        line_number = op.line_number;
    }

	public ArrayList<OrOperand> getOperands() {
		return operands;
	}

	public void addOperand(OrOperand op) {
		operands.add(op);
	}

    public String toString() {
        String ret="[Expression: ";
        for(OrOperand op : operands) {
            ret+=op.toString()+" ";
        }
        ret+="]";
        return ret;
    }
    
}
