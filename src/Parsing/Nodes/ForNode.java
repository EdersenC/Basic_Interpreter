package Parsing.Nodes;

public class ForNode extends StatementNode {

    private final AssignmentNode variable;

    private final IntergerNode end;

    private final IntergerNode step;


    public ForNode(AssignmentNode variable,IntergerNode end, IntergerNode step){
        this.variable = variable;
        this.end = end;
        this.step = step;
    }

    public ForNode(AssignmentNode variable, IntergerNode end){
        this.variable = variable;
        this.end =  end;
        this.step = new IntergerNode(1);
    }


    /**
     * @return the For ast node as a string
     */
    public String toString() {
        return "For{" +
                "variable='" + variable + '\'' +
                ", end='" + end + '\'' +
                ", step='" + step + '\'' +
                '}';
    }






}
