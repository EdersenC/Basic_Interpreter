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
     * @return the node as a string
     */
    public String toString(){
        return "StringNode{" +
                "value=" + value +
                '}';
    }


}
