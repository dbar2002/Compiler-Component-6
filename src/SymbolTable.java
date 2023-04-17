import java.util.HashMap;

//Symbol Table Hash Map
public class SymbolTable {
    private static HashMap<String, Type> symbolTable;

    public SymbolTable() {
        symbolTable = new HashMap<String, Type>();
    }

    public void visit(SymDeclaring n) {
        if (n.getType() == Type.FLOATDCL) {
            EnterSymbol(n.getId(), Type.FLOAT);
        } else {
            EnterSymbol(n.getId(), Type.INTEGER);
        }
    }

    //Add symbol to table
    public void EnterSymbol(String name, Type type) {
        if (!symbolTable.containsKey(name)) {
            symbolTable.put(name, type);
        } else {
            System.out.println("Duplicate declaration: " + name);
        }
    }

    public Type lookupSymbol(String name) {
        return symbolTable.getOrDefault(name, null);
    }

}