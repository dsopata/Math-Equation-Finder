package nodes;

import java.util.Random;

public abstract class Node {

    public int level  = 0;
    public Node[] children = new Node[2];

    public Node(int level) {
        this.level = level;
    }


    public void setChild(int index, Node node) throws Exception {
        if(index > 1 && index < 0) {
            throw new Exception("incorrect node child index");
        }
        children[index] = node;
    }

}
