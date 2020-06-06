package nodes;

public class ValueNode extends Node {

    int variableId = -1;
    double value;

    public ValueNode(int level, double value) {
        super(level);
        nodeType = NodeType.VALUE;
        this.value = value;
    }

    @Override
    public String toString() {
        return (variableId != -1) ? "x" + variableId : String.valueOf(value);
    }

    @Override
    public double calculate(double[] independentVariables) {
        return (variableId != -1) ? independentVariables[variableId] : value;
    }


    public void setVariableId(int id) {
        this.variableId = id;
    }
}
