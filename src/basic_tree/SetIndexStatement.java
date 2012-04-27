package basic_tree;

import parse.Token;


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
	public SetIndexStatement(Token t, Expression expression1, Expression expression2) {
            super(t.beginLine);
            this.id = t.image;
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
