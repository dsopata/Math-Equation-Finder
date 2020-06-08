package nodes;

/**
 * Prototyp wierzcholka drzewa
 * Rozrozniamy 3 typy:
 *  - Value node: dedykowany dla node'a zawierajacego informacje o wartosci stalej lub zmiennej niezaleznej
 *  - Math Operator node: node operacji matematycznych (typu suma, iloczyn), zawierajacy 2 dzieci.
 *  - Math Function node: node funkcji matematycznych (sinus, exp), zawierajacych 1 dzieco - 1 argument funkcji.
 */
public abstract class Node {

    public int level;
    public Node[] children = new Node[2];
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

    public enum NodeType {
        MATH_FUNCTION,
        MATH_OPERATOR,
        VALUE
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public abstract void mutate();

    public abstract double calculate(double[] independentVariables);

    public abstract Node clone(Node parent, int level);

    String bracket(boolean left) {
        switch (this.level) {
            case 0:
                return "";
            case 1:
                return left ? "(" : ")";
            case 2:
                return left ? "[" : "]";
            default:
                return left ? "{" : "}";
        }
    }
}
