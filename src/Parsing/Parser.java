package Parsing;

import Lexing.Token;
import Parsing.Nodes.MathOpNode;
import Parsing.Nodes.Node;
import Parsing.Nodes.ProgramNode;
import Parsing.Nodes.intergerNode;

import java.util.LinkedList;
import java.util.Optional;

public class Parser {

    private TokenManager handler;
    public Parser(LinkedList<Token> tokens){
        handler = new TokenManager(tokens);
        throw new UnsupportedOperationException("Not implemented yet, fortnite dot exe TROLL muhahahaha");
    }



    /**
     *
     * @return the parsed program node
     */
    public ProgramNode parse(){
        while (handler.moreTokens()){
            handler.peek(0);
        }


        return new ProgramNode();
    }



    /**
     * @return the expression
     *  Handles addtion and Subtraction
     */
    public Optional<Node> expression() {
        Optional<Node> left = term();

        return Optional.of(new MathOpNode(new intergerNode(5), new intergerNode(5)));
    }
    /**
     * @return the term
     * handles multiplication and division
     */
    public Optional<Node> term(){

        return Optional.of(new MathOpNode(new intergerNode(5), new intergerNode(5)));
    }

    /**
     * @return the factor
     * handles numbers and parenthesis
     */
    public Optional<Node> factor(){

        //check fdor paeren for nested espressions



    return Optional.of(new MathOpNode(new intergerNode(5), new intergerNode(5)));

    }





}
