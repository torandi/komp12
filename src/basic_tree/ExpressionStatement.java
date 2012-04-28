package basic_tree;

public class ExpressionStatement extends Statement {

	private Expression expression;
	
	public ExpressionStatement(Expression e) {
		super(e.line_number); 
                expression = e;
	}

	
        public Expression getExpression() {
            return expression;
        }
	
	
	
}
