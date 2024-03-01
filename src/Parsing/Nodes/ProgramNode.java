package Parsing.Nodes;

import java.util.LinkedList;



public class ProgramNode extends Node {

    private LinkedList<Node> nodes;
    public ProgramNode(LinkedList<Node> nodes){
        this.nodes = nodes;
    }

    /**
     * @return the node
     */
    public LinkedList<Node> getNodes() {
        return nodes;
    }

    public String toString(){
        return "ProgramNode{" +
                "nodes=" + nodes +
                '}';
    }

}
