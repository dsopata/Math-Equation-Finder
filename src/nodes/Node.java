package nodes;

import java.util.Random;
import java.util.UUID;

public abstract class Node {

    public int level  = 0;
    public Node[] children = new Node[2];
    private UUID id = UUID.randomUUID();
    NodeType nodeType;

    public Node(int level) {
        this.level = level;
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

    public enum NodeType {
        MATH_FUNCTION,
        MATH_OPERATOR,
        VALUE
    }

    public NodeType getNodeType() {
        return nodeType;
    }
}
