package basic_tree;


public abstract class Statement {
    public int line_number=-1;
    
    protected Statement(int line) {
        line_number = line;
    }
}

