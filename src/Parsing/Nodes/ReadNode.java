package Parsing.Nodes;

import java.util.LinkedList;

public class ReadNode extends StatementNode {


   private final LinkedList<VariableNode> variables = new LinkedList<>();

    /**
     * @param variable the variable to be added
     */
    public void addVariable(VariableNode variable){
        if (variable != null)
            variables.add(variable);
    }

    public String toString(){
        return "ReadNode{" +
                "variables=" + variables +
                '}';
    }





}
