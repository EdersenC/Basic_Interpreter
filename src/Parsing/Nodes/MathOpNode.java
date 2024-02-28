package Parsing.Nodes;

import java.util.Optional;


/**
 * MathOpNode
 */
public class MathOpNode extends Node{


    public enum MathOp{
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,

    }


    private Node left;
    private MathOp op;
    private Node right;


    /**
     * @param left node
     * @param op node
     * @param right node
     */
    public MathOpNode(Node left, MathOp op, Node right){
        this.left = left;
        this.op = op;
        this.right = right;
    }

    /**
     * @param left node
     * @param right node
     */
    public MathOpNode(Node left, Node right){
        this.left = left;
        this.right = right;
    }

    /**
     * @return the left Node
     */
    public Optional<Node> getLeft(){
        return Optional.of(left);
    }

    /**
     * @return the operation Node
     */
    public Optional<MathOp> getOp(){
        return Optional.of(op);
    }
    /**
     * @return the right Node
     */
    public Optional<Node> getRight(){
        return Optional.of(right);
    }


    /**
     * @return the MathOpNode to a string
     */
    @Override
    public String toString(){
        if(op != null){
            return "MathOpNode{" +
                    "left=" + left +
                    ", op=" + op +
                    ", right=" + right +
                    '}';
        }
        return "MathOpNode{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }



}
