package nodes.mathoperators;

import nodes.LocalFunctionStrategy;

public class LocalSqrt implements LocalFunctionStrategy {

    @Override
    public String print() {
        return "sqrt";
    }

    @Override
    public double value(double var) {
        return Math.sqrt(var);
    }
}
