package nodes;

;
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
        SIN
    }

    private  final Map<MathFunctions, LocalFunctionInterface> mathFunctionsMap  = new HashMap<>() {{
        put(MathFunctionNode.MathFunctions.SQRT, new LocalSqrt());
        put(MathFunctionNode.MathFunctions.EXP, new LocalExp());
        put(MathFunctionNode.MathFunctions.SIN, new LocalSin());
    }};


    public MathFunctionNode(int level) {
        super(level);
        nodeType = NodeType.MATH_FUNCTION;
        children = new Node[2];
        mathFunction = randomMathOperator();
    }

    private static MathFunctions randomMathOperator() {
        return MathFunctions.values()[new Random().nextInt(MathFunctions.values().length)];
    }

    @Override
    public double calculate(double[] independentVariables) {
        return  mathFunctionsMap.get(mathFunction).getLocalFunctionVal(children[0].calculate(independentVariables));
    }

    @Override
    public String toString() {
        return mathFunctionsMap.get(mathFunction).getLocalFunctionName() + "(" + children[0].toString() + ")";
    }
}
