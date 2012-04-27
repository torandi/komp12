package basic_tree;

import parse.Token;


public class Variable {
	
	private String name;
	private Type type;
        public int line_number;
	
	public Variable(Token t, Type type) {
		this.name = t.image;
                line_number = t.beginLine;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
	
}

