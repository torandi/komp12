package basic_tree;

import java.util.ArrayList;
import parse.Token;

public class AndOperand {
    private ArrayList<LessOperand> operands = new ArrayList<LessOperand>();
    private Token operator;
    int line_number;

    public AndOperand(LessOperand op) {
        line_number = op.getOperands().get(0).getOperands().get(0).getPrimary().line_number;
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
    
    public Token getOperator() {
        return operator;
    }
    
    public void setOperator(Token op) {
        operator = op;
    }
	
}
