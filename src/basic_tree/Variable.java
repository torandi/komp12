package basic_tree;


public class Variable {
	
	private String name;
	private Type type;
	
	public Variable(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
	
}

