package tree;
import temp.Label;
import temp.LabelList;


public class JUMP extends Stm {
  public Exp exp;
  public LabelList targets;
  public JUMP(Exp e, LabelList t) {exp=e; targets=t;}
  public JUMP(Label target) {
      this(new NAME(target), new LabelList(target));
  }
  public ExpList kids() {return new ExpList(exp);}
  public Stm build(ExpList kids) {
    return new JUMP(kids.head,targets);
  }
}

