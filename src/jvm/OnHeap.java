package jvm;

public class OnHeap implements frame.VMAccess
{
    public OnHeap(String className, String fieldName, String signature) {
	c = className;
	f = fieldName;
	s = signature;
    }

    public String toString() {
	return "jvm.OnHeap(" + c + ", " + f + ", " + s + ")";
    }

    public String declare() {
	return ".field public " + f + " " + s;
    }

    public String load() {
        return
	    "    aload_0 ; this\n" +
	    "    getfield " + c + "/" + f + " " + s;
    }

    // We get one extra instruction here, since the arguments
    // end up in wrong order on the stack.
    public String store() {
        return
	    "    aload_0 ; this\n" +
	    "    swap\n" +
	    "    putfield " + c + "/" + f + " " + s;
    }

    private String c;
    private String f;
    private String s;
}
