package frame;
import syntaxtree.Type;
import syntaxtree.FormalList;

public interface VMFactory 
{
    public abstract VMFrame newFrame(String methodName, FormalList formals, Type returnType);
    public abstract VMRecord newRecord(String name);
}

