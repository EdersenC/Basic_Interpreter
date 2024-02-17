package Parsing.Nodes;

/**
 * This class is used to represent an integer node
 */
public class intergerNode extends Node{


    private int value;


    public intergerNode(int value){
        this.value = value;
    }

    /**
     * @return the value
     */
    public int getValue(){
        return value;
    }

    /**
     * @return the value to a string
     */
    @Override
    public String toString(){
        return "IntegerNode{" +
                "value=" + value +
                '}';
    }





}
