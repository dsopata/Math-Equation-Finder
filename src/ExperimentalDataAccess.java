public class ExperimentalDataAccess implements ExperimentalDataAccessIntereface {

    private double[] dependentVariable = new double[0];
    private double[][] independentVariables = new double[0][];

    public ExperimentalDataAccess() {

    }

    public ExperimentalDataAccess(double[] dependentVariable, double[][] independentVariables) {
        this.dependentVariable = dependentVariable; // y - > sin(x)
        this.independentVariables = independentVariables; // x- >
    }

    @Override
    public int getNumberOfExperimentalPoints() {
        return this.independentVariables.length;
    }

    @Override
    public int getNumberOfIndependentVariables() {
        return this.independentVariables[0].length;
    }

    @Override
    public double[] getIndependentVariables(int dataPointIndex) throws IndexOutOfBoundsException {
        return independentVariables[dataPointIndex];
    }

    @Override
    public double getDependentVariable(int dataPointIndex) throws IndexOutOfBoundsException {
        return dependentVariable[dataPointIndex];
    }


    public void setDependentVariable(double[] dependentVariable) {
        this.dependentVariable = dependentVariable;
    }

    public void setIndependentVariables(double[][] independentVariables) {
        this.independentVariables = independentVariables;
    }
}
