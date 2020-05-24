/**
 * Interfejs pozwalajÄcy na pobranie rezultatu pracy aplikacji.
 *
 */
@FunctionalInterface
public interface ResultInterface {
    /**
     * Metoda zwraca formuĹÄ, ktĂłra charakteryzuje siÄ najlepszym, uzyskanym do tej
     * pory wynikiem. WartoĹci zmiennych niezaleĹźnych prowadzane sÄ do formuĹy jako
     * kolejne elementy tablicy o podanej nazwie.
     *
     * <br>
     * FormuĹa udostÄpniana jest w wersji uproszczonej, w ktĂłrej moĹźliwie najwiÄksze
     * poddrzewa zostaĹy zamienione ich wartoĹciÄ, oczywiĹcie o ile tylko jest ona
     * staĹa. Np. jeĹli nazwa tablicy to "x" a liczba niezaleĹźnych parametrĂłw to 2
     * zamiast formuĹy: ((1+3)*(2))*(x[0]+x[1]*2) w wyniku pojawia siÄ:
     * 8*(x[0]+x[1]*2)
     *
     *
     * @param tableName nazwa tablicy, z ktĂłrej pobierane bÄdÄ wartoĹci kolejnych
     *                  parametrĂłw.
     * @return uproszczony wzĂłr bÄdÄcy najlepszym, uzyskanym przez program
     *         rozwiÄzaniem.
     */
    public String getFormula(String tableName);
}