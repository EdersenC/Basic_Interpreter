package Lexing;
import java.util.*;
import java.util.HashMap;
public class Lexer {


    private CodeHandler handler;
    private int lineNumber;
    private int charPos;

    LinkedList<Token> token = new LinkedList<Token>();
    HashMap<String, Token.TokenType> tokenMap = new HashMap<String, Token.TokenType>();
    public Lexer(String StringHandler,int line, int pos) {
        handler = new CodeHandler(StringHandler, 0);
        this.lineNumber = line;
        this.charPos = pos;
        populateMap();

    }


    public void populateMap(){
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
    }

    /**
     * This method is used to process the input string and generate a list of tokens
     * @param fileName
     * @return token
     */
    public LinkedList<Token> lex(String fileName){

        while (!handler.isDone()) { // Continue processing until the input is exhausted
            char nextChar = handler.peek(0); // Peek at the next character

            if (nextChar == '\t' || nextChar == ' ') {
                // Ignore spaces and tabs
            } else if (nextChar == '\n') {
                lineNumber++; // Increment the line number for a new line character
                // Create a SEPARATOR token for a new line and add it to the token list
                charPos = 0;
                token.add(new Token(Token.TokenType.ENDOFLINE, lineNumber,charPos));

            } else if (nextChar == '\r') {
                // Ignore carriage return
            } else if (Character.isLetter(nextChar)) {
                // Process a word token
                processWord(lineNumber);
                //System.out.print(processWord(lineNumber) + "\n");
            } else if (Character.isDigit(nextChar)) {
                try {
                    // Process a number token
                    processNumber(lineNumber);
                    //System.out.print(processNumber(lineNumber) + "\n");
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
                    lineNumber++;}
            }
            else if (nextChar == '"') {
            // Handle string literals enclosed in double quotes
            //processSymbol();
            HandleStringLiteral();
        }

        // Move to the next character in the input string
            handler.swallow(1);
        }




    //System.out.println(token);
    return token;

    }





    /**
     * This method is used to process a word token
     * @param lineNumber
     */
    public void processWord(int lineNumber){
        int pos = 0;
        StringBuilder word = new StringBuilder();
        while (Character.isLetter(handler.peek(0))) {
            word.append(handler.getChar());
            pos++;
            Boolean dollarMod = handler.peek(0)=='$'|| handler.peek(0)=='%';
            Boolean carriageReturn = (handler.peek(1)=='\r'|| handler.peek(1)=='\n');
            if (dollarMod && (handler.peek(1)==' '||carriageReturn)){
                word.append(handler.getChar());
                pos++;
            }
        }
        charPos = charPos + pos;

        if (tokenMap.containsKey(word.toString())){
            token.add(new Token(tokenMap.get(word.toString()), lineNumber, charPos, word.toString()));
        } else {
            token.add(new Token(Token.TokenType.WORD, lineNumber, charPos, word.toString()));
        }
    }

    /**
     * This method is used to process a number token
     * @param lineNumber
     */
    public void processNumber(int lineNumber){
        int pos = 0;
        StringBuilder number = new StringBuilder();
        while (Character.isDigit(handler.peek(0))|| handler.peek(0) == '.'){
            number.append(handler.getChar());
            pos++;
        }
        charPos = charPos + pos;
        token.add(new Token(Token.TokenType.NUMBER, lineNumber, charPos, number.toString()));
    }


    public void HandleStringLiteral(){
        int pos = 0;
        StringBuilder string = new StringBuilder();
        string.append(handler.getChar());
        while (handler.peek(0) != '"'){

            if (handler.peek(0) == '\\'){

                for (int i = 0; handler.peek() != '"'; i++){

                }
                string.append(handler.getChar());
                pos++;
            }


            string.append(handler.getChar());
            pos++;
        }
        string.append(handler.getChar());
        charPos = charPos + pos;
        token.add(new Token(Token.TokenType.StringLiteral, lineNumber, charPos, string.toString()));
        handler.swallow(1);
    }







}
