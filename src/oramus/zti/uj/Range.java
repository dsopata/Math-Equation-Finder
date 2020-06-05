package oramus.zti.uj;

public class Range {
	private final double min;
	private final double max;

	public Range(double min, double max) {
		this.max = max;
		this.min = min;
	}

	public boolean inside(double value) {
		if (value > max)
			return false;
		if (value < min)
			return false;
		return true;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	@Override
	public String toString() {
		return "Range from " + min + " to " + max;
	}
}
