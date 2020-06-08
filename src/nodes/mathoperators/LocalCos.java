package nodes.mathoperators;

import nodes.LocalFunctionStrategy;

public class LocalCos implements LocalFunctionStrategy {

    @Override
    public String print() {
        return "cos";
    }

    @Override
    public double value(double var) {
        return Math.cos(var);
    }
}
