class Node {
    public DataType t;
    public String id;
    public String name;
    public Node child1;
    public Node child2;
    public boolean val;

    public Node(DataType type, String id, String name, Node child1, Node child2) {
        this.t = type;
        this.id = id;
        this.name = name;
        this.child1 = child1;
        this.child2 = child2;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DataType getType() {
        return t;
    }

    public Node getChild1() {
        return child1;
    }

    public Node getChild2() {
        return child2;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(DataType t) {
        this.t = t;
    }

    public void setChild1(Node child1) {
        this.child1 = child1;
    }

    public void setChild2(Node child2) {
        this.child2 = child2;
    }
}

enum DataType {
    FLOAT,
    INTEGER,
    FLOATDCL
}