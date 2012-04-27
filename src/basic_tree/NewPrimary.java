package basic_tree;

import parse.Token;


public class NewPrimary extends Primary {

	private String id;
	
	public NewPrimary(Token t) {
            super(t.beginLine);
            this.id = t.image;
	}

	public String getId() {
		return id;
	}
	
	
}
