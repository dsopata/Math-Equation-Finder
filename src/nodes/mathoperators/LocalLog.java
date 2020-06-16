package nodes.mathoperators;

import nodes.LocalFunctionStrategy;

public class LocalLog implements LocalFunctionStrategy {

    @Override
    public String print() {
        return "log";
    }

    @Override
    public double value(double var) {
        return Math.log(var);
    }
}
