package error;

public class ErrorMsg
{
    public boolean anyErrors;
    public boolean showWarnings = false;
    
    private java.io.PrintStream out;
    private String src;

    public ErrorMsg(java.io.PrintStream o, String src) {
	anyErrors = false;
	out = o;
        this.src = src;
    }

    public void complain(String context, String msg, int line) {
        complain(msg+"\ncontext: "+context, line);
    }
    
    public void complain(String msg, int line) {
	anyErrors = true;
        if(line != -1)
            out.println("\nError: "+src+":"+line+": "+msg);
        else
            out.println("\nError: "+src+": "+msg);
            
    }
    
    public void warn(String context, String msg, int line) {
        warn(msg+"\ncontext: "+context, line);
    }
    
    public void warn(String msg, int line) {
        if(showWarnings) {
            if(line != -1)
                out.println("\nWarning: "+src+":"+line+": "+msg);
            else
                out.println("\nWarning: "+src+": "+msg);
        }
            
    }
}
