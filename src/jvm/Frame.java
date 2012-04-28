package jvm;

import syntaxtree.FormalList;
import syntaxtree.Type;

/**
   An implementation of the VMFrame interface for the Java
   virtual machine (JVM).
   @see frame.VMFrame
*/
public class Frame implements frame.VMFrame
{
    private int formals = 0;
    private int locals = 0;
    private int offset = 0;
    private String name;
    private String sig;

    public Frame(String methodName, FormalList formals, Type returnType) {
	name = methodName;
	sig = Hardware.methodSignature(formals, returnType);
    }

    public String toString() {
	return "jvm.Frame(" + name + ", " + sig + ")";
    }

    public frame.VMAccess allocFormal(String id, Type type) {
	frame.VMAccess access = alloc(id, Hardware.signature(type));
        formals += access.words();
        return access;
    }

    public frame.VMAccess allocLocal(String id, Type type) {
	frame.VMAccess access = alloc(id, Hardware.signature(type));
        locals += access.words();
        return access;
    }

    public int numberOfFormals() {
	return formals;
    }

    public int numberOfLocals() {
	return locals;
    }

    public String procEntry() {
	// All functions in the JVM are called by name concatenated
	// with type signature.
	return name + sig;
    }

    private frame.VMAccess alloc(String id, String signature) {
	// The signature is actually only used for debug purposes
	// in IntegerInFrame and ObjectInFrame, but it comes in
	// handy here, too, since we need to find out what kind
	// of VMAccess object to return.
        frame.VMAccess access;
	if(signature.equals("Z") || signature.equals("I"))
	    return new IntegerInFrame(id, formals+locals, signature);
        else if(signature.equals("J"))
	    return new LongInFrame(id, formals+locals, signature);
	else
	    return new ObjectInFrame(id, formals+locals, signature);
    }

}
