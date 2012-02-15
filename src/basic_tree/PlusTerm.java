package basic_tree;

public class PlusTerm extends Term {

	public PlusTerm(Term t) {
		super(t);
	}
	public String toString() {
            return toString("(plus)");
        }
}
