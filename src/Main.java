import java.util.Scanner;

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
}