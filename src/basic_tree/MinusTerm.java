package basic_tree;

public class MinusTerm extends Term {

	public MinusTerm(Term t) {
		super(t);
	}

        public String toString() {
            return toString("(minus)");
        }
	
}
