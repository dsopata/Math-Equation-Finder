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

    private static final Map<MathOperators, LocalOperatorStrategy> mathOperatorsMap = new HashMap<>() {{
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
            return MathOperators.values()[ 1 + random.nextInt(MathOperators.values().length - 1)];
        }
        if(mathOperator.ordinal() == MathOperators.values().length - 1) {
            return MathOperators.values()[ random.nextInt(MathOperators.values().length - 1)];
        }
        int leftValue = random.nextInt(mathOperator.ordinal());
        int rightValue = mathOperator.ordinal() + random.nextInt(MathOperators.values().length - mathOperator.ordinal());
        boolean useLeft = random.nextBoolean();
        if(useLeft) {
            return MathOperators.values()[leftValue];
        }
        return MathOperators.values()[rightValue];
    }

    @Override
    public double calculate(double[] independentVariables) {
        return  mathOperatorsMap.get(mathOperator).value(children[0].calculate(independentVariables), children[1].calculate(independentVariables));
    }

    @Override
    public String toString() {
        return bracket(true) + children[0].toString() + mathOperatorsMap.get(mathOperator).print() + children[1].toString() + bracket(false);
    }

    @Override
    public Node clone(Node parent, int level) {
        MathOperatorNode clone = new MathOperatorNode(this.level, this.parent);
        clone.mathOperator = this.mathOperator;
        clone.nodeType = this.nodeType;
        clone.children = new Node[] {this.children[0].clone(clone, this.level+1), this.children[1].clone(clone, this.level+1)};

        return clone;
    }

    @Override
    public void mutate() {
        this.mathOperator = randomMathOperator(true);
    }
}
