package basic_tree;

public class NewArrayPrimary extends Primary {

    private Type base_type;
    private Expression expression;

    public NewArrayPrimary(Type base_type, Expression expression, int line) {
        super(line);
        this.base_type = base_type;
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public Type getBaseType() {
        return base_type;
    }

    public boolean valid() {
        return ArrayType.validate_base_type(base_type);
    }
}
