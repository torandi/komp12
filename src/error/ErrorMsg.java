package error;

public class ErrorMsg
{
    public boolean anyErrors;
    private java.io.PrintStream out;

    public ErrorMsg(java.io.PrintStream o) {
	anyErrors = false;
	out = o;
    }

    public void complain(String msg) {
	anyErrors = true;
	out.println(msg);
    }
}
