import nodes.Node;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Chromosome implements Comparable<Chromosome> {
    private Tree tree;
    private Double score = 0.0;

    public Chromosome(Tree tree, ExperimentalDataAccessIntereface dataAccess) {
        this.tree = tree;
        calculateScore(dataAccess);
    }

    @Override
    public int compareTo(Chromosome chromosome) {
        return this.getScore().compareTo(chromosome.getScore());
    }

    public Double getScore() {
        return score;
    }


    public void calculateScore(ExperimentalDataAccessIntereface dataAccess) {
        int experimentalPoints = dataAccess.getNumberOfExperimentalPoints();

        double newScore = IntStream.range(0, experimentalPoints).parallel()
                .mapToDouble(a -> Math.pow(dataAccess.getDependentVariable(a) - tree.getRoot().calculate(dataAccess.getIndependentVariables(a)), 2))
                .sum();

        this.score = newScore;
    }

    public void mutate(ExperimentalDataAccessIntereface dataAccess) {
        tree.mutate();
        calculateScore(dataAccess);
    }

    public Tree getTree() {
        return tree;
    }

    public void replace(Node node1, Node node2, ExperimentalDataAccessIntereface dataAccess) {
        tree.replace(node1, node2);
        calculateScore(dataAccess);
    }

    @Override
    public String toString() {
        return "Chromosome{" +
                "tree=" + tree.toString() +
                ", score=" + score +
                '}';
    }
}
