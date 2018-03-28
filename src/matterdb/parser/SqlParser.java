/* Generated By:JavaCC: Do not edit this line. SqlParser.java */
        package matterdb.parser;

        import java.io.PrintStream;
        import java.util.*;
        import matterdb.*;

        public class SqlParser implements SqlParserConstants {

  final public boolean Start(PrintStream printStream, Engine currentEngine) throws ParseException {
                Command cmd = new Command();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SELECT:
      case CREATE:
      case DROP:
      case USE:
      case ALTER:
      case EXIT:
      case INSERT:
      case UPDATE:
      case DELETE:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SELECT:
      case CREATE:
      case DROP:
      case USE:
      case ALTER:
      case INSERT:
      case UPDATE:
      case DELETE:
        cmd = Query();
        jj_consume_token(SEMICOLON);
                                                   currentEngine.commandProcessor(cmd);
        break;
      case EXIT:
        jj_consume_token(EXIT);
        cmd = ExitQuery();
                                                 {if (true) return false;}
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
                 {if (true) return false;}
    throw new Error("Missing return statement in function");
  }

  final public Command Query() throws ParseException {
                Command cmd = new Command();
    if (jj_2_1(2)) {
      jj_consume_token(CREATE);
      jj_consume_token(DATABASE);
      cmd = CreateDbQuery();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CREATE:
        jj_consume_token(CREATE);
        jj_consume_token(TABLE);
        cmd = CreateTableQuery();
        break;
      default:
        jj_la1[2] = jj_gen;
        if (jj_2_2(2)) {
          jj_consume_token(DROP);
          jj_consume_token(DATABASE);
          cmd = DropDbQuery();
        } else {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case DROP:
            jj_consume_token(DROP);
            jj_consume_token(TABLE);
            cmd = DropTableQuery();
            break;
          case USE:
            jj_consume_token(USE);
            cmd = UseDbQuery();
            break;
          case ALTER:
            jj_consume_token(ALTER);
            jj_consume_token(TABLE);
            cmd = AlterTableQuery();
            break;
          case SELECT:
            jj_consume_token(SELECT);
            cmd = SelectQuery();
            break;
          case UPDATE:
            jj_consume_token(UPDATE);
            cmd = UpdateQuery();
            break;
          case DELETE:
            jj_consume_token(DELETE);
            cmd = DeleteQuery();
            break;
          case INSERT:
            jj_consume_token(INSERT);
            cmd = InsertQuery();
            break;
          default:
            jj_la1[3] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      }
    }
                        {if (true) return cmd;}
    throw new Error("Missing return statement in function");
  }

  final public Command CreateDbQuery() throws ParseException {
                String dbName;
                Token t;
                Command newCmd = new Command("CREATE_DATABASE");
    t = jj_consume_token(NAME);
                        dbName = t.image;
                        newCmd.setObjectName(dbName);
                        {if (true) return newCmd;}
    throw new Error("Missing return statement in function");
  }

  final public Command DropDbQuery() throws ParseException {
                String dbName;
                Token t;
                Command newCmd = new Command("DROP_DATABASE");
    t = jj_consume_token(NAME);
                        dbName = t.image;
                        newCmd.setObjectName(dbName);
                        {if (true) return newCmd;}
    throw new Error("Missing return statement in function");
  }

  final public Command DropTableQuery() throws ParseException {
                String tableName;
                Token t;
                Command newCmd = new Command("DROP_TABLE");
    t = jj_consume_token(NAME);
                        tableName = t.image;
                        newCmd.setObjectName(tableName);
                        {if (true) return newCmd;}
    throw new Error("Missing return statement in function");
  }

  final public Command UseDbQuery() throws ParseException {
                String dbName;
                Token t;
                Command newCmd = new Command("USE_DATABASE");
    t = jj_consume_token(NAME);
                        dbName = t.image;
                        newCmd.setObjectName(dbName);
                        {if (true) return newCmd;}
    throw new Error("Missing return statement in function");
  }

  final public Command CreateTableQuery() throws ParseException {
                String tableName;
                Token t;
                List cols = new ArrayList();
                Command newCmd = new Command("CREATE_TABLE");
    t = jj_consume_token(NAME);
    cols = TableFieldsQuery();
                        tableName = t.image;
                        newCmd.setObjectName(tableName);
                        newCmd.setColumnList(cols);
                        {if (true) return newCmd;}
    throw new Error("Missing return statement in function");
  }

  final public Command AlterTableQuery() throws ParseException {
                String tableName;
                Token t;
                Column col;
                Command newCmd = new Command("ALTER_TABLE");
                List colList = new ArrayList();
    t = jj_consume_token(NAME);
    jj_consume_token(ADD);
    col = TableFieldDefinition();
                        tableName = t.image;
                        newCmd.setObjectName(tableName);
                        colList.add(col);
                        newCmd.setColumnList(colList);
                        {if (true) return  newCmd;}
    throw new Error("Missing return statement in function");
  }

  final public Command SelectQuery() throws ParseException {
                String tableName;

                Token t;
                Token columnToken;
                Token conditionColumnName;
                Token condition;
                Token conditionValue;
                Condition cnd = new Condition();

                Command newCmd = new Command("SELECT");
                List columns = new ArrayList();
    columnToken = jj_consume_token(NAME);
                                      columns.add(columnToken.image);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      jj_consume_token(COMMA);
      columnToken = jj_consume_token(NAME);
                                               columns.add(columnToken.image);
    }
    jj_consume_token(FROM);
    t = jj_consume_token(NAME);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case WHERE:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      jj_consume_token(WHERE);
      conditionColumnName = jj_consume_token(NAME);
      condition = jj_consume_token(CONDITIONS);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case QUOTE:
          ;
          break;
        default:
          jj_la1[6] = jj_gen;
          break label_4;
        }
        jj_consume_token(QUOTE);
      }
      conditionValue = jj_consume_token(NAME);
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case QUOTE:
          ;
          break;
        default:
          jj_la1[7] = jj_gen;
          break label_5;
        }
        jj_consume_token(QUOTE);
      }
                        cnd.columnName = conditionColumnName.image;
                        cnd.conditionType = condition.image;
                        cnd.conditionValue = conditionValue.image;
                        newCmd.setCondition(cnd);
    }
                        tableName = t.image;
                        newCmd.setObjectName(tableName);
                        newCmd.setSelectedColumns(columns);

                        {if (true) return newCmd;}
    throw new Error("Missing return statement in function");
  }

  final public List TableFieldsQuery() throws ParseException {
                List columns = new ArrayList();
                Column col;
    jj_consume_token(OPENBRACKET);
    col = TableFieldDefinition();
                                                      columns.add(col);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_6;
      }
      jj_consume_token(COMMA);
      col = TableFieldDefinition();
                                                               columns.add(col);
    }
    jj_consume_token(CLOSEBRACKET);
                        {if (true) return columns;}
    throw new Error("Missing return statement in function");
  }

  final public Column TableFieldDefinition() throws ParseException {
                Token cName;
                Token cType;
                int cSize = 0;
    cName = jj_consume_token(NAME);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      cType = jj_consume_token(INT);
      break;
    case FLOAT:
      cType = jj_consume_token(FLOAT);
      break;
    case CHAR:
      cType = jj_consume_token(CHAR);
      cSize = CheckSize();
      break;
    case VARCHAR:
      cType = jj_consume_token(VARCHAR);
      cSize = CheckSize();
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                        Column newColumn = new Column(cType.image, cName.image, cSize);
                        {if (true) return newColumn;}
    throw new Error("Missing return statement in function");
  }

  final public int CheckSize() throws ParseException {
                Token t;
    jj_consume_token(OPENBRACKET);
    t = jj_consume_token(NAME);
    jj_consume_token(CLOSEBRACKET);
                 {if (true) return Integer.parseInt(t.image);}
    throw new Error("Missing return statement in function");
  }

  final public Command ExitQuery() throws ParseException {
                Command newCmd = new Command("EXIT");
                 {if (true) return newCmd;}
    throw new Error("Missing return statement in function");
  }

  final public Command InsertQuery() throws ParseException {
            String strTableName;;
            Command cmd = new Command("INSERT");;
            Token tableName;
            Token value;
            List values = new ArrayList();
    jj_consume_token(INTO);
    tableName = jj_consume_token(NAME);
    jj_consume_token(VALUES);
    jj_consume_token(OPENBRACKET);
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case QUOTE:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_7;
      }
      jj_consume_token(QUOTE);
    }
    value = jj_consume_token(NAME);
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case QUOTE:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_8;
      }
      jj_consume_token(QUOTE);
    }
                                                      values.add(value.image);
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_9;
      }
      jj_consume_token(COMMA);
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case QUOTE:
          ;
          break;
        default:
          jj_la1[13] = jj_gen;
          break label_10;
        }
        jj_consume_token(QUOTE);
      }
      value = jj_consume_token(NAME);
      label_11:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case QUOTE:
          ;
          break;
        default:
          jj_la1[14] = jj_gen;
          break label_11;
        }
        jj_consume_token(QUOTE);
      }
                                                          values.add(value.image);
    }
    jj_consume_token(CLOSEBRACKET);
                strTableName = tableName.image;
                cmd.setObjectName(strTableName);
                cmd.setValueList(values);
                {if (true) return cmd;}
    throw new Error("Missing return statement in function");
  }

  final public Command UpdateQuery() throws ParseException {
            Command cmd = new Command("UPDATE");

            String strTableName;
            String columnToUpdate;
            String strColumnValue;
            Condition cnd = new Condition();


            Token tableName;
            Token updatedColumnName;
            Token conditionColumnName;
            Token condition;
            Token conditionValue;
            Token columnValue;
    tableName = jj_consume_token(NAME);
    jj_consume_token(SET);
    updatedColumnName = jj_consume_token(NAME);
    jj_consume_token(CONDITIONS);
    label_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case QUOTE:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_12;
      }
      jj_consume_token(QUOTE);
    }
    columnValue = jj_consume_token(NAME);
    label_13:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case QUOTE:
        ;
        break;
      default:
        jj_la1[16] = jj_gen;
        break label_13;
      }
      jj_consume_token(QUOTE);
    }
    jj_consume_token(WHERE);
    conditionColumnName = jj_consume_token(NAME);
    condition = jj_consume_token(CONDITIONS);
    label_14:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case QUOTE:
        ;
        break;
      default:
        jj_la1[17] = jj_gen;
        break label_14;
      }
      jj_consume_token(QUOTE);
    }
    conditionValue = jj_consume_token(NAME);
    label_15:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case QUOTE:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_15;
      }
      jj_consume_token(QUOTE);
    }
                        strTableName = tableName.image;
                        columnToUpdate = updatedColumnName.image;
                        strColumnValue = columnValue.image;
                        cnd.columnName = conditionColumnName.image;
                        cnd.conditionType = condition.image;
                        cnd.conditionValue = conditionValue.image;

                        cmd.setObjectName(strTableName);
                        cmd.setCondition(cnd);
                        cmd.setUpdateColumnName(columnToUpdate);
                        cmd.setUpdateColumnValue(strColumnValue);
                        {if (true) return cmd;}
    throw new Error("Missing return statement in function");
  }

  final public Command DeleteQuery() throws ParseException {
            Command cmd = new Command("DELETE");

            String strTableName;
            Condition cnd = new Condition();

            Token tableName;
            Token conditionColumnName;
            Token condition;
            Token conditionValue;
    jj_consume_token(FROM);
    tableName = jj_consume_token(NAME);
    jj_consume_token(WHERE);
    conditionColumnName = jj_consume_token(NAME);
    condition = jj_consume_token(CONDITIONS);
    label_16:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case QUOTE:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_16;
      }
      jj_consume_token(QUOTE);
    }
    conditionValue = jj_consume_token(NAME);
    label_17:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case QUOTE:
        ;
        break;
      default:
        jj_la1[20] = jj_gen;
        break label_17;
      }
      jj_consume_token(QUOTE);
    }
                        strTableName = tableName.image;
                        cnd.columnName = conditionColumnName.image;
                        cnd.conditionType = condition.image;
                        cnd.conditionValue = conditionValue.image;

                        cmd.setObjectName(strTableName);
                        cmd.setCondition(cnd);
                        {if (true) return cmd;}
    throw new Error("Missing return statement in function");
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

  private boolean jj_3_2() {
    if (jj_scan_token(DROP)) return true;
    if (jj_scan_token(DATABASE)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(CREATE)) return true;
    if (jj_scan_token(DATABASE)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public SqlParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x14d6880,0x14d6880,0x800,0x1496080,0x0,0x200,0x40000000,0x40000000,0x0,0x1e000000,0x40000000,0x40000000,0x0,0x40000000,0x40000000,0x40000000,0x40000000,0x40000000,0x40000000,0x40000000,0x40000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x1,0x0,0x0,0x0,0x1,0x0,0x0,0x0,0x1,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[2];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public SqlParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public SqlParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new SqlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public SqlParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SqlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public SqlParser(SqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(SqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
    boolean[] la1tokens = new boolean[37];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 21; i++) {
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
    for (int i = 0; i < 37; i++) {
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
    for (int i = 0; i < 2; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
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

                // public static void main(String[] args) throws ParseException, TokenMgrError {
                // 	SqlParser parser = new SqlParser(System.in);
                // 	try{
                // 		parser.Start(System.out);
                // 	} catch(ParseException ex){
                // 		System.out.println("!Error! Your SQL commands have syntax errors. Please check and try again. \n" + ex.getMessage());
                // 	}
                // }
                //String line = "";
                //HashMap<String, Column> columns = new HashMap<String, Column>();
        }
