package basic_tree;


public class IfStatement extends Statement {
	
	//private ArrayList<ElseIf> list = new ArrayList<ElseIf>();
	
	private Expression expression;
	private Statement ifStatement;
	private Statement elseStatement = null;
	
	public IfStatement(Expression expression, Statement ifStatement) {
		//list.add(new ElseIf(e, b));
		this.expression = expression;
		this.ifStatement = ifStatement;
	}
	
	public void addElseStatement(Statement elseStatement) {
		this.elseStatement = elseStatement;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public Statement getIfStatement() {
		return ifStatement;
	}
	
	public Statement getElseStatement() {
		return elseStatement;
	}
	/*
	public ArrayList<ElseIf> getListOfStatements() {
		return list;
	}
	*/
	
	
	/**
	 * 
	 * @param e the expression, NULL if just an "else"
	 * @param b the BasicStatement
	 */
//	public void addElseIf(Expression e, BasicStatement b) {
//		list.add(new ElseIf(e, b));
//	}
	
	/*

	class ElseIf {
		private Expression expression;
		private BasicStatement basicStatement;
		
		public ElseIf(Expression e, BasicStatement b) {
			this.expression = e;
			this.basicStatement = b;
		}

		public Expression getExpression() {
			return expression;
		}

		public BasicStatement getBasicStatement() {
			return basicStatement;
		}
		
	}
		 */
}