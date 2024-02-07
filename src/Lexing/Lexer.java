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
        tokenMap.put("Print", Token.TokenType.PRINT);
        tokenMap.put("Read", Token.TokenType.READ);
        tokenMap.put("Data", Token.TokenType.DATA);
        tokenMap.put("Gosub", Token.TokenType.GOSUB);
        tokenMap.put("For", Token.TokenType.FOR);
        tokenMap.put("To", Token.TokenType.TO);
        tokenMap.put("Step", Token.TokenType.STEP);
        tokenMap.put("Next", Token.TokenType.NEXT);
        tokenMap.put("Return", Token.TokenType.RETURN);
        tokenMap.put("If", Token.TokenType.IF);
        tokenMap.put("Then", Token.TokenType.THEN);
        tokenMap.put("Function", Token.TokenType.Function);
        tokenMap.put("While", Token.TokenType.While);
        tokenMap.put("End", Token.TokenType.END);
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









}
