package nodes;

import java.util.Random;

public class MathOperatorNode extends Node {

    private MathOperators mathOperator;

    public enum MathOperators {
        SUM,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }

    public MathOperatorNode(int level) {
        super(level);
        this.mathOperator = randomMathOperator();
    }

    private static MathOperators randomMathOperator() {
        return MathOperators.values()[new Random().nextInt(MathOperators.values().length)];
    }
}
