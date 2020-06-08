package nodes.mathoperators;

import nodes.LocalFunctionStrategy;
public class LocalExp implements LocalFunctionStrategy {

    @Override
    public String print() {
        return "exp";
    }

    @Override
    public double value(double var) {
        return Math.exp(var);
    }
}
