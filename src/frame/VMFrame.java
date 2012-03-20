package frame;

import syntaxtree.Type;

/**
   Interface for handling of frames on the runtime stack. The
   interface is independent of the target architecture, but it is
   designed for stack-based virtual machines. For real processors use
   the @see Frame interface.
*/
public interface VMFrame
{
    /**
       Allocates an Access object corresponding to a formal
       variable, i.e., a function parameter.
       The Access objects must be allocated in
       the order that the function arguments are declared.
       I.e., the leftmost argument should be allocated
       first and the rightmost argument should be allocated last.

       @param id The variable identifier.

       @param type The variable type.

       @return A VMAccess object that describes how the corresponding
       local variable is accessed from within the function.
    */
    public VMAccess allocFormal(String id, Type type);

    /**
       Allocates a VMAccess object corresponding to a local
       variable in the function. The VMAccess objects may
       be allocated in any order, but all formal variables
       must be allocated before any local variable is
       allocated.

       @param id The variable identifier.

       @param type The variable type.

       @return A VMAccess object that describes how the corresponding
       local variable is accessed from within the function.
    */
    public VMAccess allocLocal(String id, Type type);

    /**
       @return The number of formal variables allocated.
    */
    public int numberOfFormals();

    /**
       @return The number of local variables allocated.
    */
    public int numberOfLocals();

    /**
       @return The identifier that should be used when
       calling the function.
    */
    public String procEntry();
};
