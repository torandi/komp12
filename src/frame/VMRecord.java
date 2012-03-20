package frame;

import syntaxtree.Type;

/**
   Interface for handling of heap objects. The interface is
   independent of the target architecture, but it is designed for
   stack-based virtual machines. For real processors use the @see
   Frame interface.
*/
public interface VMRecord
{
    /**
       Allocates a VMAccess object corresponding to a member
       of a record, i.e., a class variable.

       @param id The variable identifier.

       @param type The variable type.

       @return A VMAccess object that describes how the corresponding
       class variable is accessed.
    */
    public VMAccess allocField(String id, Type type);

    /**
       @return The number of allocated fields in the record.
    */
    public int numberOfFields();
};
