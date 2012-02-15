package basic_tree;


public class NotExpressionPrimary extends Primary{

	private Expression expression;

	public NotExpressionPrimary(Expression expression) {
		super();
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}
	
}
