package Testing;

import Lexing.Lexer;
import Lexing.Token;
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
        String content = " Test1 = water "  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());



        assertEquals("ProgramNode{nodes=[Optional[" +
                        "StatementsNode{statements=[" +
                        "AssignmentNode{variable=VariableNode{name=Test1, " +
                        "value=null}, value=VariableNode{name=water, value=null}}]}]]}"
                ,programNode.toString());


    }

    @Test
    public void TestParser2() {
        String content = " bob = ( 29 + 9.4 ) / 2 "  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{" +
                        "statements=[AssignmentNode{" +
                        "variable=VariableNode{name=bob, value=null}, " +
                        "value=MathOpNode{left=MathOpNode{left=IntegerNode{value=29}, " +
                        "op=ADD, right=FloatNode{value=9.4}}, op=DIVIDE, right=IntegerNode{value=2}}}]}]]}"
                ,programNode.toString());


    }



    @Test
    public void TestParser3() {
        String content = " phips = 10.78 * 3 + (99.9 - 67)"  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=[AssignmentNode{variable=VariableNode{name=phips, value=null}," +
                        " value=MathOpNode{left=MathOpNode{left=FloatNode{value=10.78}, " +
                        "op=MULTIPLY, right=IntegerNode{value=3}}, " +
                        "op=ADD, right=MathOpNode{left=FloatNode{value=99.9}, op=SUBTRACT, right=IntegerNode{value=67}}}}]}]]}"
                ,programNode.toString());


    }


    @Test
    public void TestParser4() {
        String content = " print 2.3 + 9 * (9+3+4) / 2 "  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=[PrintNode{nodes=[MathOpNode{left=FloatNode{value=2.3}, " +
                        "op=ADD, right=MathOpNode{left=MathOpNode{left=IntegerNode{value=9}, " +
                        "op=MULTIPLY, right=MathOpNode{left=MathOpNode{left=IntegerNode{value=9}, " +
                        "op=ADD, right=IntegerNode{value=3}}, op=ADD, right=IntegerNode{value=4}}}, " +
                        "op=DIVIDE, right=IntegerNode{value=2}}}]}]}]]}"
                ,programNode.toString());


    }

    @Test
    public void TestParserAssingment() {
        String content = " Soda = 2.3 + 9 * (9+3+4) / 2 "  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{" +
                        "statements=[AssignmentNode{variable=VariableNode{name=Soda, value=null}, " +
                        "value=MathOpNode{left=FloatNode{value=2.3}, " +
                        "op=ADD, right=MathOpNode{left=MathOpNode{left=IntegerNode{value=9}, " +
                        "op=MULTIPLY, " +
                        "right=MathOpNode{left=MathOpNode{left=IntegerNode{value=9}," +
                        " op=ADD, right=IntegerNode{value=3}}, " +
                        "op=ADD, right=IntegerNode{value=4}}}, " +
                        "op=DIVIDE, right=IntegerNode{value=2}}}}]}]]}"
                ,programNode.toString());


    }



    @Test
    public void TestParserPrint() {
        String content = " print 1+2, \"Hello World\", 3.4, 5.6+22, \"Hello Water\" "  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[" +
                        "StatementsNode{statements=[" +
                        "PrintNode{nodes=[" +
                        "MathOpNode{left=IntegerNode{value=1}, " +
                        "op=ADD, right=IntegerNode{value=2}}, " +
                        "StringNode{value=Hello World}, FloatNode{value=3.4}, " +
                        "MathOpNode{left=FloatNode{value=5.6}, " +
                        "op=ADD, right=IntegerNode{value=22}}, StringNode{value=Hello Water}]}]}]]}"
                ,programNode.toString());


    }



    @Test
    public void TestReadNode() {
        String content = " read soda, water, pizza"  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[" +
                        "StatementsNode{statements=[" +
                        "ReadNode{variables=[" +
                        "VariableNode{name=soda, value=null}, " +
                        "VariableNode{name=water, value=null}, " +
                        "VariableNode{name=pizza, value=null}]}]}]]}"
                ,programNode.toString());


    }

    @Test
    public void TestDataNode() {
        String content = "data  \"water\", 420, 6.9"  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=[" +
                        "DataNode{nodes=[StringNode{value=water}, " +
                        "IntegerNode{value=420}, FloatNode{value=6.9}]}]}]]}"
                ,programNode.toString());


    }


    @Test
    public void TestInputNode() {
        String content = "input  \"StringMexico\", Chicken, Phips";
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());


        //Test the input node With a string
        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=[" +
                        "InputNode{constant=StringNode{value=StringMexico}, " +
                        "variableList=[VariableNode{name=Chicken, value=null}, " +
                        "VariableNode{name=Phips, value=null}]}]}]]}"
                ,programNode.toString());

         content = "input  VariableMexico, Chicken, Phips, water";
         tokens = runLex(null, content);
         parser = new Parser(tokens);
         programNode = parser.parse();

         //Test the input node With a variable
        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=[" +
                        "InputNode{Variable=VariableNode{name=VariableMexico, value=null}, " +
                        "variableList=[VariableNode{name=Chicken, value=null}, " +
                        "VariableNode{name=Phips, value=null}, " +
                        "VariableNode{name=water, value=null}]}]}]]}"
                ,programNode.toString());



    }



    @Test
    public void TestLabel() {
        String content = "CallMe: print 1+2, \"Hello World\", 3.4, 5.6+22, \"Hello Water\" "  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=" +
                        "[LabelNode{label='CallMe', statement=PrintNode{nodes=[" +
                        "MathOpNode{left=IntegerNode{value=1}, " +
                        "op=ADD, right=IntegerNode{value=2}}, " +
                        "StringNode{value=Hello World}, FloatNode{value=3.4}, MathOpNode{left=FloatNode{value=5.6}, " +
                        "op=ADD, right=IntegerNode{value=22}}, StringNode{value=Hello Water}]}}]}]]}"
                ,programNode.toString());
    }



    @Test
    public void TestIF() {
        String content = "if pizza < 2+99-33 + (3*5.3) then water"  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=[" +
                        "IF{condition='BooleanExpressionNode{left=VariableNode{name=pizza," +
                        " value=null}, boolOp=LESS_THAN, " +
                        "right=MathOpNode{left=MathOpNode{left=MathOpNode{left=IntegerNode{value=2}," +
                        " op=ADD, right=IntegerNode{value=99}}, op=SUBTRACT, right=IntegerNode{value=33}}, " +
                        "op=ADD, right=MathOpNode{left=IntegerNode{value=3}," +
                        " op=MULTIPLY, right=FloatNode{value=5.3}}}}', thenStatement=water}]}]]}"
                ,programNode.toString());
    }


    @Test
    public void TestGoSub() {
        String content = "gosub CallMe"  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=[" +
                        "GoSub{identifier='CallMe'}]}]]}"
                ,programNode.toString());
    }


    @Test
    public void TestReturn() {
        String content = "x = 2.3 + 9 * (9+3+4) / 2 \n return"  ;
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        //MathOpNode mathOpNode = (MathOpNode) programNode.getNodes().get(0);

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=[" +
                        "AssignmentNode{variable=VariableNode{name=x, value=null}, " +
                        "value=MathOpNode{left=FloatNode{value=2.3}, " +
                        "op=ADD, right=MathOpNode{left=MathOpNode{left=IntegerNode{value=9}, " +
                        "op=MULTIPLY, right=MathOpNode{left=MathOpNode{left=IntegerNode{value=9}, op=ADD, right=IntegerNode{value=3}}, op=ADD, right=IntegerNode{value=4}}}," +
                        " op=DIVIDE, right=IntegerNode{value=2}}}}, Return{}]}]]}"
                ,programNode.toString());
    }



    @Test
    public void TestFor() {
        String content = "for i = 1 to 10 \n print i \n next i ";
        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = "basic.basic";
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{" +
                        "statements=[For{variable='" +
                        "AssignmentNode{variable=VariableNode{name=i, value=null}, " +
                        "value=IntegerNode{value=1}}', end='IntegerNode{value=10}', " +
                        "statements='StatementsNode{statements=[null, " +
                        "PrintNode{nodes=[VariableNode{name=i, value=null}]}]}', " +
                        "step='IntegerNode{value=1}', " +
                        "next='Next{variable='VariableNode{name=i, value=null}'}'}]}]]}"
                ,programNode.toString());

        content = "for i = 1 to 10 step 5 \n print i \n next i ";
        tokens = runLex(null, content);
        parser = new Parser(tokens);
        programNode = parser.parse();
        System.out.println(programNode.toString());
        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=[" +
                        "For{variable='AssignmentNode{variable=VariableNode{name=i, value=null}, " +
                        "value=IntegerNode{value=1}}', end='IntegerNode{value=10}', " +
                        "statements='StatementsNode{" +
                        "statements=[null, PrintNode{nodes=[VariableNode{name=i, value=null}]}]}', " +
                        "step='IntegerNode{value=5}', " +
                        "next='Next{variable='VariableNode{name=i, value=null}'}'}]}]]}"
                ,programNode.toString());

    }


    @Test
    public void TestEnd() {
        String content = "print 1,3,4,5,6,3,5.6 \n end";
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=[" +
                        "PrintNode{nodes=[IntegerNode{value=1}, IntegerNode{value=3}, " +
                        "IntegerNode{value=4}, IntegerNode{value=5}, IntegerNode{value=6}, " +
                        "IntegerNode{value=3}, FloatNode{value=5.6}]}, " +
                        "End{}]}]]}"
                ,programNode.toString());

    }


    @Test
    public void TestWhile() {
        String content = "while i < 10 killWater \n print i";
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{" +
                        "statements=[" +
                        "While{condition=" +
                        "BooleanExpressionNode{left=VariableNode{name=i, value=null}, " +
                        "boolOp=LESS_THAN, right=IntegerNode{value=10}}, " +
                        "statements=StatementsNode{statements=[" +
                        "PrintNode{nodes=[VariableNode{name=i, value=null}]}]}," +
                        " label='killWater'}]}]]}"
                ,programNode.toString());

    }


    @Test
    public void TestFunction() {
        String content = "function = Random() ";
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());

        assertEquals("ProgramNode{nodes=[Optional[" +
                        "StatementsNode{statements=[" +
                        "AssignmentNode{variable=VariableNode{name=function, value=null}, " +
                        "value=Function{name='Random', parameters=[]}}]}]]}"
                ,programNode.toString());




        content = "for i = 1 to 10 step 5 \n Bob = NUM(i) \n print 1+2,1+3,bob \n next i ";
        tokens = runLex(null, content);
        parser = new Parser(tokens);
        programNode = parser.parse();
        System.out.println(programNode.toString());
        assertEquals("ProgramNode{nodes=[Optional[StatementsNode{statements=" +
                        "[For{variable='AssignmentNode{variable=VariableNode{name=i, value=null}, " +
                        "value=IntegerNode{value=1}}', end='IntegerNode{value=10}', " +
                        "statements='StatementsNode{statements=[null, " +
                        "AssignmentNode{variable=VariableNode{name=Bob, value=null}, value=Function{name='NUM', " +
                        "parameters=[VariableNode{name=i, value=null}]}}, " +
                        "PrintNode{nodes=[MathOpNode{left=IntegerNode{value=1}, op=ADD, right=IntegerNode{value=2}}, " +
                        "MathOpNode{left=IntegerNode{value=1}, op=ADD, right=IntegerNode{value=3}}, " +
                        "VariableNode{name=bob, value=null}]}]}', step='IntegerNode{value=5}'," +
                        " next='Next{variable='VariableNode{name=i, value=null}'}'}]}]]}"
                ,programNode.toString());



    }







    public LinkedList<Token> runLex(Path filePath,String testMessage){
        Boolean isFilPath = filePath != null;
        Lexer lexer = isFilPath ? new Lexer(filePath,1,0) : new Lexer(testMessage,1,0);
        lexer.lex("basic.basic");
        LinkedList<Token> tokens = lexer.lex("basic.basic");
        System.out.printf("Contents: %s \n Tokens: %s \n", filePath, tokens.toString());
        System.out.println("DONE LEXING");
        return tokens;
    }



}
