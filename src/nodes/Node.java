package nodes;

import java.util.UUID;

public abstract class Node {

    public int level;
    public Node[] children = new Node[2];
    private UUID id = UUID.randomUUID();
    public Node parent;

    NodeType nodeType;

    public Node(int level, Node parent) {
        this.level = level;
        this.parent = parent;
    }

    public void setChild(int index, Node node) throws Exception {
        if(index > 1 && index < 0) {
            throw new Exception("incorrect node child index");
        }
        children[index] = node;
    }

    public UUID getId() {
        return id;
    }

    public void replaceChild(Node node2) throws Exception {
        if(this.parent == null) {
            return;
        }
        if(this.equals(this.parent.children[0])) {
            this.parent.setChild(0, node2);
            node2.parent = this.parent;
            return;
        }
        if(this.equals(this.parent.children[1])) {
            this.parent.setChild(1, node2);
            node2.parent = this.parent;
        }
    }

    public boolean isLeaf() {
        return this.children[0] != null ||this.children[1] != null;
    }

    public abstract void mutate();

    public enum NodeType {
        MATH_FUNCTION,
        MATH_OPERATOR,
        VALUE
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public abstract double calculate(double[] independentVariables);

    public abstract Node clone(Node parent);

}
