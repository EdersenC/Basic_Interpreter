package Parsing.Nodes;

public class StringNode extends Node {

    private String value;

    public StringNode(String value){
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue(){
        return value;
    }

    /**
     * @return the AST representation of the node
     */

    public String toString(){
        return "StringNode{" +
                "value=" + value +
                '}';
    }


}
