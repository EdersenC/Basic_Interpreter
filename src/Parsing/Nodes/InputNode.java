package Parsing.Nodes;

import java.util.LinkedList;

public class InputNode extends StatementNode {

    private StringNode constant;
    private VariableNode variable;
    private final LinkedList<VariableNode> variableList = new LinkedList<>();

    public InputNode(StringNode input){
        this.constant = input;
    }

    public InputNode(VariableNode variable){
        this.variable = variable;
    }


    /**
     * add a variable to the list of variables
     * @param variable the list of variables
     */
    public void addVariable(VariableNode variable){
        if (variable != null)
            variableList.add(variable);
    }

    /**
     * @return the input Ast as a string
     */
    public String toString(){

        String withVariable = "Variable="
                + variable
                + ", variableList="
                + variableList + "}";

        String withConstant = "constant="
                + constant
                + ", variableList="
                + variableList + "}";

        return "InputNode{" +
                (variable != null ? withVariable : withConstant);
    }





}
