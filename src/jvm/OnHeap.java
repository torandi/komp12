package jvm;

public class OnHeap implements frame.VMAccess
{
    int words;
    public OnHeap(String className, String fieldName, String signature, int words) {
	c = className;
	f = fieldName;
	s = signature;
        this.words = words;
    }

    public String toString() {
	return "jvm.OnHeap(" + c + ", " + f + ", " + s + ")";
    }

    public String declare() {
	return ".field public '" + f + "' " + s;
    }

    public String load() {
        return
	    "aload_0 ; this\n" +
	    "\tgetfield '" + c + "/" + f + "' " + s;
    }

    // Before using this the following content must be on the stack:
    // data (latest push)
    // object
    public String store() {
        return "putfield '" + c + "/" + f + "' " + s;
    }

    private String c;
    private String f;
    private String s;

    @Override
    public int words() {
        return words;
    }
}
