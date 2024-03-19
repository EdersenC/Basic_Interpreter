package Parsing.Nodes;

public class LabelNode extends StatementNode{



    private String label;

    private StatementNode statement;

    public LabelNode(String label, StatementNode statement){
        this.label = label;
        this.statement = statement;
    }











}
