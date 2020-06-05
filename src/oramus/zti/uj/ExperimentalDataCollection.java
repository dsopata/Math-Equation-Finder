package oramus.zti.uj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Klasa pozwala na utworzenie kolekcji danych doświadczalnych. Odebranie danych
 * jest możliwe tylko w postaci kolekcji niemodyfikowalnych.
 */
public class ExperimentalDataCollection implements Serializable {

	private static final long serialVersionUID = 1932411631736379056L;
	private final List<ExperimentalPoint> collection;

	public ExperimentalDataCollection() {
		collection = new ArrayList<>();
	}

	public void addExperimentalPoint(ExperimentalPoint point) {
		collection.add(point);
	}

	public List<ExperimentalPoint> getDataCollection() {
		return Collections.unmodifiableList(collection);
	}

	@Override
	public String toString() {
		return collection.toString();
	}

}
