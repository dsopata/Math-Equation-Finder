package nodes;


public class ValueNode extends Node {

    boolean isVariableNode = false;
    int variableId;
    double value;

    public ValueNode(int level) {
        super(level);
    }


}
