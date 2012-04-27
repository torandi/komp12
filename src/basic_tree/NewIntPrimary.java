package basic_tree;


public class NewIntPrimary extends Primary{

	private Expression expression;
	
	public NewIntPrimary(Expression expression, int line) {
            super(line);
            this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}
	
	
}
