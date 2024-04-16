package Parsing.Nodes;

public class WhileNode extends StatementNode{




    private final BooleanExpressionNode condition;
    private final StatementsNode statement;

    private final String label;


    public WhileNode(BooleanExpressionNode condition, StatementsNode statement, String label){
        this.condition = condition;
        this.statement = statement;
        this.label = label;
    }


    public String toString() {
        return "While{" +
                "condition=" + condition +
                ", statements=" + statement +
                ", label='" + label + '\'' +
                '}';
    }





}
