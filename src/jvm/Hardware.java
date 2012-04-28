package jvm;

import syntaxtree.LongType;
import syntaxtree.BooleanType;
import syntaxtree.FormalList;
import syntaxtree.IdentifierType;
import syntaxtree.ArrayType;
import syntaxtree.IntegerType;
import syntaxtree.Type;

public class Hardware
{
    public static String signature(Type t) {
	if(t instanceof BooleanType) {
	    return "Z";
	}
	else if(t instanceof IntegerType) {
	    return "I";
	}
	else if(t instanceof LongType) {
	    return "J";
	}
        else if(t instanceof ArrayType) {
	    return "["+signature(((ArrayType)t).base_type);
	}
	else if(t instanceof IdentifierType) {
	    return "L" + t.toString() + ";";
	}
	else if(t instanceof IdentifierType) {
	    return "L" + t.toString() + ";";
	}
	else {
	    throw new InternalError("Unknown type " + t);
	}
    }

    public static String methodSignature(FormalList formals, Type returnType) {
	String sig = "(";
	if(formals != null) {
	    for(int i = 0; i < formals.size(); i++) {
		sig = sig + Hardware.signature(formals.elementAt(i).t);
	    }
	}
	sig = sig + ")";
	if(returnType != null) {
	    sig = sig + Hardware.signature(returnType);
	}
	else {
	    sig = sig + "V";
	}
	return sig;
    }
}
