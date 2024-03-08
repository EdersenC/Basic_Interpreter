package Parsing.Nodes;

import java.util.LinkedList;

public class Input {

    private StringNode input;
    private VariableNode variable;
    private LinkedList<VariableNode> variableList = new LinkedList<>();

    public Input(StringNode input){
        this.input = input;
    }

    public Input(VariableNode variable){
        this.variable = variable;
    }

    public Input(LinkedList<VariableNode> variableList){
        this.variableList = variableList;
    }

    /**
     *
     * add a variable to the list of variables
     */
    public void addVariable(VariableNode variable){
        variableList.add(variable);
    }

    /**
     * @return the input Ast as a string
     */
    public String toString(){
        return "Input{" +
                "input=" + input +
                ", variable=" + variable +
                ", variableList=" + variableList +
                '}';
    }





}
