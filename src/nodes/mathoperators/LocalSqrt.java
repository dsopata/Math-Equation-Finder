package nodes.mathoperators;

import nodes.LocalFunctionInterface;

public class LocalSqrt implements LocalFunctionInterface {

    @Override
    public String getLocalFunctionName() {
        return "sqrt";
    }

    @Override
    public String getLocalFunctionVal(double var) {
        return String.valueOf(Math.sqrt(var));
    }
}
