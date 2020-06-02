public class LocalSubstract implements LocalFunctionInterface {

    @Override
    public String getLocalFunctionString(double val) {
        return " + " + val;
    }

    @Override
    public String getLocalFunctionName() {
        return " + ";
    }

    @Override
    public String getLocalFunctionVal(double var) {
        return String.valueOf(var);
    }
}
