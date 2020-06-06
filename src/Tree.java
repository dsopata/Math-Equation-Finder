import nodes.MathFunctionNode;
import nodes.MathOperatorNode;
import nodes.Node;
import nodes.ValueNode;

import java.util.Random;

public class Tree {

    private static final int MAXIMUM_TREE_HEIGHT = 6;
    private static final int MINIMAL_TREE_HEIGHT = 1;
    private static final int MAXIMUM_RANDOM_BOUND = 100;

    private Node root;
    private int height = 0;
    private int maximumHeight;
    private Random random;

    public Tree(int numberOfIndependentVariables) {
        this.random = new Random();

        int level = 0;
        this.root = new MathOperatorNode(level);
        this.maximumHeight = MAXIMUM_TREE_HEIGHT < numberOfIndependentVariables ? numberOfIndependentVariables+1 : MAXIMUM_TREE_HEIGHT;

        try {
            this.generateTree( root, level);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void generateTree( Node parent, int level) throws Exception {
        int nextLevel = level + 1;

        if(maximumHeight <= nextLevel) {
            Node child =  new ValueNode(nextLevel);
            parent.setChild(0, child);
            return;
        }

        if(parent.getNodeType() != Node.NodeType.VALUE) {
            //left
            Node child1 =  randomTypeChild(nextLevel);
            parent.setChild(0, child1);
            generateTree(child1, nextLevel);
        } else {
            //left value child
            Node child =  new ValueNode(nextLevel);
            parent.setChild(0, child);
            return;
        }
        if(parent.getNodeType() == Node.NodeType.MATH_OPERATOR) {
            // right
            Node child2 =  randomTypeChild(nextLevel);
            parent.setChild(1, child2);
            generateTree(child2, nextLevel);

        }

        return;
    }

    private Node randomTypeChild(int nextLevel) {
        float mathOperatorPropability = (float) .07 * nextLevel * random.nextInt(MAXIMUM_RANDOM_BOUND);
        float mathFunctionPropability = (float) .09  * nextLevel * random.nextInt(MAXIMUM_RANDOM_BOUND);
        float valuePropability = (float) .1 * nextLevel * random.nextInt(MAXIMUM_RANDOM_BOUND);

        if(valuePropability >= mathFunctionPropability && valuePropability >= mathOperatorPropability) {
            return new ValueNode(nextLevel);
        }
        if(mathFunctionPropability >= valuePropability && mathFunctionPropability >= mathOperatorPropability) {
            return new MathFunctionNode(nextLevel);
        }
        if(mathOperatorPropability >= valuePropability && mathOperatorPropability >= mathFunctionPropability) {
            return new MathOperatorNode(nextLevel);
        }

        return null;
    }


}
