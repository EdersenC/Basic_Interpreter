package Testing;

import Lexing.CodeHandler;
import Lexing.Lexer;
import Lexing.Token;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import static org.junit.Assert.*;


public class Junit {




    @Test
    public void testFizz() {
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        System.out.println(basicFilePath);


        try {
            //String content = new String(Files.readAllBytes(basicFilePath));
            String content = "FizzBuzz$\n 33 food@ \n soda is great";
            LinkedList<Token> tokens = runLex(null, content);
            System.out.println("DONE LEXING");



            assertEquals(Token.TokenType.WORD, tokens.get(0).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(1).getType());
            assertEquals(Token.TokenType.WORD, tokens.get(2).getType());
            assertEquals(Token.TokenType.ENDOFLINE, tokens.get(3).getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Check the tokens produced by the lexer

    }



    @Test
    public void test2() {
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        System.out.println(basicFilePath);


        try {
            //String content = new String(Files.readAllBytes(basicFilePath));
            String content = "\n 5 hello \n 5.32 8.5 4\n \t 44";
            Lexer lexer = new Lexer(content,0,0);
            lexer.lex("basic.basic");
            LinkedList<Token> tokens = lexer.lex("basic.basic");
            System.out.println("DONE LEXING");

            assertEquals(Token.TokenType.ENDOFLINE, tokens.get(0).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(1).getType());
            assertEquals(Token.TokenType.WORD, tokens.get(2).getType());
            assertEquals(Token.TokenType.ENDOFLINE, tokens.get(3).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(4).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(5).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(5).getType());


        } catch (Exception e) {
            e.printStackTrace();
        }
        // Check the tokens produced by the lexer

    }




    @Test
    public void testEnhancedLexer() {
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        System.out.println(basicFilePath);


        try {
            //String content = new String(Files.readAllBytes(basicFilePath));'
            String content = " myLabel: Fax = 9.4 \n story = \" \\\"She said Wonk Wonk\\\" \" water "  ;
            Lexer lexer = new Lexer(basicFilePath,0,0);
            lexer.lex("basic.basic");
            LinkedList<Token> tokens = lexer.lex("basic.basic");
            System.out.println("Contents: %s \n Tokens: %s ".formatted(content, tokens.toString()));
            System.out.println("DONE LEXING");

            assertEquals(Token.TokenType.LABEL, tokens.get(0).getType());
            assertEquals(Token.TokenType.WORD, tokens.get(1).getType());
            assertEquals(Token.TokenType.EQUALS, tokens.get(2).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(3).getType());
            assertEquals(Token.TokenType.ENDOFLINE, tokens.get(4).getType());
            assertEquals(Token.TokenType.WORD, tokens.get(5).getType());
            assertEquals(Token.TokenType.EQUALS, tokens.get(6).getType());
            assertEquals(Token.TokenType.StringLiteral, tokens.get(7).getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Check the tokens produced by the lexer

    }



    @Test
    public void testEnhancedLexer2() {
        String content = "MyPizza: 3 <> 4   3 <= 3 <> 44 +  -  * / ( ) "  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(basicFilePath,null);

            assertEquals(Token.TokenType.LABEL, tokens.get(0).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(1).getType());
            assertEquals(Token.TokenType.NOT_EQUAL, tokens.get(2).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(3).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(4).getType());
            assertEquals(Token.TokenType.LESS_THAN_OR_EQUAL, tokens.get(5).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(6).getType());
            assertEquals(Token.TokenType.NOT_EQUAL, tokens.get(7).getType());
            assertEquals(Token.TokenType.NUMBER, tokens.get(8).getType());
            assertEquals(Token.TokenType.PLUS, tokens.get(9).getType());
            assertEquals(Token.TokenType.MINUS, tokens.get(10).getType());
            assertEquals(Token.TokenType.MULTIPLY, tokens.get(11).getType());
            assertEquals(Token.TokenType.DIVIDE, tokens.get(12).getType());
            assertEquals(Token.TokenType.LEFT_PAREN, tokens.get(13).getType());
            assertEquals(Token.TokenType.RIGHT_PAREN, tokens.get(14).getType());
        // Check the tokens produced by the lexer

    }







public LinkedList<Token> runLex(Path filePath,String testMessage){
    Boolean isFilPath = filePath != null;
    Lexer lexer = isFilPath ? new Lexer(filePath,0,0) : new Lexer(testMessage,0,0);
    lexer.lex("basic.basic");
    LinkedList<Token> tokens = lexer.lex("basic.basic");
    System.out.println("Contents: %s \n Tokens: %s ".formatted(filePath, tokens.toString()));
    System.out.println("DONE LEXING");
    return tokens;
}










    @Test
    public void testCodeHandler() {
        String content = "The Blue Fox Jumped Over the Lazy Dog";
        char[] charArray = content.toCharArray();
        CodeHandler codeHandler = new CodeHandler(content, 0);

        System.out.println("Contents: %s \n Tokens: %s ".formatted(content, codeHandler.toString()));
        // Test peek function
        for (int i = 0; i < charArray.length; i++) {
            assertEquals("Peek at index " + i + " failed", charArray[i], codeHandler.peek(i));
        }

        // Test peekString function
        for (int i = 0; i < charArray.length; i++) {
            assertEquals("PeekString up to index " + i + " failed", content.substring(0, i + 1), codeHandler.peekString(i));
        }

        // Test getChar function
        for (char c : charArray) {
            assertEquals("getChar failed to return the correct character", c, codeHandler.getChar());
        }

        // Reset for next tests
        codeHandler = new CodeHandler(content, 0);

        // Test swallow function
        int swallowAmount = 4;
        codeHandler.swallow(swallowAmount);
        assertEquals("Swallow did not advance index correctly", swallowAmount, codeHandler.getIndex());
        assertEquals("Character after swallow incorrect", charArray[swallowAmount], codeHandler.getChar());

        // Test isDone function
        assertFalse(codeHandler.isDone());
        codeHandler.swallow(content.length() - swallowAmount - 1); // Move index to the end
        assertTrue("isDone should return true at end", codeHandler.isDone());

        // Reset for remainder test
        codeHandler = new CodeHandler(content, 0);
        int midIndex = content.length() / 2;
        codeHandler.swallow(midIndex); // Swallow half the string
        String expectedRemainder = content.substring(midIndex);
        assertEquals("Remainder did not match expected result", expectedRemainder, codeHandler.remainder());
    }





















}
