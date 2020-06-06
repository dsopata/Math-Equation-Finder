package nodes.mathoperators;

import nodes.LocalFunctionInterface;

public class LocalSin implements LocalFunctionInterface {

    @Override
    public String getLocalFunctionName() {
        return "sin";
    }

    @Override
    public double getLocalFunctionVal(double var) {
        return Math.sin(var);
    }
}
