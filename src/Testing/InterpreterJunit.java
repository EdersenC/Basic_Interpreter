package Testing;

import Interpreting.Interpreter;
import Program.Program;
import Lexing.Lexer;
import Lexing.Token;
import Parsing.Nodes.ProgramNode;
import Parsing.Parser;
import org.junit.Test;
import java.nio.file.Path;
import java.util.LinkedList;
import static org.junit.Assert.*;
public class InterpreterJunit {


    public LinkedList<Token> runLex(Path filePath,String testMessage){
        Boolean isFilPath = filePath != null;
        Lexer lexer = isFilPath ? new Lexer(filePath,1,0) : new Lexer(testMessage,1,0);
        lexer.lex("basic.basic");
        LinkedList<Token> tokens = lexer.lex("basic.basic");
        System.out.printf("Contents: %s \n Tokens: %s \n", filePath, tokens.toString());
        System.out.println("DONE LEXING");
        return tokens;
    }



    @Test
    public void dataNodeOptimization(){
        String content = "data 1,4.4," + " \"Hello World\" , 4.89\n" +
                "read a,b%,c$,d%";
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());
        Interpreter interpreter = new Interpreter(programNode);
        Program program = interpreter.interpret();
        System.out.println(program.toString());
        assertEquals(1, program.getVariableStorage().get(0).get("a"));
        assertEquals("Hello World", program.getVariableStorage().get(1).get("c$"));
        assertEquals((float) 4.89, program.getVariableStorage().get(2).get("d%"));
    }


    @Test
    public void LabelNodeOptimization(){
        String content = "Start: print 1,2 \n" +"dogs = train\n "+
                "data 1,4.4," + " \"Hello World\" , 4.89\n" +
                "read a,b%,c$,d% \n" +
                "Stop: print \"Done\"\n";
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());
        Interpreter interpreter = new Interpreter(programNode);
        Program program = interpreter.interpret();
        System.out.println(program.toString());
        assertEquals("Start", program.getLabels().get("Start").getName());
        assertEquals("Stop", program.getLabels().get("Stop").getName());
    }

    @Test
    public void defaultFunctions(){
        String content = "data 1,4.4," + " \"Hello World\" , 4.89\n" +
                "read a,b%,c$,d%\n" +
                "a = RANDOM()\n";
        LinkedList<Token> tokens = runLex(null, content);
        Parser parser = new Parser(tokens);
        ProgramNode programNode = parser.parse();
        System.out.println(programNode.toString());
        Interpreter interpreter = new Interpreter(programNode);
        Program program = interpreter.interpret();
        System.out.println("\n\n"+program.toString());

        assertEquals("waterm", Interpreter.left("watermelon", 6));
        assertEquals("melon", Interpreter.right("watermelon", 5));
        assertEquals("ban", Interpreter.mid("Albany", 2, 3));
        assertEquals("12",Interpreter.num(12));
        assertEquals("12.43",Interpreter.num((float) 12.43));
        assertEquals((float)12.98888,Interpreter.valFloat("12.98888"),0.0001);
        assertEquals(12,Interpreter.valInt("12"));

    }








































































}
