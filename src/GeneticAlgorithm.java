
import nodes.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private static final int POPULATION_SIZE = 800;
    private static final int POPULATION_ELITE = (int) Math.ceil(0.01 * POPULATION_SIZE);
    private static final int MUTATION_PROPABILITY_IN_PERCENTEGE = 2;
    private static final boolean SHOW_LOG = true;

    private List<Chromosome> population = new ArrayList<>();
    private final ExperimentalDataAccessIntereface dataAccess;
    private final int numberOfIndependentVariables;
    private Random random = new Random();
    private static String currentBest = "";

    public GeneticAlgorithm(ExperimentalDataAccessIntereface dataAccess) {
        this.dataAccess = dataAccess;
        this.numberOfIndependentVariables = dataAccess.getNumberOfIndependentVariables();
    }

    public  void generatePopulation() {
        if(SHOW_LOG) {
            System.out.println("Generating population...");
        }
        for(int a = 0; a < POPULATION_SIZE; a++) {
            population.add(generateChromosome());
        }
        Collections.sort(population);
    }

    private Chromosome generateChromosome()  {
        Tree tree = new Tree(numberOfIndependentVariables);
        return new Chromosome(tree, dataAccess);
    }

    public void nextGeneration(ResultInterface result) {
        //krzyzowanie
        crossover();
        //mutowanie
        mutate();
        //podmiana najslabszych na losowe osobniki (o rozmiarze elity)
        appendNewChromosomes();
        //selekcja
        Collections.sort(population);
        if(!currentBest.equals(population.get(0).getTree().toString())){
            currentBest = population.get(0).getTree().toString();
            ((Result)result).setBestResult(currentBest);
            if(SHOW_LOG) {
                System.out.println("Best: " + currentBest);
            }
       }
    }

    private void appendNewChromosomes() {
        population = population.subList(0, POPULATION_SIZE - POPULATION_ELITE);
        for(int a = 0; a < POPULATION_ELITE; a++) {
            population.add(generateChromosome());
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
        int chromosomeId1 = POPULATION_ELITE + (int) (Math.random() * Math.random() * (POPULATION_SIZE - 2 * POPULATION_ELITE));
        Chromosome chromosome1 = population.get(chromosomeId1);

        int chromosomeId2 = POPULATION_ELITE + (int) (Math.random() * Math.random() * (POPULATION_SIZE - 2 * POPULATION_ELITE));
        Chromosome chromosome2 = population.get(chromosomeId2);
        Node node1 = chromosome1.getTree().getRandomNode();
        Node node2 = chromosome2.getTree().getRandomNode();

        Node temp =  node1.clone(null, 0);
        Node temp2 = node2.clone(null, 0);

        chromosome1.replace(node1, temp2, dataAccess);
        chromosome2.replace(node2, temp, dataAccess);
    }

}
