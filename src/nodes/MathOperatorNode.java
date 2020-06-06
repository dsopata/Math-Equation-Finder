package nodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import nodes.mathfunctions.*;
public class MathOperatorNode extends Node {

    private MathOperators mathOperator;

    public enum MathOperators {
        SUM,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }

    private static final Map<MathOperators, LocalOperatorInterface> mathOperatorsMap = new HashMap<>() {{
        put(MathOperators.SUM, new LocalSum());
        put(MathOperators.DIVIDE, new LocalDivide());
        put(MathOperators.MULTIPLY, new LocalMultiply());
        put(MathOperators.SUBTRACT, new LocalSubstract());
    }};

    public MathOperatorNode(int level) {
        super(level);
        this.mathOperator = randomMathOperator();
        nodeType = NodeType.MATH_OPERATOR;
        children = new Node[2];
    }

    private static MathOperators randomMathOperator() {
        return MathOperators.values()[new Random().nextInt(MathOperators.values().length)];
    }

    @Override
    public double calculate(double[] independentVariables) {
        return  mathOperatorsMap.get(mathOperator).getLocalFunctionVal(children[0].calculate(independentVariables), children[1].calculate(independentVariables));
    }

    @Override
    public String toString() {
        return "(" + children[0].toString() + mathOperatorsMap.get(mathOperator).getLocalFunctionName() + children[1].toString() + ")";
    }
}
