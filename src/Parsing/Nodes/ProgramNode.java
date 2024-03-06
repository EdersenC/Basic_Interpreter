package Parsing.Nodes;

import java.util.LinkedList;
import java.util.Optional;


public class ProgramNode extends Node {

    private LinkedList<Optional<Node>> nodes;
    public ProgramNode(LinkedList<Optional<Node>> nodes){
        this.nodes = nodes;
    }

    /**
     * @return the node
     */
    public LinkedList<Optional<Node>> getNodes() {
        return nodes;
    }

    public String toString(){
        return "ProgramNode{" +
                "nodes=" + nodes +
                '}';
    }

}
