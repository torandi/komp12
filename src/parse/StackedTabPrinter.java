package parse;

import java.io.PrintWriter;

/**
 *
 * @author torandi
 */
public class StackedTabPrinter {
    private PrintWriter pw;
    private int num_tabs = 0;
    private boolean printed_tabs = false;
    
    public StackedTabPrinter(PrintWriter pw) {
        this.pw  = pw;
    }
    
    private void print_tabs() {
        for(int i=0;i<num_tabs;++i)
            pw.print("\t");
        printed_tabs = true;
    }
    
    public void print(String s) {
        if(!printed_tabs)
            print_tabs();
        pw.print(s);
    }
    
    public void println(String s) {
        if(!printed_tabs)
            print_tabs();
        pw.println(s);
        printed_tabs = false;
    }
    
    public void println() {
        pw.println();
        printed_tabs = false;
    }
    
    public void add_tab() {
        ++num_tabs;
    }
    
    public void del_tab() {
        --num_tabs;
    }
    
    public void reset_tabs() {
        num_tabs = 0;
    }
}
