package tree;
public class EXP extends Stm {
  public Exp exp; 
  public EXP(Exp e) {exp=e;}
  public ExpList kids() {return new ExpList(exp);}
  public Stm build(ExpList kids) {
    return new EXP(kids.head);
  }
}

