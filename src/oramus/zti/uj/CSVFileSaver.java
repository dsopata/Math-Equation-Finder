package oramus.zti.uj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFileSaver implements DataStorageInterface {

	private List<Double> arrayToDouble(String[] array) {
		return Arrays.stream(array).map(d -> Double.valueOf(d)).collect(Collectors.toList());
	}

	@Override
	public ExperimentalDataCollection load(String filename) throws FileNotFoundException, IOException {
		File csvInputFile = new File(filename);

		ExperimentalDataCollection edc = new ExperimentalDataCollection();

		try (BufferedReader reader = new BufferedReader(new FileReader(csvInputFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineT = line.split(",");
				List<Double> independentVariables = arrayToDouble( Arrays.copyOfRange(lineT, 0, lineT.length-1));
				double dependentVariable = Double.valueOf( lineT[ lineT.length-1]);
				edc.addExperimentalPoint( new ExperimentalPoint(new Point(independentVariables), dependentVariable ));				
			}
		}

		return edc;
	}

	@Override
	public void save(ExperimentalDataCollection collection, String filename) throws FileNotFoundException {
		List<List<Double>> values = new ArrayList<List<Double>>();
		collection.getDataCollection().forEach(ep -> {
			List<Double> tmp = new ArrayList<Double>(ep.getIndependentVariables());
			tmp.add(ep.getDependentVariable());
			values.add(tmp);
		});

		File csvOutputFile = new File(filename);
		try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
			for (List<Double> line : values) {
				pw.println(line.stream().map(v -> v.toString()).collect(Collectors.joining(",")));
			}
		}
	}
}
