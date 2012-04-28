package jvm;

public class LongInFrame implements frame.VMAccess
{
    public LongInFrame(String name, int offset, String signature) {
	n = name;
	o = offset;
	s = signature;
    }

    public String toString() {
	return "jvm.LongInFrame(" + o + ", " + n + ", " + s + ")";
    }

    public String declare() {
	return ".var " + o + " is '" + n + "' " + s;
    }

    public String load() {
	switch(o) {
	case 0: return "lload_0 ; " + n;
	case 1: return "lload_1 ; " + n;
	case 2: return "lload_2 ; " + n;
	case 3: return "lload_3 ; " + n;
	default:
	    return "lload " + o + " ; " + n;
	}
    }

    public String store() {
	switch(o) {
	case 0: return "lstore_0 ; " + n;
	case 1: return "lstore_1 ; " + n;
	case 2: return "lstore_2 ; " + n;
	case 3: return "lstore_3 ; " + n;
	default:
	    return "lstore " + o + " ; " + n;
	}
    }

    private int o;
    private String n;
    private String s;

    @Override
    public int words() {
        return 2;
    }
}
