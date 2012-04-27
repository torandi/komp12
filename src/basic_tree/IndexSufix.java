package basic_tree;

/**
 * [Expression] sufix
 */
public class IndexSufix implements Sufix{
    private Expression expression;
    int line_number;

    public IndexSufix(Expression expression, int line) {
        this.expression=expression;
        line_number = line;
    }

    public Expression getExpression() {
        return expression;
    }
        
    @Override
    public int line_number() {
       return line_number;
    }
}
