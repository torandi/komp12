package basic_tree;

import parse.Token;


public class LongPrimary extends Primary{

	private long l;

	public LongPrimary(Token t) {
		super(t.beginLine);
		l = Long.parseLong(t.image.substring(0, t.image.length()-1));
	}

	public long getLong() {
		return l;
	}
	
	
	public String toString() {
            return "[LongPrimary: "+l+"]";
        }
}
