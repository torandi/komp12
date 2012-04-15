package jvm;

/**
 *
 * @author torandi
 */
public class Label {
    private int num;
    private String name;
    
    public Label(int n, String hint) {
        num = n;
        name = "L"+n;
        if(hint != null)
            name += "_"+hint;
    }
    
    public String name() {
        return name;
    }
    
    public String declare() {
        return name()+":";
    }
}
