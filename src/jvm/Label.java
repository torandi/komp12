package jvm;

/**
 *
 * @author torandi
 */
public class Label {
    private int num;
    
    public Label(int n) {
        num = n;
    }
    
    public String name() {
        return "L"+num;
    }
    
    public String declare() {
        return name()+":";
    }
}
