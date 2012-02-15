package basic_tree;

import java.util.ArrayList;


public class Start {

	Class mainClass;
	ArrayList<Class> classes = new ArrayList<Class>();
	
	public Start(Class mainClass) {
		super();
		this.mainClass = mainClass;
	}
	
	public Class getMainClass() {
		return mainClass;
	}
	
	public void addClass(Class c) {
		classes.add(c);
	}

	public ArrayList<Class> getClasses() {
		return classes;
	}
}
