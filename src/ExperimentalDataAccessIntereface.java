/**
 * Interfejs umożliwiający dostęp do danych doświadczalnych.
 *
 */
public interface ExperimentalDataAccessIntereface {

	/**
	 * Metoda zwraca liczbę punktów doświadczalnych.
	 * 
	 * @return liczba punktów doświadczalnych.
	 */
	public int getNumberOfExperimentalPoints();

	/**
	 * Metoda zwraca liczbę zmiennych niezależnych.
	 * 
	 * @return liczba zmiennych niezależnych.
	 */
	public int getNumberOfIndependentVariables();

	/**
	 * Metoda zwraca tablicę wartości zmiennych niezależnych. Tablica ma rozmiar
	 * getNumberOfExperimentalPoints().
	 * 
	 * @param dataPointIndex indeks (numer) punktu doświadczalnego. Dozwolone są
	 *                       wartości od 0 do getNumberOfExperimentalPoints()-1.
	 *                       Rozmiar tablicy jest identyczny dla wszystkich punktów
	 *                       doświadczalnych i wynosi
	 *                       getNumberOfIndependentVariables();
	 * @return tablica z wartościami zmiennych niezależnych dla punktu
	 *         doświadczalnego numer dataPointIndex.
	 * @throws IndexOutOfBoundsException wyjątek informujący o wyjście poza
	 *                                   dozwolony zakres indeksów punktów
	 *                                   doświadczalnych.
	 */
	public double[] getIndependentVariables(int dataPointIndex) throws IndexOutOfBoundsException;

	/**
	 * Metoda zwraca wartość zmiennej zależnej.
	 * 
	 * @param dataPointIndex indeks punktu doświadczalnego
	 * @return wartość zmiennej zależnej
	 * @throws IndexOutOfBoundsException wyjątek informujący o wyjście poza
	 *                                   dozwolony zakres indeksów punktów
	 *                                   doświadczalnych.
	 */
	public double getDependentVariable(int dataPointIndex) throws IndexOutOfBoundsException;

}
