package nodes;

;
import nodes.mathoperators.LocalCos;
import nodes.mathoperators.LocalExp;
import nodes.mathoperators.LocalSin;
import nodes.mathoperators.LocalSqrt;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MathFunctionNode extends Node {

    private MathFunctions mathFunction;

    public enum MathFunctions {
        SQRT,
        EXP,
        SIN,
        COS
    }

    private  final Map<MathFunctions, LocalFunctionInterface> mathFunctionsMap  = new HashMap<>() {{
        put(MathFunctionNode.MathFunctions.SQRT, new LocalSqrt());
        put(MathFunctionNode.MathFunctions.EXP, new LocalExp());
        put(MathFunctionNode.MathFunctions.SIN, new LocalSin());
        put(MathFunctionNode.MathFunctions.COS, new LocalCos());
    }};


    public MathFunctionNode(int level, Node parent) {
        super(level, parent);
        nodeType = NodeType.MATH_FUNCTION;
        children = new Node[2];
        mathFunction = randomMathOperator(false);
    }

    private MathFunctions randomMathOperator(boolean differentThanCurrent) {
        Random random = new Random();
        if(!differentThanCurrent) {
            return MathFunctions.values()[random.nextInt(MathFunctions.values().length)];
        }
        if(mathFunction.ordinal() == 0) {
            return MathFunctions.values()[ 1 + random.nextInt(MathFunctions.values().length - 1)];
        }
        if(mathFunction.ordinal() == MathFunctions.values().length - 1) {
            return MathFunctions.values()[ random.nextInt(MathFunctions.values().length - 1)];
        }
        int leftValue = random.nextInt(mathFunction.ordinal());
        int rightValue = mathFunction.ordinal() + random.nextInt(MathFunctions.values().length - mathFunction.ordinal());
        boolean useLeft = random.nextBoolean();
        if(useLeft) {
            return MathFunctions.values()[leftValue];
        }
        return MathFunctions.values()[rightValue];
    }

    @Override
    public double calculate(double[] independentVariables) {
        return  mathFunctionsMap.get(mathFunction).getLocalFunctionVal(children[0].calculate(independentVariables));
    }

    @Override
    public String toString() {
        return mathFunctionsMap.get(mathFunction).getLocalFunctionName() + "(" + children[0].toString() + ")";
    }

    @Override
    public Node clone(Node parent) {
        MathFunctionNode clone = new MathFunctionNode(this.level, this.parent);
        clone.nodeType =  this.nodeType;
        clone.children =  new Node[] {this.children[0].clone(clone), null};
        clone.mathFunction =  this.mathFunction;
        return clone;
    }

    @Override
    public void mutate() {
        mathFunction = randomMathOperator(true);
    }
}
