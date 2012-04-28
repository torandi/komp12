package basic_tree;

import java.util.ArrayList;
import parse.Token;

public class Method {
	
	private String name;
	private Type returnType;
	private Expression returnExpression;
        private boolean hasReturn = false;
	private ArrayList<Variable> parameters = new ArrayList<Variable>();
	private ArrayList<Variable> variables = new ArrayList<Variable>();
	private ArrayList<Statement> statements = new ArrayList<Statement>();
	
        public int line_number;
        
        public int last_line;
        
	public Method(Token t, Type returnType) {
		this.name = t.image;
                line_number = t.beginLine;
		this.returnType = returnType;
	}

	public Type getReturnType() {
		return returnType;
	}
	
	public Expression getReturnExpression() {
		return returnExpression;
	}

	public void setReturnExpression(Expression returnExpression) {
            hasReturn = true;
            this.returnExpression = returnExpression;
	}
        
        public void setHasReturn() {
            hasReturn = true;
        }
        
        public boolean hasReturn() {
            return hasReturn;
        }

	public ArrayList<Variable> getParameters() {
		return parameters;
	}

	public void addParameter(Variable parameter) {
		parameters.add(parameter);
	}

	public ArrayList<Variable> getVariables() {
		return variables;
	}

	public void addVariable(Variable variable) {
		variables.add(variable);
	}

	public ArrayList<Statement> getStatements() {
		return statements;
	}

	public void addStatement(Statement statement) {
		statements.add(statement);
	}

	public String getName() {
		return name;
	}

}
