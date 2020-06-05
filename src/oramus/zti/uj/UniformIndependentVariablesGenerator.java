package oramus.zti.uj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UniformIndependentVariablesGenerator implements IndependentVariablesGeneratorInterface {

	private void inc(List<Integer> counters, int level, int max) {
		counters.set(level, counters.get(level) + 1);
		if (counters.get(level) == max) {
			counters.set(level, 0);
			if (level + 1 == counters.size()) {
				return;
			} else {
				inc(counters, level + 1, max);
			}
		}
	}

	private List<Integer> createCounters(int dimensions) {
		List<Integer> counters = new ArrayList<Integer>(dimensions);
		IntStream.range(0, dimensions).forEach(i -> counters.add(0));
		return counters;
	}

	private Point countersToPoint(List<Integer> counters, List<Double> starts, List<Double> deltas) {
		Point p = new Point(counters.size());
		for (int i = 0; i < counters.size(); i++)
			p.setLocation(i, starts.get(i) + deltas.get(i) * counters.get(i));
		return p;
	}

	@Override
	public List<Point> generate(Hypercube hcube, int points) {
		int dimensions = hcube.getDimensions();
		int pointsInOneDim = (int) Math.ceil(Math.pow(points, 1.0 / dimensions));
		int iterations = (int) Math.pow(pointsInOneDim, dimensions);
		List<Integer> counters = createCounters(dimensions);
		List<Double> starts = hcube.get().stream().map(r -> r.getMin()).collect(Collectors.toList());
		List<Double> deltas = hcube.get().stream().map(r -> (r.getMax() - r.getMin()) / (pointsInOneDim - 1))
				.collect(Collectors.toList());

		List<Point> pointsL = new ArrayList<Point>();
		IntStream.range(0, iterations).forEach(i -> {
			inc(counters, 0, pointsInOneDim);
			pointsL.add(countersToPoint(counters, starts, deltas));
		});

		if ( pointsL.size() == points )
			return pointsL;
		
		Collections.shuffle(pointsL); // szuflowanie punktów

		return pointsL.subList(0, points);
	}

}
