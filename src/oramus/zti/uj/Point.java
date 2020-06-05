package oramus.zti.uj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Point implements Serializable {
	private static final long serialVersionUID = 5405687847049097007L;
	private final List<Double> position;
	private final int dimensions;

	public Point(int dimensions) {
		position = new ArrayList<Double>(dimensions);
		IntStream.range(0, dimensions).forEach( i -> position.add(0.0));
		this.dimensions = dimensions;
	}
	
	public Point(List<Double> position ) {
		this.position = new ArrayList<Double>( position );
		dimensions = position.size();
	}
	
	public List<Double> getLocation( ) {
		return Collections.unmodifiableList(position);
	}

	public void setLocation(int dimension, double value) {
		position.set(dimension, value);
	}

	public double distanceSQ(Point other) {
		double sum = 0;
		double dx;
		for (int i = 0; i < dimensions; i++) {
			dx = position.get(i) - other.position.get(i);
			sum += dx * dx;
		}
		return sum;
	}

	public double distance(Point other) {
		return Math.sqrt(distanceSQ(other));
	}

	@Override
	public String toString() {
		String tmp = "Point(";
		for (int i = 0; i < dimensions - 1; i++) {
			tmp += position.get(0) + ",";
		}
		return tmp + position.get(dimensions - 1) + ")";
	}
}
