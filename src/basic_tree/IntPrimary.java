package basic_tree;

import parse.Token;


public class IntPrimary extends Primary{

	private int i;

	public IntPrimary(Token t) {
		super(t.beginLine);
		i = Integer.parseInt(t.image);
	}

	public int getInt() {
		return i;
	}
	
	
	public String toString() {
            return "[IntPrimary: "+i+"]";
        }
}
