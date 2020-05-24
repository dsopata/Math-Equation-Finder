import java.util.concurrent.TimeUnit;

/**
 * Podstawowy interfejs aplikacji. Pozwala na przekazanie danych oraz odebranie
 * wyniku obliczeĹ.
 *
 */
public interface ApplicationInterface {

    /**
     * Metoda zleca wykonanie obliczeĹ dla przekazywanych danych doĹwiadczalnych. Metoda blokuje
     * wÄtek wywoĹujÄcego jÄ uĹźytkownika do czasu zakoĹczenia obliczeĹ.
     *
     * @param dataAccess  dostÄp do danych doĹwiadczalnych
     * @param maxWorkTime maksymalny czas pracy
     * @param timeUnit    jednostka czasu uĹźyta do okreĹlenia maksymalnego czasu
     *                    pracy.
     * @return referencja do obiektu umoĹźliwiajÄcego pobranie wyniku obliczeĹ
     */
    public ResultInterface execute(ExperimentalDataAccessIntereface dataAccess, long maxWorkTime, TimeUnit timeUnit);
}