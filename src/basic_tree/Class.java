package basic_tree;

import java.util.ArrayList;


public 
class Class{
	private String name;
	private ArrayList<Variable> variables = new ArrayList<Variable>();
	private ArrayList<Method> methods = new ArrayList<Method>();
	
	public Class(String name) {
		this.name = name;
	}
	
	public void addVariable(Variable v) {
		variables.add(v);
	}
	
	public void addMethod(Method m) {
		methods.add(m);
	}
	
	public ArrayList<Variable> getVariables() {
		return variables;
	}
	
	public ArrayList<Method> getMethods() {
		return methods;
	}
	
	public String getName() {
		return name;
	}
	
}
