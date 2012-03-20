package frame;

/**
   Interface that models an "access" to a variable. This interface is
   independent of the target architecture, but it is designed for
   stack-based virtual machines. For real processors, use the @see
   Access interface.
*/
public interface VMAccess
{
    /**
       @return The assembly language instructions and/or directives
       used to declare the variable.
    */
    public String declare();

    /**
       @return The assembly language instructions used to load the
       variable onto the stack.
    */
    public String load();

    /**
       @return The assembly language instructions used to store the
       topmost value on the stack into the variable.
    */
    public String store();
}
