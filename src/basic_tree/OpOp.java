package basic_tree;

import parse.*;

/**
 * Class holding and operator and an operand (used in Expression)
 */
public class OpOp {
    private Token operator;
    private Factor  operand;

	public OpOp(Token operator, Factor operand) {
        this.operand=operand;
        this.operator=operator;
    }
	
    public Token getOperator() {
		return operator;
	}

	public Factor getOperand() {
		return operand;
	}
	
/*
    public Operand eval(Operand op) throws SyntaxError {
        return operator.eval(op, operand);
    }*/
}
