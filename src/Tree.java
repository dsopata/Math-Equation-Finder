import nodes.Node;

public class Tree {

    private static final int MAXIMUM_TREE_HEIGHT = 6;
    private static final int MINIMAL_TREE_HEIGHT = 1;

    private Node root;
    private int height = 0;

    public Tree(Node root, int numberOfIndependentVariables) {
        this.root = root;
        generateTree(numberOfIndependentVariables);
    }

    private void generateTree(int numberOfIndependentVariables) {
        int maximumHeight = MAXIMUM_TREE_HEIGHT < numberOfIndependentVariables ? numberOfIndependentVariables+1 : MAXIMUM_TREE_HEIGHT;

    }


}
