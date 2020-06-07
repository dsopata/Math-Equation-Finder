public class Chromosome implements Comparable<Chromosome> {
    private Tree tree;
    private Double score = 0.0;

    public Chromosome(Tree tree) {
        this.tree = tree;
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

    public void mutate() {
        tree.mutate();
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public Tree getTree() {
        return tree;
    }
}
