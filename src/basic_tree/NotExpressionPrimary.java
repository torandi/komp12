package basic_tree;


public class NotExpressionPrimary extends Primary{

	private AndOperand ao;

	public NotExpressionPrimary(AndOperand op) {
		super();
		ao = op;
	}

	public AndOperand getOperand() {
		return ao;
	}
	
}
