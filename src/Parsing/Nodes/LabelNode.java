package Parsing.Nodes;

import Parsing.Nodes.StatementNode;

public class LabelNode extends StatementNode {

    private final String label;

    private StatementNode statement;
    public LabelNode(String label, StatementNode statement){
        this.label = label;
        this.statement = statement;
    }

    /**
     * @return the labels name
     */
    public String getName() {
        return label;
    }

     public String toString() {
         return "LabelNode{" +
                 "label='" + label + '\'' +
                 ", statement=" + statement +
                 '}';
     }

}
