package nodes;

/**
 *  Strategia dedykowana dla matematycznych typu sin, cos, exp,
 *  czyli tym, które przyjmują 1 argument.
 *
 */
public interface LocalFunctionStrategy {

    /**
     *
     * @return Otrzymumemy rekursywnie toString nodeow danego drzewa / poddrzewa
     */
    String print();

    /**
     *
     * @param var wartość dziecka damego wierzchołka.
     * @return Zwrócona wartość obliczeń Idąc od korzenia drzewa zostanie policzona wartość dla drzewa rekursywnie
     */
    double value(double var);

}
