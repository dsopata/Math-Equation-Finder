package nodes.mathfunctions;

import nodes.LocalOperatorInterface;

public class LocalSubstract implements LocalOperatorInterface {


    @Override
    public String getLocalFunctionName() {
        return " - ";
    }

    @Override
    public double getLocalFunctionVal(double var1, double var2) {
        return var1 - var2;
    }
}
