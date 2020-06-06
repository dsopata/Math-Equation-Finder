package nodes.mathoperators;

import nodes.LocalFunctionInterface;

public class LocalSin implements LocalFunctionInterface {

    @Override
    public String getLocalFunctionName() {
        return "sin";
    }

    @Override
    public String getLocalFunctionVal(double var) {
        return String.valueOf(Math.sqrt(var));
    }
}
