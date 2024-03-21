package Parsing.Nodes;

public class While extends StatementNode{




    private final StatementNode condition;
    private final StatementNode statement;

    private final String label;


    public While(StatementNode condition, StatementNode statement, String label){
        this.condition = condition;
        this.statement = statement;
        this.label = label;
    }


    public String toString() {
        return "While{" +
                "condition=" + condition +
                ", statement=" + statement +
                ", label='" + label + '\'' +
                '}';
    }





}
