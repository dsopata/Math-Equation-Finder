import nodes.MathFunctionNode;
import nodes.MathOperatorNode;
import nodes.Node;
import nodes.ValueNode;

import java.util.Random;

public class Tree {

    private static final int MAXIMUM_TREE_HEIGHT = 6;
    private static final int MAXIMUM_RANDOM_BOUND = 100;

    private static final float MATH_OPERATOR_NODE_PROPABILITY = .03f;
    private static final float MATH_FUNCTION_NODE_PROPABILITY = .05f;
    private static final float VALUE_NODE_PROPABILITY = .055f;

    private Node root;
    private int maximumHeight;
    private Random random;
    private int assignedIndependendVariables;
    private int size = 0;
    private int randomTravelsalCounter = 0;

    public Tree(int numberOfIndependentVariables) {
        this.random = new Random();
        this.assignedIndependendVariables = 0;
        int level = 0;
        this.root = new MathOperatorNode(level);
        this.maximumHeight = MAXIMUM_TREE_HEIGHT < numberOfIndependentVariables ? numberOfIndependentVariables+1 : MAXIMUM_TREE_HEIGHT;

        try {
            this.generateTree( root, level);
            size = size(root);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void generateTree( Node parent, int level) throws Exception {
        int nextLevel = level + 1;

        if(maximumHeight <= nextLevel) {
            if (parent.getNodeType() == Node.NodeType.VALUE) {
                return;
            }
            Node child =  new ValueNode(nextLevel, random.nextDouble());
            parent.setChild(0, child);
            if (parent.getNodeType() == Node.NodeType.MATH_OPERATOR) {
                parent.setChild(1, new ValueNode(nextLevel, random.nextDouble()));
            }
            return;
        }

        if(parent.getNodeType() != Node.NodeType.VALUE) {
            //left
            Node child1 = randomTypeChild(nextLevel);
            parent.setChild(0, child1);
            generateTree(child1, nextLevel);
        } else {
            //left value child
            Node child =  new ValueNode(nextLevel, random.nextDouble());
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
        float mathOperatorPropability =  MATH_OPERATOR_NODE_PROPABILITY * nextLevel * random.nextInt(MAXIMUM_RANDOM_BOUND);
        float mathFunctionPropability =  MATH_FUNCTION_NODE_PROPABILITY  * nextLevel * random.nextInt(MAXIMUM_RANDOM_BOUND);
        float valuePropability =  VALUE_NODE_PROPABILITY * nextLevel * random.nextInt(MAXIMUM_RANDOM_BOUND);

        if(valuePropability >= mathFunctionPropability && valuePropability >= mathOperatorPropability) {
            return new ValueNode(nextLevel, random.nextDouble());
        }
        if(mathFunctionPropability >= valuePropability && mathFunctionPropability >= mathOperatorPropability) {
            return new MathFunctionNode(nextLevel);
        }
        if(mathOperatorPropability >= valuePropability && mathOperatorPropability >= mathFunctionPropability) {
            return new MathOperatorNode(nextLevel);
        }

        return null;
    }

    @Override
    public String toString() {
        return (this.root).toString();
    }

    public void setIndependentVariablesInTree(int numberOfIndependentVariables) {
        setIndependentVariables(root,  numberOfIndependentVariables);
    }

    private void setIndependentVariables(Node root, int numberOfIndependentVariables) {
       if(root == null || assignedIndependendVariables >= numberOfIndependentVariables) {
           return;
       }
       if(root.getNodeType().equals(Node.NodeType.VALUE)) {
           ((ValueNode)root).setVariableId(assignedIndependendVariables++);
           return;
       }
       if(root.children[0] != null) {
           setIndependentVariables(root.children[0], numberOfIndependentVariables);
       }
        if(root.children[1] != null) {
            setIndependentVariables(root.children[1], numberOfIndependentVariables);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void mutate() {
        //root.mutate();
    }

    public int size() {
        if(size == 0) {
            this.size = size(root);
        }
        return size;
    }

    private int size(Node node) {
        if (node == null) return(0);
        else {
            return(size(node.children[0]) + 1 + size(node.children[1]));
        }
    }

    public Node getRandomNode() {
        randomTravelsalCounter = 0;
        int randomNum = random.nextInt(size);
        return getRandomTraversal(root, randomNum);
    }

    private Node getRandomTraversal(Node node, int randomNum) {
        randomTravelsalCounter++;

        if(randomTravelsalCounter == randomNum)
            return node;

        if(node.children[0] != null)
            return getRandomTraversal(node.children[0], randomNum);

        if(node.children[1] != null)
            return getRandomTraversal(node.children[1], randomNum);

        return null;
    }

    public void replaceNode(Node node1, Node node2) {
        try {
            replaceNode(root, node1, node2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void replaceNode(Node root, Node node1, Node node2) throws Exception {
        if(root.children[0] == node1) {
            root.setChild(0, node2);
            return;
        } else if(root.children[0] != null) {
            replaceNode(root.children[0], node1, node2);
        }

        if(root.children[1] == node1) {
            root.setChild(1, node2);
            return;
        } else if(root.children[1] != null) {
            replaceNode(root.children[1], node1, node2);
        }
    }
}
