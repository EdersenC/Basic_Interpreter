package Parsing.Nodes;

public class WhileNode extends StatementNode{




    private final BooleanExpressionNode condition;
    private final StatementNode statement;

    private final String label;


    public WhileNode(BooleanExpressionNode condition, StatementNode statement, String label){
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
