package Lexing;
import java.nio.file.Path;
import java.util.*;
import java.util.HashMap;
public class Lexer {


    private CodeHandler handler;
    private int lineNumber;
    private int charPos;

    LinkedList<Token> token = new LinkedList<Token>();
    HashMap<String, Token.TokenType> tokenMap = new HashMap<String, Token.TokenType>();
    HashMap<String, Token.TokenType> oneCharacter = new HashMap<String, Token.TokenType>();
    HashMap<String, Token.TokenType> twoCharacter = new HashMap<String, Token.TokenType>();
    HashMap<String, Token.TokenType> operators = new  HashMap<String, Token.TokenType>();
    public Lexer(Path filePath, int line, int pos) {
        handler = new CodeHandler(filePath, 0);
        this.lineNumber = line;
        this.charPos = pos;
        populateMaps();

    }
    public Lexer(String filePath, int line, int pos) {
        handler = new CodeHandler(filePath, 0);
        this.lineNumber = line;
        this.charPos = pos;
        populateMaps();

    }


    public void populateMaps(){
        tokenMap.put("print", Token.TokenType.PRINT);
        tokenMap.put("read", Token.TokenType.READ);
        tokenMap.put("data", Token.TokenType.DATA);
        tokenMap.put("gosub", Token.TokenType.GOSUB);
        tokenMap.put("for", Token.TokenType.FOR);
        tokenMap.put("to", Token.TokenType.TO);
        tokenMap.put("step", Token.TokenType.STEP);
        tokenMap.put("next", Token.TokenType.NEXT);
        tokenMap.put("return", Token.TokenType.RETURN);
        tokenMap.put("if", Token.TokenType.IF);
        tokenMap.put("then", Token.TokenType.THEN);
        tokenMap.put("function", Token.TokenType.Function);
        tokenMap.put("while", Token.TokenType.While);
        tokenMap.put("end", Token.TokenType.END);
        tokenMap.put("input", Token.TokenType.INPUT);
        tokenMap.put(",", Token.TokenType.COMMA);




        oneCharacter.put("(", Token.TokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put(")", Token.TokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("<", Token.TokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put(">", Token.TokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("=", Token.TokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("+", Token.TokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("-", Token.TokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("*", Token.TokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("/", Token.TokenType.SINGLE_CHAR_SYMBOL);


        twoCharacter.put("<>", Token.TokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("<=", Token.TokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put(">=", Token.TokenType.TWO_CHAR_SYMBOL);


        operators.put("(", Token.TokenType.LEFT_PAREN);
        operators.put(")", Token.TokenType.RIGHT_PAREN);
        operators.put("<", Token.TokenType.LESS_THAN);
        operators.put("<=", Token.TokenType.LESS_THAN_OR_EQUAL);
        operators.put(">", Token.TokenType.GREATER_THAN);
        operators.put(">=", Token.TokenType.GREATER_THAN_OR_EQUAL);
        operators.put("=", Token.TokenType.EQUALS);
        operators.put("<>", Token.TokenType.NOT_EQUAL);
        operators.put("+", Token.TokenType.PLUS);
        operators.put("-", Token.TokenType.MINUS);
        operators.put("*", Token.TokenType.MULTIPLY);
        operators.put("/", Token.TokenType.DIVIDE);

    }



















    /**
     * This method is used to process the input string and generate a list of tokens
     * @param filePath
     * @return token
     */
    public LinkedList<Token> lex(String filePath){
        while (!handler.isDone()) { // Continue processing until the input is exhausted
            char nextChar = handler.peek(0); // Peek at the next character
            if (nextChar == '\t' || nextChar == ' '|| nextChar == '\u0000') {
                // Ignore spaces and tabs
                handler.swallow(1);
            } else if (nextChar == '\n') {
                token.add(new Token(Token.TokenType.ENDOFLINE, lineNumber,charPos));
                lineNumber++; // Increment the line number for a new line character
                // Create a SEPARATOR token for a new line and add it to the token list
                charPos = 0;
                handler.swallow(1);

            } else if (nextChar == '\r') {
                handler.swallow(1);
                // Ignore carriage return
            } else if (Character.isLetter(nextChar)) {
                // Process a word token
                processWord(lineNumber);
                //handler.swallow(1);
            } else if (Character.isDigit(nextChar)) {
                try {
                    // Process a number token
                    processNumber(lineNumber);
                    //handler.swallow(1);
                } catch (IllegalArgumentException ex) {
                    // Handle the exception or rethrow it if necessary
                    throw new IllegalArgumentException("Error processing number token at line " + handler.getIndex(), ex);
                }
            } else if (nextChar == '#') {
                // Handle comments starting with '#'
                while (handler.peek(0) != '\n') {
                    handler.swallow(1);
                }
                if(handler.peek(0) == '\n') {
                    lineNumber++;
                    //token.add(new Token(Token.TokenType.ENDOFLINE, lineNumber, charPos));
                    handler.swallow(1);
                }
            }
            else if (nextChar == '"'){
            // Handle string literals enclosed in double quotes
            //processSymbol();
            handleStringLiteral();
            handler.swallow(1);
        }
            else if (nextChar == ','){
                token.add(new Token(Token.TokenType.COMMA, ",", lineNumber, charPos));
                handler.swallow(1);
            }
            else


                // Process a symbol token
                processSymbol();

                //System.out.print(processSymbol() + "\n");
        // Move to the next character in the input string

        //handler.swallow(1);

        }
        token.add(new Token(Token.TokenType.ENDOFLINE, lineNumber,charPos));
    //System.out.println(token);
    return token;

    }





    /**
     * This method is used to process a word token
     * @param lineNumber
     */
    public void processWord(int lineNumber){
        int pos = 0;
        Boolean end = false;
        StringBuilder word = new StringBuilder();
        while ( handler.peek(0) != ' '
                && handler.peek(0) != '\r'
                && handler.peek(0) != '\n'
                && (Character.isLetterOrDigit(handler.peek(0)) || handler.peek(0) == '_'
                || handler.peek(0) == ':')
                || handler.peek(0) == '$' || handler.peek(0) == '%'){
            word.append(handler.getChar());
            pos++;
            Boolean isLabel = handler.peek(0)==':';
            Boolean endsWith = (handler.peek(0)=='$'|| handler.peek(0)=='%'
                    || isLabel) && (!Character.isLetterOrDigit(handler.peek(1)));
            Boolean lineFeed = (handler.peek(1)=='\r'|| handler.peek(1)=='\n');
            if (endsWith && (handler.peek(1)==' '||lineFeed)){ // if the next character is a space or a carriage return
                if (isLabel){
                    token.add(new Token(Token.TokenType.LABEL, word.toString(), lineNumber, charPos));
                    //handler.swallow(1);
                    charPos++;
                    end = true;
                }
                word.append(handler.getChar());
                pos++;
            }
        }


        charPos = charPos + pos;
        if (!end) {
            if (tokenMap.containsKey(word.toString())&& !word.toString().contentEquals("function")) {
                token.add(new Token(tokenMap.get(word.toString()), word.toString(), lineNumber, charPos));
            } else if (isfunction()) {
                token.add(new Token(Token.TokenType.Function, word.toString(), lineNumber, charPos));

            } else
                token.add(new Token(Token.TokenType.WORD, word.toString(), lineNumber, charPos));
        }
    }




    /**
     * This method is used to check if the token is a function
     * @return boolean
     */
    public boolean isfunction(){
        if (handler.peek(0)=='(') {
            int pos = 0;
            while (handler.peek(pos) != ')') {
                pos++;
            }
            return handler.peek(pos) == ')';
        }
        return false;
    }




    /**
     * This method is used to process a number token
     * @param lineNumber
     */
    public void processNumber(int lineNumber){
        int pos = 0;
        boolean toManyDecimals = false;
        StringBuilder number = new StringBuilder();
        while (Character.isDigit(handler.peek(0))|| handler.peek(0) == '.'){
            if (handler.peek(0) == '.'){
                if (toManyDecimals){
                    throw new IllegalArgumentException("Too many decimals at line %d".formatted(lineNumber));
                }
                toManyDecimals = true;
            }
            number.append(handler.getChar());
            pos++;
        }
        charPos = charPos + pos;
        token.add(new Token(Token.TokenType.NUMBER,number.toString(), lineNumber, charPos));
    }


    /**
     * This method is used to process a string literal token
     */
    public void handleStringLiteral(){
        //TODO This Method is very inefficient and slow
        int pos = 0;
        boolean escaped = false;
        StringBuilder string = new StringBuilder();
        //string.append(handler.getChar());
        handler.swallow(1);
        while (!escaped){
            if (handler.peek(0) == '\\'){
                handler.swallow(1); // swllow the escape character

                while (handler.peek(0) != '\\'){
                    string.append(handler.getChar());
                }
                handler.swallow(1);
                string.append(handler.getChar());
            }

            if (handler.peek(0) == '"'){
                escaped = true;
            }
            if (!escaped) {
                string.append(handler.getChar());
                pos++;
            }
        }
        charPos = charPos + pos;
        token.add(new Token(Token.TokenType.StringLiteral, string.toString(), lineNumber, charPos));

    }


    /**
     * This method is used to compare the maps and add the token to the list
     * @param map   the map to compare
     * @param token the token list
     * @param value the value to compare
     * @throws NoSuchElementException
     */
    public Boolean compareMaps(HashMap<String, Token.TokenType> map,
                            LinkedList<Token> token,
                            String value) throws NoSuchElementException{
        for(Map.Entry<String, Token.TokenType> entry : map.entrySet()){
            if (entry.getKey().contentEquals(value)){
                token.add(new Token(entry.getValue(),value, lineNumber, charPos));
                return true;
            }
        }
        //throw new NoSuchElementException("Invalid symbol at line %d".formatted(lineNumber));
        return false;
    }


    /**
     * This method is used to process a symbol token
     */
    public void processSymbol(){
        int pos = 0;
        StringBuilder symbol = new StringBuilder();

        if (handler.peek(0) == '(' ){
            token.add(new Token(Token.TokenType.LEFT_PAREN, "(", lineNumber, charPos));
            handler.swallow(1);
        } else if (handler.peek(0)==')') {
            token.add(new Token(Token.TokenType.RIGHT_PAREN, ")", lineNumber, charPos));
            handler.swallow(1);
        }

        while (handler.peek(0) != ' '
                && handler.peek(0) != '"'
                && handler.peek(0) != '\r'
                && handler.peek(0) != '\n'
                && handler.peek(0) != '\u0000'
                && !(Character.isLetterOrDigit(handler.peek(0)))
        )

        {
            symbol.append(handler.getChar());
            pos++;

        }



        charPos = charPos + pos;

//        if (!oneCharacter.containsKey(symbol.toString()) && !twoCharacter.containsKey(symbol.toString())){
//            throw new IllegalArgumentException("Invalid symbol at line %d".formatted(lineNumber));
//        }
        compareMaps(operators, token ,symbol.toString());
        //im
        //  handler.swallow(1);
    }



}
