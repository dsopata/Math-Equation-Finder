package nodes;

import java.util.Random;

public class MathFunctionNode extends Node {

    public enum MathFunctions {
        SQRT,
        ABS,
        EXP,
        SIN,
        COS
    }

    public MathFunctionNode(int level) {
        super(level);
    }

    @Override
    public void setChild(int index, Node node) throws Exception {
        super.setChild(index, node);
    }

    private static MathFunctions randomMathOperator() {
        return MathFunctions.values()[new Random().nextInt(MathFunctions.values().length)];
    }
}
