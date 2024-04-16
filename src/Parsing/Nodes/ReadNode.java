package Parsing.Nodes;

import java.util.LinkedList;

public class ReadNode extends StatementNode {


   private final LinkedList<VariableNode> variables = new LinkedList<>();

    /**
     * @param variable the variable to be added
     */
    public void addVariable(VariableNode variable){
        if (variable == null)
            throw new IllegalArgumentException("Invalid Syntax Not A Variable");
        variables.add(variable);
    }

    /**
     * @return the list of variables
     */
    public LinkedList<VariableNode> getVariables(){
        return new LinkedList<>(this.variables);
    }

    /**
     * @return the node as a string
     */
    public String toString(){
        return "ReadNode{" +
                "variables=" + variables +
                '}';
    }





}
