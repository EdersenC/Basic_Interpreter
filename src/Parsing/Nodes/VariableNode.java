package Parsing.Nodes;
/**
 * A class that represents a variable node
 */
public class VariableNode extends StatementNode{
    private String name;
    private Node value;

    /**
     * @param name the name of the variable
     * @param value the value of the variable
     */
    public VariableNode(String name){
        this.name = name;
    }


    public VariableNode(String name, Node value){
        this.name = name;
        this.value = value;
    }


    /**
     * @return the name of the variable
     */
    public String getName(){
        return name;
    }
    /**
     * @return the value of the variable
     */
    public Node getValue(){
        return value;
    }
    /**
     * @return the value of the variable to a string
     */
    @Override
    public String toString(){
        return "VariableNode{" +
                "name=" + name +
                ", value=" + value +
                '}';
    }
}
