
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import oramus.zti.uj.Apparatus;
import oramus.zti.uj.CSVFileSaver;
import oramus.zti.uj.ExperimentInterface;
import oramus.zti.uj.ExperimentalDataCollection;
import oramus.zti.uj.Experimenter;
import oramus.zti.uj.UniformIndependentVariablesGenerator;
import oramus.zti.uj.example.DecayRadialSin;
import oramus.zti.uj.example.UniformDistributionNoise;

/**
 * Klasa pozwala na szybkie odbieranie danych doświadczalnych przez interfejs
 * zadania.
 */
public class PMO_ExperimentalDataGetter implements ExperimentalDataAccessIntereface {

	private final List<double[]> iVariablesT;
	private final List<Double> dVariables;
	private final int iVariablesSize;
	private final int dataPoints;

	private void convertToArray(List<List<Double>> iVariables) {
		for (List<Double> oneLine : iVariables) {
			if (oneLine == null)
				throw new RuntimeException("Na liście danych niezależnych wykryto null");
			double[] tmp = oneLine.stream().mapToDouble(d -> d).toArray();
			if (tmp.length != iVariablesSize) {
				throw new RuntimeException("Wykryto błędną ilość danych niezależnych");
			}

			iVariablesT.add(tmp);
		}
	}

	public PMO_ExperimentalDataGetter(ExperimentalDataCollection ed) {
		List<List<Double>> iVariables = new ArrayList<List<Double>>();
		List<Double> dVariables = new ArrayList<Double>();

		ed.getDataCollection().forEach(ep -> {
			iVariables.add(ep.getIndependentVariables());
			dVariables.add(ep.getDependentVariable());
		});

		iVariablesT = new ArrayList<double[]>();
		iVariablesSize = iVariables.get(0).size();
		dataPoints = dVariables.size();
		this.dVariables = dVariables;
		convertToArray(iVariables);
	}

	public int getNumberOfExperimentalPoints() {
		return dataPoints;
	}

	public int getNumberOfIndependentVariables() {
		return iVariablesSize;
	}

	public double[] getIndependentVariables(int dataPointIndex) throws IndexOutOfBoundsException {
		return iVariablesT.get(dataPointIndex);
	}

	public double getDependentVariable(int dataPointIndex) throws IndexOutOfBoundsException {
		return dVariables.get(dataPointIndex);
	}

	public static void main(String[] args) throws IOException {
		ExperimentInterface ei = new DecayRadialSin();
		Apparatus apparatus = new Apparatus(ei.getResponse());
		apparatus.addNoiseGenerator(new UniformDistributionNoise(-0.05, 0.05));
		Experimenter experimenter = new Experimenter(new UniformIndependentVariablesGenerator(), apparatus,
				ei.getConditions());
		ExperimentalDataCollection collection = experimenter.carryOutExperiment(2500);
//		new CSVFileSaver().save(collection, "/tmp/out");
//		System.out.println( new CSVFileSaver().load("/tmp/out") );
		
		PMO_ExperimentalDataGetter data = new PMO_ExperimentalDataGetter(collection);
		
	}

}
