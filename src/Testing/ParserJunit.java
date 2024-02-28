package Testing;

import Lexing.CodeHandler;
import Lexing.Lexer;
import Lexing.Token;
import Parsing.Nodes.IntergerNode;
import Parsing.Nodes.MathOpNode;
import Parsing.Nodes.ProgramNode;
import Parsing.Parser;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ParserJunit {




    @Test
    public void TestParser1() {
        String content = " 2.3 - 9 * (9+3+4) / 2 "  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[MathOpNode{left=FloatNode{value=2.3}, " +
                        "op=SUBTRACT, right=MathOpNode{left=MathOpNode{left=IntegerNode{value=9}, " +
                        "op=MULTIPLY, right=MathOpNode{left=MathOpNode{left=IntegerNode{value=9}, " +
                        "op=ADD, right=IntegerNode{value=3}}, " +
                        "op=ADD, right=IntegerNode{value=4}}}, " +
                        "op=DIVIDE, right=IntegerNode{value=2}}}]}"
                ,programNode.toString());


    }

    @Test
    public void TestParser2() {
        String content = " ( 29 + 9.4 ) / 2 "  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[MathOpNode{left=MathOpNode{left=IntegerNode{value=29}, " +
                        "op=ADD, right=FloatNode{value=9.4}}, " +
                        "op=DIVIDE, right=IntegerNode{value=2}}]}"
                ,programNode.toString());


    }



    @Test
    public void TestParser3() {
        String content = "10.78 * 3 + (99.9 - 67)"  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[MathOpNode{left=MathOpNode{left=FloatNode{value=10.78}, " +
                        "op=MULTIPLY, right=IntegerNode{value=3}}, " +
                        "op=ADD, right=MathOpNode{left=FloatNode{value=99.9}, " +
                        "op=SUBTRACT, right=IntegerNode{value=67}}}]}"
                ,programNode.toString());


    }


    @Test
    public void TestParser4() {
        String content = " 2.3 + 9 * (9+3+4) / 2 "  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[MathOpNode{left=FloatNode{value=2.3}, " +
                        "op=ADD, right=MathOpNode{left=MathOpNode{left=IntegerNode{value=9}, " +
                        "op=MULTIPLY, right=MathOpNode{left=MathOpNode{left=IntegerNode{value=9}, " +
                        "op=ADD, right=IntegerNode{value=3}}, " +
                        "op=ADD, right=IntegerNode{value=4}}}, op=DIVIDE, right=IntegerNode{value=2}}}]}"
                ,programNode.toString());


    }



    public LinkedList<Token> runLex(Path filePath,String testMessage){
        Boolean isFilPath = filePath != null;
        Lexer lexer = isFilPath ? new Lexer(filePath,1,0) : new Lexer(testMessage,1,0);
        lexer.lex("basic.basic");
        LinkedList<Token> tokens = lexer.lex("basic.basic");
        System.out.println("Contents: %s \n Tokens: %s ".formatted(filePath, tokens.toString()));
        System.out.println("DONE LEXING");
        return tokens;
    }



}
