package oramus.zti.uj;

/**
 * Klasa odpowiada za wygenerowanie zmiennych niezależnych.
 */
public class Experimenter {

	private final IndependentVariablesGeneratorInterface generator;
	private final Apparatus apparatus;
	private final ExperimentConditionsInterface conditions;

	public Experimenter(IndependentVariablesGeneratorInterface generator, Apparatus apparatus,
			ExperimentConditionsInterface conditions) {
		this.generator = generator;
		this.apparatus = apparatus;
		this.conditions = conditions;
	}

	public ExperimentalDataCollection carryOutExperiment(int numberOfPoints) {
		ExperimentalDataCollection storage = new ExperimentalDataCollection();
		generator.generate(conditions.prefferedRangeOfIndependentVariables(), numberOfPoints)
				.forEach(point -> storage.addExperimentalPoint(apparatus.response(point)));
		return storage;
	}

}
