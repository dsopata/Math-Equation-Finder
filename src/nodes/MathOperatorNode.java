package nodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import nodes.mathfunctions.*;
public class MathOperatorNode extends Node {

    public MathOperators mathOperator;

    public enum MathOperators {
        SUM,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
    }

    private static final Map<MathOperators, LocalOperatorInterface> mathOperatorsMap = new HashMap<>() {{
        put(MathOperators.SUM, new LocalSum());
        put(MathOperators.DIVIDE, new LocalDivide());
        put(MathOperators.MULTIPLY, new LocalMultiply());
        put(MathOperators.SUBTRACT, new LocalSubstract());
    }};

    public MathOperatorNode(int level, Node parent) {
        super(level, parent);
        this.mathOperator = randomMathOperator(false);
        nodeType = NodeType.MATH_OPERATOR;
        children = new Node[2];
    }

    private  MathOperators randomMathOperator(boolean differentThanCurrent) {
        Random random = new Random();
        if(!differentThanCurrent) {
            return MathOperators.values()[new Random().nextInt(MathOperators.values().length)];
        }
        if(mathOperator.ordinal() == 0) {
            return MathOperatorNode.MathOperators.values()[ 1 + random.nextInt(MathOperatorNode.MathOperators.values().length - 1)];
        }
        if(mathOperator.ordinal() == MathOperatorNode.MathOperators.values().length - 1) {
            return MathOperatorNode.MathOperators.values()[ random.nextInt(MathOperatorNode.MathOperators.values().length - 1)];
        }
        int leftValue = random.nextInt(mathOperator.ordinal());
        int rightValue = mathOperator.ordinal() + random.nextInt(MathOperatorNode.MathOperators.values().length - mathOperator.ordinal());
        boolean useLeft = random.nextBoolean();
        if(useLeft) {
            return MathOperatorNode.MathOperators.values()[leftValue];
        }
        return MathOperatorNode.MathOperators.values()[rightValue];
    }

    @Override
    public double calculate(double[] independentVariables) {
        return  mathOperatorsMap.get(mathOperator).getLocalFunctionVal(children[0].calculate(independentVariables), children[1].calculate(independentVariables));
    }

    @Override
    public String toString() {
        return "(" + children[0].toString() + mathOperatorsMap.get(mathOperator).getLocalFunctionName() + children[1].toString() + ")";
    }

    @Override
    public Node clone(Node parent) {
        MathOperatorNode clone = new MathOperatorNode(this.level, this.parent);
        clone.mathOperator = this.mathOperator;
        clone.nodeType = this.nodeType;
        clone.children = new Node[] {this.children[0].clone(clone), this.children[1].clone(clone)};
        return clone;
    }

    @Override
    public void mutate() {
        this.mathOperator = randomMathOperator(true);
    }
}
