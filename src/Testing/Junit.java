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





}
