package basic_tree;

import java.util.ArrayList;
import parse.Token;

public class MethodSufix implements Sufix {

    private String methodName;
    private ArrayList<Expression> parameters = new ArrayList<Expression>();
    public int line_number;
    
    public MethodSufix(Token t) {
        this.methodName = t.image;
        line_number = t.beginLine;
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

    
    @Override
    public int line_number() {
       return line_number;
    }
}
