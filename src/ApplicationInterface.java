import java.util.concurrent.TimeUnit;

/**
 * Podstawowy interfejs aplikacji. Pozwala na przekazanie danych oraz odebranie
 * wyniku obliczeń.
 * 
 */
public interface ApplicationInterface {

	/**
	 * Metoda zleca wykonanie obliczeń dla przekazywanych danych doświadczalnych. Metoda blokuje
	 * wątek wywołującego ją użytkownika do czasu zakończenia obliczeń.
	 * 
	 * @param dataAccess  dostęp do danych doświadczalnych
	 * @param maxWorkTime maksymalny czas pracy
	 * @param timeUnit    jednostka czasu użyta do określenia maksymalnego czasu
	 *                    pracy.
	 * @return referencja do obiektu umożliwiającego pobranie wyniku obliczeń
	 */
	public ResultInterface execute(ExperimentalDataAccessIntereface dataAccess, long maxWorkTime, TimeUnit timeUnit);
}
