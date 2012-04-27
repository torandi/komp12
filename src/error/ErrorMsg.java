package error;

public class ErrorMsg
{
    public boolean anyErrors;
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
            out.println("\n"+src+":"+line+": "+msg);
        else
            out.println("\n"+src+": "+msg);
            
    }
}
