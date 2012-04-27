package basic_tree;

import parse.Token;


public class SetStatement extends Statement {

	private String id;
	private Expression expression;

	/**
	 * Use when no brackets are used like: id = expression;
	 * @param id
	 * @param expression
	 */
	public SetStatement(Token t, Expression expression) {
            super(t.beginLine);
            this.id = t.image;
            this.expression = expression;
	}

	public String getId() {
		return id;
	}

	public Expression getExpression() {
		return expression;
	}

	
	
}
