package basic_tree;


public class SetIndexStatement extends Statement{

	private String id;
	private Expression expression;
	private Expression bracketExpression;

	/**
	 * Use when square-brackets are used like: id[expression] = expression;
	 * @param id
	 * @param expression1 the expression in brackets
	 * @param expression2 
	 */
	public SetIndexStatement(String id, Expression expression1, Expression expression2) {
		this.id = id;
		this.bracketExpression = expression1;
		this.expression = expression2;
	}
	

	public String getId() {
		return id;
	}

	public Expression getExpression() {
		return expression;
	}
	
	public Expression getBracketExpression() {
		return bracketExpression;
	}
	
}
