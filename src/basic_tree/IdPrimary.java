package basic_tree;


public class IdPrimary extends Primary {

	private String id;

	public IdPrimary(String s) {
		super();
		id = s;
	}

	public String getId() {
		return id;
	}

        public String toString() {
            return "[IdPrimary: "+id+"]";
        }
}
