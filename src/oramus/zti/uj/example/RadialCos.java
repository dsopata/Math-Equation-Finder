package oramus.zti.uj.example;

import oramus.zti.uj.ExperimentConditionsInterface;
import oramus.zti.uj.ExperimentInterface;
import oramus.zti.uj.ExperimentResponseInterface;
import oramus.zti.uj.Hypercube;
import oramus.zti.uj.Point;
import oramus.zti.uj.Range;

public class RadialCos implements ExperimentInterface {
	@Override
	public ExperimentConditionsInterface getConditions() {
		return new ExperimentConditionsInterface() {
			@Override
			public Hypercube prefferedRangeOfIndependentVariables() {
				Hypercube hc = new Hypercube();
				hc.addRange(new Range(-3.0 * Math.PI, 3.0 * Math.PI));
				hc.addRange(new Range(-3.0 * Math.PI, 3.0 * Math.PI));
				return hc;
			}

			@Override
			public int prefferedDimensions() {
				return 2;
			}
		};
	}

	@Override
	public ExperimentResponseInterface getResponse() {
		return new ExperimentResponseInterface() {

			@Override
			public double get(Point independentVariables) {
				double x = independentVariables.getLocation().get(0);
				double y = independentVariables.getLocation().get(1);
				double r = Math.sqrt(x * x + y * y);
				return Math.cos(r);
			}
		};
	}
}
