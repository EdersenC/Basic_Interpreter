package Parsing;

import Lexing.Token;
import Parsing.Nodes.*;

import java.util.LinkedList;
import java.util.Optional;

/**
 * The Parser class is responsible for constructing an Abstract Syntax Tree (AST) from a list of tokens.
 * It utilizes a TokenManager to handle the traversal and manipulation of the token list based on grammar rules.
 */
public class Parser {

    private TokenManager handler;

    /**
     * Constructs a Parser with a given list of tokens.
     * @param tokens The list of tokens to be parsed.
     */
    public Parser(LinkedList<Token> tokens){
        handler = new TokenManager(tokens);
    }

    /**
     * Parses the list of tokens into an AST.
     * This method sequentially processes expressions to build the program structure.
     * @return ProgramNode representing the root of the AST.
     */
    public ProgramNode parse(){
        LinkedList<Node> nodes = new LinkedList<>();
        while (handler.moreTokens()){
            Optional<Node> expression = expression();
            if(expression.isPresent()){
                nodes.add(expression.get());
                handler.acceptSeparator(); // Skips token separators like semicolons, if present.
            }
        }
        return new ProgramNode(nodes);
    }

    /**
     * Parses expressions, handling binary operations such as addition and subtraction.
     * It builds upon the lower-level term() method to construct the expression hierarchy.
     * @return Optional containing the Node representing the expression, if parsed successfully.
     */
    public Optional<Node> expression() {
        Optional<Node> left = term();
        Optional<Token> plus = handler.matchAndRemove(Token.TokenType.PLUS);
        Optional<Token> minus = handler.matchAndRemove(Token.TokenType.MINUS);
        while (plus.isPresent() || minus.isPresent()){
            if(plus.isPresent()){
                left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.ADD, term().get()));
            }
            if(minus.isPresent()) {
                left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.SUBTRACT, term().get()));
            }
            plus = handler.matchAndRemove(Token.TokenType.PLUS);
            minus = handler.matchAndRemove(Token.TokenType.MINUS);
        }
        return left;
    }

    /**
     * Parses terms within expressions, handling multiplication and division.
     * This method supports the construction of nodes for higher precedence operations.
     * @return Optional containing the Node representing the term, if parsed successfully.
     */
    public Optional<Node> term(){
        Optional<Node> left = factor();
        Optional<Token> multiply = handler.matchAndRemove(Token.TokenType.MULTIPLY);
        Optional<Token> divide = handler.matchAndRemove(Token.TokenType.DIVIDE);
        while (multiply.isPresent() || divide.isPresent()){
            if(multiply.isPresent()){
                left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.MULTIPLY, factor().get()));
            }
            if(divide.isPresent()) {
                left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.DIVIDE, factor().get()));
            }
            multiply = handler.matchAndRemove(Token.TokenType.MULTIPLY);
            divide = handler.matchAndRemove(Token.TokenType.DIVIDE);
        }
        return left;
    }

    /**
     * Parses factors within terms, handling literals and parenthesized expressions.
     * This forms the base of the expression hierarchy, dealing directly with numbers or nested expressions.
     * @return Optional containing the Node representing the factor, if parsed successfully.
     */
    public Optional<Node> factor(){
        Optional<Token> number = handler.matchAndRemove(Token.TokenType.NUMBER);
        // If the token is a number, it is either an integer or a float.
        if(number.isPresent()){
            try {
                return Optional.of(new IntergerNode(stringToInt(number.get().getValue())));
            } catch (NumberFormatException e){
                return Optional.of(new FloatNode(stringToFloat(number.get().getValue())));
            }
        }
        if (handler.matchAndRemove(Token.TokenType.LEFT_PAREN).isPresent()){
            Optional<Node> expression = expression();
            if(expression.isPresent() && handler.matchAndRemove(Token.TokenType.RIGHT_PAREN).isPresent()){
                return expression;
            } else {
                throw new RuntimeException("Expected Right Parenthesis");
            }
        }
        return Optional.empty();
    }

    /**
     * Converts a string representation of an integer into its integer value.
     * @param value The string to convert.
     * @return The integer value of the string.
     */
    private int stringToInt(String value){
        return Integer.parseInt(value);
    }

    private float stringToFloat(String value){
        return Float.parseFloat(value);
    }

}

