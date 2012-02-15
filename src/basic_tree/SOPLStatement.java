package basic_tree;


public class SOPLStatement extends Statement {

	private Expression expression;

	public SOPLStatement(Expression expression) {
		super();
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	
	
}
