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
     * This method sequentially processes expressions And Statements to build the program structure.
     * @return ProgramNode representing the root of the AST.
     */
    public ProgramNode parse(){
        LinkedList<Optional<Node>> nodes = new LinkedList<>();
        while (handler.moreTokens()){
            nodes.add(Optional.of(statements()));
            handler.acceptSeparator(); // skips EndLine Tokens
        }
        return new ProgramNode(nodes);
    }


    /**
     * Parses a list of statements, handling both assignments and print statements.
     * @return StatementsNode representing the parsed list of statements.
     */
    public StatementsNode statements(){
        StatementsNode statements = new StatementsNode();
        StatementNode statement = statement();
        while (statement != null){
            statements.addStatement(statement);
            handler.acceptSeparator();
            statement = statement();
        }
        return  statements;
    }

    /**
     * Parses a statement, handling both assignments and print statements.
     * @return StatementNode representing the parsed statement.Eiter an assignment or a print statement.
     */
    public StatementNode statement(){
        AssignmentNode assignment = assignment();
        if (assignment != null){
        return assignment;
        }
        if (handler.matchAndRemove(Token.TokenType.PRINT).isPresent()){
            return printList();
        }
       return null;
    }


    /**
     * Parses a print statement, handling the list of printable expressions.
     * @return PrintNode representing the print statement.
     */
    public PrintNode printList(){
        LinkedList<Node> nodes = new LinkedList<Node>();
        do {
            Node value = printAble();
            if (value != null){
                nodes.add(value);
            }
            else
                expression().ifPresent(nodes::add);
        }while (handler.matchAndRemove(Token.TokenType.COMMA).isPresent());

    return new PrintNode(nodes);
    }


    // this functions checks what tokens types are printable
    /**
     * Parses a printable expression, handling both string literals and variables.
     * @return Token representing the printable expression.
     */
    private Node printAble(){
        Optional<Token> word = handler.matchAndRemove(Token.TokenType.WORD);
        if(word.isPresent()){
            return new VariableNode(word.get().getValue());
        }
        return stringLiteral();
    }

    /**
     * Parses a string literal.
     * @return StringNode representing the string literal.
     */
    public StringNode stringLiteral(){
        return handler.matchAndRemove(Token.TokenType.StringLiteral)
                .map(token -> new StringNode(token.getValue()))
                .orElse(null);
    }


    /**
     * Parses an assignment statement, handling the assignment of a value to a variable.
     * @return AssignmentNode representing the parsed assignment statement.
     */
    public AssignmentNode assignment(){
        Optional<Token> assign = handler.matchAndRemove(Token.TokenType.WORD);
        if (assign.isPresent()){
            if(handler.matchAndRemove(Token.TokenType.EQUALS).isPresent()){
                return expression()
                        .map(node -> new AssignmentNode(new VariableNode(assign.get().getValue()), node))
                        .orElse(null);
            }
            throw new RuntimeException("Missing word");
        }
        return null;
    }

    /**
     * Parses expressions, handling binary operations such as addition and subtraction.
     * It builds upon the lower-level term() method to construct the expression hierarchy.
     * @return Optional containing the Node representing the expression, if parsed successfully.
     */
    public Optional<Node> expression() {
        Optional<Node> left = term();
        while (handler.matchAndRemove(Token.TokenType.PLUS).isPresent()){
            left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.ADD, term().get()));
        }
        while (handler.matchAndRemove(Token.TokenType.MINUS).isPresent()){
            left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.SUBTRACT, term().get()));
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
        while (handler.matchAndRemove(Token.TokenType.MULTIPLY).isPresent()) {
            left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.MULTIPLY, factor().get()));
        }
        while (handler.matchAndRemove(Token.TokenType.DIVIDE).isPresent()){
            left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.DIVIDE, factor().get()));
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
            return intOrFloat(number.get());
        }
        Optional<Token> word = handler.matchAndRemove(Token.TokenType.WORD);
        if (word.isPresent()){
            return Optional.of(new VariableNode(word.get().getValue()));
        }
        if (handler.matchAndRemove(Token.TokenType.LEFT_PAREN).isPresent()){
            Optional<Node> expression = expression();
            if(expression.isPresent() && handler.matchAndRemove(Token.TokenType.RIGHT_PAREN).isPresent()){
                return expression;
            } else
                throw new RuntimeException("Expected Right Parenthesis");
        }
        return Optional.empty();
    }

    /**
     * Converts a token representing a number into an integer or float node.
     * @param number The token to convert.
     * @return Optional containing the Node representing the number, if parsed successfully.
     */
    private Optional<Node> intOrFloat(Token number) {
        try {
            return Optional.of(new IntergerNode(Integer.parseInt(number.getValue())));
        } catch (NumberFormatException e){
            return Optional.of(new FloatNode(Float.parseFloat(number.getValue())));
        }
    }


}

