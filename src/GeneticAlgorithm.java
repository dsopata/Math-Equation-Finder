import nodes.LocalFunctionInterface;
import nodes.MathFunctionNode;
import nodes.MathOperatorNode.MathOperators;
import nodes.mathfunctions.*;
import nodes.MathOperatorNode;
import nodes.Node;
import nodes.mathoperators.LocalSqrt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


public class GeneticAlgorithm {

    public  AtomicBoolean breakLoop = new AtomicBoolean(false);

    private  int populationSize;
    private  List<Chromosome> population = new ArrayList<>();
    private final int numberOfExperimentalPoints;
    private final int numberOfIndependentVariables;

    private  final Map<MathOperators, LocalFunctionInterface> mathOperatorsMap  = new HashMap<>() {{
        put(MathOperators.SUM, new LocalSum());
        put(MathOperators.DIVIDE, new LocalDivide());
        put(MathOperators.MULTIPLY, new LocalMultiply());
        put(MathOperators.SUBTRACT, new LocalSubstract());
    }};

    private  final Map<MathFunctionNode.MathFunctions, LocalFunctionInterface> mathFunctionsMap  = new HashMap<>() {{
        put(MathFunctionNode.MathFunctions.SQRT, new LocalSqrt());
    }};

    public GeneticAlgorithm(int numberOfExperimentalPoints, int numberOfIndependentVariables) {
        this.numberOfExperimentalPoints = numberOfExperimentalPoints;
        this.numberOfIndependentVariables = numberOfIndependentVariables;
    }


    public  void generatePopulation(int populationSize, ExperimentalDataAccessIntereface dataAccess) {
        System.out.println("Generating population...");

        for(int a = 0; a < populationSize; a++) {
            population.add(generateChromosome(dataAccess));
        }
    }

    private  Chromosome generateChromosome(ExperimentalDataAccessIntereface dataAccess) {

        Tree tree = new Tree(numberOfIndependentVariables);

        return new Chromosome(tree);
    }

    private  void printoutResult() {

    }

}
