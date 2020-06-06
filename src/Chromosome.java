public class Chromosome implements Comparable<Chromosome> {
    public Tree tree;
    public Double score = 0.0;

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
        for(int a = 0; a<experimentalPoints; a++) {
            newScore += Math.pow(dataAccess.getDependentVariable(a) - tree.getRoot().calculate(dataAccess.getIndependentVariables(a)), 2);
        }
        this.score = newScore;
    }
}
