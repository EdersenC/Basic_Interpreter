package Parsing.Nodes;

import java.util.LinkedList;

public class DataNode extends StatementNode {


    private LinkedList<Node> nodes = new LinkedList<>();


    public DataNode(LinkedList<Node> nodes){
        this.nodes = nodes;
    }


    /**
     * @return the node as a string
     */
    public String toString(){
        return "DataNode{}";
    }








}
