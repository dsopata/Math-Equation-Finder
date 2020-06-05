package oramus.zti.uj;

import java.util.List;

/**
 * Interfejs generatora zmiennych niezależnych.
 */
@FunctionalInterface
public interface IndependentVariablesGeneratorInterface {
	public List<Point> generate(Hypercube hcube, int points);
}
