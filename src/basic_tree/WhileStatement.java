package basic_tree;


public class WhileStatement extends Statement{

	private Expression expression;
	private Statement statement;
	

	public WhileStatement(Expression expression, Statement statement) {
		super();
		this.expression = expression;
		this.statement = statement;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	
	
	
	
}
