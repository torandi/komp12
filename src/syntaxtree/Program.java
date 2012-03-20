package syntaxtree;
import java.util.HashMap;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Program {
  public MainClass m;
  public ClassDeclList cl;

  public HashMap<String,ClassDecl> classes = new HashMap<String, ClassDecl>();

  public Program(MainClass am, ClassDeclList acl) {
    m=am; cl=acl;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public boolean addClass(Identifier id,ClassDecl c) {
      return (classes.put(id.s.intern(), c)==null);
  }

  public ClassDecl findClass(String s) {
      return classes.get(s.intern());
  }
}
