package Parsing.Nodes;

public class ForNode extends StatementNode {

    private final AssignmentNode variable;

    private final IntergerNode end;

    private final IntergerNode step;

    private final Next next;

    private final StatementsNode statements;


    public ForNode(AssignmentNode variable,IntergerNode end, IntergerNode step,StatementsNode statements,Next next){
        this.variable = variable;
        this.end = end;
        this.step = step;
        this.statements = statements;
        this.next = next;
    }

    public ForNode(AssignmentNode variable, IntergerNode end,StatementsNode statements, Next next){
        this.variable = variable;
        this.end =  end;
        this.step = new IntergerNode(1);
        this.statements = statements;
        this.next = next;
    }


    /**
     * @return the For ast node as a string
     */
    public String toString() {
        return "For{" +
                "variable='" + variable + '\'' +
                ", end='" + end + '\'' +
                ", statements='" + statements + '\'' +
                ", step='" + step + '\'' +
                ", next='" + next + '\''+
                '}';
    }






}
