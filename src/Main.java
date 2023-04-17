import java.util.Scanner;
import java.util.HashMap;

public class Main {

    public static void main(String[] args){
        String a = "i 5 f 30 p 55 a 23";
        scanner(a);

        Scanner Stmt_scanner1 = new Scanner("src/inputs/Stmt.txt");
        Scanner Stmts_scanner2 = new Scanner("src/inputs/Stmts.txt");

        Stmt(Stmt_scanner1);
        Stmts(Stmts_scanner2);

        SymbolTable table = new SymbolTable();
        table.EnterSymbol("a", Type.FLOAT);
        table.EnterSymbol("b", Type.INTEGER);
        table.EnterSymbol("a", Type.FLOATDCL);

        Main main = new Main();

        Node child1test = new Node(DataType.INTEGER, "55", "int Consting", null, null);
        Node child2test = new Node(DataType.FLOAT, "0.55", "float Consting", null, null);
        Node computingtest = new Node(DataType.FLOAT, "a", "computing", child1test, child2test);
        Node assigningtest = new Node(DataType.FLOAT, "b", "assigning", child1test, child2test);
        Node symReferencingtest = new Node(DataType.FLOAT, "c", "symReferencing", child1test, child2test);

        main.visit(computingtest);
        main.visit(assigningtest);
        main.visit(symReferencingtest);
    }

    private static String Peek(Scanner s) {
        if (s.hasNext()) {
            String nextToken = s.next();
            return nextToken;
        }
        return " ";
    }

    public static void lexicalError() {

        System.out.println("LEXICAL ERROR THROWN");
    }

    public static Token scanner(String a) {
        String stream = " ";
        Scanner s = new Scanner(stream);
        int i = 0;

        String t = Peek(s);
        while (s.hasNext()) {
            t = Peek(s);
            s.next();
        }
        Token ans = new Token();
        while (s.hasNext()) {
            if (s.hasNext()) {
                ans.type = "number";
                ans.val = t;
            } else if (Character.isDigit(t.charAt(0))) {
                //    ans = ScanDigits();
            }
        }
        Character c = t.charAt(0);
        switch (c) {
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'g':
            case 'h':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
                if (c != 'i' && c != 'f' && c != 'p') {
                    ans.type = TokenType.ID;
                    ans.val = String.valueOf(c);
                } else {
                }
                break;
            case 'f':
                ans.type = TokenType.FLOATDCL;
                ans.val = c.toString();
                break;
            case 'i':
                ans.type = TokenType.INTDCL;
                ans.val = c.toString();
                break;
            case 'p':
                ans.type = TokenType.PRINT;
                break;
            case '=':
                ans.type = TokenType.ASSIGN;
                break;
            case '+':
                ans.type = TokenType.PLUS;
                break;
            case '-':
                ans.type = TokenType.MINUS;
                break;
            default:
                lexicalError();
        }
        return ans;
    }

    //Statement
    public static void Stmt(Scanner s) {
        String nextToken = peek(s);

        if (nextToken.equals("id")) {
            match(s.next(), "id");
            match(s.next(), "assign");

            System.out.println("Val");
            System.out.println("Expr");

        } else if (nextToken.equals("print")) {
            match(s.next(), "print");
            match(s.next(), "id");

        } else {
            System.out.println("Stmt ERROR");
        }
    }

    //Statements
    public static void Stmts(Scanner scanner) {

        String nextToken = peek(scanner);

        if (nextToken.equals("id") || nextToken.equals("print")){
            Stmt(scanner);
            Stmts(scanner);

        } else if (nextToken.equals(" ")) {

        } else {
            System.out.println("Stmts ERROR");
        }

    }

    //match function
    public static boolean match(String word, String input) {
        return word.equals(input);
    }

    //peek function
    private static String peek(Scanner s) {
        if (s.hasNext()) {
            String nextToken = s.next();
            return nextToken;
        }
        return " ";
    }

    public HashMap<String, DataType> symbolTable = new HashMap<>();

    public void visit(Node node) {
        if (node.getName().equals("Computing")) {
            node.setType(consistent(node.getChild1(), node.getChild2()));
        } else if (node.getName().equals("Assigning")) {
            node.setType(convert(node.getChild2(), node.getChild1().getType()));
        } else if (node.getName().equals("SymReferencing")) {
            node.setType(lookupSymbol(node.getId()));
        } else if (node.getName().equals("IntConsting")) {
            node.setType(DataType.INTEGER);
        } else if (node.getName().equals("FloatConsting")) {
            node.setType(DataType.FLOAT);
        } else if (node.getName().equals("FloatDeclaration")) {
            node.setType(DataType.FLOATDCL);
        }
    }

    public DataType consistent(Node node1, Node node2) {
        DataType type1 = node1.getType();
        DataType type2 = node2.getType();
        if (type1 == DataType.FLOAT || type2 == DataType.FLOAT) {
            if (type1 == DataType.INTEGER) {
                convert(node1, DataType.FLOAT);
            }
            if (type2 == DataType.INTEGER) {
                convert(node2, DataType.FLOAT);
            }
            return DataType.FLOAT;
        } else {
            return DataType.INTEGER;
        }
    }

    public DataType convert(Node node, DataType type) {
        if (node.getType() == DataType.FLOAT && type == DataType.INTEGER) {
            throw new IllegalArgumentException("Illegal type conversion");
        } else if (node.getType() == DataType.INTEGER && type == DataType.FLOAT) {
            return DataType.FLOAT; // replace node n by convert-to-float of node n
        } else {
            return null;
        }
    }
    public static DataType generalize(DataType t1, DataType t2) {
        if (t1 == DataType.FLOAT || t2 == DataType.FLOAT) {
            return DataType.FLOAT;
        } else {
            return DataType.INTEGER;
        }
    }

    public DataType lookupSymbol(String name) {
        return symbolTable.get(name);
    }

    // use an instance method to enter symbols
    public void enterSymbol(String name, DataType type) {
        if (type == null) {
            throw new NullPointerException("Type cannot be null");
        }
        if (symbolTable.containsKey(name)) {
            throw new IllegalArgumentException("Symbol '" + name + "' already exists in the SymbolTable");
        }
        symbolTable.put(name, type);
        System.out.println("[" + name + ", " + "] successfully added");
    }
}