import nodes.MathOperatorNode;
import nodes.Node;

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
    private static List<Tree> population = new ArrayList<>();

    private static final Map<MathOperatorNode.MathOperators, LocalFunctionInterface> doubleBraceMap  = new HashMap<>() {{
        put(MathOperatorNode.MathOperators.SUM, new LocalSum());
        put(MathOperatorNode.MathOperators.DIVIDE, new LocalDivide());
        put(MathOperatorNode.MathOperators.MULTIPLY, new LocalMultiply());
        put(MathOperatorNode.MathOperators.SUBTRACT, new LocalSubstract());
    }};

    public  void startGeneticAlgorithm() {

    }


    public static void generatePopulation(int populationSize, ExperimentalDataAccessIntereface dataAccess) {
        System.out.println("Generating population...");
        for(int a = 0; a < populationSize; a++) {
            population.add(generateChromosome());
        }
    }

    private static Tree generateChromosome() {
        Node root = new MathOperatorNode(0);
        Tree tree = new Tree(root);

        return tree;
    }

    private  void printoutResult() {

    }

}
