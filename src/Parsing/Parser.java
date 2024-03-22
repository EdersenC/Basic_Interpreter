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
     * Parses a single statement, handling assignments, print statements, and other statement types.
     * @return StatementNode representing the parsed statement.
     */
    public StatementNode statement(){

        if (handler.matchAndRemove(Token.TokenType.GOSUB).isPresent()){
            return new GoSub(handler.matchAndRemove(Token.TokenType.WORD)
                    .map(Token::getValue)
                    .orElse(null));
        }
        Optional<Token> token = handler.matchAndRemove(Token.TokenType.RETURN);
        if (token.isPresent()){
            return new Return();
        }
        if (handler.matchAndRemove(Token.TokenType.IF).isPresent()){
            return IF();
        }
        if (handler.matchAndRemove(Token.TokenType.FOR).isPresent()){
            return For();
        }
        if (handler.matchAndRemove(Token.TokenType.While).isPresent()){
            return While();
        }
        if (handler.matchAndRemove(Token.TokenType.NEXT).isPresent()){
            return next();
         }
        if (handler.matchAndRemove(Token.TokenType.END).isPresent()){
            return new End();
        }


        LabelNode token1 = label();
        if (token1 != null) return token1;
        AssignmentNode assignment = assignment();
        if (assignment != null) return assignment;

        if (handler.matchAndRemove(Token.TokenType.PRINT).isPresent()){
            PrintNode printNode = new PrintNode();
            handleCommas(printNode);
            return printNode;
        }
        if (handler.matchAndRemove(Token.TokenType.READ).isPresent()){
            ReadNode readNode = new ReadNode();
            handleCommas(readNode);
            return readNode;
        }
        if (handler.matchAndRemove(Token.TokenType.DATA).isPresent()){
            DataNode dataNode = new DataNode();
            handleCommas(dataNode);
            return dataNode;
        }
        if (handler.matchAndRemove(Token.TokenType.INPUT).isPresent()){
            return inputList();
        }
       return null;
    }



    private WhileNode While(){
        BooleanExpressionNode expression = booleanExpression();
        Optional<Token> token = handler.matchAndRemove(Token.TokenType.WORD);
        if (token.isEmpty())
            throw new RuntimeException("Expected Label");
        StatementNode statement = statement();
        if (statement == null)
            throw new RuntimeException("Expected Statement");
        return new WhileNode(expression, statement, token.get().getValue());
    }


    /**
     * Parses a NEXT statement, handling the variable to be incremented.
     * @return NextNode representing the parsed NEXT statement.
     */
    private Next next(){
        Optional<Node> factor = factor();
        if (factor.isEmpty())
            throw new RuntimeException("Expected Factor");
        if (factor.get() instanceof VariableNode) return new Next((VariableNode) factor.get());
        throw new RuntimeException("Expected Variable");
    }


    /**
     * Parses a label statement, handling the label identifier and the statement to be executed.
     * @return LabelNode representing the parsed label statement.
     */
    private LabelNode label() {
        return handler.matchAndRemove(Token.TokenType.LABEL)
                .map(value -> new LabelNode(value.getValue(), statement()))
                .orElse(null);
    }


    /**
     * Parses an IF statement, handling the condition and the statement to be executed if the condition is true.
     * @return IFNode representing the parsed IF statement.
     */
    private IFNode IF(){
        BooleanExpressionNode expression = booleanExpression();
        if (handler.matchAndRemove(Token.TokenType.THEN).isEmpty())
            throw new RuntimeException("Expected THEN");
        Optional<Token> word = handler.matchAndRemove(Token.TokenType.WORD);
        if (word.isEmpty())
            throw new RuntimeException("Expected Label");
        return new IFNode(expression, word.get().getValue());
    }



    private ForNode For(){
        AssignmentNode assignment = assignment();
        if (assignment == null)
            throw new RuntimeException("Expected Expression");
        if (handler.matchAndRemove(Token.TokenType.TO).isEmpty())
            throw new RuntimeException("Expected TO");

        IntergerNode end = isIntergerNode();
        if (end == null)
            throw new RuntimeException("Expected Integer");

        if (handler.matchAndRemove(Token.TokenType.STEP).isEmpty())
            return new ForNode(assignment, end);

       IntergerNode step = isIntergerNode();
        if (step == null)
            throw new RuntimeException("Expected Integer");

        return new ForNode(assignment, end, step);

    }



    private IntergerNode isIntergerNode(){
        Optional<Node> interger =  intOrFloat();
        if (interger.get() instanceof IntergerNode)
            return (IntergerNode) interger.get();
        else
            return null;
    }






    /**
     * Parses an input statement, handling the list of input variables.
     * With the first parameter being a Variable or a StringNode
     * @return InputNode representing the input statement.
     */
    private InputNode inputList(){
        //TODO Make this better, dont like the null's!!
        VariableNode variable = variable();
        InputNode inputNode;
        if (variable != null){
            inputNode = new InputNode(variable);
        }
        else {
            StringNode constant = constant();
            if (constant == null)
                throw new RuntimeException("Expected a variable");
            inputNode = new InputNode(constant);
        }
        handleCommas(inputNode);
        return inputNode;
    }

    /**
     * Handles the parsing of comma-separated lists of expressions and variables.
     * This method is used to parse the arguments of print and input statements, as well as the data statement.
     * @param node The node to which the parsed expressions or variables will be added.
     */
    private void handleCommas(Node node){
        do {
            if (node instanceof PrintNode){
                ((PrintNode) node).addNode(variable());
                ((PrintNode) node).addNode(constant());
                expression().ifPresent(((PrintNode) node)::addNode);
            }
            if (node instanceof ReadNode){
                    ((ReadNode) node).addVariable(variable());
            }
            if (node instanceof InputNode){
                ((InputNode) node).addVariable(variable());
            }
            if (node instanceof DataNode){
                ((DataNode) node).addNode(constant());
                Optional<Node> number = intOrFloat();
                number.ifPresent(value -> ((DataNode) node).addNode(value));
            }
            if (node instanceof InputNode){
                ((InputNode) node).addVariable(variable());
            }
        }while (handler.matchAndRemove(Token.TokenType.COMMA).isPresent());
        if (handler.matchAndRemove(Token.TokenType.ENDOFLINE).isEmpty()){
            throw new RuntimeException("Invalid Syntax");
        }

    }


    /**
     * Parses a variable.
     * @return VariableNode representing the parsed variable.
     */
    private VariableNode variable(){
        return handler.matchAndRemove(Token.TokenType.WORD)
                .map(token -> new VariableNode(token.getValue()))
                .orElse(null);
    }

    /**
     * Parses a string literal.
     * @return StringNode representing the string literal.
     */
    public StringNode constant(){
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
     * Parses boolean expressions, handling comparison operations such as equals and less than.
     * @return Opt containing the Node representing the boolean expression, if parsed successfully.
     */
    private BooleanExpressionNode.BooleanOp booleanType(){
        if (handler.matchAndRemove(Token.TokenType.EQUALS).isPresent()){
            return BooleanExpressionNode.BooleanOp.EQUALS;
        }
        if (handler.matchAndRemove(Token.TokenType.GREATER_THAN).isPresent()){
            return BooleanExpressionNode.BooleanOp.GREATER_THAN;
        }
        if (handler.matchAndRemove(Token.TokenType.LESS_THAN).isPresent()){
            return BooleanExpressionNode.BooleanOp.LESS_THAN;
        }
        if (handler.matchAndRemove(Token.TokenType.GREATER_THAN_OR_EQUAL).isPresent()){
            return BooleanExpressionNode.BooleanOp.GREATER_THAN_OR_EQUAL;
        }
        if (handler.matchAndRemove(Token.TokenType.LESS_THAN_OR_EQUAL).isPresent()){
            return BooleanExpressionNode.BooleanOp.LESS_THAN_OR_EQUAL;
        }
        if (handler.matchAndRemove(Token.TokenType.NOT_EQUAL).isPresent()){
            return BooleanExpressionNode.BooleanOp.NOT_EQUALS;
        }
        return null;

    }

    /**
     * Parses boolean expressions, handling comparison operations such as equals and less than.
     * @return Opt containing the Node representing the boolean expression, if parsed successfully.
     */
    private BooleanExpressionNode booleanExpression(){
        Optional<Node> left = expression();
        BooleanExpressionNode.BooleanOp boolOp = booleanType();
        Optional<Node> right = expression();
        if (left.isEmpty()) throw new RuntimeException("Expected Expression");
        if (right.isEmpty()) throw new RuntimeException("Expected Expression");
        if (boolOp == null) throw new RuntimeException("Expected Boolean Operator");
        return new BooleanExpressionNode(left.get(), boolOp, right.get());
    }




    /**
     * Parses expressions, handling binary operations such as addition and subtraction.
     * It builds upon the lower-level term() method to construct the expression hierarchy.
     * @return Optional containing the Node representing the expression, if parsed successfully.
     */
    public Optional<Node> expression() {
        Optional<Node> left = term();
        boolean halt = false;
        while (!halt){
            if (handler.matchAndRemove(Token.TokenType.PLUS).isPresent()){
                left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.ADD, term().get()));
            }
            else if (handler.matchAndRemove(Token.TokenType.MINUS).isPresent()){
                left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.SUBTRACT, term().get()));
            }
            else
                halt = true;
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
        boolean halt = false;
        while (!halt){
            if (handler.matchAndRemove(Token.TokenType.MULTIPLY).isPresent()){
                left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.MULTIPLY, factor().get()));
            }
            else if (handler.matchAndRemove(Token.TokenType.DIVIDE).isPresent()){
                left = Optional.of(new MathOpNode(left.get(), MathOpNode.MathOp.DIVIDE, factor().get()));
            }
            else
                halt = true;
        }
        return left;
    }

    /**
     * Parses factors within terms, handling literals and parenthesized expressions.
     * This forms the base of the expression hierarchy, dealing directly with numbers or nested expressions.
     * @return Optional containing the Node representing the factor, if parsed successfully.
     */
    public Optional<Node> factor(){
        Optional<Node> number = intOrFloat();
        // If the token is a number, it is either an integer or a float.
        if(number.isPresent()){
            return number;
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
     * @return Optional containing the Node representing the number, if parsed successfully.
     */
    private Optional<Node> intOrFloat() {
        Optional<Token> number =  handler.matchAndRemove(Token.TokenType.NUMBER);
        if (number.isEmpty()){
            return Optional.empty();
        }
        try {
            return Optional.of(new IntergerNode(Integer.parseInt(number.get().getValue())));
        } catch (NumberFormatException e){
            return Optional.of(new FloatNode(Float.parseFloat(number.get().getValue())));
        }
    }


}

