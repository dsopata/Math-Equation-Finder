package oramus.zti.uj;

/**
 * Oto pełen eksperyment - mamy i obiekt generujący odpowiedz ekspetymentu
 * oraz obiekt odpowiedzialny za warunki przerowadzenia eksperymentu.
 */
public interface ExperimentInterface {
	public ExperimentResponseInterface getResponse();

	public ExperimentConditionsInterface getConditions();
}
