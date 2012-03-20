package tree;
public class ExpList {
    public Exp head;
    public ExpList tail;
    public ExpList(Exp h, ExpList t) {head=h; tail=t;}
    public ExpList(Exp h)            {head=h; tail=null;}
    public ExpList(Exp e1, Exp e2)   {head=e1; tail=new ExpList(e2);}
    /*
    public ExpList(Exp e1, Exp e2, Exp e3) {
	head=e1; tail=new ExpList(e2, e3);
   
	}
 */
}



