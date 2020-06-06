package nodes.mathoperators;

import nodes.LocalFunctionInterface;

public class LocalSqrt implements LocalFunctionInterface {

    @Override
    public String getLocalFunctionName() {
        return "sqrt";
    }

    @Override
    public double getLocalFunctionVal(double var) {
        return Math.sqrt(var);
    }
}
