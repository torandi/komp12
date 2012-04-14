package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class IdentifierType extends Type {
    public String s;
    public ClassDecl c=null;
    
    @Override
    public boolean equals(Type tp)
    {
	if (! (tp instanceof IdentifierType) ) return false;
	return ((IdentifierType)tp).s.equals(s);
    }
    
  public IdentifierType(String as) {
    s=as;
  }
  
  public IdentifierType(ClassDecl c) {
      s = c.i.s;
      this.c = c;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public String toString() {
      return s;
  }
}
