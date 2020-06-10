Aplikacja umożliwia predykcje funkcji matematycznej na podstawie wyłącznie zmiennych zależnych oraz niezależnych.

1. Struktura aplikacji

    Podstawową klasa jest klasa Application, której przesyłając odpowiednie argumenty uruchamiamy algorytm genetyczny.
    Najważniejsza klasa algorytmu do GenethicAlgorithm. W niej są zaimplementowane podstawowe funkcje oraz parametry algorytmu genetycznego.
    
    Klasa Result służy do pozyskiwania najlepszego obliczonego wyniku algorytmu. Dlaczego tak naprawdę w niej nie zaimplementowalimy optymalizacji wzorów? Ponieważ wygodniejsze jest optymalizowanie wzoru gdy mamy jeszcze dostęp do struktury drzewa binarnego - nie jednowymiarowej tablicy. 
    
    Klasa Chromosome jest reprezentacja osobnika w populacji. Zawiera ona w sobie drzewo binarne, oraz score, który jest wykonywany zawsze przy:
     * tworzeniu osobnika
     * mutacji osobnika
     * zmianie struktury osobnika (przy krzyżowaniu)
     
    Tree jest reprezentacja drzewa binarnego zbudowanego z wierzchołków reprezentowanych przez klasę Node.
    
    Pakiet nodes:
    
    Pakiet ten zawiera implementacje strategii dla każdego typu wierzchołka, oraz jego prototyp (klasa abstrakcyjna Node).

2. Wzorce projektowe:

    Podstawowe wzorce projektowe, z których korzystaliśmy do budowy tego projektu:
    * Strategia - przygotowane zostały dwie strategie dla wierzchołków funkcji matematycznych jednoargumentowych (sin, cos), oraz dwurgumentowych (suma +, iloczyn * itp.).
     Te interfejsy to odpowiednio: LocalFunctionStrategy, LocalOperatorStrategy.
    * Prototyp - prototyp wierzchołka, który w tym projekcie posiada 3 rozszerzenia - MathOperatorNode, MathFunctionNode, ValueNode.
    Każde z klas bazujących na tym prototypie jest kolejno rodzajem wierzchołka. MathOperatorNode - funkcje dwuargumentowe, MathFunctionNode - funkcje jednoargumentowe, ValueNode - wierzchołki ze zmiennymi niezależnymi lub stałymi.
    * Singleton - serce algorytmu genetycznego czyli klasa GeneticAlgorithm. W niej są zawarte implementacje metod do algorytmu genetycznego, jak również w statycznej tablicy jest przechowywany najlepszy rezultat.
 
3. Działanie algorymtu:

    Na początku pe†li tworzoącej kolejne populacje, jest przygotowana populacja początkowa. Wszystkie osobniki mają tworzone losowo drzewo binarne. Jego wysokość maksymalna jest parametryzowalna (Tree.MAXIMUM_TREE_HEIGHT), oraz prawdopodobieństwo wygenerowania danego wierzchołka (Tree.MATH_OPERATOR_NODE_PROPABILITY, Tree.MATH_FUNCTION_NODE_PROPABILITY, Tree.VALUE_NODE_PROPABILITY).
    Nastepnie osobniki są sortowani w liście w zależności od score.
    Jest on obliczany ze wzoru F = sum( ( f(i) - y(i) )^2 ).
    W zalezności od tego czy mamy ustawiony elitaryzm (ilosc procentowa osobnikw nie poddawanej mutacji, krzyżowaniu, usuwaniu - GenethicAlgorithm.POPULATION_ELITE) poza elitą zosją wykonywane krzyżowania, mutacje, oraz usuwanie najsłabszych osobnikow / dodawanie nowych losowych (rozmiar ich jest taki sam jak elity w populacji).
    Wyżej wymienone operacje są wykonywane, dopoki nie przekroczymy wyznaczonego czasu.
    
4. Rownoleglośc 
 
    Została wykorzystana przy obliczaniu score dla każdego osobnika (paraller Stream).