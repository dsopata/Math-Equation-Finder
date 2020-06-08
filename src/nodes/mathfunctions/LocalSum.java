package nodes.mathfunctions;

import nodes.LocalOperatorStrategy;

public class LocalSum implements LocalOperatorStrategy {

    @Override
    public String print() {
        return " + ";
    }

    @Override
    public double value(double var1, double var2) {
        return var1 + var2;
    }
}
