package oramus.zti.uj;

/**
 * Interfejs pozwalający na utworzenie rodziny generatorów 
 * zakłóceń.
 */
@FunctionalInterface
public interface NoiseInterface {
	public double get(Point point, double value);
}
