package Parsing.Nodes;

import java.util.LinkedList;

public class ReadNode extends StatementNode {


    LinkedList<VariableNode> variables = new LinkedList<>();

    public ReadNode(LinkedList<VariableNode> variables){
        this.variables = variables;
    }


    public String toString(){
        return "ReadNode{" +
                "variables=" + variables +
                '}';
    }





}
