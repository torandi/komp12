package basic_tree;


public class SOPLStatement extends Statement {

	private Expression expression;

	public SOPLStatement(Expression expression, int line) {
		super(line);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	
	
}
