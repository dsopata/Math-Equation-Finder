/**
 * Interfejs umoĹźliwiajÄcy dostÄp do danych doĹwiadczalnych.
 *
 */
public interface ExperimentalDataAccessIntereface {

    /**
     * Metoda zwraca liczbÄ punktĂłw doĹwiadczalnych.
     *
     * @return liczba punktĂłw doĹwiadczalnych.
     */
    public int getNumberOfExperimentalPoints();

    /**
     * Metoda zwraca liczbÄ zmiennych niezaleĹźnych.
     *
     * @return liczba zmiennych niezaleĹźnych.
     */
    public int getNumberOfIndependentVariables();

    /**
     * Metoda zwraca tablicÄ wartoĹci zmiennych niezaleĹźnych. Tablica ma rozmiar
     * getNumberOfExperimentalPoints().
     *
     * @param dataPointIndex indeks (numer) punktu doĹwiadczalnego. Dozwolone sÄ
     *                       wartoĹci od 0 do getNumberOfExperimentalPoints()-1.
     *                       Rozmiar tablicy jest identyczny dla wszystkich punktĂłw
     *                       doĹwiadczalnych i wynosi
     *                       getNumberOfIndependentVariables();
     * @return tablica z wartoĹciami zmiennych niezaleĹźnych dla punktu
     *         doĹwiadczalnego numer dataPointIndex.
     * @throws IndexOutOfBoundsException wyjÄtek informujÄcy o wyjĹcie poza
     *                                   dozwolony zakres indeksĂłw punktĂłw
     *                                   doĹwiadczalnych.
     */
    public double[] getIndependentVariables(int dataPointIndex) throws IndexOutOfBoundsException;

    /**
     * Metoda zwraca wartoĹÄ zmiennej zaleĹźnej.
     *
     * @param dataPointIndex indeks punktu doĹwiadczalnego
     * @return wartoĹÄ zmiennej zaleĹźnej
     * @throws IndexOutOfBoundsException wyjÄtek informujÄcy o wyjĹcie poza
     *                                   dozwolony zakres indeksĂłw punktĂłw
     *                                   doĹwiadczalnych.
     */
    public double getDependentVariable(int dataPointIndex) throws IndexOutOfBoundsException;

}