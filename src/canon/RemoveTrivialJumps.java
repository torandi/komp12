package canon;

public class RemoveTrivialJumps
{
    tree.StmList stms;
    
    public tree.StmList stms() 
    {
	return stms;
    }
    
    public  RemoveTrivialJumps(tree.StmList s) 
    {
	stms = fix(s);	
    }
    
    private tree.StmList L(tree.Stm a, tree.StmList s)
    {
	return new tree.StmList(a,s);
    }
    
    private tree.StmList fix(tree.StmList ss)
    {
	if (ss == null) return null;

	tree.Stm h = ss.head;	
	tree.StmList t = fix(ss.tail);

	if ( t == null 
	     || ! (h instanceof tree.JUMP && (t.head instanceof tree.LABEL)))
	    return L(h,t);
	
	tree.JUMP  jmp = (tree.JUMP)  h;
	
	if (jmp.targets.tail != null)  
	    return L(h, t);
	
	String labl = ((tree.LABEL) t.head).label.toString();
	String labj = jmp.targets.head.toString();
	
	if (labl == labj) return t;
	
	return L(h,t);
    }
}

	
