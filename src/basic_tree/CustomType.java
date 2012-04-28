package basic_tree;

import parse.Token;

public class CustomType extends Type {

    private String id;

    public CustomType(Token t) {
        super(t.beginLine);
        this.id = t.image;
    }

    public String getId() {
        return id;
    }
    
    public String toString() {
        return id;
    }
}
