package frame;
import temp.Label;
import java.util.List;

/**
 * Implement a factory to return Frame and Record objects
 * for the appropriate architecture, e.g., sparc.Fatory, x86.Factory..
 */
public interface Factory 
{
    public abstract Frame newFrame(Label name, 
				   java.util.List<Boolean> formals);
    public abstract Record newRecord(String name);
}

