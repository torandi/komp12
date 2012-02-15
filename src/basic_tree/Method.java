package basic_tree;

import java.util.ArrayList;

public class Method {
	
	private String name;
	private Type returnType;
	private Expression returnExpression;
	private ArrayList<Variable> parameters = new ArrayList<Variable>();
	private ArrayList<Variable> variables = new ArrayList<Variable>();
	private ArrayList<Statement> statements = new ArrayList<Statement>();
	
	public Method(String name, Type returnType) {
		this.name = name;
		this.returnType = returnType;
	}

	public Type getReturnType() {
		return returnType;
	}
	
	public Expression getReturnExpression() {
		return returnExpression;
	}

	public void setReturnExpression(Expression returnExpression) {
		this.returnExpression = returnExpression;
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
