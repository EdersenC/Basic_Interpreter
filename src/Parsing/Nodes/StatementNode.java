package Parsing.Nodes;

import java.util.LinkedList;

/**
 * StatementNode – holds a list of StatementNode's
 */
public abstract class StatementNode extends Node{


    private LinkedList<StatementNode> statements;



    /**
     * @return the statements
     */
    public LinkedList<StatementNode> getStatements() {
        return statements;
    }


    /**
     * @return the statements as a string
     */
    @Override
    public String toString(){
        return "StatementNode{" +
                "statements=" + statements +
                '}';
    }


}
