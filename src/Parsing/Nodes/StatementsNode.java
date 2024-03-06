package Parsing.Nodes;

import java.util.LinkedList;

public class StatementsNode extends StatementNode {


    private LinkedList<StatementNode> statements = new LinkedList<>();

    public void addStatement(StatementNode statement){
        statements.add(statement);
    }


    public String toString(){
        return "StatementsNode{" +
                "statements=" + statements +
                '}';
    }










}
