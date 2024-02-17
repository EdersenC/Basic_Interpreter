package Parsing;

import Lexing.Token;
import Parsing.Nodes.ProgramNode;

import java.util.LinkedList;
import java.util.Optional;

public class Parser {

    private TokenManager handler;
    public Parser(LinkedList<Token> tokens){
        handler = new TokenManager(tokens);

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
     */
    public Optional<Token> expression(){
        return handler.peek(0);
    }

    /**
     * @return the term
     */
    public Optional<Token> term(){
        return handler.peek(0);
    }

    /**
     * @return the factor
     */
    public Optional<Token> factor(){
        return handler.peek(0);
    }





}
