package nodes.mathfunctions;

import nodes.LocalOperatorInterface;

public class LocalDivide implements LocalOperatorInterface {

    @Override
    public String getLocalFunctionName() {
        return " / ";
    }

    @Override
    public double getLocalFunctionVal(double var1, double var2) {
        if(var2 == 0) {
            return 0d;
        }
        return var1 / var2;
    }

}
