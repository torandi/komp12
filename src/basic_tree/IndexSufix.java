package basic_tree;

/**
 * [Expression] sufix
 */
public class IndexSufix implements Sufix{
    private Expression expression;

    public IndexSufix(Expression expression) {
        this.expression=expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
