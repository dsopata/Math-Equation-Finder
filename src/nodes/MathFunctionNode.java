package nodes;

;
import nodes.mathoperators.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MathFunctionNode extends Node {

    private MathFunctions mathFunction;

    public enum MathFunctions {
        SQRT,
        EXP,
        SIN,
        COS,
        ABS,
        LOG,
        TAN
    }

    private  final Map<MathFunctions, LocalFunctionStrategy> mathFunctionsMap  = new HashMap<>() {{
        put(MathFunctionNode.MathFunctions.SQRT, new LocalSqrt());
        put(MathFunctionNode.MathFunctions.EXP, new LocalExp());
        put(MathFunctionNode.MathFunctions.SIN, new LocalSin());
        put(MathFunctionNode.MathFunctions.COS, new LocalCos());
        put(MathFunctionNode.MathFunctions.ABS, new LocalAbs());
        put(MathFunctionNode.MathFunctions.LOG, new LocalLog());
        put(MathFunctionNode.MathFunctions.TAN, new LocalTan());
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
        if(bothChildrenHaveConstValue()) {
            return calculateConstChildren();
        }

        return  mathFunctionsMap.get(mathFunction).value(children[0].calculate(independentVariables));
    }

    @Override
    public String toString() {
        if(bothChildrenHaveConstValue()) {
            return String.valueOf(calculateConstChildren());
        }

        return mathFunctionsMap.get(mathFunction).print() + bracket(true) + children[0].toString() + bracket(false);
    }

    @Override
    public Node clone(Node parent, int level) {
        MathFunctionNode clone = new MathFunctionNode(this.level, this.parent);
        clone.nodeType =  this.nodeType;
        clone.children =  new Node[] {this.children[0].clone(clone, this.level+1), null};
        clone.mathFunction =  this.mathFunction;
        return clone;
    }

    @Override
    public void mutate() {
        mathFunction = randomMathOperator(true);
    }

    private Double calculateConstChildren() {
        return mathFunctionsMap.get(mathFunction).value(((ValueNode)children[0]).value);
    }

    private boolean bothChildrenHaveConstValue() {
        return children[0].getNodeType().equals(NodeType.VALUE) &&
                ((ValueNode)children[0]).variableId == -1;
    }

}
