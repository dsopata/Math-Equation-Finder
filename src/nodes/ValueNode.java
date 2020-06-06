package nodes;


public class ValueNode extends Node {

    boolean isVariableNode = false;
    int variableId = -1;
    double value;

    public ValueNode(int level, double value) {
        super(level);
        nodeType = NodeType.VALUE;
        this.value = value;
    }

    @Override
    public String toString() {
        return (variableId != -1) ? "x" + String.valueOf(variableId) : String.valueOf(value);
    }

    public void setVariableId(int id) {
        this.variableId = id;
    }
}
