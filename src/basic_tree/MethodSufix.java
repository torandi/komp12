package basic_tree;

import java.util.ArrayList;

public class MethodSufix implements Sufix {
    private String methodName;
    private ArrayList<Expression> parameters = new ArrayList<Expression>();

    public MethodSufix(String methodName) {
        this.methodName=methodName;
    }

	public ArrayList<Expression> getParameters() {
		return parameters;
	}

	public void addParameter(Expression parameter) {
		parameters.add(parameter);
	}

	public String getMethodName() {
		return methodName;
	}
    
    
    
    
}
