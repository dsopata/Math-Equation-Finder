package oramus.zti.uj.example;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import oramus.zti.uj.NoiseInterface;
import oramus.zti.uj.Point;

public class UniformDistributionNoise implements NoiseInterface {

	private final double min;
	private final double delta;
	private final Random rnd;

	public UniformDistributionNoise(double min, double max) {
		this.min = min;
		this.delta = max - min;
		rnd = ThreadLocalRandom.current();
	}

	@Override
	public double get(Point point, double value) {
		return value + min + rnd.nextDouble() * delta;
	}
}
