package Parsing.Nodes;

import java.util.LinkedList;

public class FunctionNode extends StatementNode{

    private final String name;
    private final LinkedList<Node> parameters = new LinkedList<>();



    /**
     *
     * @param name
     */
    public FunctionNode(String name){
        this.name = name;
    }


    /**
     * @return the name of the function
     */
    public String getName(){
        return name;
    }


    /**
     *
     * add a parameter to the function
     */
    public void addParameters(Node parameter){
        if (parameter !=null)
            parameters.add(parameter);
    }

    /**
     * @return the number of parameters
     */
    public int getNumParameters() {
        return parameters.size();
    }

    /**
     * @return the function name
     */
    public String getFunctionName() {
        return name;
    }

    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                '}';
    }










}
