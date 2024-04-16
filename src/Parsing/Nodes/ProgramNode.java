package Parsing.Nodes;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;


public class ProgramNode extends Node {

    private LinkedList<StatementsNode> nodes;
    private DataNode dataNode;

    private LinkedList<LabelNode> labelNode;

    public ProgramNode(LinkedList<StatementsNode> nodes, DataNode dataNode, LinkedList<LabelNode>  labelNode){
        this.nodes = nodes;
        this.dataNode = dataNode;
        this.labelNode = labelNode;
    }
    /**
     * @return the node
     */
    public LinkedList<StatementsNode> getNodes() {
        return new LinkedList<>(this.nodes);
    }




    /**
     * @return True if the AST has any nodes Remaining
     */
    public boolean hasMoreNodes(){
        return !nodes.isEmpty();
    }


    /**
     * @return True if the AST has any labels
     */
    public boolean hasLabels(){
        return labelNode != null;
    }

    /**
     * @return True if the AST any has data
     */
    public boolean hasDataNode(){
        return dataNode != null;
    }

    /**
     * @return the a new data node
     */
    public DataNode getDataNode(){
        DataNode DataNode = new DataNode();
        DataNode.setNodes(dataNode.getNodes());
        return DataNode;
    }

    /**
     * @return  a new Label LinkedList
     */
    public LinkedList<LabelNode> getLabelNodes(){
        return new LinkedList<>(labelNode);
    }

    public String toString(){
        return "ProgramNode{" +
                "nodes=" + nodes +
                '}';
    }

}
