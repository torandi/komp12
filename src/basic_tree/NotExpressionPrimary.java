package basic_tree;


public class NotExpressionPrimary extends Primary{

	private Primary primary;

	public NotExpressionPrimary(Primary primary) {
		super();
		this.primary = primary;
	}

	public Primary getPrimary() {
		return primary;
	}
	
}
