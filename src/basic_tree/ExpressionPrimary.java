package basic_tree;


public class ExpressionPrimary extends Primary {

	private Expression expression;

	public ExpressionPrimary(Expression expression, int line) {
		super(line);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}
	
	public String toString() {
           return "[ExpressionPrimary: "+expression.toString()+"]";
        }
	
}
