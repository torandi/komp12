package basic_tree;

public class LengthSufix implements Sufix{
    public int line_number;
    
    public LengthSufix(int line) {
        line_number = line;
    }
    
    @Override
    public int line_number() {
       return line_number;
    }
}
