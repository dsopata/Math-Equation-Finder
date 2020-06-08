package nodes.mathoperators;

import nodes.LocalFunctionInterface;

public class LocalCos implements LocalFunctionInterface {

    @Override
    public String getLocalFunctionName() {
        return "cos";
    }

    @Override
    public double getLocalFunctionVal(double var) {
        return Math.cos(var);
    }
}
