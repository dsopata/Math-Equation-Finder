package nodes.mathoperators;

import nodes.LocalFunctionStrategy;

public class LocalAbs implements LocalFunctionStrategy {

    @Override
    public String print() {
        return "abs";
    }

    @Override
    public double value(double var) {
        return Math.abs(var);
    }
}
