package Parsing.Nodes;

import java.util.LinkedList;

public class DataNode extends StatementNode {


    private final LinkedList<Node> nodes = new LinkedList<>();

    /**
     * @param node the node to be added
     */
    public void addNode(Node node){
        if (node != null)
            nodes.add(node);
    }

    /**
     * @return the node as a string
     */
    public String toString() {
        return "DataNode{" +
                "nodes=" + nodes +
                '}';
    }






}















