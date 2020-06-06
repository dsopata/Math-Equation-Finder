package nodes.mathfunctions;

import nodes.LocalFunctionInterface;

public class LocalSubstract implements LocalFunctionInterface {


    @Override
    public String getLocalFunctionName() {
        return " - ";
    }

    @Override
    public String getLocalFunctionVal(double var) {
        return String.valueOf(var);
    }
}
