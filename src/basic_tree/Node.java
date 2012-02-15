package basic_tree;

import parse.*;

import java.util.LinkedList;

public class Node {
	public Token token=null;
	public String rule=null;

	public LinkedList< Node > children;

	public Node(Token token) {
		this.token=token;
		children=new LinkedList< Node >();
	}

	public Node(String rule) {
		this.rule = rule;
		children=new LinkedList< Node >();
	}

	public void addChild(Node child) {
		children.addLast(child);
	}

	public Node addChild(Token child) {
		Node n = new Node(child);
		children.addLast(n);
		return n;
	}

	public Node addChild(String child) {
		Node n = new Node(child);
		children.addLast(n);
		return n;
	}

	public boolean isToken() {
		return token!=null;
	}

	public String toString(Parser p) {
		return toString(0,p);
	}

	public String toString(int depth,Parser p) {
		String ret="";
		String tabs="";
		for(int i=0;i<depth;++i) {
				tabs+="\t";
		}
		ret+=tabs;
		if(isToken()) {
			ret+=p.tokenImage[token.kind];
			if(token.image!=null) {
				ret+=":"+token.image;
			}
		} else if(rule!=null) {
			ret+=rule;
		} else {
			ret+="error";
		}
		for(Node n : children) {
			ret+="\n"+tabs+"\t"+n.toString(depth+1,p);
		}
		return ret;
	}
}
