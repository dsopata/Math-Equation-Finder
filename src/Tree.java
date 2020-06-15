import nodes.MathFunctionNode;
import nodes.MathOperatorNode;
import nodes.Node;
import nodes.ValueNode;

import java.util.*;

public class Tree  {

    private static final int MAXIMUM_TREE_HEIGHT = 10;
    private static final int MAXIMUM_RANDOM_BOUND = 100;
    private static final float MATH_OPERATOR_NODE_PROPABILITY = .03f;
    private static final float MATH_FUNCTION_NODE_PROPABILITY = .05f;
    private static final float VALUE_NODE_PROPABILITY = .055f;

    private Node root;
    private int maximumHeight;
    private Random random;
    private int assignedIndependendVariables;
    private int size = 0;
    List<String> nodesList = new ArrayList<String>();

    public Tree(int numberOfIndependentVariables) {
        this.random = new Random();
        this.assignedIndependendVariables = 0;
        int level = 0;
        this.root = new MathOperatorNode(level, null);
        this.maximumHeight = MAXIMUM_TREE_HEIGHT < numberOfIndependentVariables ? numberOfIndependentVariables+1 : MAXIMUM_TREE_HEIGHT;

        try {
            this.generateTree( root, level);
            this.setIndependentVariablesInTree(numberOfIndependentVariables);
            size = size(root);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<String> getNodesStringList() {
        return getNodesStringList(root);
    }

    private List<String> getNodesStringList(Node node) {
        nodesList = new ArrayList<String>();
        travelTree(root);
        return nodesList;
    }

    private void travelTree(Node node) {
        if (node != null) {
            if(node.children[0] != null)
                travelTree(node.children[0]);
            nodesList.add(node.toString());
            if(node.children[1] != null)
                travelTree(node.children[1]);
        }
    }

    private void generateTree(Node parent, int level) throws Exception {
        int nextLevel = level + 1;

        if(maximumHeight <= nextLevel) {
            if (parent.getNodeType() == Node.NodeType.VALUE) {
                return;
            }
            Node child =  new ValueNode(nextLevel, random.nextDouble() * 10, parent);
            parent.setChild(0, child);
            if (parent.getNodeType() == Node.NodeType.MATH_OPERATOR) {
                parent.setChild(1, new ValueNode(nextLevel, random.nextDouble()* 10, parent));
            }

            return;
        }

        if(parent.getNodeType() != Node.NodeType.VALUE) {
            //left
            Node child1 = randomTypeChild(nextLevel, parent);
            parent.setChild(0, child1);
            generateTree(child1, nextLevel);
        } else {
            return;
        }
        if(parent.getNodeType() == Node.NodeType.MATH_OPERATOR) {
            // right
            Node child2 =  randomTypeChild(nextLevel, parent);
            parent.setChild(1, child2);
            generateTree(child2, nextLevel);
        }

        return;
    }

    private Node randomTypeChild(int nextLevel, Node parent) {
        float mathOperatorPropability =  MATH_OPERATOR_NODE_PROPABILITY * nextLevel * random.nextInt(MAXIMUM_RANDOM_BOUND);
        float mathFunctionPropability =  MATH_FUNCTION_NODE_PROPABILITY  * nextLevel * random.nextInt(MAXIMUM_RANDOM_BOUND);
        float valuePropability =  VALUE_NODE_PROPABILITY * nextLevel * random.nextInt(MAXIMUM_RANDOM_BOUND);

        if(valuePropability >= mathFunctionPropability && valuePropability >= mathOperatorPropability) {
            return new ValueNode(nextLevel, random.nextDouble()* 10, parent);
        }
        if(mathFunctionPropability >= valuePropability && mathFunctionPropability >= mathOperatorPropability) {
            return new MathFunctionNode(nextLevel, parent);
        }
        if(mathOperatorPropability >= valuePropability && mathOperatorPropability >= mathFunctionPropability) {
            return new MathOperatorNode(nextLevel, parent);
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
        Node node = getRandomNode();
        node.mutate();
    }

    private int size(Node node) {
        if (node == null) return(0);
        else {
            return(size(node.children[0]) + 1 + size(node.children[1]));
        }
    }

    public Node getRandomNode() {
        int ranNum = 1 + random.nextInt(size-1);
        return getRandomNode(root, ranNum);
    }

    public Node getRandomNode(Node temp, int ranNum) {
        if(temp == null)
            return null;

        Queue<Node> q = new LinkedList<Node>();
        q.add(temp);
        int count = 0;

        Node predecesor = null;
        while(!q.isEmpty() && count <= ranNum) {
            Node current = q.remove();
            if(count == ranNum) {
                if(current.isLeaf()) {
                   return predecesor;
                }
                return current;
            }

            if(current.children[0] != null ) {
                q.add(current.children[0]);
            }
            if(current.children[1] != null ) {
                q.add(current.children[1]);
            }
            count++;
            predecesor = current;
        }

        return null;
    }

    public void replace(Node node1, Node node2) {
        try {
            node1.replaceChild(node2);
            size = size(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
