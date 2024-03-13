package Parsing.Nodes;

import java.util.LinkedList;

/**
 * holds a list of Nodes to be printed
 */
public class PrintNode extends StatementNode{

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
     * PrintNode{nodes=[VariableNode{name=a, value=FloatNode{value=5.0}}, FloatNode{value=5.0}]}
     */
    public String toString(){
        return "PrintNode{" +
                "nodes=" + nodes +
                '}';
    }

}
