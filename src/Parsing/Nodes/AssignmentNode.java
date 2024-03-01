package Parsing.Nodes;

/**
 * AssignmentNode holds a variable node and a Node value
 */
public class AssignmentNode extends StatementNode{



    private VariableNode variable;

    private Node value;


    public AssignmentNode(VariableNode variable, Node value){
        this.variable = variable;
        this.value = value;
    }


    /**
     * @return the variable
     */
    public VariableNode getVariable() {
        return variable;
    }

    /**
     * @return the value of the variable
     */
    public Node getValue() {
        return value;
    }

    @Override
    public String toString(){
        return "AssignmentNode{" +
                "variable=" + variable +
                ", value=" + value +
                '}';
    }












}
