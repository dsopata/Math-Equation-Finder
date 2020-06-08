import nodes.Node;

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
        double newScore = 0d;

        for(int a = 0; a < experimentalPoints; a++) {
            newScore += Math.pow(dataAccess.getDependentVariable(a) - tree.getRoot().calculate(dataAccess.getIndependentVariables(a)), 2);
        }

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
