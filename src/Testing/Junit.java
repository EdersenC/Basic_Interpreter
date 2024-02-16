package Testing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import Lexing.*;
import org.junit.Test;

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
            Lexer lexer = new Lexer(content,0,0);
            lexer.lex("basic.basic");
            LinkedList<Token> tokens = lexer.lex("basic.basic");
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
            Lexer lexer = new Lexer(content,0,0);
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
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        System.out.println(basicFilePath);


        try {
            //String content = new String(Files.readAllBytes(basicFilePath));'
            String content = "MyPizza: 3 <> 4   3 <= 3 <> 44 +  -  * / ( ) "  ;
            Lexer lexer = new Lexer(content,0,0);
            lexer.lex("basic.basic");
            LinkedList<Token> tokens = lexer.lex("basic.basic");
            System.out.println("Contents: %s \n Tokens: %s ".formatted(content, tokens.toString()));
            System.out.println("DONE LEXING");

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


        } catch (Exception e) {
            e.printStackTrace();
        }
        // Check the tokens produced by the lexer

    }




}
