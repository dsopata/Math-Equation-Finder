import nodes.MathOperatorNode;
import nodes.Node;
import nodes.ValueNode;

public class Tree {

    private static final int MAXIMUM_TREE_HEIGHT = 6;
    private static final int MINIMAL_TREE_HEIGHT = 1;

    private Node root;
    private int height = 0;
    private int maximumHeight;

    public Tree( int numberOfIndependentVariables) {
        int level = 0;
        this.root = new MathOperatorNode(level);
        this.maximumHeight = MAXIMUM_TREE_HEIGHT < numberOfIndependentVariables ? numberOfIndependentVariables+1 : MAXIMUM_TREE_HEIGHT;


        try {
            this.generateTree(this.maximumHeight, root, level);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void generateTree(int numberOfNodes, Node parent, int level) throws Exception {
        int nextLevel = level + 1;
        if(numberOfNodes == 1) {
            parent.setChild(nextLevel, new ValueNode(nextLevel));
        }
    }


}
