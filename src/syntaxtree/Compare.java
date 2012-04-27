package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Compare extends Exp {
  public Exp e1,e2;
  public Operator op;
  public enum Operator { LT, LTEQ, GT, GTEQ, EQ, NEQ, NOP }; 
  
  public Compare(Exp ae1, Exp ae2, Operator op) {
    e1=ae1; e2=ae2; this.op = op;
    line_number = AbstractTree.interpolate_line_number(e1, e2);
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public static String op_to_str(Operator op) {
      switch(op) {
          case LT:
              return "<";
          case LTEQ:
              return "<=";
          case GT:
              return ">";
          case GTEQ:
              return ">=";
          case EQ:
              return "==";
          case NEQ:
              return "!=";
          case NOP:
              return "NOP";
      }
      return "ERR";
  }
}
