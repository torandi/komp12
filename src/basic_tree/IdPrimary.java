package basic_tree;

import parse.Token;


public class IdPrimary extends Primary {

	private String id;

	public IdPrimary(Token t) {
		super(t.beginLine);
		id = t.image;
	}

	public String getId() {
		return id;
	}

        public String toString() {
            return "[IdPrimary: "+id+"]";
        }
}
