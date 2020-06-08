
import nodes.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class GeneticAlgorithm {

    private static final int POPULATION_SIZE = 500;
    private static final int POPULATION_ELITE = (int) Math.ceil(0.02 * POPULATION_SIZE);
    private static final int MUTATION_PROPABILITY_IN_PERCENTEGE = 2;

    public  AtomicBoolean breakLoop = new AtomicBoolean(false);

    private int populationSize;
    private List<Chromosome> population = new ArrayList<>();
    private final ExperimentalDataAccessIntereface dataAccess;
    private final int numberOfExperimentalPoints;
    private final int numberOfIndependentVariables;
    private Random random = new Random();
    private String currentBest = "";

    public GeneticAlgorithm(ExperimentalDataAccessIntereface dataAccess) {
        this.dataAccess = dataAccess;
        this.numberOfExperimentalPoints = dataAccess.getNumberOfExperimentalPoints();
        this.numberOfIndependentVariables = dataAccess.getNumberOfIndependentVariables();
    }

    public  void generatePopulation() {
        System.out.println("Generating population...");

        for(int a = 0; a < POPULATION_SIZE; a++) {
            population.add(generateChromosome());
        }
        calculateAndSortByScore(population);
    }

    private void calculateAndSortByScore(List<Chromosome> population) {
        population
                .parallelStream()
                .forEach( c -> c.calculateScore(dataAccess)
        );
        Collections.sort(population);
    }

    private  Chromosome generateChromosome() {
        Tree tree = new Tree(numberOfIndependentVariables);
        tree.setIndependentVariablesInTree(dataAccess.getNumberOfIndependentVariables());
        return new Chromosome(tree);
    }

    public void nextGeneration() {
        //krzyzowanie
        crossover();
        //mutowanie
        mutate();
        //selekcja
        Collections.sort(population);
        if(!currentBest.equals(population.get(0).getTree().toString())){
            currentBest = population.get(0).getTree().toString();
            System.out.println(currentBest);
        }
    }

    private void mutate() {
        if(random.nextInt(100) < MUTATION_PROPABILITY_IN_PERCENTEGE) {
            int chromosomeId1 = POPULATION_ELITE + (int) (Math.random() * Math.random() * (POPULATION_SIZE - POPULATION_ELITE));
            Chromosome chromosome1 = population.get(chromosomeId1);
            chromosome1.mutate(dataAccess);
        }
    }

    private void crossover() {
        int chromosomeId1 = POPULATION_ELITE + (int) (Math.random() * Math.random() * (POPULATION_SIZE - POPULATION_ELITE));
        Chromosome chromosome1 = population.get(chromosomeId1);

        int chromosomeId2 = POPULATION_ELITE + (int) (Math.random() * Math.random() * (POPULATION_SIZE - POPULATION_ELITE));
        Chromosome chromosome2 = population.get(chromosomeId2);
        Node node1 = chromosome1.getTree().getRandomNode();
        Node node2 = chromosome2.getTree().getRandomNode();

        Node temp =  node1.clone(null);
        Node temp2 = node2.clone(null);

        chromosome1.replace(node1, temp2, dataAccess);
        chromosome2.replace(node2, temp, dataAccess);
    }

}
