package basic_tree;


public class IntPrimary extends Primary{

	private int i;

	public IntPrimary(String s) {
		super();
		i = Integer.parseInt(s);
	}

	public int getInt() {
		return i;
	}
	
	
	public String toString() {
            return "[IntPrimary: "+i+"]";
        }
}
