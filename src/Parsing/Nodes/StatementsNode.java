package Parsing.Nodes;

import java.util.LinkedList;

public class StatementsNode extends StatementNode {


    private LinkedList<StatementNode> statements = new LinkedList<>();

    /**
     * @param statement the statement to be added
     */
    public void addStatement(StatementNode statement){
        statements.add(statement);
    }



    /**
     * @return a new linked list of statements
     */
    public LinkedList<StatementNode> getStatements(){
        return new LinkedList<>(this.statements);
    }

    public String toString(){
        return "StatementsNode{" +
                "statements=" + statements +
                '}';
    }










}
