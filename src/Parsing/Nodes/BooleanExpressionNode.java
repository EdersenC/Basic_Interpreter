package Parsing.Nodes;

public class BooleanExpressionNode extends StatementNode{


    public enum BooleanOp{
        AND,
        OR,
        NOT,
        EQUALS,
        NOT_EQUALS,
        GREATER_THAN,
        LESS_THAN,
        GREATER_THAN_OR_EQUAL,
        LESS_THAN_OR_EQUAL
    }



    private Node left;

    private BooleanOp boolOp;

    private Node right;


    /**
     * @param left node
     * @param boolOp node
     * @param right node
     */
    public BooleanExpressionNode(Node left, BooleanOp boolOp, Node right) {
        this.left = left;
        this.boolOp = boolOp;
        this.right = right;
    }


    public String toString() {
        return "BooleanExpressionNode{" +
                "left=" + left +
                ", boolOp=" + boolOp +
                ", right=" + right +
                '}';
    }




}
