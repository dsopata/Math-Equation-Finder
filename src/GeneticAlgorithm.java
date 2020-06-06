
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class GeneticAlgorithm {

   private static final int POPULATION_SIZE = 200;

   public  AtomicBoolean breakLoop = new AtomicBoolean(false);

    private int populationSize;
    private List<Chromosome> population = new ArrayList<>();
    private final int numberOfExperimentalPoints;
    private final int numberOfIndependentVariables;

    public GeneticAlgorithm(int numberOfExperimentalPoints, int numberOfIndependentVariables) {
        this.numberOfExperimentalPoints = numberOfExperimentalPoints;
        this.numberOfIndependentVariables = numberOfIndependentVariables;
    }

    public  void generatePopulation(ExperimentalDataAccessIntereface dataAccess) {
        System.out.println("Generating population...");

        for(int a = 0; a < POPULATION_SIZE; a++) {
            population.add(generateChromosome(dataAccess));
        }
        calculateAndSortByScore(population, dataAccess);

    }

    private void calculateAndSortByScore(List<Chromosome> population, ExperimentalDataAccessIntereface dataAccess) {
        population.forEach(c -> c.calculateScore(dataAccess));
        Collections.sort(population);
    }

    private  Chromosome generateChromosome(ExperimentalDataAccessIntereface dataAccess) {
        Tree tree = new Tree(numberOfIndependentVariables);
        tree.setIndependentVariablesInTree(dataAccess.getNumberOfIndependentVariables());
//        System.out.println(tree.toString());
        return new Chromosome(tree);
    }

}
