/* Generated By:JavaCC: Do not edit this line. Parser.java */
        package parse;
        import basic_tree.*;

        public class Parser implements ParserConstants {
                public static void main(String args[])
                throws ParseException, TokenMgrError, ParseException
                {
                        Parser parser = new Parser(System.in);
                        Start s = parser.Start();
                        System.out.println(s.toString());
                }

/* Parser */
  final public Start Start() throws ParseException, ParseException {
        Start start;
        basic_tree.Class c;
    c = MainClass();
          start= new Start(c);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CLASS:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      c = ClassDecl();
                  start.addClass(c);
    }
          {if (true) return start;}
    throw new Error("Missing return statement in function");
  }

  final public basic_tree.Class MainClass() throws ParseException, ParseException {
        basic_tree.Class mainClass;
        Token className,method,type,id;
    jj_consume_token(CLASS);
    className = jj_consume_token(ID);
    jj_consume_token(OBRACE);
    jj_consume_token(PUBLIC);
    jj_consume_token(STATIC);
    jj_consume_token(VOID);
    method = jj_consume_token(ID);
    jj_consume_token(OPAR);
    type = jj_consume_token(ID);
    jj_consume_token(OSB);
    jj_consume_token(CSB);
    id = jj_consume_token(ID);
    jj_consume_token(CPAR);
    jj_consume_token(OBRACE);
                //Check id's
                if( !method.image.equals("main") || !type.image.equals("String")) {
                        {if (true) throw new ParseException("Expected method public static void main (String[] id)");}
                }
                mainClass = new basic_tree.Class(className.image);
                Method main=new Method("main",new VoidType());
                main.addParameter(new Variable(id.image,null));
                mainClass.addMethod(main);
                Statement s;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OBRACE:
      case SOPL:
      case IF:
      case WHILE:
      case ID:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      s = Statement();
                  main.addStatement(s);
    }
    jj_consume_token(CBRACE);
    jj_consume_token(CBRACE);
          {if (true) return mainClass;}
    throw new Error("Missing return statement in function");
  }

  final public Statement Statement() throws ParseException, ParseException {
        Token id;
        Statement statement;
        Expression e;
        Statement s;
        Variable var;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OBRACE:
      jj_consume_token(OBRACE);
                  BasicStatement bs = new BasicStatement();
      label_3:
      while (true) {
        if (jj_2_1(2)) {
          ;
        } else {
          break label_3;
        }
        var = VarDecl();
                          bs.addVariable(var);
      }
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case OBRACE:
        case SOPL:
        case IF:
        case WHILE:
        case ID:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_4;
        }
        s = Statement();
                          bs.addStatement(s);
      }
      jj_consume_token(CBRACE);
                  statement = bs;
      break;
    case IF:
      jj_consume_token(IF);
      jj_consume_token(OPAR);
      e = Expression();
      jj_consume_token(CPAR);
      s = Statement();
                  IfStatement ifs = new IfStatement(e,s);
      jj_consume_token(ELSE);
      s = Statement();
                          ifs.addElseStatement(s);
                  statement = ifs;
      break;
    case WHILE:
      jj_consume_token(WHILE);
      jj_consume_token(OPAR);
      e = Expression();
      jj_consume_token(CPAR);
      s = Statement();
                  statement = new WhileStatement(e,s);
      break;
    case SOPL:
      jj_consume_token(SOPL);
      jj_consume_token(OPAR);
      e = Expression();
      jj_consume_token(CPAR);
      jj_consume_token(SCL);
                  statement = new SOPLStatement(e);
      break;
    case ID:
      id = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SET:
        jj_consume_token(SET);
        e = Expression();
        jj_consume_token(SCL);
                          statement = new SetStatement(id.image,e);
        break;
      case OSB:
                          Expression e2;
        jj_consume_token(OSB);
        e = Expression();
        jj_consume_token(CSB);
        jj_consume_token(SET);
        e2 = Expression();
        jj_consume_token(SCL);
                          statement = new SetIndexStatement(id.image,e,e2);
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          {if (true) return statement;}
    throw new Error("Missing return statement in function");
  }

  final public basic_tree.Class ClassDecl() throws ParseException, ParseException {
        basic_tree.Class c;
        Token className;
        Variable v;
        Method m;
    jj_consume_token(CLASS);
    className = jj_consume_token(ID);
    jj_consume_token(OBRACE);
          c = new basic_tree.Class(className.image);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case KWINT:
      case KWBOOL:
      case ID:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_5;
      }
      v = VarDecl();
                  c.addVariable(v);
    }
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PUBLIC:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_6;
      }
      m = MethodDecl();
                  c.addMethod(m);
    }
    jj_consume_token(CBRACE);
          {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

  final public Variable VarDecl() throws ParseException, ParseException {
        Token id;
        Type t;
    t = Type();
    id = jj_consume_token(ID);
    jj_consume_token(SCL);
          {if (true) return new Variable(id.image,t);}
    throw new Error("Missing return statement in function");
  }

  final public Method MethodDecl() throws ParseException, ParseException {
        Token id;
        Type t;
        Method m;
        Variable v;
        Statement s;
        Expression e;
    jj_consume_token(PUBLIC);
    t = Type();
    id = jj_consume_token(ID);
    jj_consume_token(OPAR);
          m = new Method(id.image,t);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case KWINT:
    case KWBOOL:
    case ID:
      FormalList(m);
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
    jj_consume_token(CPAR);
    jj_consume_token(OBRACE);
    label_7:
    while (true) {
      if (jj_2_2(2)) {
        ;
      } else {
        break label_7;
      }
      v = VarDecl();
                  m.addVariable(v);
    }
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OBRACE:
      case SOPL:
      case IF:
      case WHILE:
      case ID:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_8;
      }
      s = Statement();
                  m.addStatement(s);
    }
    jj_consume_token(RETURN);
    e = Expression();
          m.setReturnExpression(e);
    jj_consume_token(SCL);
    jj_consume_token(CBRACE);
          {if (true) return m;}
    throw new Error("Missing return statement in function");
  }

  final public void FormalList(Method m) throws ParseException, ParseException {
        Type t;
        Token id;
    t = Type();
    id = jj_consume_token(ID);
          m.addParameter(new Variable(id.image,t));
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_9;
      }
      jj_consume_token(COMMA);
      t = Type();
      id = jj_consume_token(ID);
                  m.addParameter(new Variable(id.image,t));
    }
  }

  final public Type Type() throws ParseException, ParseException {
        Type t;
        Token id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case KWINT:
      jj_consume_token(KWINT);
                  t = new IntegerType();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OSB:
        jj_consume_token(OSB);
        jj_consume_token(CSB);
                          t = new IntegerArrayType();
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      break;
    case KWBOOL:
      jj_consume_token(KWBOOL);
                  t = new BooleanType();
      break;
    case ID:
      id = jj_consume_token(ID);
                        t = new CustomType(id.image);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  final public Expression Expression() throws ParseException, ParseException {
        Expression e;
        AndOperand op;
    op = AndOperand();
          e = new Expression(op);
    label_10:
    while (true) {
      if (jj_2_3(2)) {
        ;
      } else {
        break label_10;
      }
      jj_consume_token(AND);
      op = AndOperand();
                  e.addOperand(op);
    }
          {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public AndOperand AndOperand() throws ParseException, ParseException {
        AndOperand op;
        LessOperand op2;
    op2 = LessOperand();
          op = new AndOperand(op2);
    label_11:
    while (true) {
      if (jj_2_4(2)) {
        ;
      } else {
        break label_11;
      }
      jj_consume_token(LT);
      op2 = LessOperand();
                  op.addOperand(op2);
    }
          {if (true) return op;}
    throw new Error("Missing return statement in function");
  }

  final public LessOperand LessOperand() throws ParseException, ParseException {
        LessOperand op;
        Term t;
    t = Term();
          op = new LessOperand(t);
    label_12:
    while (true) {
      if (jj_2_5(2)) {
        ;
      } else {
        break label_12;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        t = Term();
                          op.addOperand(new PlusTerm(t));
        break;
      case MINUS:
        jj_consume_token(MINUS);
        t = Term();
                          op.addOperand(new MinusTerm(t));
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
          {if (true) return op;}
    throw new Error("Missing return statement in function");
  }

  final public Term Term() throws ParseException, ParseException {
        Term op;
        Factor f;
    f = Factor();
          op = new Term(f);
    label_13:
    while (true) {
      if (jj_2_6(2)) {
        ;
      } else {
        break label_13;
      }
      jj_consume_token(MULT);
      f = Factor();
                  op.addOperand(f);
    }
          {if (true) return op;}
    throw new Error("Missing return statement in function");
  }

  final public Factor Factor() throws ParseException, ParseException {
        Factor f;
        Primary p;
        Sufix s;
    p = Primary();
          f = new Factor(p);
    label_14:
    while (true) {
      if (jj_2_7(2)) {
        ;
      } else {
        break label_14;
      }
      s = Sufix();
                  f.addSufix(s);
    }
          {if (true) return f;}
    throw new Error("Missing return statement in function");
  }

  final public Sufix Sufix() throws ParseException, ParseException {
        Sufix s;
        Token id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OSB:
      jj_consume_token(OSB);
                  Expression e;
      e = Expression();
      jj_consume_token(CSB);
                  s = new IndexSufix(e);
      break;
    case DOT:
      jj_consume_token(DOT);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LENGTH:
        jj_consume_token(LENGTH);
                          s = new LengthSufix();
        break;
      case ID:
        id = jj_consume_token(ID);
        jj_consume_token(OPAR);
                          MethodSufix ms = new MethodSufix(id.image);
        ExpList(ms);
        jj_consume_token(CPAR);
                          s = ms;
        break;
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public Primary Primary() throws ParseException, ParseException {
        Primary p, p2;
        Token v;
        Expression e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      v = jj_consume_token(INT);
                  p = new IntPrimary(v.image);
      break;
    case MINUS:
      jj_consume_token(MINUS);
      v = jj_consume_token(INT);
                  p = new IntPrimary("-"+v.image);
      break;
    case BOOL:
      v = jj_consume_token(BOOL);
                  p = new BooleanPrimary(v.image);
      break;
    case ID:
      v = jj_consume_token(ID);
                  p = new IdPrimary(v.image);
      break;
    case NEW:
      jj_consume_token(NEW);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case KWINT:
        jj_consume_token(KWINT);
        jj_consume_token(OSB);
        e = Expression();
        jj_consume_token(CSB);
                          p = new NewIntPrimary(e);
        break;
      case ID:
        v = jj_consume_token(ID);
        jj_consume_token(OPAR);
        jj_consume_token(CPAR);
                          p = new NewPrimary(v.image);
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case EXCL:
      jj_consume_token(EXCL);
      p2 = Primary();
                  p = new NotExpressionPrimary(p2);
      break;
    case OPAR:
      jj_consume_token(OPAR);
      e = Expression();
      jj_consume_token(CPAR);
                  p = new ExpressionPrimary(e);
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public void ExpList(MethodSufix m) throws ParseException, ParseException {
  Expression e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case MINUS:
    case OPAR:
    case EXCL:
    case BOOL:
    case NEW:
    case ID:
      e = Expression();
                  m.addParameter(e);
      label_15:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[17] = jj_gen;
          break label_15;
        }
        jj_consume_token(COMMA);
        e = Expression();
                          m.addParameter(e);
      }
      break;
    default:
      jj_la1[18] = jj_gen;
      ;
    }
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_3R_41() {
    if (jj_scan_token(OSB)) return true;
    return false;
  }

  private boolean jj_3R_30() {
    if (jj_scan_token(ID)) return true;
    return false;
  }

  private boolean jj_3R_32() {
    if (jj_scan_token(MINUS)) return true;
    return false;
  }

  private boolean jj_3_7() {
    if (jj_3R_22()) return true;
    return false;
  }

  private boolean jj_3R_29() {
    if (jj_scan_token(KWBOOL)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_3R_16()) return true;
    return false;
  }

  private boolean jj_3R_31() {
    if (jj_scan_token(INT)) return true;
    return false;
  }

  private boolean jj_3R_18() {
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3R_28() {
    if (jj_scan_token(KWINT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_41()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_scan_token(LT)) return true;
    if (jj_3R_18()) return true;
    return false;
  }

  private boolean jj_3R_21() {
    if (jj_3R_25()) return true;
    return false;
  }

  private boolean jj_3R_25() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_31()) {
    jj_scanpos = xsp;
    if (jj_3R_32()) {
    jj_scanpos = xsp;
    if (jj_3R_33()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) {
    jj_scanpos = xsp;
    if (jj_3R_35()) {
    jj_scanpos = xsp;
    if (jj_3R_36()) {
    jj_scanpos = xsp;
    if (jj_3R_37()) return true;
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_40() {
    if (jj_scan_token(ID)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_3R_16()) return true;
    return false;
  }

  private boolean jj_3R_39() {
    if (jj_scan_token(LENGTH)) return true;
    return false;
  }

  private boolean jj_3R_23() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_28()) {
    jj_scanpos = xsp;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_6() {
    if (jj_scan_token(MULT)) return true;
    if (jj_3R_21()) return true;
    return false;
  }

  private boolean jj_3R_37() {
    if (jj_scan_token(OPAR)) return true;
    return false;
  }

  private boolean jj_3R_17() {
    if (jj_3R_18()) return true;
    return false;
  }

  private boolean jj_3R_36() {
    if (jj_scan_token(EXCL)) return true;
    return false;
  }

  private boolean jj_3R_27() {
    if (jj_scan_token(DOT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_39()) {
    jj_scanpos = xsp;
    if (jj_3R_40()) return true;
    }
    return false;
  }

  private boolean jj_3R_20() {
    if (jj_scan_token(MINUS)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_scan_token(AND)) return true;
    if (jj_3R_17()) return true;
    return false;
  }

  private boolean jj_3R_24() {
    if (jj_3R_21()) return true;
    return false;
  }

  private boolean jj_3R_19() {
    if (jj_scan_token(PLUS)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3R_26() {
    if (jj_scan_token(OSB)) return true;
    if (jj_3R_38()) return true;
    return false;
  }

  private boolean jj_3R_16() {
    if (jj_3R_23()) return true;
    if (jj_scan_token(ID)) return true;
    return false;
  }

  private boolean jj_3R_35() {
    if (jj_scan_token(NEW)) return true;
    return false;
  }

  private boolean jj_3R_38() {
    if (jj_3R_17()) return true;
    return false;
  }

  private boolean jj_3R_34() {
    if (jj_scan_token(ID)) return true;
    return false;
  }

  private boolean jj_3_5() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_19()) {
    jj_scanpos = xsp;
    if (jj_3R_20()) return true;
    }
    return false;
  }

  private boolean jj_3R_22() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_26()) {
    jj_scanpos = xsp;
    if (jj_3R_27()) return true;
    }
    return false;
  }

  private boolean jj_3R_33() {
    if (jj_scan_token(BOOL)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[19];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x8200000,0x8200000,0x88000,0x8200000,0x6000000,0x0,0x6000000,0x8200000,0x0,0x80000,0x6000000,0x1800,0x80000000,0x20080000,0x2000000,0x50821400,0x0,0x50821400,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x10,0x224,0x224,0x0,0x224,0x200,0x1,0x200,0x224,0x80,0x0,0x200,0x0,0x200,0x0,0x200,0x200,0x80,0x200,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[7];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[42];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 19; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 42; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 7; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

        }
