package oramus.zti.uj;

import java.io.Serializable;
import java.util.List;

public class ExperimentalPoint implements Serializable {
	private static final long serialVersionUID = 1816257314738466224L;
	private final Point independentVariables;
	private final double dependentVariable;

	public ExperimentalPoint(Point independentVariables, double dependentVariable) {
		this.independentVariables = independentVariables;
		this.dependentVariable = dependentVariable;
	}

	public List<Double> getIndependentVariables() {
		return independentVariables.getLocation();
	}

	public Point getIndependentVariablesAsPoint() {
		return independentVariables;
	}

	public double getDependentVariable() {
		return dependentVariable;
	}

	@Override
	public String toString() {
		return "Experimental Point [ " + independentVariables + " -> " + dependentVariable + " ]";
	}
}
