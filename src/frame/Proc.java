package frame;

public class Proc 
{
    public String begin, end;
    public java.util.List<assem.Instr> body;
    public Proc(String bg, java.util.List<assem.Instr> bd, String ed)
    {
	begin = bg; end = ed; body = bd;	
    }
}
