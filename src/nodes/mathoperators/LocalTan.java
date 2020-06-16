package nodes.mathoperators;

import nodes.LocalFunctionStrategy;

public class LocalTan implements LocalFunctionStrategy {

    @Override
    public String print() {
        return "tan";
    }

    @Override
    public double value(double var) {
        return Math.tan(var);
    }
}
