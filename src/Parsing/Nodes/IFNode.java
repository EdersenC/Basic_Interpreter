package Parsing.Nodes;

import java.util.Optional;

public class IFNode extends StatementNode {


private final Optional<Node> condition;
private final String thenStatement;



public IFNode(Optional<Node> condition, String thenStatement){
    this.condition = condition;
    this.thenStatement = thenStatement;
}


public String toString() {
    return "IF{" +
            "condition='" + condition + '\'' +
            ", thenStatement=" + thenStatement +
            '}';
}





}
