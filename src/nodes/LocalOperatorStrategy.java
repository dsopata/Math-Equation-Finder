package nodes;

/**
 *  Strategia dedykowana dla matematycznych typu suma, iloczyn, iloraz,
 *  czyli tym, które przyjmują 2 argumenty.
 *
 */
public interface LocalOperatorStrategy {

    /**
     *
     * @return Otrzymumemy rekursywnie toString nodeow danego drzewa / poddrzewa
     */
    String print();

    /**
     *
     * @param var1 wartość 1 dziecka.
     * @param var2 wartość 2 dziecka.
     * @return Zwrócona wartość obliczeń funkcji operatorów matematycznych typu + - / *.
     * Idąc od korzenia drzewa zostanie policzona wartość dla drzewa rekursywnie
     */
    double value(double var1, double var2);

}
