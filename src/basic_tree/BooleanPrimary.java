package basic_tree;

import parse.Token;


public class BooleanPrimary extends Primary{

	private boolean b;

	public BooleanPrimary(Token t) {
		super(t.beginLine);
		b = (t.image.equals("true"));
	}

	public boolean getBoolean() {
		return b;
	}
	
	
}
