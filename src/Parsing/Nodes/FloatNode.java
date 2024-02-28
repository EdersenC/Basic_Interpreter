package Parsing.Nodes;

public class FloatNode extends Node {




    private float value;


    public FloatNode(float value){
        this.value = value;
    }

    /**
     * @return the value
     */
    public float getValue(){
        return value;
    }

    /**
     * @return the value to a string
     */
    @Override
    public String toString(){
        return "FloatNode{" +
                "value=" + value +
                '}';
    }
}
