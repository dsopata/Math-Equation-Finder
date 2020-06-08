package nodes.mathfunctions;

import nodes.LocalOperatorStrategy;

public class LocalDivide implements LocalOperatorStrategy {

    @Override
    public String print() {
        return " / ";
    }

    @Override
    public double value(double var1, double var2) {
        if(var2 == 0) {
            return 0d;
        }
        return var1 / var2;
    }

}
