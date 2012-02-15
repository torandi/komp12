package basic_tree;


public class BooleanPrimary extends Primary{

	private boolean b;

	public BooleanPrimary(String s) {
		super();
		b = (s.equals("true"));
	}

	public boolean getBoolean() {
		return b;
	}
	
	
}
