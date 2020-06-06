package nodes.mathoperators;

import nodes.LocalFunctionInterface;

public class LocalExp implements LocalFunctionInterface {

    @Override
    public String getLocalFunctionName() {
        return "exp";
    }

    @Override
    public String getLocalFunctionVal(double var) {
        return String.valueOf(Math.sqrt(var));
    }
}
