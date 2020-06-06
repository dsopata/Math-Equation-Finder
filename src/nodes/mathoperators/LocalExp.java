package nodes.mathoperators;

import nodes.LocalFunctionInterface;
public class LocalExp implements LocalFunctionInterface {

    @Override
    public String getLocalFunctionName() {
        return "exp";
    }

    @Override
    public double getLocalFunctionVal(double var) {
        return Math.exp(var);
    }
}
