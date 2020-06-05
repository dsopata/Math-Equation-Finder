public class Node {

    public int level  = 0;
    public Node[] children = new Node[2];
    public LocalFunctionInterface localFunction;

    enum NodeType {

    }

    private Node(Node child1, LocalFunctionInterface localFunction) {
        children[0] = child1;
        this.localFunction = localFunction;
    }

    public Node(Node child1, int level, LocalFunctionInterface localFunction) {
        this(child1, localFunction);
        this.level = level;
    }

    public Node(Node child1, Node child2, int level, LocalFunctionInterface localFunction) {
        this(child1, level, localFunction);
        children[1] = child2;
    }

    public void setChild(int index, Node node) throws Exception {
        if(index > 1 && index < 0) {
            throw new Exception("incorrect node child index");
        }
        children[index] = node;
    }


}
