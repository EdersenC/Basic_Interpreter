package Parsing;

import Lexing.Token;
import Parsing.Nodes.ProgramNode;

import java.util.LinkedList;

public class Parser {

    private TokenManager handler;
    public Parser(LinkedList<Token> tokens){
        handler = new TokenManager(tokens);

    }



    public ProgramNode parse(String input){
        while (handler.moreTokens()){
            handler.peek(0);
        }


        return new ProgramNode();
    }








}
