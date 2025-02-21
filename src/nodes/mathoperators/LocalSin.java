package nodes.mathoperators;

import nodes.LocalFunctionStrategy;

public class LocalSin implements LocalFunctionStrategy {

    @Override
    public String print() {
        return "sin";
    }

    @Override
    public double value(double var) {
        return Math.sin(var);
    }
}
