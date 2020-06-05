public class ExperimentalDataAccess implements ExperimentalDataAccessIntereface {

    private double   dependendVariable;
    private double[] independentVariables;

    public ExperimentalDataAccess() {
        dependendVariable = 0;
        independentVariables = new double[0];
    }

    public ExperimentalDataAccess(double dependentVariable, double[] independentVariables) {
        this.dependendVariable = dependentVariable; // y - > sin(x)
        this.independentVariables = independentVariables; // x- >
    }

    @Override
        public int getNumberOfExperimentalPoints() {
            return this.independentVariables.length + 1;
        }

        @Override
        public int getNumberOfIndependentVariables() {
            return this.independentVariables.length;
        }

        @Override
        public double[] getIndependentVariables(int dataPointIndex) throws IndexOutOfBoundsException {
            return new double[0];
        }

        @Override
        public double getDependentVariable(int dataPointIndex) throws IndexOutOfBoundsException {
            return 0;
    }


}
