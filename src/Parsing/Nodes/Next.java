package Parsing.Nodes;

public class Next extends StatementNode{

    private final VariableNode variable;


    public Next(VariableNode variable){
        this.variable = variable;
    }

    public String toString() {
        return "Next{" +
                "variable='" + variable + '\'' +
                '}';
    }



}
