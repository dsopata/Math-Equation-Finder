package nodes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ValueNode extends Node {

    private static final String VARIABLE_ALIAS = "_x";
    private static final int GENERATED_DOUBLE_PRECISION = 3;

    int variableId = -1;
    double value;

    public ValueNode(int level, double value, Node parent) {
        super(level, parent);
        nodeType = NodeType.VALUE;
        this.value = precision(value);
        this.children = new Node[2];
    }

    private double precision(double val) {
        return BigDecimal.valueOf(val)
                .setScale(GENERATED_DOUBLE_PRECISION, RoundingMode.HALF_UP)
                .doubleValue();
    }

    @Override
    public String toString() {
        return (variableId != -1) ? VARIABLE_ALIAS + "[" + variableId + "]": String.valueOf(precision(value));
    }

    @Override
    public double calculate(double[] independentVariables) {
        return (variableId != -1) ? independentVariables[variableId] : precision(value);
    }

    public void setVariableId(int id) {
        this.variableId = id;
    }

    @Override
    public void setChild(int index, Node node) throws Exception {
        throw new Exception("Value node cant have children");
    }

    @Override
    public Node clone(Node parent, int level) {
        ValueNode clone = new ValueNode(this.level, this.value, this.parent);
        clone.nodeType = this.nodeType;
        clone.value = this.value;
        clone.variableId = this.variableId;
        clone.children = new Node[2];
        return clone;
    }

    @Override
    public void mutate() {}
}
