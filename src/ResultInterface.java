/**
 * Interfejs pozwalający na pobranie rezultatu pracy aplikacji.
 *
 */
@FunctionalInterface
public interface ResultInterface {
	/**
	 * Metoda zwraca formułę, która charakteryzuje się najlepszym, uzyskanym do tej
	 * pory wynikiem. Wartości zmiennych niezależnych prowadzane są do formuły jako
	 * kolejne elementy tablicy o podanej nazwie.
	 * 
	 * <br>
	 * Formuła udostępniana jest w wersji uproszczonej, w której możliwie największe
	 * poddrzewa zostały zamienione ich wartością, oczywiście o ile tylko jest ona
	 * stała. Np. jeśli nazwa tablicy to "x" a liczba niezależnych parametrów to 2
	 * zamiast formuły: ((1+3)*(2))*(x[0]+x[1]*2) w wyniku pojawia się:
	 * 8*(x[0]+x[1]*2)
	 *
	 * 
	 * @param tableName nazwa tablicy, z której pobierane będą wartości kolejnych
	 *                  parametrów.
	 * @return uproszczony wzór będący najlepszym, uzyskanym przez program
	 *         rozwiązaniem.
	 */
	public String getFormula(String tableName);
}
