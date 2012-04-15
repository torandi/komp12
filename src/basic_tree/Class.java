package basic_tree;

import java.util.ArrayList;


public 
class Class{
	private String name;
        private String super_class = null;
	private ArrayList<Variable> variables = new ArrayList<Variable>();
	private ArrayList<Method> methods = new ArrayList<Method>();
	
	public Class(String name) {
		this.name = name;
	}
       
        public void setSuper(String s) {
            super_class = s;
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

        public String getSuperClass() {
            return super_class;
        }
}
