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


public class GeneticAlgorithmService {

    private static final int MAXIMUM_TREE_HEIGHT = 6;
    private static final int MINIMAL_TREE_HEIGHT = 3;

    public static AtomicBoolean breakLoop = new AtomicBoolean(false);

    private static int populationSize;
    private static List<Chromosome> population = new ArrayList<>();

    private static final Map<MathOperators, LocalFunctionInterface> mathOperatorsMap  = new HashMap<>() {{
        put(MathOperators.SUM, new LocalSum());
        put(MathOperators.DIVIDE, new LocalDivide());
        put(MathOperators.MULTIPLY, new LocalMultiply());
        put(MathOperators.SUBTRACT, new LocalSubstract());
    }};

    private static final Map<MathFunctionNode.MathFunctions, LocalFunctionInterface> mathFunctionsMap  = new HashMap<>() {{
        put(MathFunctionNode.MathFunctions.SQRT, new LocalSqrt());
    }};

    public  void startGeneticAlgorithm() {

    }


    public static void generatePopulation(int populationSize, ExperimentalDataAccessIntereface dataAccess) {
        System.out.println("Generating population...");
        for(int a = 0; a < populationSize; a++) {
            population.add(generateChromosome(dataAccess));
        }
    }

    private static Chromosome generateChromosome(ExperimentalDataAccessIntereface dataAccess) {
        Node root = new MathOperatorNode(0);
        int numberOfSamples = dataAccess.getNumberOfExperimentalPoints();


        Tree tree = new Tree(root);


        return new Chromosome(tree);
    }

    private  void printoutResult() {

    }

}
