package basic_tree;


public class SetStatement extends Statement {

	private String id;
	private Expression expression;

	/**
	 * Use when no brackets are used like: id = expression;
	 * @param id
	 * @param expression
	 */
	public SetStatement(String id, Expression expression) {
		super();
		this.id = id;
		this.expression = expression;
	}

	public String getId() {
		return id;
	}

	public Expression getExpression() {
		return expression;
	}

	
	
}
