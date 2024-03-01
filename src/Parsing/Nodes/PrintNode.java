package Parsing.Nodes;

import java.util.LinkedList;

/**
 * holds a list of Nodes to be printed
 */
public class PrintNode extends StatementNode{

    private LinkedList<Node> nodes;


    public PrintNode(LinkedList<Node> nodes){
        this.nodes = nodes;
    }

    /**
     * @return the node
     */
    public LinkedList<Node> getNodes() {
        return nodes;
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
